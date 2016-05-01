package com.shapematchandroid;


import com.shapematchandroid.grid.CellGridPair;

import static com.shapematchandroid.GameLevel.initialLevel;
import static com.shapematchandroid.Score.initialScore;
import static com.shapematchandroid.grid.CellGridUtil.getShapesForLevel;

/**
 * Immutable
 */
public class GameLogic {

    public final static int REQUIRED_CORRECT_CONSECUTIVE_ANSWERS = 2;
    private final static int INITIAL_CORRECT_ANSWERS = 0;
    public static GameLogic initialState = new GameLogic(
            GameLevel.initialLevel,
            getShapesForLevel(GameLevel.initialLevel),
            INITIAL_CORRECT_ANSWERS, Score.initialScore,
            false);

    private final GameLevel currentLevel;
    private final CellGridPair cellGridPair;
    private final int correctAnswers;
    private final Score score;
    private boolean isGameOver;

    public GameLogic(GameLevel currentLevel, CellGridPair cellGridPair, int correctAnswers, Score score, boolean isGameOver) {
        this.currentLevel = currentLevel;
        this.cellGridPair = cellGridPair;
        this.correctAnswers = correctAnswers;
        this.score = score;
        this.isGameOver = isGameOver;
    }

    public GameLogic evaluateUserInput(UserInput userInput) {
        boolean isCorrectAnswerGiven = isCorrectAnswer(userInput);

        return gameLogicBasedOnUserSelection(isCorrectAnswerGiven);
    }

    private GameLogic gameLogicBasedOnUserSelection(Boolean isCorrectAnswer) {
        if (isCorrectAnswer) {
            GameLevel level = determineGameLevel();
            int corrAnswers = determineCorrectAnswerCount(level);
            return new GameLogic(
                    level,
                    getShapesForLevel(level),
                    corrAnswers,
                    score.add(level.points()),
                    false);
        } else {
            return new GameLogic(
                    currentLevel,
                    getShapesForLevel(currentLevel),
                    correctAnswers,
                    score.deduct(currentLevel.points()),
                    false);
        }
    }

    private int determineCorrectAnswerCount(GameLevel level) {
        return level == currentLevel ? correctAnswers + 1 : 0;
    }

    private GameLevel determineGameLevel() {
        return correctAnswers == (REQUIRED_CORRECT_CONSECUTIVE_ANSWERS - 1)
                ? currentLevel.nextLevel()
                : currentLevel;
    }

    public GameLevel currentLevel() {
        return currentLevel;
    }

    public CellGridPair cellGridPair() {
        return cellGridPair;
    }

    public int correctAnswers() {
        return correctAnswers;
    }

    public Score score() {
        return score;
    }

    public int currentPoints() {
        return score.points();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isMatchingPairShapes() {
        return cellGridPair.leftGrid().equals(cellGridPair.rightGrid());
    }

    public GameLogic markGameAsFinished() {
        return new GameLogic(currentLevel, cellGridPair, correctAnswers, score, true);
    }

    public GameLogic reset() {
        return new GameLogic(
                initialLevel,
                getShapesForLevel(initialLevel),
                0,
                initialScore,
                false);
    }

    public boolean isCorrectAnswer(UserInput userInput) {
        return userInput == UserInput.Match
                ? cellGridPair.leftGrid().equals(cellGridPair.rightGrid())
                : cellGridPair.leftGrid().isNotEqual(cellGridPair.rightGrid());
    }
}
