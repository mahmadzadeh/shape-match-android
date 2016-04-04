package com.shapematchandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.shapematchandroid.GameLevel;
import com.shapematchandroid.GameLogic;
import com.shapematchandroid.R;
import com.shapematchandroid.Score;
import com.shapematchandroid.util.SoundPlayer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.shapematchandroid.UserInput.Match;
import static com.shapematchandroid.UserInput.Mismatch;
import static com.shapematchandroid.grid.CellGridUtil.getShapesForLevel;

public class GameScreenActivity extends AppCompatActivity {

    public final int ONE_ROUND_IN_MILLIS = 5000;
    public final int COUNT_DOWN_INTERVAL_IN_MILLIS = 1000;

    private final int INITIAL_CORRECT_ANSWERS = 0;
    private RelativeLayout rl;
    private UIElements uiElements;

    private Handler handler;
    private GameLogic gameLogic;
    private GameCountDownTimer timer;
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_screen);

        rl = (RelativeLayout) findViewById(R.id.contentId);

        uiElements = new UIElements(new GameButtons(this), new CountDownTextView(this), new ScoreTextView(this));

        gameLogic   = new GameLogic(
                GameLevel.initialLevel,
                getShapesForLevel(GameLevel.initialLevel),
                INITIAL_CORRECT_ANSWERS,
                Score.initialScore,
                false);

        handler = new Handler() {
            public void handleMessage(Message m) {
                new CellGridDisplay(
                        gameLogic.cellGridPair(),
                        rl,
                        GameScreenActivity.this,
                        uiElements)
                        .displayOnScreen(gameLogic.currentPoints());

                timer = new GameCountDownTimer(ONE_ROUND_IN_MILLIS, COUNT_DOWN_INTERVAL_IN_MILLIS);
                timer.start();
            }
        };

        soundPlayer = new SoundPlayer(this);

        uiElements.matchButton().setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                handleMatch();
            }
        });

        uiElements.mismatchButton().setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                handleMismatch();
            }
        });

        updateUI();

    }

    private void handleMatch() {
        soundPlayer.soundFeedbackForUserInput(Match, gameLogic);
        gameLogic = gameLogic.evaluateUserInput(Match);

        new CellGridDisplay(
                gameLogic.cellGridPair(),
                rl,
                this,
                uiElements)
                .displayOnScreen(gameLogic.currentPoints());
    }

    private void handleMismatch() {
        soundPlayer.soundFeedbackForUserInput(Mismatch, gameLogic);
        gameLogic = gameLogic.evaluateUserInput(Mismatch);

        new CellGridDisplay(
                gameLogic.cellGridPair(),
                rl,
                this,
                uiElements).displayOnScreen(gameLogic.currentPoints());
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

            uiElements.setCountDownText(text);
        }

        @Override
        public void onFinish() {
            uiElements.setCountDownText("00:00");

            Intent countDownIntent = new Intent(GameScreenActivity.this, ContinueScreenActivity.class);
            startActivity(countDownIntent);
        }
    }
}
