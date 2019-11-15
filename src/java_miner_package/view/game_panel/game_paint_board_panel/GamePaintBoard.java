package java_miner_package.view.game_panel.game_paint_board_panel;

import java_miner_package.controller.MouseControl;
import java_miner_package.model.Cell;
import java_miner_package.view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class GamePaintBoard extends JPanel {
    private final int paintBoardWidth;
    private final int paintBoardHeight;
    private int countCellsWidth;
    private int countCellsHeight;
    private DrawCell[][] paintBoard;

    public GamePaintBoard(MainWindow mainWindow) {
        this.paintBoardWidth = 700;//test
        this.paintBoardHeight = 700;//test
        this.setSize(this.paintBoardWidth, this.paintBoardHeight);
        this.countCellsWidth = mainWindow.getGameController().getGameParameters().getCellsCountWidth(); // width from game parameters
        this.countCellsHeight = mainWindow.getGameController().getGameParameters().getCellsCountHeight(); // height from game parameters
        this.paintBoard = new DrawCell[countCellsWidth][countCellsHeight];
        this.addMouseListener(new MouseControl(mainWindow.getGameController())); // set mouse control on this board
    }

    public void paint(Graphics g) { // drawing pain board
        super.paint(g);
        for(int x = 0; x < this.countCellsWidth; x++) {
            for(int y = 0; y < this.countCellsHeight; y++) {
                this.paintBoard[x][y].paintDrawCell(g, this.getDrawCellWidth(), this.getDrawCellHeight());
            }
        }
    }

    public DrawCell[][] getPaintBoard() {
        return paintBoard;
    }

    private int getDrawCellWidth() {
        return  this.paintBoardWidth / this.countCellsWidth;
    }

    private int getDrawCellHeight() {
        return  this.paintBoardHeight / this.countCellsHeight;
    }

    public void loadPaintBoard(int width, int height, Cell[][] minesField) { // fill paint board with DrawCell
        this.countCellsWidth = width;
        this.countCellsHeight = height;
        this.paintBoard = new DrawCell[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                this.paintBoard[x][y] = new DrawCell(minesField[x][y], getDrawCellWidth(), getDrawCellHeight());
            }
        }
    }
}