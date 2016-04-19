package com.shapematchandroid.dao;

import com.shapematchandroid.io.FileIO;
import com.shapematchandroid.io.FileIOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileBasedDaoTest {

    @Mock
    FileIO mockFileIO;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void canCreateAnInstance() {

        Dao dao = new FileBasedDao(mockFileIO);
    }

    @Test
    public void givenFileReadFailsThenReadReturnsEmptyListOfDataPoints() throws FileIOException {

        when(mockFileIO.read()).thenThrow(new FileIOException("badass mexican exception"));

        Dao dao = new FileBasedDao(mockFileIO);

        assertThat( dao.read().size(), is(equalTo(0)));

        verify(mockFileIO).read();
    }

    @Test
    public void givenUnparsableJSONThenReadReturnsEmptyListOfDataPoints() throws FileIOException {

        when(mockFileIO.read()).thenReturn("some f'ed up JSON...}");

        Dao dao = new FileBasedDao(mockFileIO);

        assertThat( dao.read().size(), is(equalTo(0)));

        verify(mockFileIO).read();
    }

    @Test
    public void givenValidInputThenReadReturnsEmptyListOfContainedInFile() throws FileIOException {

        String JSON = "{\"data\": [ { \"datapoint\" : { \"date\": \"2012-04-23T18:25:43.511Z\", \"score\":  2} } ] }";

        when(mockFileIO.read()).thenReturn(JSON);

        Dao dao = new FileBasedDao(mockFileIO);

        DataDto dataPoints = dao.read();

        verify(mockFileIO).read();

        assertThat(dataPoints.size(), is(equalTo(1)));

        DataPoint dataPoint  = dataPoints.userDataPoints().get(0);

        assertThat(dataPoint.score(), is(equalTo(2)));
    }

    @Ignore
    public void givenNothingStoredInScoreFileThenReadReturnsEmptyListOfDataPoints() {

        Dao dao = new FileBasedDao(mockFileIO);

        assertThat( dao.read(), not(nullValue()));
    }
}