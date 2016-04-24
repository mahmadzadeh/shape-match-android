package com.shapematchandroid.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.shapematchandroid.R;
import com.shapematchandroid.dao.Dao;
import com.shapematchandroid.dao.DataDto;
import com.shapematchandroid.dao.DataPoint;
import com.shapematchandroid.dao.FileBasedDao;
import com.shapematchandroid.io.FileIO;
import com.shapematchandroid.util.DateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class ChartActivity extends AppCompatActivity {

    Dao dao = new FileBasedDao(
            new FileIO(new File(ChartActivity.this.getFilesDir(), "data.json")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chart_screen);

        LineChart lineChart = (LineChart) findViewById(R.id.line_chart);

        DataPoint lastDataPoint = extractDatePointFromExtras();

        final DataDto allDataSoFar = dao.read().addDataPoint(lastDataPoint);
        // setData( allDataSoFar )
        setData(2, 100, lineChart);

        Button continueButton = (Button) findViewById(R.id.char_continue);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the last datapoint to file and move on
                // dao.write(allDataSoFar);
                Intent countDownIntent = new Intent(v.getContext(), StartScreenActivity.class);
                startActivity(countDownIntent);
            }
        });
    }

    private void setData(int count, float range, LineChart mChart) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 3;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals.add(new Entry(val, i));
        }

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "Scores");

        // set1.setFillAlpha(110);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.YELLOW);
        set1.setCircleColor(Color.YELLOW);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        set1.setFillColor(Color.GREEN);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDescription("Score Data");

    }

    private DataPoint extractDatePointFromExtras() {
        Bundle extras = getIntent().getExtras();

        int score = 0;
        Date date = new Date();

        if (extras != null) {
            score = extras.getInt(GameScreenActivity.FINAL_SCORE);
            date = DateUtil.parse(extras.getString(ContinueScreenActivity.DATE));
        }
        return new DataPoint(date,score);
    }


}
