package com.shapematchandroid.grid;

import com.shapematchandroid.grid.Cell;
import com.shapematchandroid.grid.CellGrid;
import com.shapematchandroid.grid.EmptyCell;
import com.shapematchandroid.shape.HollowSquare;
import com.shapematchandroid.shape.NullShape;
import com.shapematchandroid.shape.Shape;
import com.shapematchandroid.shape.ShapeSlotPair;
import com.shapematchandroid.shape.SolidSquare;
import com.shapematchandroid.util.IntegerRange;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.shapematchandroid.grid.CellGrid.GRID_COL_CNT;
import static com.shapematchandroid.grid.CellGrid.GRID_ROW_CNT;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

public class CellGridTest {


    @Test
    public void givenEmptyListOfShapeAndSlotsThenPopulateGridCellsCreatesGridOfEmptyCells() {
        List<ShapeSlotPair> pairs = new ArrayList<>();

        List<List<Cell>> grid = new CellGrid(pairs).populateGridCells();

        assertEquals(GRID_ROW_CNT, grid.size());

        for(List<Cell> oneRow : grid) {
            assertEquals(GRID_COL_CNT, oneRow.size());
            for(Cell cell : oneRow){
                assertTrue(cell instanceof EmptyCell);
            }
        }
    }

    @Test
    public void givenListShapeAndSlotsSizeOneThenPopulateGridCellsCreatesGridWithOneCellPopulated() {
        List<ShapeSlotPair> pairs = new ArrayList<>();
        pairs.add(new ShapeSlotPair(new HollowSquare(), 0));

        List<List<Cell>> grid = new CellGrid(pairs).populateGridCells();

        Cell populatedCell = grid.get(0).get(0);
        assertTrue(populatedCell.getShape() instanceof HollowSquare);
    }

    @Test
    public void givenListShapeAndSlotsThenPopulateGridCellsCreatesGridWithOneCellPopulated() {
        List<ShapeSlotPair> pairs = new ArrayList<>();
        pairs.add(new ShapeSlotPair(new HollowSquare(), 0));

        int lastGridIndex = (GRID_COL_CNT * GRID_ROW_CNT) - 1 ;

        pairs.add(new ShapeSlotPair(new SolidSquare(), lastGridIndex));

        List<List<Cell>> grid = new CellGrid(pairs).populateGridCells();

        Cell populatedCell = grid.get(0).get(0);
        assertTrue(populatedCell.getShape() instanceof HollowSquare);

        Cell lastCell = grid.get(GRID_ROW_CNT - 1 ).get(GRID_COL_CNT - 1);
        assertTrue(lastCell.getShape() instanceof SolidSquare);

        // two non-empty cells in the grid therefore the rest of the
        // grid should be just empty cells
        assertEquals((GRID_COL_CNT * GRID_ROW_CNT) - 2, filterEmptyCellCount(grid));
    }

    @Test
    public void givenFullGridThenGridContainsNoEmptyCells() {
        List<ShapeSlotPair> pairs = getShapeSlotPairsOfSize((GRID_COL_CNT * GRID_ROW_CNT));

        List<List<Cell>> grid = new CellGrid(pairs).populateGridCells();

        assertEquals(0 , filterEmptyCellCount(grid));
    }

    @Test
    public void givenShouldAlterOneShapeThenMaybeAlterShapesReturnsNewInstanceOfCellGrid(){
        List<ShapeSlotPair> pairs = getShapeSlotPairsOfSize((GRID_COL_CNT * GRID_ROW_CNT));

        CellGrid originalGrid = new CellGrid(pairs);
        int originalShapeCount = originalGrid.getShapeSlotPairCount();

        CellGrid newGrid = originalGrid.maybeAlterShapes(true);

        assertNotSame(originalGrid, newGrid);

        assertEquals( originalShapeCount , newGrid.getShapeSlotPairCount());
    }

    private int  filterEmptyCellCount(List<List<Cell>> grid ) {
        int count = 0;
        for(List<Cell> oneRow : grid ) {
            for(Cell cell: oneRow ){
                if( cell instanceof EmptyCell ){
                    count++;
                }
            }
        }
        return count;
    }

    private List<ShapeSlotPair> getShapeSlotPairsOfSize( int size) {

        List<ShapeSlotPair> pairs = new ArrayList<>();

        for(int i= 0; i<  size ; i++) {
            pairs.add(i, new ShapeSlotPair(new HollowSquare(),i));
        }
        return pairs;
    }


}