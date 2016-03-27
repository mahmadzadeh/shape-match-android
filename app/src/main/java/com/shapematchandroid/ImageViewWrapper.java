package com.shapematchandroid;

import android.widget.ImageView;

/**
 * Wrapper around ImageView.
 * This so that unit tests are easier to write
 */
public class ImageViewWrapper {

    private final ImageView imageView;
    private final int id;
    private int shapeResourceId;


    public ImageViewWrapper(ImageView imageView, int id, int shapeResourceId) {
        this.imageView = imageView;
        this.id = id;
        this.shapeResourceId = shapeResourceId;
    }

    public ImageView getImageViewWithAttributesAdded() {
        imageView.setImageResource(shapeResourceId);
        imageView.setId(id);

        return imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getId() {
        return id;
    }

    public int getShapeResourceId() {
        return shapeResourceId;
    }

    public void setShapeResourceId(int shapeResourceId) {
        this.shapeResourceId = shapeResourceId;
    }
}
