package com.shapematchandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.shapematchandroid.grid.CellGridDisplay;
import com.shapematchandroid.grid.CellGridUtil;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreenActivity extends AppCompatActivity {


    RelativeLayout rl;
    Handler handler = new Handler() {
        public void handleMessage(Message m) {
            new CellGridDisplay(CellGridUtil.getShapesForLevel(new GameLevel(6)), rl,GameScreenActivity.this )
                    .displayOnScreen();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_screen);

        rl = (RelativeLayout) findViewById(R.id.contentId);

        new CellGridDisplay(CellGridUtil.getShapesForLevel(new GameLevel(6)), rl, this)
                .displayOnScreen();

        updatePriodically();

    }


    private void updatePriodically() {
        long TIMER_INTERVAL = 100;
        long TIMER_DELAY = 10000;
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                handler.obtainMessage(1).sendToTarget();
            }
        }, TIMER_INTERVAL, TIMER_DELAY);
    }

}
