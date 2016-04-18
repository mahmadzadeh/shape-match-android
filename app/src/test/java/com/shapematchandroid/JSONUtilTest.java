package com.shapematchandroid;


import com.shapematchandroid.dao.DataPoint;
import com.shapematchandroid.io.FileIO;
import com.shapematchandroid.io.FileIOException;

import org.json.JSONException;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONUtilTest {

    @Test
    public void givenJSONStringNoDataThenParsReturnsEmptyListOfDataPoints() throws FileIOException, JSONException {

        String JSON = new FileIO(new File("src/test/resources/emptySampleFile.json")).read();

        List<DataPoint> dataPointList = JSONUtil.parse(JSON);

        assertTrue(dataPointList.isEmpty());
    }

    @Test
    public void givenJSONStringWithOneDataPointThenParsReturnsListOfOneDataPoints() throws FileIOException, JSONException {

        String JSON = new FileIO(new File("src/test/resources/oneDataPointSampleFile.json")).read();

        List<DataPoint> dataPointList = JSONUtil.parse(JSON);

        assertEquals(1, dataPointList.size());
    }


}
