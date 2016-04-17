package com.shapematchandroid.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDataDto {

    List<DataPoint> userDataPoints = new ArrayList<DataPoint>();

    public void addAll(Collection<DataPoint> dataPoints) {
        this.userDataPoints.addAll(dataPoints);
    }

    public void add(DataPoint dataPoint) {
        this.userDataPoints.add(dataPoint);
    }

    public List<DataPoint> getUserDataPoints() {
        return userDataPoints;
    }
}
