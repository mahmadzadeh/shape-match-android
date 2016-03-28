package com.shapematchandroid.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.shapematchandroid.GameLevel;
import com.shapematchandroid.GameLogic;
import com.shapematchandroid.R;
import com.shapematchandroid.Score;
import com.shapematchandroid.UserInput;
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

        handler = new Handler() {
            public void handleMessage(Message m) {
                new CellGridDisplay(
                        gameLogic.cellGridPair(),
                        gameButtons,
                        rl,GameScreenActivity.this )
                        .displayOnScreen(gameLogic.currentPoints());
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
                rl, this).displayOnScreen(gameLogic.currentPoints());
    }

    private void handleMismatch() {
        gameLogic = gameLogic.evaluateUserInput(UserInput.formValue("mismatch"));
        new CellGridDisplay(
                gameLogic.cellGridPair(),
                gameButtons,
                rl, this).displayOnScreen(gameLogic.currentPoints());
    }

    private void updateUI() {
        long TIMER_DELAY = 100;
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                handler.obtainMessage(1).sendToTarget();
            }
        }, TIMER_DELAY);
    }

}
