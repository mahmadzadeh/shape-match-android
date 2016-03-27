package com.shapematchandroid;

public class GridIndex {

    private final int row;
    private final int column;

    public GridIndex(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public GridIndex oneToLeft() {
        return new GridIndex(this.row, this.column-1);
    }

    public GridIndex oneAbove() {
        return new GridIndex(row-1, this.column);
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
