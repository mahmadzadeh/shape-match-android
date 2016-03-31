package com.shapematchandroid.util;


public class CellMargin {

    private final double topMargin;
    private final double leftMargin;

    public CellMargin(double leftMargin, double topMargin) {
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public CellMargin(CellMargin another) {
        this.leftMargin = another.getLeftMargin();
        this.topMargin = another.getTopMargin();
    }

    public double getLeftMargin() {
        return leftMargin;
    }

    public double getTopMargin() {
        return topMargin;
    }


    public CellMargin addToTop(double height) {
        return new CellMargin(this.leftMargin, this.topMargin + height);
    }
}
