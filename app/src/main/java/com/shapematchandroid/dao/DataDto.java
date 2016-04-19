package com.shapematchandroid.dao;

import org.json.JSONException;

import java.util.List;

import static com.shapematchandroid.util.DtoJSONConversion.dataDtoToJSON;

public class DataDto {

    List<DataPoint> userDataPoints;

    public DataDto(List<DataPoint> userDataPoints) {
        this.userDataPoints = userDataPoints;
    }

    public List<DataPoint> userDataPoints() {
        return userDataPoints;
    }

    public int size() { return userDataPoints.size(); }

    public String toJSON() {
        try {
           return dataDtoToJSON(this).toString();
        } catch (JSONException e) {
            return ""; // not the end of the world if this can't be represented as JSON
        }
    }
}
