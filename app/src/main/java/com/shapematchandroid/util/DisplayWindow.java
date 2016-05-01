package com.shapematchandroid.util;

public class DisplayWindow {
    public static final double LEFT_GUTTER_WIDTH_PERCENTAGE = 0.02;
    public static final double LEFT_GUTTER_HEIGHT_PERCENTAGE = 0.15;

    public static final double LEFT_BUTTON_WIDTH_PERCENTAGE = 0.026;
    public static final double LEFT_BUTTON_HEIGHT_PERCENTAGE = 0.65;

    public static final double LEFT_GRID_WIDTH_PERCENTAGE = 0.35;
    public static final double LEFT_GRID_HEIGHT_PERCENTAGE =0.15;

    public static final double RIGHT_GRID_WIDTH_PERCENTAGE = 0.61;
    public static final double RIGHT_GRID_HEIGHT_PERCENTAGE =0.15;

    public static final double RIGHT_BUTTON_WIDTH_PERCENTAGE = 0.527;
    public static final double RIGHT_BUTTON_HEIGHT_PERCENTAGE = 0.65;

    public static final double SINGLE_DISPLAY_CELL_WIDTH_PERCENTAGE = 0.053;
    public static final double SINGLE_DISPLAY_CELL_HEIGHT_PERCENTAGE = 0.067;

    public static final double BUTTON_WIDTH_PERCENTAGE = 0.395;
    public static final double BUTTON_HEIGHT_PERCENTAGE = 0.30;

    public static final double GAP_BETWEEN_GRIDS_PERCENTAGE = 0.22;
    public static final double FUDGE_FACTOR = 0.006;

    private final Dimension screenDimension;

    public DisplayWindow(Dimension screenDimension) {
        this.screenDimension = screenDimension;
    }


    public CellMargin topLeftCornerOfLeftGrid() {
        return new CellMargin(LEFT_GUTTER_WIDTH_PERCENTAGE * screenDimension.getWidth(), LEFT_GUTTER_HEIGHT_PERCENTAGE * screenDimension.getHeight());

    }

    public CellMargin topLeftCornerOfScore() {
        return new CellMargin(
                ( LEFT_GUTTER_WIDTH_PERCENTAGE +
                        LEFT_GRID_WIDTH_PERCENTAGE +
                        ( ( GAP_BETWEEN_GRIDS_PERCENTAGE / 2 ) - (SINGLE_DISPLAY_CELL_WIDTH_PERCENTAGE + FUDGE_FACTOR))) * screenDimension.getWidth(),
                LEFT_GUTTER_HEIGHT_PERCENTAGE * screenDimension.getHeight());

    }

    public CellMargin topLeftCornerOfCountDownTimer() {
        return  topLeftCornerOfScore() .addToTop(oneCellDimension().getHeight());
    }

    public CellMargin topLeftCornerOfQuitButton() {
        return  topLeftCornerOfCountDownTimer().addToTop(oneCellDimension().getHeight() * 3);
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
        return new Dimension( screenDimension.getWidth() * SINGLE_DISPLAY_CELL_WIDTH_PERCENTAGE, screenDimension.getHeight() * SINGLE_DISPLAY_CELL_HEIGHT_PERCENTAGE);
    }

    public Dimension singleButtonDimension() {
        return new Dimension( screenDimension.getWidth() * BUTTON_WIDTH_PERCENTAGE, screenDimension.getHeight() * BUTTON_HEIGHT_PERCENTAGE);
    }

    public Dimension scoreBoardDimension() {
        return oneCellDimension();
    }

    @Override
    public String toString() {
        return "DisplayWindow{" +
                "screenDimension=" + screenDimension +
                '}';
    }
}
