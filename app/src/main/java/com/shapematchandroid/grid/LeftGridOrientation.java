package com.shapematchandroid.grid;

import com.shapematchandroid.util.CellMargin;
import com.shapematchandroid.util.Dimension;

public class LeftGridOrientation extends Orientation {

    public LeftGridOrientation(CellMargin firstColumnMargin, Dimension oneCellDimension) {
        this.firstRowMargin = firstColumnMargin;
        this.oneCellDimension = oneCellDimension;
    }
}
