package com.shapematchandroid;

import android.os.Handler;
import android.os.Message;

import com.shapematchandroid.ui.CountDownScreenActivity;

import java.util.Arrays;
import java.util.List;


public class CountdownImageSwapHandler extends Handler {
    private int currentImageIndex;
    private CountDownScreenActivity activity;

    private List<Integer> imageResourceIds =
            Arrays.asList(R.drawable.android3, R.drawable.android2, R.drawable.android1, R.drawable.go);

    private int countDownImageCount = imageResourceIds.size();

    public CountdownImageSwapHandler(CountDownScreenActivity activity) {
        super();
        this.activity = activity;
        this.currentImageIndex = 0;
    }

    public void handleMessage(Message m) {

        if (hasMoreImagesToSwap()) {
            activity.swapImage(imageResourceIds.get(currentImageIndex));
        }
        currentImageIndex += 1;
    }

    public boolean hasMoreImagesToSwap() {
        return currentImageIndex < countDownImageCount;
    }

    public int getCountOfImagesToBeSwapped() {
        return countDownImageCount;
    }
}
