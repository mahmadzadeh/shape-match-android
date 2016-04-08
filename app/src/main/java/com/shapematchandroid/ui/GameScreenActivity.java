package com.shapematchandroid.ui;

import android.os.Bundle;
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

import static com.shapematchandroid.UserInput.Match;
import static com.shapematchandroid.UserInput.Mismatch;
import static com.shapematchandroid.grid.CellGridUtil.getShapesForLevel;

public class GameScreenActivity extends AppCompatActivity {

    public static final String FINAL_SCORE = "FINAL_SCORE";
    public final int ONE_ROUND_IN_MILLIS = 90000;
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

        timer = new GameCountDownTimer(this, ONE_ROUND_IN_MILLIS, COUNT_DOWN_INTERVAL_IN_MILLIS);

        handler = new Handler() {

            public void handleMessage(Message m) {
                gameLogic   = new GameLogic(
                        GameLevel.initialLevel,
                        getShapesForLevel(GameLevel.initialLevel),
                        INITIAL_CORRECT_ANSWERS,
                        Score.initialScore,
                        false);

                new CellGridDisplay(
                        gameLogic.cellGridPair(),
                        rl,
                        GameScreenActivity.this,
                        uiElements)
                        .displayOnScreen(gameLogic.currentPoints());

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

    public int currentPoints() {
        return  gameLogic.currentPoints();
    }

    public void setCountDownText(String text) {
        uiElements.setCountDownText(text);
    }

}
