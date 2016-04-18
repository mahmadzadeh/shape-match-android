package com.shapematchandroid;


import android.provider.ContactsContract;

import com.shapematchandroid.dao.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONUtil {

    public static List<DataPoint> parse(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray array  = jsonObject.optJSONArray("data");

        List<DataPoint> dataPoints = new ArrayList<>();

        for(int i = 0; i < array.length(); i++ ){
            JSONObject oneRow = (JSONObject) array.get(i);
            JSONObject object = oneRow.getJSONObject("datapoint");

            Date date = new Date(object.getString("date"));
            int score = object.getInt("score");
            dataPoints.add(new DataPoint(date, score));
        }

        return dataPoints;
    }
}
