package com.shapematchandroid.ui;

import android.widget.Button;
import android.widget.TextView;

public class UIElements {

    private final GameButtons gameButtons;
    private final CountDownTextView countDownText;
    private final ScoreTextView scoreTextView;

    public UIElements(GameButtons gameButtons, CountDownTextView countDownText, ScoreTextView scoreTextView ) {
        this.gameButtons = gameButtons;
        this.countDownText = countDownText;
        this.scoreTextView = scoreTextView;
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

    public TextView scoreTextView(){
        return scoreTextView.textView();
    }

    public void setScore(int score) {
        scoreTextView.setScore(score);
    }
}
