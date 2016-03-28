package com.shapematchandroid;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class GameTest {

    @Test
    public void canCreateInstanceWithGameLogic() {
        new Game(mock(GameLogic.class));
    }
}