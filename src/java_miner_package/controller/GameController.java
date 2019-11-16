package java_miner_package.controller;

import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.view.game_panel.game_paint_board_panel.DrawingCell;
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
        this.gameModel.loadMinesField();
        this.loadGamePaintBoard();
    }

    private void loadGamePaintBoard() {
        this.mainWindow.getGamePaintBoard().loadPaintBoard(this.gameModel.getMinesField());
        this.repaintGamePaintBoard();
    }

    private void repaintGamePaintBoard() {
        this.mainWindow.getGamePaintBoard().repaint();
    }

    void openCell(Point p) {
        for(DrawingCell[] arr : this.mainWindow.getGamePaintBoard().getPaintBoardField()) {
            for(DrawingCell drawingCell : arr) {
                if(drawingCell.isPointInCellBounds(p)) {
                    this.gameModel.openCell(drawingCell.getCell());
                }
            }
        }
        this.repaintGamePaintBoard();
    }

    void openAllCells() {
        this.gameModel.openAllCells();
        this.repaintGamePaintBoard();
    }

    void setFlag(Point p) {
        for(DrawingCell[] arr : this.mainWindow.getGamePaintBoard().getPaintBoardField()) {
            for(DrawingCell drawingCell : arr) {
                if(drawingCell.isPointInCellBounds(p)) {
                    this.gameModel.setFlagOnCell(drawingCell.getCell());
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
