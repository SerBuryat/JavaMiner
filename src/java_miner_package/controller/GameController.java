package java_miner_package.controller;

import java_miner_package.controller.input_control.MouseControl;
import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.view.game_panel.game_paint_board_panel.CurrentDrawingCell;
import java_miner_package.view.game_panel.game_paint_board_panel.DrawingCell;
import java_miner_package.view.MainWindow;


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
        this.gameParameters.setInputControlType(new MouseControl(this)); // set control type (mouse, key board, etc.)
        this.setGameParameters(this.gameParameters);
        this.gameModel.createGame();
    }

    public void repaintGamePaintBoard() {
        this.mainWindow.getGamePaintBoard().repaint();
    }

    public void openCell(CurrentDrawingCell currentCell) {
        for(DrawingCell[] arr : this.mainWindow.getGamePaintBoard().getPaintBoardField()) {
            for(DrawingCell drawingCell : arr) {
                if(currentCell.getX() == drawingCell.getX() && currentCell.getY() == drawingCell.getY()) {
                    this.gameModel.openCell(drawingCell.getCell());
                }
            }
        }
        this.repaintGamePaintBoard();
    }

    public void openAllCells() {
        this.gameModel.openAllCells();
        this.repaintGamePaintBoard();
    }

    public void setFlag(CurrentDrawingCell currentCell) {
        for(DrawingCell[] arr : this.mainWindow.getGamePaintBoard().getPaintBoardField()) {
            for(DrawingCell drawingCell : arr) {
                if(currentCell.getX() == drawingCell.getX() && currentCell.getY() == drawingCell.getY()) {
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
