package com.shapematchandroid.grid;

import com.shapematchandroid.shape.ShapeSelector;
import com.shapematchandroid.shape.ShapeSlotPair;
import com.shapematchandroid.util.random.RandomBoolean;
import com.shapematchandroid.util.random.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.shapematchandroid.util.IntegerRange.instanceWithinCollectionSize;

public class CellGrid {

    public static final int GRID_ROW_CNT = 6;
    public static final int GRID_COL_CNT = 6;
    public static final int GRID_SIZE = GRID_ROW_CNT * GRID_ROW_CNT;
    private final List<ShapeSlotPair> shapeSlotPairs;

    public CellGrid(List<ShapeSlotPair> shapeSlotPairs) {
        this.shapeSlotPairs = shapeSlotPairs;
    }

    public List<List<Cell>> populateGridCells() {
        List<List<Cell>> grid = new ArrayList<>();

        for (int r = 0; r < GRID_ROW_CNT; r++) {
            List<Cell> oneRow = new ArrayList<>();
            for (int c = 0; c < GRID_COL_CNT; c++) {
                ShapeSlotPair pair;
                if ((pair = getMatchingShapeSlotTuple(toIndex(r, c))) != null) {
                    oneRow.add(new Cell(pair.getShape()));
                } else {
                    oneRow.add(new EmptyCell());
                }
            }

            grid.add(oneRow);

        }

        return grid;
    }

    private ShapeSlotPair getMatchingShapeSlotTuple(int index) {
        for (ShapeSlotPair pair : shapeSlotPairs) {
            if (pair.getSlot() == index) {
                return pair;
            }
        }

        return null;
    }

    /**
     * There is 50% chance that this method will return a CellGrid that has one
     * shape replaced.
     */
    public CellGrid maybeAlterShapes() {
        return maybeAlterShapes(RandomBoolean.nextRandomTrueWithOneOutOfNChance(2));
    }

    public List<ShapeSlotPair> getShapeSlotPairs() {
        return shapeSlotPairs;
    }

    public CellGrid maybeAlterShapes(Boolean shouldAlterShapes) {

        if (shouldAlterShapes) {
            Integer index = RandomNumberGenerator.next(instanceWithinCollectionSize(shapeSlotPairs));
            ShapeSlotPair toBeReplaced = shapeSlotPairs.get(index);

            ShapeSlotPair replacement = new ShapeSlotPair(
                    ShapeSelector.selectOneRandomShapeOtherThan(toBeReplaced.getShape()), toBeReplaced.getSlot());

            List<ShapeSlotPair> newShapeSlotPairs = new ArrayList<>(shapeSlotPairs);
            newShapeSlotPairs.set(index, replacement);

            return new CellGrid(newShapeSlotPairs);
        } else {
            return this;
        }
    }

    public int getShapeSlotPairCount() {
        return shapeSlotPairs.size();
    }

    private Integer toIndex(int r, int c) {
        return (GRID_ROW_CNT * r) + c;
    }

    private void debugPrintGridStat(List<List<Cell>> grid) {
        int ec = 0;
        int sc = 0;

        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                if (cell instanceof EmptyCell) {
                    ec++;
                } else {
                    sc++;
                }
            }
        }

        System.out.println(">> Empty cell count: " + ec + " and shaped cell count: " + sc);

    }
}
