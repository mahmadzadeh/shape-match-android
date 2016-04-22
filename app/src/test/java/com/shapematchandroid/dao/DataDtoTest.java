package com.shapematchandroid.dao;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.shapematchandroid.util.JSONUtil.parse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataDtoTest {

    @Test
    public void testToJSONWithEmptyDataPointList() throws JSONException {
        List<DataPoint> dataPointList = new ArrayList<>();

        String json = new DataDto(dataPointList).toJSON();

        assertThat(parse(json).size(), is(equalTo(0)));
    }

    @Test
    public void testToJSONWithDataPointList() throws JSONException {
        List<DataPoint> dataPointList = Arrays.asList(new DataPoint(new Date(), 2));

        String json = new DataDto(dataPointList).toJSON();

        assertThat(parse(json).size(), is(equalTo(1)));
    }
}