package java_miner_package.controller;

import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.view.game_panel.game_mines_field_panel.GamePaintBoard;
import java_miner_package.view.game_panel.game_mines_field_panel.DrawCell;
import java_miner_package.view.game_panel.game_status_panel.GameStatusBoard;
import java_miner_package.view.MainWindow;

import java.awt.*;


public class GameController {
    public static final GameController GAME_CONTROLLER = new GameController();
    private MainWindow mainWindow;
    private GameParameters gameParameters;
    private GameModel gameModel;

    private GameController() {
        this.gameParameters = new GameParameters(); // default game parameters (15 x 15 table , 25 mines) or can create manually
    }

    public void loadMainWindow() { // create main window
        this.mainWindow = new MainWindow();
    }

    public void gameInitializing() { // initializing game parameters
        this.gameModel = new GameModel(this.gameParameters);
        this.gameModel.gameStart();
        this.loadGamePaintBoard(); // load game paint board
        this.getGameStatusBoard().setFlagsCount(this.gameModel.getFlagsCount());
        this.getGameStatusBoard().setCellsCount(this.gameModel.getCellCount());
        this.getGameStatusBoard().setMinesCount(this.gameModel.getMinesCount());
    }

    public void paintGamePaintBoard(Graphics g, int fieldWidth, int fieldHeight, int cellWidth, int cellHeight, DrawCell[][] board) { // draw gameBoard
        for(int x = 0; x < fieldWidth; x++) {
            for(int y = 0; y < fieldHeight; y++) {
                board[x][y].paintDrawCell(g, cellWidth, cellHeight);
            }
        }
    }

    private void repaintGameBoard() {
        this.getGamePaintBoard().repaint();
    }

    private void loadGamePaintBoard() {
        int width = this.gameModel.getFieldWidth();
        int height = this.gameModel.getFieldHeight();
        this.getGamePaintBoard().loadPaintBoard(width, height, this.gameModel.getMinesField());
        this.repaintGameBoard();
    }

    private GamePaintBoard getGamePaintBoard() {
        return this.mainWindow.getGamePanel().getGamePaintBoard();
    }

    void openCell(Point p) {
        for(DrawCell[] arr : this.getGamePaintBoard().getPaintBoard()) {
            for(DrawCell drawCell : arr) {
                if(drawCell.isPointInCellBounds(p)) {
                    this.gameModel.setCellOpen(drawCell.getCell());
                }
            }
        }
        this.getGameStatusBoard().setCellsCount(this.gameModel.getCellCount());
        this.repaintGameBoard();
    }

    void openAllCells() {
        this.gameModel.setAllCellsOpen();
        this.getGameStatusBoard().setCellsCount(this.gameModel.getCellCount());
        this.repaintGameBoard();
    }

    void setFlag(Point p) {
        for(DrawCell[] arr : this.getGamePaintBoard().getPaintBoard()) {
            for(DrawCell drawCell : arr) {
                if(drawCell.isPointInCellBounds(p)) {
                    this.gameModel.setFlagOnBlock(drawCell.getCell());
                    break;
                }
            }
        }
        this.getGameStatusBoard().setFlagsCount(this.gameModel.getFlagsCount());
        this.repaintGameBoard();
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    private GameStatusBoard getGameStatusBoard() {
        return this.getMainWindow().getGamePanel().getGameStatusBoard();
    }

    public GameParameters getGameParameters() {
        return this.gameParameters;
    }

    public void setGameParameters(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
    }
}
