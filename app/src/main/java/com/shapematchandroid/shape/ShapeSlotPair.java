package com.shapematchandroid.shape;

public class ShapeSlotPair {

    private final Shape shape;
    private final Integer slot;

    public ShapeSlotPair(Shape shape, Integer slot ) {
        this.shape = shape;
        this.slot = slot;
    }

    public Shape getShape() {
        return shape;
    }

    public Integer getSlot() {
        return slot;
    }

    @Override
    public boolean equals(Object other) {
        if ( this == other ) return true;
        if ( !(other instanceof ShapeSlotPair) ) return false;

        ShapeSlotPair that = (ShapeSlotPair)other;

        return shape.equals(that.shape)
                && this.slot ==that.slot;
    }
}
