package java_miner_package.controller;

import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.model.Player;
import java_miner_package.view.game_field.Block;
import java_miner_package.view.game_field.GameBoard;
import java_miner_package.view.main_frame.GameWindow;

import java.awt.*;


public class GameController {
    public static final GameController GAME_CONTROLLER = new GameController();
    private GameWindow gameWindow;
    private GameParameters gameParameters;
    private GameModel gameModel;
    private Player player;

    public GameController() {
        this.gameParameters = new GameParameters(); // load default parameters
    }

    public void initialize() { // initializing game parameters
        this.gameWindow = new GameWindow();
        this.gameModel = new GameModel(this.gameParameters, getGameBoard().getMinesField());
        this.player = new Player(this.gameParameters);
    }

    public void start() {
        this.openGameField();
        this.gameModel.gameStart();
    }

    public GameParameters getGameParameters() {
        return this.gameParameters;
    }

    public GameBoard getGameBoard() {
        return this.gameWindow.getGameField().getGameBoard();
    }

    public void paintGameBoard(Graphics g, int fieldWidth, int fieldHeight, int blockWidth, int blockHeight, int blockArc, Block[][] board) { // draw gameBoard
        for(int x = 0; x < fieldWidth; x++) {
            for(int y = 0; y < fieldHeight; y++) {
                board[x][y].paintBlock(g, blockWidth, blockHeight, blockArc);
            }
        }
    }

    public void openBlock(Point p) {
       this.gameModel.setBlockOpen(p);
       this.repaintGameBoard();
    }

    public void openAllBlocks() {
        this.gameModel.setAllBlocksOpen();
        this.repaintGameBoard();
    }

    public void setFlag(Point p) {
        this.gameModel.setFlagOnBlock(p);
        this.repaintGameBoard();
    }

    public void repaintGameBoard() {
       this.getGameBoard().repaint();
    }

    public void openGameField() {
        this.gameWindow.hideGameMenu();
        this.gameWindow.showGameField();
    }

    public void createGameBoard() {
        this.getGameBoard().fillGameBoardWithBlocks();
        this.repaintGameBoard();
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }
}
