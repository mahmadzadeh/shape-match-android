package com.shapematchandroid.dao;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
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

    public DataDto addDataPoint(DataPoint dataPoint) {
        List<DataPoint> copy = new ArrayList<>(userDataPoints);
        copy.add(dataPoint);

        return new DataDto(copy);
    }

    public String toJSON() {
        try {
           return dataDtoToJSON(this).toString();
        } catch (JSONException e) {
            return ""; // not the end of the world if this can't be represented as JSON
        }
    }

    public List<DataPoint> sortedDataPoints() {
        List<DataPoint> sortedCopy = new ArrayList<>(userDataPoints);

        Collections.sort(sortedCopy);

        return sortedCopy;
    }
}
