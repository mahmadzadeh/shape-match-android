package com.shapematchandroid.ui;

import android.content.Context;
import android.widget.Button;

import com.shapematchandroid.R;

public class GameButtons {

    private  final Button match;
    private  final Button mismatch;
    private  final Button quit;

    public GameButtons(Button matchButton, Button mismatchButton, Button quit) {
        this.match = matchButton;
        this.mismatch = mismatchButton;
        this.quit = quit;
    }

    public GameButtons(Context context){

        match = new Button(context);
        match.setTextColor(context.getResources().getColor(R.color.matchMismatchButtonTextColor));
        match.setText(R.string.match_button_string);
        match.setBackground(context.getResources().getDrawable(R.drawable.roundbutton_green));

        mismatch = new Button(context);
        mismatch.setTextColor(context.getResources().getColor(R.color.matchMismatchButtonTextColor));
        mismatch.setText(R.string.mismatch_button_string);
        mismatch.setBackground(context.getResources().getDrawable(R.drawable.roundbutton_red));

        quit = new Button(context);
        quit.setTextColor(context.getResources().getColor(R.color.quitButtonTextColor));
        quit.setText(R.string.quit_button_string);
        quit.setBackground(context.getResources().getDrawable(R.drawable.roundbutton_yellow));
    }

    public Button matchButton() {
        return match;
    }

    public Button mismatchButton() {
        return mismatch;
    }

    public Button quitButton() {
        return quit;
    }


}
