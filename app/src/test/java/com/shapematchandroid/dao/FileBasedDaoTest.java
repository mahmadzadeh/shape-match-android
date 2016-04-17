package com.shapematchandroid.dao;

import com.shapematchandroid.io.FileIO;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
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

    @Ignore
    public void givenNothingStoredInScoreFileThenReadReturnsUserDataWithNothingInIt() {

        Dao dao = new FileBasedDao(mockFileIO);

        assertThat( dao.read(), not(nullValue()));
    }
}