package java_miner_package.view.game_field.game_board;

import java_miner_package.controller.GameController;
import java_miner_package.controller.MouseControl;
import java_miner_package.model.Block;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private int width;
    private int height;
    private int countCellsWidth;
    private int countCellsHeight;
    private DrawBlock[][] gamePaintBoard;

    public GameBoard (int countCellsWidth, int countCellsHeight) {
        this.width = 700;//test
        this.height = 700;//test
        this.setSize(this.width, this.height);
        this.countCellsWidth = countCellsWidth;
        this.countCellsHeight = countCellsHeight;

        this.gamePaintBoard = new DrawBlock[countCellsWidth][countCellsHeight]; // create the game paint board

        addMouseListener(new MouseControl()); // set mouse control on this board
    }

    public void paint(Graphics g) { // drawing game board
        super.paint(g);
        GameController.GAME_CONTROLLER.paintGameBoard(g, countCellsWidth, countCellsHeight, getBlockWidth(), getBlockHeight(),this.gamePaintBoard);
    }

    public DrawBlock[][] getGamePaintBoard() {
        return gamePaintBoard;
    }

    public int getBlockWidth() {
        return  this.width / this.countCellsWidth;
    }

    public int getBlockHeight() {
        return  this.height / this.countCellsHeight;
    }

    public void loadGameBoard(int width, int height, Block[][] minesField) {
        this.countCellsWidth = width;
        this.countCellsHeight = height;
        this.gamePaintBoard = new DrawBlock[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                this.gamePaintBoard[x][y] = new DrawBlock(minesField[x][y], getBlockWidth(), getBlockHeight());
            }
        }
    }
}