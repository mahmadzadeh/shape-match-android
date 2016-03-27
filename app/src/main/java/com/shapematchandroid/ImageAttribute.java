package com.shapematchandroid;

import android.widget.ImageView;
import android.widget.RelativeLayout;


public class ImageAttribute {

    private final ImageViewWrapper imageView;
    private RelativeLayout.LayoutParams params;

    public ImageAttribute(RelativeLayout.LayoutParams params, ImageViewWrapper imageView) {

        this.params = params;
        this.imageView = imageView;
    }

    public RelativeLayout.LayoutParams getParams() {
        return params;
    }

    public ImageViewWrapper getImageView() {
        return imageView;
    }

    public String toString() {
        return "Image with ID: " + imageView.getId() + " " + " Param margin top : " + params.topMargin + " and Margin left " + params.leftMargin;
    }

    private String translateRule(int[] rules) {
        StringBuffer buffer = new StringBuffer();

        for (int rule : rules) {
            switch (rule) {
                case RelativeLayout.RIGHT_OF:
                    buffer.append("To the right of ");
                case RelativeLayout.BELOW:
                    buffer.append("Below ");
                default:
                    buffer.append("other rule ");
            }
        }

        return buffer.toString();
    }
}
