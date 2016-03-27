package com.shapematchandroid.grid;

import android.content.Context;
import android.widget.RelativeLayout;

import com.shapematchandroid.GameLevel;
import com.shapematchandroid.GridIndex;
import com.shapematchandroid.ImageViewWrapper;
import com.shapematchandroid.util.CellMargin;
import com.shapematchandroid.util.Dimension;
import com.shapematchandroid.util.DisplayWindow;

import org.junit.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.shapematchandroid.grid.CellGrid.GRID_COL_CNT;
import static com.shapematchandroid.grid.CellGrid.GRID_ROW_CNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class CellGridDisplayTest {

    @Mock
    RelativeLayout mockLayout;

    @Test
    public void givenCellGridThenImageViewTransformerTurnsCellsToImageViewGrid() {

        CellGridDisplay gridDisplay = new CellGridDisplay(mock(CellGridPair.class), mock(RelativeLayout.class), mock(Context.class));

        CellGrid cellGrid =  CellGridUtil.getShapesForLevel(new GameLevel(GRID_ROW_CNT * GRID_COL_CNT)).leftGrid();

        List<List<Cell>> populatedCells = cellGrid.populateGridCells();

        List<List<ImageViewWrapper>> imageViewGrid = gridDisplay.transformToImageViewWrapperGrid(populatedCells);

        Set<Integer> allImageIds = new HashSet<>();
        for(List<ImageViewWrapper> oneRow: imageViewGrid) {
            for(ImageViewWrapper imageView: oneRow) {
                allImageIds.add(imageView.getId());
                assertNotNull(imageView.getShapeResourceId());
            }
        }

        assertEquals(GRID_ROW_CNT * GRID_COL_CNT , allImageIds.size());
    }

    @Test
    public void givenRowAndColThenGetLeftNeighbourReturnsGridIndexOfLeftNeighbour() {

        CellGridDisplay gridDisplay = new CellGridDisplay(mock(CellGridPair.class), mock(RelativeLayout.class), mock(Context.class));

        GridIndex gridIndex = gridDisplay.getLeftNeighbourTo(new GridIndex(0, 0));

        assertEquals(0, gridIndex.getRow());
        assertEquals(-1, gridIndex.getColumn());

        gridIndex = gridDisplay.getLeftNeighbourTo(new GridIndex(1,3));

        assertEquals(1, gridIndex.getRow());
        assertEquals(2, gridIndex.getColumn());
    }

    @Test
    public void givenRowAndColThenGetNeighbourAboveReturnsGridIndexOfNeighbourAbove() {

        CellGridDisplay gridDisplay = new CellGridDisplay(mock(CellGridPair.class), mock(RelativeLayout.class), mock(Context.class));

        GridIndex gridIndex = gridDisplay.getNeighbourAbove(new GridIndex(0, 0));

        assertEquals(-1, gridIndex.getRow());
        assertEquals(0, gridIndex.getColumn());

        gridIndex = gridDisplay.getNeighbourAbove(new GridIndex(1, 3));

        assertEquals(0, gridIndex.getRow());
        assertEquals(3, gridIndex.getColumn());
    }

    @Test
    public void givenRowThenRelativeLayoutParamForRowReturnsLayoutWithCorrectParams() {
        DisplayWindow window = new DisplayWindow(new Dimension(100, 100));
        Dimension cellDimension = window.oneCellDimension();

        CellGridDisplay gridDisplay = new CellGridDisplay(mock(CellGridPair.class), mock(RelativeLayout.class), mock(Context.class));

        CellMargin topLeftOfLeftGrid = new CellMargin(window.topLeftCornerOfLeftGrid());
        RelativeLayout.LayoutParams params = gridDisplay.relativeLayoutParamFor(0, new LeftGridOrientation(topLeftOfLeftGrid, cellDimension));

        assertEquals( 4, params.leftMargin  );
        assertEquals(15, params.topMargin   );

        params = gridDisplay.relativeLayoutParamFor(1, new LeftGridOrientation(topLeftOfLeftGrid, cellDimension));

        assertEquals( 4, params.leftMargin  );
        assertEquals( 21, params.topMargin);

        params = gridDisplay.relativeLayoutParamFor(2, new LeftGridOrientation(topLeftOfLeftGrid, cellDimension));

        assertEquals( 4, params.leftMargin  );
        assertEquals( 28, params.topMargin);
    }
}
