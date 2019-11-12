package java_miner_package.controller;

import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.view.game_field.game_board.GameBoard;
import java_miner_package.view.game_field.game_board.DrawBlock;
import java_miner_package.view.game_field.game_status_board.GameStatusBoard;
import java_miner_package.view.main_frame.GameWindow;

import java.awt.*;


public class GameController {
    public static final GameController GAME_CONTROLLER = new GameController();
    private GameWindow gameWindow;
    private GameParameters gameParameters;
    private GameModel gameModel;

    public GameController() {
        this.gameParameters = new GameParameters(); // default game parameters (15 x 15 table , 25 mines) or can create manually
    }

    public void loadGameWindow() { // load main frame -> game window
        this.gameWindow = new GameWindow();
    }

    public void gameInitialize() { // initializing game parameters
        this.gameModel = new GameModel(this.gameParameters);
        this.gameModel.gameStart();
        this.loadGameBoard(); // load game board
        this.getGameStatusBoard().setFlagsCount(this.gameModel.getFlagsCount());
        this.getGameStatusBoard().setBlocksCount(this.gameModel.getBlocksCount());
        this.getGameStatusBoard().setMinesCount(this.gameModel.getMinesCount());
    }


    public void paintGameBoard(Graphics g, int fieldWidth, int fieldHeight, int blockWidth, int blockHeight, DrawBlock[][] board) { // draw gameBoard
        for(int x = 0; x < fieldWidth; x++) {
            for(int y = 0; y < fieldHeight; y++) {
                board[x][y].paintBlock(g, blockWidth, blockHeight);
            }
        }
    }

    private void repaintGameBoard() {
        this.getGameBoard().repaint();
    }

    private void loadGameBoard() {
        int width = this.gameModel.getFieldWidthLength();
        int height = this.gameModel.getFieldHeightLength();
        this.getGameBoard().loadGameBoard(width, height, this.getGameModel().getMinesField());
    }

    private GameBoard getGameBoard() {
        return this.gameWindow.getGameField().getGameBoard();
    }


    void openBlock(Point p) {
        for(DrawBlock[] arr : this.getGameBoard().getGamePaintBoard()) {
            for(DrawBlock drawBlock : arr) {
                if(drawBlock.isPointInBlockBounds(p)) {
                    this.gameModel.setBlockOpen(drawBlock.getBlock());
                }
            }
        }
        this.getGameStatusBoard().setBlocksCount(this.gameModel.getBlocksCount());
        this.repaintGameBoard();
    }

    void openAllBlocks() {
        this.gameModel.setAllBlocksOpen();
        this.getGameStatusBoard().setBlocksCount(this.gameModel.getBlocksCount());
        this.repaintGameBoard();
    }

    void setFlag(Point p) {
        for(DrawBlock[] arr : this.getGameBoard().getGamePaintBoard()) {
            for(DrawBlock drawBlock : arr) {
                if(drawBlock.isPointInBlockBounds(p)) {
                    this.gameModel.setFlagOnBlock(drawBlock.getBlock());
                    break;
                }
            }
        }
        this.getGameStatusBoard().setFlagsCount(this.gameModel.getFlagsCount());
        this.repaintGameBoard();
    }


    public GameWindow getGameWindow() {
        return gameWindow;
    }

    private GameStatusBoard getGameStatusBoard() {
        return this.getGameWindow().getGameField().getGameStatusBoard();
    }

    private GameModel getGameModel() {
        return gameModel;
    }

    public GameParameters getGameParameters() {
        return this.gameParameters;
    }

    public void setGameParameters(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
    }
}
