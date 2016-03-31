package com.shapematchandroid.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shapematchandroid.GameLevel;
import com.shapematchandroid.GameLogic;
import com.shapematchandroid.R;
import com.shapematchandroid.Score;
import com.shapematchandroid.UserInput;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.shapematchandroid.grid.CellGridUtil.getShapesForLevel;

public class GameScreenActivity extends AppCompatActivity {

    private RelativeLayout rl;
    private GameButtons gameButtons;
    private TextView countDownText;

    private Handler handler;
    private GameLogic gameLogic;
    private GameCountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_screen);

        rl = (RelativeLayout) findViewById(R.id.contentId);
        gameButtons = new GameButtons(this);
        countDownText = new TextView(this);
        countDownText.setText("");
        countDownText.setTextAppearance(this, R.style.scoreBoardFont);

        gameLogic   = new GameLogic(
                GameLevel.initialLevel,
                getShapesForLevel(GameLevel.initialLevel),
                0,
                Score.initialScore,
                false);

        handler = new Handler() {
            public void handleMessage(Message m) {
                new CellGridDisplay(
                        gameLogic.cellGridPair(),
                        gameButtons,
                        rl, GameScreenActivity.this, countDownText)
                        .displayOnScreen(gameLogic.currentPoints());

                timer = new GameCountDownTimer(90000, 1000);
                timer.start();
            }
        };

        gameButtons.getMatchButton().setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                handleMatch();
            }
        });

        gameButtons.getMismatchButton().setOnClickListener(new OnClickListener() {
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
                rl, this, countDownText).displayOnScreen(gameLogic.currentPoints());
    }

    private void handleMismatch() {
        gameLogic = gameLogic.evaluateUserInput(UserInput.formValue("mismatch"));
        new CellGridDisplay(
                gameLogic.cellGridPair(),
                gameButtons,
                rl, this, countDownText).displayOnScreen(gameLogic.currentPoints());
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

    public class GameCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public GameCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String text = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                    );

            countDownText.setText(text);
        }

        @Override
        public void onFinish() {

        }
    }
}
