package java_miner_package.controller;

import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.view.game_panel.game_paint_board_panel.DrawCell;
import java_miner_package.view.MainWindow;

import java.awt.*;


public class GameController {
    private final MainWindow mainWindow;
    private GameParameters gameParameters;
    private final GameModel gameModel;

    public GameController(GameModel gameModel, GameParameters gameParameters) {
        this.gameModel = gameModel;
        this.gameParameters = gameParameters;
        this.mainWindow = new MainWindow(this, this.gameModel); // create game window (frame)
    }

    public void startGame() {
        this.setGameParameters(this.gameParameters);
        this.gameModel.createMinesField();
        this.loadGamePaintBoard();
    }

    private void loadGamePaintBoard() {
        int width = this.gameParameters.getCellsCountWidth();
        int height = this.gameParameters.getCellsCountHeight();
        this.mainWindow.getGamePaintBoard().loadPaintBoard(width, height, this.gameModel.getMinesField());
        this.repaintGamePaintBoard();
    }

    private void repaintGamePaintBoard() {
        this.mainWindow.getGamePaintBoard().repaint();
    }

    void openCell(Point p) {
        for(DrawCell[] arr : this.mainWindow.getGamePaintBoard().getPaintBoard()) {
            for(DrawCell drawCell : arr) {
                if(drawCell.isPointInCellBounds(p)) {
                    this.gameModel.setCellOpen(drawCell.getCell());
                }
            }
        }
        this.repaintGamePaintBoard();
    }

    void openAllCells() {
        this.gameModel.setAllCellsOpen();
        this.repaintGamePaintBoard();
    }

    void setFlag(Point p) {
        for(DrawCell[] arr : this.mainWindow.getGamePaintBoard().getPaintBoard()) {
            for(DrawCell drawCell : arr) {
                if(drawCell.isPointInCellBounds(p)) {
                    this.gameModel.setFlagOnBlock(drawCell.getCell());
                    break;
                }
            }
        }
        this.repaintGamePaintBoard();
    }

    public void setGameParameters(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
        this.gameModel.setGameParameters(gameParameters);
    }

    public GameParameters getGameParameters() {
        return this.gameParameters;
    }
}
