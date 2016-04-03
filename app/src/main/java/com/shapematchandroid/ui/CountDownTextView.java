package com.shapematchandroid.ui;


import android.content.Context;
import android.widget.TextView;

import com.shapematchandroid.R;

public class CountDownTextView {

    public static final int SCORE_BOARD_FONT = R.style.scoreBoardFont;
    private TextView textView;

    public CountDownTextView(Context context) {
        this.textView = new TextView(context);
        this.textView.setText("");
        this.textView.setTextAppearance(context, SCORE_BOARD_FONT);
    }

    public TextView textView() {
        return textView;
    }

    public void setText(String text) {
        this.textView.setText(text);
    }
}
