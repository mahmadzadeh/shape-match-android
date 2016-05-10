package com.shapematchandroid.util;

import org.junit.Test;

import java.text.DecimalFormat;

import static junit.framework.TestCase.assertEquals;


public class DisplayWindowTest {

    double height = 1800;
    double width = 1000;

    Dimension windowDimension = new Dimension(width, height);

    @Test
    public void givenDisplayWindowThenGetPositionForTopLeftCellInLeftCellGrid() {

        DisplayWindow window = new DisplayWindow(windowDimension);

        CellMargin margin = window.topLeftCornerOfLeftGrid();

        assertEquals(20.0, margin.getLeftMargin());
        assertEquals(180.0, margin.getTopMargin());
    }

    @Test
    public void givenDisplayWindowThenGetPositionForTopLeftCellInRightCellGrid() {

        DisplayWindow window = new DisplayWindow(windowDimension);

        CellMargin margin = window.topLeftCornerOfRightGrid();

        assertEquals(610.0, margin.getLeftMargin());
        assertEquals(180.0, margin.getTopMargin());
    }

    @Test
    public void givenDisplayWindowThenGetPositionForTopLeftCornerOfLeftButton() {

        DisplayWindow window = new DisplayWindow(windowDimension);

        CellMargin margin = window.topLeftCornerOfLeftButton();

        assertEquals(26.0, margin.getLeftMargin());
        assertEquals(1170.0, margin.getTopMargin());
    }

    @Test
    public void givenDisplayWindowThenGetPositionForTopRightCornerOfLeftButton() {
        DisplayWindow window = new DisplayWindow(windowDimension);

        CellMargin margin = window.topLeftCornerOfRightButton();

        assertEquals(527.0, margin.getLeftMargin());
        assertEquals(1170.0, margin.getTopMargin());
    }

    @Test
    public void givenDisplayWindowThenGetDimensionOfIndividualCell() {
        DisplayWindow window = new DisplayWindow(windowDimension);

        Dimension dimension = window.oneCellDimension();

        assertEquals(53.0, dimension.getWidth());
        assertEquals("120.60", new DecimalFormat("#0.00").format(dimension.getHeight()));
    }

}