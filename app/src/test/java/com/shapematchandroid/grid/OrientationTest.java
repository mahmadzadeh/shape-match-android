package com.shapematchandroid.grid;

import com.shapematchandroid.util.CellMargin;
import com.shapematchandroid.util.Dimension;

import org.junit.Test;

import static org.junit.Assert.*;


public class OrientationTest {

    @Test
    public void getSubsequentColumnMarginReturnsCorrectCellMarginForRow() {
        Dimension cellDimension = new Dimension(10, 10);
        CellMargin firstRowMargin = new CellMargin(10, 20);

        Orientation orientation  = new LeftGridOrientation(firstRowMargin, cellDimension);

        CellMargin subsequentRowMargin = orientation.getSubsequentRowMargin(0);
        assertEquals(10.0, subsequentRowMargin.getLeftMargin(), 0.01);
        assertEquals(20.0, subsequentRowMargin.getTopMargin(), 0.01);

        subsequentRowMargin = orientation.getSubsequentRowMargin(1);
        assertEquals(10.0, subsequentRowMargin.getLeftMargin(), 0.01);
        assertEquals(30.0, subsequentRowMargin.getTopMargin(), 0.01);

        subsequentRowMargin = orientation.getSubsequentRowMargin(2);
        assertEquals(10.0, subsequentRowMargin.getLeftMargin(), 0.01);
        assertEquals(40.0, subsequentRowMargin.getTopMargin(), 0.01);
    }

}