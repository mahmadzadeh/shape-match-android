package com.shapematchandroid.dao;

import org.json.JSONException;

import java.util.Date;

import static com.shapematchandroid.util.DtoJSONConversion.dataPointToJSON;

public class DataPoint {

    private final Date date;
    private final int score;

    public DataPoint(Date date, int score) {
        this.date = date;
        this.score = score;
    }

    public Date date() {
        return date;
    }

    public int score() {
        return score;
    }

    @Override
    public String toString() {
        return "data point with date: " + date.toString() + " and score: " + score;
    }

    public String toJSON() {
        try {
            return dataPointToJSON(this).toString();
        } catch (JSONException e) {
            return "";
        }
    }
}
