package com.shapematchandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.shapematchandroid.grid.CellGridDisplay;
import com.shapematchandroid.grid.CellGridUtil;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreenActivity extends AppCompatActivity {

    private RelativeLayout rl;
    private GameButtons gameButtons;

    private Handler handler;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_screen);

        rl = (RelativeLayout) findViewById(R.id.contentId);
        gameButtons = new GameButtons(this);
        gameLogic   = new GameLogic(
                GameLevel.initialLevel,
                CellGridUtil.getShapesForLevel(GameLevel.initialLevel),
                0,
                Score.initialScore,
                false);

        new CellGridDisplay(
                CellGridUtil.getShapesForLevel(new GameLevel(2)),
                gameButtons,
                rl, this)
                .displayOnScreen();

        handler = new Handler() {
            public void handleMessage(Message m) {
                new CellGridDisplay(
                        gameLogic.cellGridPair(),
                        gameButtons,
                        rl,GameScreenActivity.this ) .displayOnScreen();
            }
        };
        gameButtons.getMatchButton().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleMatch();
            }
        });

        gameButtons.getMismatchButton().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleMismatch();
            }
        });

        updateUI();

    }

    private void handleMatch() {
        gameLogic = gameLogic.evaluateUserInput(UserInput.formValue("match"));
        new CellGridDisplay(
                gameLogic.cellGridPair(),
                gameButtons,
                rl, this).displayOnScreen();
    }

    private void handleMismatch() {
        gameLogic = gameLogic.evaluateUserInput(UserInput.formValue("mismatch"));
        new CellGridDisplay(
                gameLogic.cellGridPair(),
                gameButtons,
                rl, this).displayOnScreen();
    }


    private void updateUI() {
        long TIMER_DELAY = 10;
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                handler.obtainMessage(1).sendToTarget();
            }
        }, TIMER_DELAY);
    }

}
