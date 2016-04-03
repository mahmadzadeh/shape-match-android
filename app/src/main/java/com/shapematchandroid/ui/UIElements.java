package com.shapematchandroid.ui;

import android.widget.Button;
import android.widget.TextView;

import com.shapematchandroid.grid.CellGridPair;

public class UIElements {

    private final GameButtons gameButtons;
    private final CountDownTextView countDownText;

    public UIElements(GameButtons gameButtons, CountDownTextView countDownText) {
        this.gameButtons = gameButtons;
        this.countDownText = countDownText;
    }

    public GameButtons gameButtons() {
        return gameButtons;
    }

    public Button matchButton() {
        return gameButtons.matchButton();
    }
    public Button mismatchButton() {
        return gameButtons.mismatchButton();
    }

    public CountDownTextView countDownText() {
        return countDownText;
    }

    public TextView countDownTextView() {
        return countDownText.textView();
    }


    public void setCountDownText(String countDownText) {
        this.countDownText.setText(countDownText);
    }
}
