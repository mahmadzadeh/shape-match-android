package com.shapematchandroid.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.shapematchandroid.R;
import com.shapematchandroid.dao.ChartData;
import com.shapematchandroid.dao.Dao;
import com.shapematchandroid.dao.DataDto;
import com.shapematchandroid.dao.DataDtoConversion;
import com.shapematchandroid.dao.DataPoint;
import com.shapematchandroid.dao.FileBasedDao;
import com.shapematchandroid.io.FileIO;
import com.shapematchandroid.util.DateUtil;
import com.shapematchandroid.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class ChartActivity extends AppCompatActivity {

    Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = FileUtil.getDataFile(this);

        dao = new FileBasedDao( new FileIO(file) );

        setContentView(R.layout.chart_screen);

        LineChart lineChart = (LineChart) findViewById(R.id.line_chart);

        DataPoint lastDataPoint = extractDatePointFromExtras();

        final DataDto allDataSoFar = dao.read().addDataPoint(lastDataPoint);
        setData( lineChart, allDataSoFar );

        Button continueButton = (Button) findViewById(R.id.char_continue);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.write(allDataSoFar);
                Intent countDownIntent = new Intent(v.getContext(), StartScreenActivity.class);
                startActivity(countDownIntent);
            }
        });
    }

    private void setData(LineChart mChart, DataDto dto ) {
        ChartData chartData = DataDtoConversion.convertToChartData(dto);

        LineDataSet dataSet = new LineDataSet(chartData.getyVals(), "Scores");
        dataSet.enableDashedLine(10f, 5f, 0f);
        dataSet.enableDashedHighlightLine(10f, 5f, 0f);
        dataSet.setColor(Color.YELLOW);
        dataSet.setCircleColor(Color.YELLOW);
        dataSet.setLineWidth(1f);
        dataSet.setCircleRadius(3f);
        dataSet.setDrawCircleHole(false);
        dataSet.setValueTextSize(9f);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.GREEN);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet);

        LineData data = new LineData(chartData.getxVals(), dataSets);

        mChart.setData(data);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDescription("Score Data");
    }

    private DataPoint extractDatePointFromExtras() {
        Bundle extras = getIntent().getExtras();

        int score = 0;
        Date date = new Date();

        if (extras != null) {
            score = Integer.valueOf(extras.getString(GameScreenActivity.FINAL_SCORE));
            date  = DateUtil.parse(extras.getString(ContinueScreenActivity.DATE));
        }
        return new DataPoint(date,score);
    }


}
