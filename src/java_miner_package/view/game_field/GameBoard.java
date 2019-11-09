package java_miner_package.view.game_field;

import java_miner_package.controller.GameController;
import java_miner_package.controller.MouseControl;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private int width;
    private int height;
    private int countCellsWidth;
    private int countCellsHeight;
    private Block[][] minesField;
    private int blockArc;

    public GameBoard (int countCellsWidth, int countCellsHeight) {
        this.width = 770;//test
        this.height = 750;//test
        this.setSize(this.width, this.height);
        this.countCellsWidth = countCellsWidth;
        this.countCellsHeight = countCellsHeight;

        this.minesField = new Block[countCellsWidth][countCellsHeight]; // create the game board array
        this.fillGameBoardWithBlocks(); // fill game board

        this.blockArc = 10; // rounding every block corner on 10px

        addMouseListener(new MouseControl()); // set mouse control on this board
    }

    public void paint(Graphics g) { // drawing game board
        super.paint(g);
        GameController.GAME_CONTROLLER.paintGameBoard(g, countCellsWidth, countCellsHeight, getBlockWidth(), getBlockHeight(), this.blockArc, this.minesField);
    }

    public Block[][] getMinesField() {
        return minesField;
    }

    public int getBlockWidth() {
        return  this.width / this.countCellsWidth;
    }

    public int getBlockHeight() {
        return  this.height / this.countCellsHeight;
    }

    public void fillGameBoardWithBlocks() {
        for(int x = 0; x < countCellsWidth; x++) {
            for(int y = 0; y < countCellsHeight; y++) {
                this.minesField[x][y] = new Block(x, y, getBlockWidth(), getBlockHeight());
            }
        }
    }

}