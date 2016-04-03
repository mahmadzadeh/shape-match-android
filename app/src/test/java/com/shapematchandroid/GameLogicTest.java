package com.shapematchandroid;

import com.shapematchandroid.grid.CellGrid;
import com.shapematchandroid.grid.CellGridPair;
import com.shapematchandroid.shape.HollowSquare;
import com.shapematchandroid.shape.ShapeSlotPair;
import com.shapematchandroid.shape.SolidCircle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.shapematchandroid.GameLogic.REQUIRED_CORRECT_CONSECUTIVE_ANSWERES;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class GameLogicTest {

    GameLevel levelOne = GameLevel.initialLevel;
    GameLevel levelTwo = levelOne.nextLevel();
    GameLevel levelThree = levelTwo.nextLevel();

    List<ShapeSlotPair> matchingShapesSlotPairs = new ArrayList<ShapeSlotPair>() {{
        add(new ShapeSlotPair(new HollowSquare(), 0));
        add(new ShapeSlotPair(new HollowSquare(), 1));
    }};

    List<ShapeSlotPair> nonMatchingShapes = new ArrayList<ShapeSlotPair>() {{
        add(new ShapeSlotPair(new HollowSquare(), 0));
        add(new ShapeSlotPair(new SolidCircle(),1));
    }};

    CellGridPair matchingDisplayShapesPair = new CellGridPair(
            new CellGrid(matchingShapesSlotPairs),
            new CellGrid(matchingShapesSlotPairs));

    CellGridPair nonMatchingDisplayShapesPair = new CellGridPair(
            new CellGrid(matchingShapesSlotPairs),
            new CellGrid(nonMatchingShapes));


    @Test
    public void canCreateInstance() {
        new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false);
    }

    @Test
    public void givenAGameWhenUserSelectsMatchAndLeftAndRightMatchAndFirstConsecutiveCorrectAnswerThenIsUserInputCorrectReturnsGameLogicWithSameLevel() {
        UserInput userInput = UserInput.Match;

        GameLogic gl = new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false).evaluateUserInput(userInput);

        assertEquals(gl.currentLevel(), levelOne); // still the at same game level
        assertEquals(1, gl.correctAnswers());  // accumulated one correct answer
    }

    @Test
    public void givenAGameWhenUserChoosesWronglyThenPointsDeducted() {
        UserInput userInput = UserInput.Mismatch;
        GameLogic gl = new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false).evaluateUserInput(userInput);

        assertEquals(gl.currentLevel(), levelOne);

        assertEquals(-1, gl.score().points());
    }

    @Test
    public void givenGameWhenUserMakesNConsecutiveCorrectAnswersThenLevelIncreased() {

        GameLogic gl = makeNConsecutiveCorrectGuess(REQUIRED_CORRECT_CONSECUTIVE_ANSWERES,
                new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false));

        assertEquals(levelTwo, gl.currentLevel());
    }

    @Test

    public void giveGameWhenUserMakesFourConsecutiveCorrectGuessesThenLevelIsIncreasedToThree() {

        GameLogic gl = makeNConsecutiveCorrectGuess(REQUIRED_CORRECT_CONSECUTIVE_ANSWERES * 2,
                new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false));

        assertEquals(levelThree, gl.currentLevel());
    }

    @Test
    public void givenGameWhenUserSelectsMismatchAndLeftAndRightMatchThenGgameRemainsInCurrentLevel() {
        UserInput userInput = UserInput.Mismatch;

        GameLogic gl = new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false).evaluateUserInput(userInput);

        assertEquals(gl.currentLevel(), levelOne);
    }

    @Test
    public void givenGameWhnUserSelectsCorrectlyThenNextLevelHasShapeCountCorrespondingToTheLevel() {
        UserInput userInput = UserInput.Match;

        GameLogic gl = makeNConsecutiveCorrectGuess(REQUIRED_CORRECT_CONSECUTIVE_ANSWERES,
                new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false));

        assertEquals(gl.cellGridPair().leftGrid().getShapeSlotPairCount(), levelTwo.getShapeCount());
        assertEquals(gl.cellGridPair().rightGrid().getShapeSlotPairCount(), levelTwo.getShapeCount());
    }

    @Test
    public void givenGameWhenGameMarkedAsFinishedThenIsGameOverReturnsTrue() {
        assertTrue(
                new GameLogic(levelOne, matchingDisplayShapesPair, 0, new Score(0), false).markGameAsFinished().isGameOver());
    }

    @Test
    public void givenGameWhenResetIsCalledThenNewInstanceOfGameIsReturnedWithInitialState() {
        GameLogic newGame = new GameLogic(new GameLevel(10), matchingDisplayShapesPair, 12, new Score(2222), false).reset();

        assertEquals( 1 , newGame.currentLevel().getShapeCount());
        assertEquals( 1 , newGame.cellGridPair().leftGrid().getShapeSlotPairCount());
        assertEquals(1, newGame.cellGridPair().rightGrid().getShapeSlotPairCount());
    }

    @Test
    public void givenUserInputThatIsCorrectThenIsCorrectGuessReturnsTrue() {
        GameLogic gameLogic  = new GameLogic(new GameLevel(10), matchingDisplayShapesPair, 12, new Score(2222), false);

        assertTrue(gameLogic.isCorrectAnswer(UserInput.Match));
    }

    @Test
    public void givenUserInputThatIsNotCorrectThenIsCorrectGuessReturnsFalse() {
        GameLogic gameLogic  = new GameLogic(new GameLevel(10), matchingDisplayShapesPair, 12, new Score(2222), false);

        assertFalse(gameLogic.isCorrectAnswer(UserInput.Mismatch));
    }

    private GameLogic makeNConsecutiveCorrectGuess(Integer n, GameLogic gameLogic) {
        return recurse(n, gameLogic);
    }

    private GameLogic recurse(Integer num , GameLogic gameLogic) {

        if (num == 0) return gameLogic;
        else {
            if (gameLogic.isMatchingPairShapes())
                return recurse(num - 1, gameLogic.evaluateUserInput(UserInput.Match));
            else
                return recurse(num - 1, gameLogic.evaluateUserInput(UserInput.Mismatch));
        }
    }
}