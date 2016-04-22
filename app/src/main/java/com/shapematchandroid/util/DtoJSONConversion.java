package com.shapematchandroid.util;


import com.shapematchandroid.dao.DataDto;
import com.shapematchandroid.dao.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.shapematchandroid.util.JSONUtil.DATAPOINT_ELEMENT;
import static com.shapematchandroid.util.JSONUtil.DATA_ELEMENT;
import static com.shapematchandroid.util.JSONUtil.DATE_ELEMENT;
import static com.shapematchandroid.util.JSONUtil.SCORE_ELEMENT;

public class DtoJSONConversion {

    public static JSONObject dataPointToJSON(DataPoint dataPoint) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        JSONObject innerObject = new JSONObject();

        innerObject.put(DATE_ELEMENT, DateUtil.format(dataPoint.date()));
        innerObject.put(SCORE_ELEMENT, dataPoint.score());

        jsonObject.put(DATAPOINT_ELEMENT, innerObject);

        return jsonObject;
    }

    public static JSONObject dataDtoToJSON(DataDto dto) throws JSONException {

        JSONObject rootObject = new JSONObject();
        JSONArray array = new JSONArray();

        for (DataPoint dataPoint : dto.userDataPoints()) {
            array.put(dataPointToJSON(dataPoint));
        }

        rootObject.put(DATA_ELEMENT, array);

        return rootObject;
    }

}
