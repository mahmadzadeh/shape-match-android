package com.shapematchandroid.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.shapematchandroid.ImageViewWrapper;
import com.shapematchandroid.grid.Cell;
import com.shapematchandroid.grid.CellGridPair;
import com.shapematchandroid.grid.LeftGridOrientation;
import com.shapematchandroid.grid.Orientation;
import com.shapematchandroid.grid.RightGridOrientation;
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
    private final UIElements uiElements;

    public CellGridDisplay(CellGridPair cellGridPair,
                           RelativeLayout layout,
                           Context context,
                           UIElements uiElements) {
        this.cellGridPair = cellGridPair;
        this.layout = layout;
        this.context = context;
        this.displayWindow = new DisplayWindow(new Dimension(layout.getWidth(), layout.getHeight()));
        this.uiElements = uiElements;
    }

    public void displayOnScreen(int points) {
        layout.invalidate();
        layout.removeAllViews();

        List<List<Cell>> lgrid = cellGridPair.leftGrid().populateGridCells();
        List<List<Cell>> rgrid = cellGridPair.rightGrid().populateGridCells();

        LeftGridOrientation leftGridOrientation = new LeftGridOrientation(displayWindow.topLeftCornerOfLeftGrid(), displayWindow.oneCellDimension());
        RightGridOrientation rightGridOrientation = new RightGridOrientation(displayWindow.topLeftCornerOfRightGrid(), displayWindow.oneCellDimension());

        displayGrid(transformToImageViewWrapperGrid(lgrid), leftGridOrientation);
        displayGrid(transformToImageViewWrapperGrid(rgrid), rightGridOrientation);
        displayGameButtons();
        displayScore(points);
        displayCountDownTimer();

    }

    private void displayCountDownTimer() {
        Dimension countdownTimerDimension = displayWindow.scoreBoardDimension().increaseByFactor(2,2);

        LayoutParams timerParams = new LayoutParams(
                cast(countdownTimerDimension.getWidth()), cast(countdownTimerDimension.getHeight()));

        timerParams.leftMargin = cast(displayWindow.topLeftCornerOfCountDownTimer().getLeftMargin());
        timerParams.topMargin = cast(displayWindow.topLeftCornerOfCountDownTimer().getTopMargin());

        layout.addView(uiElements.countDownTextView(), timerParams);
    }

    private void displayGameButtons() {

        Dimension buttonDimension = displayWindow.singleButtonDimension();

        layout.addView(uiElements.mismatchButton(), layoutParamsFor(buttonDimension,
                displayWindow.topLeftCornerOfLeftButton().getLeftMargin(),
                displayWindow.topLeftCornerOfLeftButton().getTopMargin()));

        layout.addView(uiElements.matchButton(), layoutParamsFor(buttonDimension,
                displayWindow.topLeftCornerOfRightButton().getLeftMargin(),
                displayWindow.topLeftCornerOfRightButton().getTopMargin()));

        Dimension quitButtonDimension = displayWindow.scoreBoardDimension().increaseByFactor(2,2);

        LayoutParams quitButtonParams = new LayoutParams(
                cast(quitButtonDimension.getWidth()), cast(quitButtonDimension.getHeight()));

        quitButtonParams.leftMargin = cast(displayWindow.topLeftCornerOfQuitButton().getLeftMargin());
        quitButtonParams.topMargin = cast(displayWindow.topLeftCornerOfQuitButton().getTopMargin());

        layout.addView(uiElements.quitButton(), quitButtonParams);
    }

    private LayoutParams layoutParamsFor(Dimension buttonDimension, double leftMargin, double topMargin) {
        LayoutParams mismatchParams = new LayoutParams(
                cast(buttonDimension.getWidth()), cast(buttonDimension.getHeight()));

        mismatchParams.leftMargin = cast(leftMargin);
        mismatchParams.topMargin = cast(topMargin);

        return mismatchParams;
    }


    private void displayScore(int score) {
        Dimension scoreDimension = displayWindow.scoreBoardDimension();

        LayoutParams scoreBoardParams = new LayoutParams(
                cast(scoreDimension.getWidth()), cast(scoreDimension.getHeight()));

        uiElements.setScore(score);

        scoreBoardParams.leftMargin = cast(displayWindow.topLeftCornerOfScore().getLeftMargin());
        scoreBoardParams.topMargin = cast(displayWindow.topLeftCornerOfScore().getTopMargin());

        layout.addView(uiElements.scoreTextView(), scoreBoardParams);
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

    public void displayGrid(List<List<ImageViewWrapper>> imageViewGrid, Orientation orientation) {

        Dimension cellDimension = orientation.getSingleCellDimension();

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
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
            ll.addView(imageView, params);
        }
        return ll;
    }

    RelativeLayout.LayoutParams relativeLayoutParamFor(int row, Orientation orientation) {
        Dimension cellDimension = orientation.getSingleCellDimension();
        int width = cast(cellDimension.getWidth());
        int height = cast(cellDimension.getHeight());

        LayoutParams params = new LayoutParams(width * GRID_COL_CNT, height * GRID_ROW_CNT);
        params.leftMargin = cast(orientation.getFirstRowMargin().getLeftMargin());
        params.topMargin = cast(orientation.getSubsequentRowMargin(row).getTopMargin());

        return params;
    }

}