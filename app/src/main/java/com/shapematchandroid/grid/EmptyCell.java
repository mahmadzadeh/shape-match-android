package com.shapematchandroid.grid;


import com.shapematchandroid.shape.NullShape;

public class EmptyCell extends Cell {

    public EmptyCell() {
        super(new NullShape());
    }
}
