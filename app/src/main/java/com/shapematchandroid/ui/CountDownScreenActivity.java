package com.shapematchandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.shapematchandroid.CountdownImageSwapHandler;
import com.shapematchandroid.R;
import com.shapematchandroid.util.CountDownTimerUtil;

import java.util.TimerTask;

public class CountDownScreenActivity extends AppCompatActivity {
    ImageView countDownImage;

    private final Long TIMER_INTERVAL = 100l;
    private final Long TIMER_DELAY = 1000l;

    CountdownImageSwapHandler handler = new CountdownImageSwapHandler(this);

    TimerTask timerTask = new TimerTask() {
        public void run() {
            if (handler.hasMoreImagesToSwap()) {
                handler.obtainMessage(1).sendToTarget();
            } else {
                Intent countDownIntent = new Intent(CountDownScreenActivity.this, GameScreenActivity.class);
                startActivity(countDownIntent);
            }
        }
    };
    CountDownTimerUtil countDownTimer = new CountDownTimerUtil(timerTask, TIMER_INTERVAL, TIMER_DELAY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.countdown_screen);

        countDownImage = (ImageView) findViewById(R.id.imageView);

        countDownTimer.start();
    }

    public void swapImage(int resourceId) {
        countDownImage.setImageResource(resourceId);
    }

    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        countDownTimer.pause();
    }
}
