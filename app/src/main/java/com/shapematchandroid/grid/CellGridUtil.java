package com.shapematchandroid.grid;


import com.shapematchandroid.GameLevel;
import com.shapematchandroid.shape.Shape;
import com.shapematchandroid.shape.ShapeSelector;
import com.shapematchandroid.shape.ShapeSlotPair;
import com.shapematchandroid.util.DisplayWindow;
import com.shapematchandroid.util.IntegerRange;
import com.shapematchandroid.util.random.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.shapematchandroid.grid.CellGrid.GRID_COL_CNT;
import static com.shapematchandroid.grid.CellGrid.GRID_ROW_CNT;

public class CellGridUtil {

    public static CellGridPair getShapesForLevel(GameLevel currentLvl) {

        List<Shape> shapes = ShapeSelector.select(currentLvl);
        Set<Integer> slots = getSlotIndicesToPutShapesIn(shapes);

        List<ShapeSlotPair> shapeSlotPairs = zipAllShapesAndSlots(shapes, slots);

        if(shapeSlotPairs.size() != currentLvl.getShapeCount()){
            throw new RuntimeException("Expected to have " + currentLvl.getShapeCount() + " shapes and slots " +
                    "but have " + shapeSlotPairs.size());
        }

        CellGrid leftGrid = new CellGrid(shapeSlotPairs);
        CellGrid rightGrid = leftGrid.maybeAlterShapes();


        return new CellGridPair(leftGrid, rightGrid);
    }

    public static Set<Integer> getSlotIndicesToPutShapesIn(List<Shape> shapes) {
        return RandomNumberGenerator.next_N_DistinctNumbersWithinRange(shapes.size(),
                new IntegerRange(0, CellGrid.GRID_SIZE));
    }

    /**
     * implement Scala's zip functionality
     * * @return
     */
    public static List<ShapeSlotPair> zipAllShapesAndSlots(List<Shape> shapes , Set<Integer> slots) {
        if(shapes.size() != slots.size()) {
            throw new RuntimeException("Need the same number of shapes as the slots they go into. ");
        }

        List<ShapeSlotPair> pairs  = new ArrayList<>();

        int index=0;
        for(Integer slot : slots) {
            ShapeSlotPair pair  = new ShapeSlotPair(shapes.get(index++), slot);
            pairs.add(pair);
        }

        return pairs;
    }

    private static void printDebugGrid( CellGrid grid ) {
        for(ShapeSlotPair pair : grid.getShapeSlotPairs()) {
            System.out.println("::" +
                    "PAIR:: " + pair.getShape().toString() + " - " + pair.getSlot() + " \n");
        }
    }
}
