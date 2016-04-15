package com.shapematchandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shapematchandroid.R;

public class ContinueScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.continue_screen);

        final Button continueButton = (Button) findViewById(R.id.continueButton);
        final Button quitButton = (Button) findViewById(R.id.quit);
        final Button saveButton = (Button) findViewById(R.id.saveScore);
        final TextView score = (TextView) findViewById(R.id.score);

        score.setText("Score " + extractScoreFromIntentExtras());

        continueButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent countDownIntent = new Intent(v.getContext(), StartScreenActivity.class);
                        startActivity(countDownIntent);
                    }
                });

        quitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent countDownIntent = new Intent(v.getContext(), StartScreenActivity.class);
                        startActivity(countDownIntent);
                    }
                }
        );

        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent countDownIntent = new Intent(v.getContext(), ChartActivity.class);
                        startActivity(countDownIntent);
                    }
                }
        );
    }

    private String extractScoreFromIntentExtras() {
        Bundle extras = getIntent().getExtras();

        String value="";

        if (extras != null) {
            value = extras.getInt(GameScreenActivity.FINAL_SCORE)+"";
        }
        return value;
    }
}
