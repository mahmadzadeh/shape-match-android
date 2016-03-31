package com.shapematchandroid.util;

public class Dimension {

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private final double width;
    private final double height;

    public Dimension(double width, double height) {
        if(width < 0 || height < 0 ) {
            throw new RuntimeException("Window with negative dimensions does not make sense. " +
                    "Given width: " + width + " and height: " + height );
        }

        this.width = width;
        this.height = height;
    }

    public Dimension increaseByFactor(double widthFactor, double heightFactor) {
        return new Dimension(this.width*widthFactor, this.height*heightFactor);
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
