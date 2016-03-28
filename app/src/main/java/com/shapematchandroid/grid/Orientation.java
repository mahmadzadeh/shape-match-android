package com.shapematchandroid.grid;


import com.shapematchandroid.util.CellMargin;
import com.shapematchandroid.util.Dimension;

public abstract class Orientation {

    protected CellMargin firstRowMargin;
    protected Dimension singleCellDimension;

    public Dimension getSingleCellDimension() {
        return singleCellDimension;
    }

    public CellMargin getFirstRowMargin() {
        return firstRowMargin;
    }

    public CellMargin getSubsequentRowMargin(int row) {
        return new CellMargin(
                this.firstRowMargin.getLeftMargin(),
                this.firstRowMargin.getTopMargin() + ( row * singleCellDimension.getHeight()));
    }

}
