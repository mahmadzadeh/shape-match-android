package com.shapematchandroid;


import com.shapematchandroid.grid.CellGridPair;

import static com.shapematchandroid.GameLevel.initialLevel;
import static com.shapematchandroid.Score.initialScore;
import static com.shapematchandroid.grid.CellGridUtil.getShapesForLevel;

public class GameLogic {

    public final static int REQUIRED_CORRECT_CONSECUTIVE_ANSWERES = 2;

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
        return userInput == UserInput.Match
                ? gameLogicBasedOnUserSelection(cellGridPair.leftGrid().equals(cellGridPair.rightGrid()))
                : gameLogicBasedOnUserSelection(cellGridPair.leftGrid().isNotEqual(cellGridPair.rightGrid()));
    }

    private GameLogic gameLogicBasedOnUserSelection( Boolean shapesEquality ) {
        if (shapesEquality) {
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

    private int determineCorrectAnswerCount(GameLevel level ) {
        return level == currentLevel? correctAnswers + 1:  0;
    }

    private GameLevel determineGameLevel() {
        return correctAnswers == (REQUIRED_CORRECT_CONSECUTIVE_ANSWERES - 1)
                ? currentLevel.nextLevel()
                :currentLevel;
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
        return  isGameOver;
    }

    public boolean isMatchingPairShapes() {
        return  cellGridPair.leftGrid().equals(cellGridPair.rightGrid());
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
}
