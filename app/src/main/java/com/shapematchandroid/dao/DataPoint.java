package com.shapematchandroid.dao;


import java.util.Date;

public class DataPoint {

    private final Date date;
    private final int score;

    public DataPoint(Date date, int score) {
        this.date = date;
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "data point with date: "+ date.toString() + " and score: " + score;
    }
}
