package com.shapematchandroid.ui;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shapematchandroid.GameLevel;
import com.shapematchandroid.ImageViewWrapper;
import com.shapematchandroid.grid.Cell;
import com.shapematchandroid.grid.CellGrid;
import com.shapematchandroid.grid.CellGridPair;
import com.shapematchandroid.grid.CellGridUtil;
import com.shapematchandroid.grid.LeftGridOrientation;
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

    CellGridDisplay gridDisplay = new CellGridDisplay(
            mock(CellGridPair.class),
            mock(RelativeLayout.class),
            mock(Context.class), mock(UIElements.class));

    @Test
    public void givenCellGridThenImageViewTransformerTurnsCellsToImageViewGrid() {

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
    public void givenRowThenRelativeLayoutParamForRowReturnsLayoutWithCorrectParams() {
        DisplayWindow window = new DisplayWindow(new Dimension(100, 100));
        Dimension cellDimension = window.oneCellDimension();


        CellMargin topLeftOfLeftGrid = new CellMargin(window.topLeftCornerOfLeftGrid());
        RelativeLayout.LayoutParams params = gridDisplay.relativeLayoutParamFor(0, new LeftGridOrientation(topLeftOfLeftGrid, cellDimension));

        assertEquals( 2, params.leftMargin  );
        assertEquals(15, params.topMargin   );

        params = gridDisplay.relativeLayoutParamFor(1, new LeftGridOrientation(topLeftOfLeftGrid, cellDimension));

        assertEquals( 2, params.leftMargin  );
        assertEquals( 21, params.topMargin);

        params = gridDisplay.relativeLayoutParamFor(2, new LeftGridOrientation(topLeftOfLeftGrid, cellDimension));

        assertEquals( 2, params.leftMargin  );
        assertEquals( 28, params.topMargin);
    }
}
