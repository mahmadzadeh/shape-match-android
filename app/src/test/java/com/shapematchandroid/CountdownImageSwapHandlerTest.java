package com.shapematchandroid;

import android.os.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.MockitoAnnotations.initMocks;


public class CountdownImageSwapHandlerTest {

    @Mock
    private CountDownScreenActivity mockActivity;

    @Mock
    private Message mockMessage;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void givenAnInstanceThenCanCallHandleMessage() {

        doNothing().when(mockActivity).swapImage(1);

        new CountdownImageSwapHandler(mockActivity).handleMessage(mockMessage);
    }

    @Test
    public void multipleCallsToHandleMessageSetImagesInDecsendingOrder() {
        setUpMockSwapImageExpectation();

        CountdownImageSwapHandler handler = new CountdownImageSwapHandler(mockActivity);

        for (int i = 0; i < handler.getCountOfImagesToBeSwapped(); i++) {
            handler.handleMessage(mockMessage);
        }

        verifyMockSwapImageExpectation();
    }

    @Test
    public void whenNoImagesToSwapThenHasMoreImagesToSwapReturnsFalse() {

        setUpMockSwapImageExpectation();

        CountdownImageSwapHandler handler = new CountdownImageSwapHandler(mockActivity);

        for (int i = 0; i < handler.getCountOfImagesToBeSwapped(); i++) {
            handler.handleMessage(mockMessage);
        }

        verifyMockSwapImageExpectation();

        assertFalse(handler.hasMoreImagesToSwap());
    }


    private void setUpMockSwapImageExpectation() {
        doNothing().when(mockActivity).swapImage(R.drawable.android3);
        doNothing().when(mockActivity).swapImage(R.drawable.android2);
        doNothing().when(mockActivity).swapImage(R.drawable.android1);
    }

    private void verifyMockSwapImageExpectation() {
        verify(mockActivity).swapImage(R.drawable.android3);
        verify(mockActivity).swapImage(R.drawable.android2);
        verify(mockActivity).swapImage(R.drawable.android1);
    }

}