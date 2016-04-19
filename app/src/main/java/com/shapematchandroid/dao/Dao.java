package com.shapematchandroid.dao;


import java.util.List;

public interface Dao {

    DataDto read();

    void write(List<DataPoint> data);

}
