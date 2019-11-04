package java_miner_package.controller;

import java_miner_package.model.GameParametrs;
import java_miner_package.view.game_field.Block;
import java_miner_package.view.game_field.GameBoard;
import java_miner_package.view.main_frame.GameWindow;

import java.awt.*;


public class GameController {
    public static final GameController GAME_CONTROLLER = new GameController();
    private GameWindow gameWindow;
    private GameParametrs gameParametrs;

   public GameController() {
        gameParametrs = new GameParametrs(10,10);
    } // testing game parameters 10 x 10 table

    public void gameStart(GameParametrs gameParametrs) {
    }

    public void initialize() { // initializing game parameters
        gameWindow = new GameWindow();
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public GameParametrs getGameParameters() {
        return gameParametrs;
    }

    public void paintGameBoard(Graphics g, int fieldWidth, int fieldHeight, int blockWidth, int blockHeight, int blockArc, Block[][] board) { // draw gameBoard
        for(int x = 0; x < fieldWidth; x++) {
            for(int y = 0; y < fieldHeight; y++) {
                board[x][y].paintBlock(g, blockWidth, blockHeight, blockArc);
            }
        }
    }

    public void openBlock(int x, int y, Block[][] board) {
       board[x][y].setBlockOpen(true);
       repaintGameBoard();
    }

    public void repaintGameBoard() {
       getGameWindow().getGameField().getGameBoard().repaint();
    }

}
