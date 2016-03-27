package com.shapematchandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownScreenActivity extends AppCompatActivity {
    ImageView countDownImage;

    CountdownImageSwapHandler handler = new CountdownImageSwapHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.countdown_screen);

        countDownImage = (ImageView) findViewById(R.id.imageView);

        startCountdownTimer();
    }

    public void startCountdownTimer() {
        long TIMER_INTERVAL = 100;
        long TIMER_DELAY = 1000;
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (handler.hasMoreImagesToSwap()) {
                    handler.obtainMessage(1).sendToTarget();
                } else {
                    timer.cancel();
                    timer.purge();
                    Intent countDownIntent = new Intent(CountDownScreenActivity.this, GameScreenActivity.class);
                    startActivity(countDownIntent);
                }
            }
        }, TIMER_INTERVAL, TIMER_DELAY);
    }

    public void swapImage(int resourceId) {
        countDownImage.setImageResource(resourceId);
    }

}
