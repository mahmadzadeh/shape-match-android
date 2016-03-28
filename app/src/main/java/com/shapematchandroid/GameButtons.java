package com.shapematchandroid;

import android.content.Context;
import android.widget.Button;

public class GameButtons {

    private  final Button matchButton;
    private  final Button mismatchButton;

    public GameButtons(Button matchButton, Button mismatchButton) {
        this.matchButton = matchButton;
        this.mismatchButton = mismatchButton;
    }

    public GameButtons(Context context){
        matchButton = new Button(context);
        matchButton.setText(R.string.match_button_string);
        matchButton.setBackground(context.getResources().getDrawable(R.drawable.roundbutton_green));
        mismatchButton  = new Button(context);
        mismatchButton.setText(R.string.mismatch_button_string);
        mismatchButton.setBackgroundColor(context.getResources().getColor(R.color.mismatchButtonBackground));
        mismatchButton.setBackground(context.getResources().getDrawable(R.drawable.roundbutton_red));
    }

    public Button getMatchButton() {
        return matchButton;
    }

    public Button getMismatchButton() {
        return mismatchButton;
    }


}
