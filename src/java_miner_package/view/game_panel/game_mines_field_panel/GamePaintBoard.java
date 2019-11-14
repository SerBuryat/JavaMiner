package java_miner_package.view.game_panel.game_mines_field_panel;

import java_miner_package.controller.GameController;
import java_miner_package.controller.MouseControl;
import java_miner_package.model.Cell;

import javax.swing.*;
import java.awt.*;

public class GamePaintBoard extends JPanel {
    private final int paintBoardWidth;
    private final int paintBoardHeight;
    private int countCellsWidth;
    private int countCellsHeight;
    private DrawCell[][] paintBoard;

    public GamePaintBoard(int countCellsWidth, int countCellsHeight) {
        this.paintBoardWidth = 700;//test
        this.paintBoardHeight = 700;//test
        this.setSize(this.paintBoardWidth, this.paintBoardHeight);
        this.countCellsWidth = countCellsWidth;
        this.countCellsHeight = countCellsHeight;

        this.paintBoard = new DrawCell[countCellsWidth][countCellsHeight]; // create the game paint board

        addMouseListener(new MouseControl()); // set mouse control on this board
    }

    public void paint(Graphics g) { // drawing pain board
        super.paint(g);
        GameController.GAME_CONTROLLER.paintGamePaintBoard(g, countCellsWidth, countCellsHeight, getDrawCellWidth(), getDrawCellHeight(),this.paintBoard);
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

    public void loadPaintBoard(int width, int height, Cell[][] minesField) {
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