package com.shapematchandroid.ui;

import android.content.Context;
import android.widget.Button;

import com.shapematchandroid.R;

public class GameButtons {

    private  final Button matchButton;
    private  final Button mismatchButton;

    public GameButtons(Button matchButton, Button mismatchButton) {
        this.matchButton = matchButton;
        this.mismatchButton = mismatchButton;
    }

    public GameButtons(Context context){

        matchButton = new Button(context);
        matchButton.setTextColor(context.getResources().getColor(R.color.matchMismatchButtonTextColor));
        matchButton.setText(R.string.match_button_string);
        matchButton.setBackground(context.getResources().getDrawable(R.drawable.roundbutton_green));

        mismatchButton  = new Button(context);
        mismatchButton.setTextColor(context.getResources().getColor(R.color.matchMismatchButtonTextColor));
        mismatchButton.setText(R.string.mismatch_button_string);
        mismatchButton.setBackground(context.getResources().getDrawable(R.drawable.roundbutton_red));
    }

    public Button getMatchButton() {
        return matchButton;
    }

    public Button getMismatchButton() {
        return mismatchButton;
    }


}
