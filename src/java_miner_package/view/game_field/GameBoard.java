package java_miner_package.view.game_field;

import java_miner_package.controller.GameController;
import java_miner_package.controller.MouseControl;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private int countCellsWidth;
    private int countCellsHeight;
    private Block[][] board;
    private boolean isGameBoardLoad;
    private int blockArc;

    public GameBoard (int countCellsWidth, int countCellsHeight) {
        this.countCellsWidth = countCellsWidth;
        this.countCellsHeight = countCellsHeight;

        this.board = new Block[countCellsWidth][countCellsHeight]; // create the game board array

        this.isGameBoardLoad = false;

        this.blockArc = 10; // rounding every block corner on 10px

        addMouseListener(new MouseControl()); // set mouse control on this board
    }

    public void paint(Graphics g) { // drawing gameField
        super.paint(g);
        if(!(isGameBoardLoad)) // if gameBoard is not loaded -> load it with Block objects // test mode need to reform
            this.fillGameBoardWithBlocks();
        GameController.GAME_CONTROLLER.paintGameBoard(g, countCellsWidth, countCellsHeight, getBlockWidth(), getBlockHeight(), this.blockArc, this.board);
    }

    public Block[][] getBoardArr() {
        return board;
    }

    public int getBlockWidth() {
        return  (int) (this.getSize().getWidth() / this.countCellsWidth);
    }

    public int getBlockHeight() {
        return  (int) (this.getSize().getHeight() / this.countCellsHeight);
    }

    public void fillGameBoardWithBlocks() {
        for(int x = 0; x < countCellsWidth; x++) {
            for(int y = 0; y < countCellsHeight; y++) {
                this.board[x][y] = new Block(x, y, getBlockWidth(), getBlockHeight());
            }
        }
        this.isGameBoardLoad = true;
    }
}
