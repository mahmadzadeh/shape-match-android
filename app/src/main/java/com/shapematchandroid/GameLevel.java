package com.shapematchandroid;


import com.shapematchandroid.grid.CellGrid;

public class GameLevel {

    public static int MAX_LEVEL_SHAPE_COUNT = CellGrid.GRID_ROW_CNT * CellGrid.GRID_COL_CNT;

    private int shapeCount;

    public GameLevel(int shapeCnt) {

        shapeCount = shapeCnt;
    }

    public GameLevel nextLevel() {
        return hasReachedMaxLevel() ? this : new GameLevel(shapeCount + 1);
    }

    private boolean hasReachedMaxLevel() {
        return shapeCount == MAX_LEVEL_SHAPE_COUNT;
    }
    // for now the points for each level is equal to the
    // shape count presented in that level.
    public int getPoints() {
        return getShapeCount();
    }

    public int getShapeCount() {
        return shapeCount;
    }
}
