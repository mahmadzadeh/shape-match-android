package com.shapematchandroid.util;


import android.content.Context;
import android.media.MediaPlayer;

import com.shapematchandroid.GameLogic;
import com.shapematchandroid.R;
import com.shapematchandroid.UserInput;

public class SoundPlayer {

    private final MediaPlayer buzzerMediaPlayer;
    private final MediaPlayer dingMediaPlayer;

    public SoundPlayer(MediaPlayer buzzerMediaPlayer, MediaPlayer dingMediaPlayer) {
        this.buzzerMediaPlayer = buzzerMediaPlayer;
        this.dingMediaPlayer = dingMediaPlayer;
    }

    public SoundPlayer(Context context) {
        this.buzzerMediaPlayer = MediaPlayer.create(context, R.raw.fail);
        this.dingMediaPlayer = MediaPlayer.create(context, R.raw.ding);
    }

    public void soundFeedbackForUserInput(UserInput userInput, GameLogic gameLogic) {
        if(gameLogic.isCorrectAnswer(userInput)) {
            dingMediaPlayer.start();
        } else {
            buzzerMediaPlayer.start();
        }
    }
}
