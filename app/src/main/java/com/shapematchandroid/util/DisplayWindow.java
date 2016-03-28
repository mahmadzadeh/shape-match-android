package com.shapematchandroid.util;

public class DisplayWindow {
    public static final double LEFT_GUTTER_WIDTH_PERCENTAGE = 0.02;
    public static final double LEFT_GUTTER_HEIGHT_PERCENTAGE = 0.15;

    public static final double LEFT_BUTTON_WIDTH_PERCENTAGE = 0.20;
    public static final double LEFT_BUTTON_HEIGHT_PERCENTAGE = 0.65;

    public static final double RIGHT_GRID_WIDTH_PERCENTAGE = 0.61;
    public static final double RIGHT_GRID_HEIGHT_PERCENTAGE =0.15;

    public static final double RIGHT_BUTTON_WIDTH_PERCENTAGE = 0.53;
    public static final double RIGHT_BUTTON_HEIGHT_PERCENTAGE = 0.65;

    private final Dimension screenDimension;

    public DisplayWindow(Dimension screenDimension) {
        this.screenDimension = screenDimension;
    }

    public Dimension getScreenDimension() {
        return screenDimension;
    }


    public CellMargin topLeftCornerOfLeftGrid() {
        return new CellMargin(LEFT_GUTTER_WIDTH_PERCENTAGE * screenDimension.getWidth(), LEFT_GUTTER_HEIGHT_PERCENTAGE * screenDimension.getHeight());

    }

    public CellMargin topLeftCornerOfRightGrid() {
        return new CellMargin(RIGHT_GRID_WIDTH_PERCENTAGE * screenDimension.getWidth(), RIGHT_GRID_HEIGHT_PERCENTAGE * screenDimension.getHeight());
    }

    public CellMargin topLeftCornerOfLeftButton() {
        return new CellMargin(LEFT_BUTTON_WIDTH_PERCENTAGE * screenDimension.getWidth(), LEFT_BUTTON_HEIGHT_PERCENTAGE * screenDimension.getHeight());
    }

    public CellMargin topLeftCornerOfRightButton() {
        return new CellMargin(RIGHT_BUTTON_WIDTH_PERCENTAGE * screenDimension.getWidth(), RIGHT_BUTTON_HEIGHT_PERCENTAGE * screenDimension.getHeight());
    }

    public Dimension oneCellDimension() {
        return new Dimension( screenDimension.getWidth() * 0.053, screenDimension.getHeight() * 0.067);
    }

    public Dimension singleButtonDimension() {
        return new Dimension( screenDimension.getWidth() * 0.22, screenDimension.getHeight() * 0.20);
    }

    @Override
    public String toString() {
        return "DisplayWindow{" +
                "screenDimension=" + screenDimension +
                '}';
    }
}
