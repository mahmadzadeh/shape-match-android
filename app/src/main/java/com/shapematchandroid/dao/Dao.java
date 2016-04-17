package com.shapematchandroid.dao;


import java.util.List;

public interface Dao {

    List<DataPoint> read();

    void write(List<DataPoint> data);

}
