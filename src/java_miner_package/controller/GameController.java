package java_miner_package.controller;

import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.model.Player;
import java_miner_package.model.Block;
import java_miner_package.view.game_field.game_board.GameBoard;
import java_miner_package.view.game_field.game_status_board.GameStatusBoard;
import java_miner_package.view.main_frame.GameWindow;

import java.awt.*;


public class GameController {
    public static final GameController GAME_CONTROLLER = new GameController();
    private GameWindow gameWindow;
    private GameParameters gameParameters;
    private GameModel gameModel;
    private Player player;

    public GameController() {
        this.gameParameters = new GameParameters(); // default game parameters (15 x 15 table , 25 mines) or can create manually
    }

    public void loadGameWindow() { // initializing game parameters
        this.gameWindow = new GameWindow();
    }

    public void gameInitialize() {
        this.gameModel = new GameModel(this.gameParameters, getGameBoard().getMinesField());
        this.player = new Player(this.gameParameters);
        this.loadGameBoardWithBlocks();
        this.gameModel.gameStart();
        this.getGameStatusBoard().setFlagsCount(this.gameModel.getFlagsCount());
        this.getGameStatusBoard().setBlocksCount(this.gameModel.getBlocksCount());
    }

    public GameParameters getGameParameters() {
        return this.gameParameters;
    }

    public GameBoard getGameBoard() {
        return this.gameWindow.getGameField().getGameBoard();
    }

    public void paintGameBoard(Graphics g, int fieldWidth, int fieldHeight, int blockWidth, int blockHeight, Block[][] board) { // draw gameBoard
        for(int x = 0; x < fieldWidth; x++) {
            for(int y = 0; y < fieldHeight; y++) {
                board[x][y].paintBlock(g, blockWidth, blockHeight);
            }
        }
    }

    public void openBlock(Point p) {
        for(Block[] arr : this.getGameBoard().getMinesField()) {
            for(Block block : arr) {
                if(block.isPointInBlockBounds(p)) {
                    this.gameModel.setBlockOpen(block);
                }
            }
        }
        this.getGameStatusBoard().setBlocksCount(this.gameModel.getBlocksCount());
        this.repaintGameBoard();
    }

    public void openAllBlocks() {
        this.gameModel.setAllBlocksOpen();
        this.getGameStatusBoard().setBlocksCount(this.gameModel.getBlocksCount());
        this.repaintGameBoard();
    }

    public void setFlag(Point p) {
        for(Block[] arr : this.getGameBoard().getMinesField()) {
            for(Block block : arr) {
                if(block.isPointInBlockBounds(p)) {
                    this.gameModel.setFlagOnBlock(block);
                    break;
                }
            }
        }
        this.getGameStatusBoard().setFlagsCount(this.gameModel.getFlagsCount());
        this.repaintGameBoard();
    }

    public void repaintGameBoard() {
       this.getGameBoard().repaint();
    }

    private void loadGameBoardWithBlocks() {
        this.getGameBoard().fillGameBoardWithBlocks();
        this.repaintGameBoard();
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public GameStatusBoard getGameStatusBoard() {
        return this.getGameWindow().getGameField().getGameStatusBoard();
    }
}
