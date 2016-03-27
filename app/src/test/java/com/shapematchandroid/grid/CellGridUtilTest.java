package com.shapematchandroid.grid;

import com.shapematchandroid.GameLevel;
import com.shapematchandroid.shape.HollowSquare;
import com.shapematchandroid.shape.Shape;
import com.shapematchandroid.shape.ShapeSlotPair;
import com.shapematchandroid.shape.SolidSquare;
import com.shapematchandroid.util.Dimension;
import com.shapematchandroid.util.DisplayWindow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class CellGridUtilTest {

    DisplayWindow testDisplayWindow = new DisplayWindow(new Dimension(10, 10));

    @Test
    public void givenDisplayShapeObjectAndLevelOneThenGettShapesReturnsShapesToBeDisplayedOnTheRightAsWellAsLeftScreen() {

        CellGridPair cellGridPair = CellGridUtil.getShapesForLevel(new GameLevel(1));

        assertNotNull(cellGridPair);
    }

    @Test(expected = RuntimeException.class)
    public void givenUnequalNumberOfShapesAndSlotsThenZipAllShapesAndSlotsThrowsRuntime() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new HollowSquare());

        CellGridUtil.zipAllShapesAndSlots(shapes, new HashSet<Integer>());
    }

    @Test
    public void givenEqualNumberOfShapesAndSlotsThenZipAllShapesAndSlotsRetunsListOfShapeSlotPairs() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new HollowSquare());

        HashSet<Integer> slots = new HashSet<>();
        slots.add(1);

        List<ShapeSlotPair> pairs = CellGridUtil.zipAllShapesAndSlots(shapes, slots);

        assertTrue(pairs.get(0).getShape() instanceof HollowSquare);
        assertTrue(1 == pairs.get(0).getSlot());
    }

    @Test
    public void givenMultiplefShapesAndSlotsThenZipAllShapesAndSlotsRetunsListOfShapeSlotPairs() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new HollowSquare());
        shapes.add(new SolidSquare());

        HashSet<Integer> slots = new HashSet<>();
        slots.add(1);
        slots.add(14);

        List<ShapeSlotPair> pairs = CellGridUtil.zipAllShapesAndSlots(shapes, slots);

        ShapeSlotPair firstPair = pairs.get(0);
        assertTrue(firstPair.getShape() instanceof HollowSquare);
        assertTrue(1 == firstPair.getSlot());

        ShapeSlotPair secondPair = pairs.get(1);
        assertTrue(secondPair.getShape() instanceof SolidSquare);
        assertTrue(14 == secondPair.getSlot());
    }

}