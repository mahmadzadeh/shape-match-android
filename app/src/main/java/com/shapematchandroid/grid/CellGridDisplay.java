package com.shapematchandroid.grid;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shapematchandroid.GridIndex;
import com.shapematchandroid.ImageViewWrapper;
import com.shapematchandroid.util.Dimension;
import com.shapematchandroid.util.DisplayWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.shapematchandroid.grid.CellGrid.GRID_COL_CNT;
import static com.shapematchandroid.grid.CellGrid.GRID_ROW_CNT;
import static com.shapematchandroid.util.DoubleToIntCast.cast;

public class CellGridDisplay {

    private final CellGridPair cellGridPair;
    private final RelativeLayout layout;
    private final Random random = new Random(System.currentTimeMillis());
    private final Context context;
    private final DisplayWindow displayWindow;

    public CellGridDisplay(CellGridPair cellGridPair, RelativeLayout layout, Context context) {
        this.cellGridPair = cellGridPair;
        this.layout = layout;
        this.context = context;
        this.displayWindow = new DisplayWindow(new Dimension(layout.getWidth(), layout.getHeight()));
    }

    public void displayOnScreen() {
        layout.invalidate();
        layout.removeAllViews();

        List<List<Cell>> lgrid = cellGridPair.getLeft().populateGridCells();
        List<List<Cell>> rgrid = cellGridPair.getRight().populateGridCells();

        LeftGridOrientation leftGridOrientation = new LeftGridOrientation(displayWindow.topLeftCornerOfLeftGrid(), displayWindow.oneCellDimension());
        RightGridOrientation rightGridOrientation = new RightGridOrientation(displayWindow.topLeftCornerOfRightGrid(), displayWindow.oneCellDimension());

        display(transformToImageViewWrapperGrid(lgrid), leftGridOrientation);
        display(transformToImageViewWrapperGrid(rgrid), rightGridOrientation);
    }

    private int generateViewId() {
        return random.nextInt(1000000);
    }

    public List<List<ImageViewWrapper>> transformToImageViewWrapperGrid(List<List<Cell>> populatedCells) {
        List<List<ImageViewWrapper>> grid = new ArrayList<>();

        for (List<Cell> oneRow : populatedCells) {

            List<ImageViewWrapper> oneRowOfImageView = new ArrayList<>();
            for (Cell cell : oneRow) {
                ImageView iv = new ImageView(context);
                oneRowOfImageView.add(new ImageViewWrapper(iv, generateViewId(), cell.getShapeResourceId()));
            }

            grid.add(oneRowOfImageView);
        }

        return grid;
    }

    public void display(List<List<ImageViewWrapper>> imageViewGrid, Orientation orientation) {

        Dimension cellDimension = orientation.getOneCellDimension();

        for (int r = 0; r < imageViewGrid.size(); r++) {

            LinearLayout oneRow = linearLayoutForOneRow(imageViewGrid.get(r), cellDimension);
            layout.addView(oneRow, relativeLayoutParamFor(r, orientation));
        }
    }

    LinearLayout linearLayoutForOneRow(List<ImageViewWrapper> oneRow, Dimension oneCellDimension) {
        LinearLayout ll = new LinearLayout(context);

        for (ImageViewWrapper imageViewWrapper : oneRow) {
            ImageView imageView = imageViewWrapper.getImageViewWithAttributesAdded();
            int w = cast(oneCellDimension.getWidth());
            int h = cast(oneCellDimension.getHeight());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
            ll.addView(imageView, params);
        }
        return ll;
    }

    RelativeLayout.LayoutParams relativeLayoutParamFor(int row, Orientation orientation) {
        Dimension cellDimension = orientation.getOneCellDimension();
        int width = cast(cellDimension.getWidth());
        int height = cast(cellDimension.getHeight());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width * GRID_COL_CNT, height * GRID_ROW_CNT);
        params.leftMargin = cast(orientation.firstRowMargin.getLeftMargin());
        params.topMargin = cast(orientation.getSubsequentRowMargin(row).getTopMargin());

        return params;
    }

    public GridIndex getLeftNeighbourTo(GridIndex gridIndex) {
        return gridIndex.oneToLeft();
    }

    public GridIndex getNeighbourAbove(GridIndex gridIndex) {
        return gridIndex.oneAbove();
    }


}