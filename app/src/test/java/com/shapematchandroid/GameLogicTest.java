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
import static junit.framework.TestCase.assertEquals;

public class GameLogicTest {

    GameLevel gameLevelOne = new GameLevel(1);
    GameLevel LevelTwo = new GameLevel(1).nextLevel();
    GameLevel LevelThree = LevelTwo.nextLevel();

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
        new GameLogic(gameLevelOne, matchingDisplayShapesPair, 0, new Score(0), false);
    }

    @Test
    public void givenAnInstanceThenCanStartGame() {

        new GameLogic(gameLevelOne, matchingDisplayShapesPair, 0, new Score(0), false).start();

    }

    @Test
    public void givenAGameWhenUserSelectsMatchAndLeftAndRightMatchAndFirstConsecutiveCorrectAnswerThenIsUserInputCorrectReturnsGameLogicWithSameLevel() {
        UserInput userInput = UserInput.Match;

        GameLogic gl = new GameLogic(gameLevelOne, matchingDisplayShapesPair, 0, new Score(0), false).evaluateUserInput(userInput);

        assertEquals(gl.currentLevel(), gameLevelOne); // still the at same game level
        assertEquals(1, gl.correctAnswers());  // accumulated one correct answer
    }

    @Test
    public void givenAGameWhenUserChoosesWronglyThenPointsDeducted() {
        UserInput userInput = UserInput.Mismatch;
        GameLogic gl = new GameLogic(gameLevelOne, matchingDisplayShapesPair, 0, new Score(0), false).evaluateUserInput(userInput);

        assertEquals(gl.currentLevel(),  gameLevelOne);

        assertEquals(-1, gl.score().points());
    }

    @Test
    public void givenGameWhenUserMakesNConsecutiveCorrectAnswersThenLevelIncreased() {

        GameLogic gl = makeNConsecutiveCorrectGuess(REQUIRED_CORRECT_CONSECUTIVE_ANSWERES,
                new GameLogic(gameLevelOne, matchingDisplayShapesPair,0, new Score(0), false));

        assertEquals(LevelTwo, gl.currentLevel());
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