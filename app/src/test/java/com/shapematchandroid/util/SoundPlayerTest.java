package com.shapematchandroid.util;


import android.media.MediaPlayer;

import com.shapematchandroid.GameLogic;
import com.shapematchandroid.UserInput;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SoundPlayerTest {

    @Mock
    MediaPlayer mockBuzzerMediaPlayer;

    @Mock
    MediaPlayer mockDingMediaPlayer;

    @Before
    public void setUp() {
        initMocks(this);
    }
    @Test
    public void givenWrongAnswerGivenByUserSoundPlayerPlaysBuzzerSound() {
        doNothing().when(mockBuzzerMediaPlayer).start();

        SoundPlayer player  = new SoundPlayer(mockBuzzerMediaPlayer, mockDingMediaPlayer);

        GameLogic mockGameLogic = mock(GameLogic.class);
        when(mockGameLogic.isCorrectAnswer(any(UserInput.class))).thenReturn(false);

        player.soundFeedbackForUserInput(UserInput.Match, mockGameLogic);

        verify(mockGameLogic).isCorrectAnswer(any(UserInput.class));
        verify(mockBuzzerMediaPlayer).start();
    }

    @Test
    public void givenCorrectAnswerGivenByUserSoundPlayerPlaysBuzzerSound() {

        doNothing().when(mockDingMediaPlayer).start();
        SoundPlayer player  = new SoundPlayer(mockBuzzerMediaPlayer, mockDingMediaPlayer);

        GameLogic mockGameLogic = mock(GameLogic.class);
        when(mockGameLogic.isCorrectAnswer(any(UserInput.class))).thenReturn(true);

        player.soundFeedbackForUserInput(UserInput.Match, mockGameLogic);

        verify(mockGameLogic).isCorrectAnswer(any(UserInput.class));
        verify(mockDingMediaPlayer).start();
    }

}
