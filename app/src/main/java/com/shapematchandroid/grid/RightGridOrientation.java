package com.shapematchandroid.grid;

import com.shapematchandroid.util.CellMargin;
import com.shapematchandroid.util.Dimension;

public class RightGridOrientation extends Orientation {

    public RightGridOrientation(CellMargin firstRowMargin, Dimension oneCellDimension) {
        this.firstRowMargin = firstRowMargin;
        this.singleCellDimension = oneCellDimension;
    }
}
