package java_miner_package.controller;

import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.view.game_paint_board.CellPointer;
import java_miner_package.view.game_paint_board.DrawingCell;
import java_miner_package.view.MainWindow;
import java_miner_package.view.game_paint_board.GamePaintBoard;


public class GameController {
    private final MainWindow mainWindow;
    private GamePaintBoard gamePaintBoard;
    private GameParameters gameParameters;
    private final GameModel gameModel;

    public GameController(GameModel gameModel, GameParameters gameParameters) {
        this.gameModel = gameModel;
        this.gameParameters = gameParameters;
        this.mainWindow = new MainWindow(this, this.gameModel); // create game window (frame)
    }

    public void startGame() {
        this.setGameParameters(this.gameParameters);
        this.gameModel.createGame();
        this.gamePaintBoard = new GamePaintBoard(this.mainWindow, this.gameParameters.getInputControlType());
    }

    private void repaintGamePaintBoard() {
        this.gamePaintBoard.repaint();
    }

    public void openCell(CellPointer cellPointer) {
        for(DrawingCell[] arr : this.gamePaintBoard.getPaintBoardField()) {
            for(DrawingCell drawingCell : arr) {
                if(cellPointer.getX() == drawingCell.getX() && cellPointer.getY() == drawingCell.getY()) {
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

    public void setFlag(CellPointer cellPointer) {
        for(DrawingCell[] arr : this.gamePaintBoard.getPaintBoardField()) {
            for(DrawingCell drawingCell : arr) {
                if(cellPointer.getX() == drawingCell.getX() && cellPointer.getY() == drawingCell.getY()) {
                    this.gameModel.setFlagOnCell(drawingCell.getCell());
                    break;
                }
            }
        }
        this.repaintGamePaintBoard();
    }

    public void moveCellPointerUp(CellPointer cellPointer) {
        cellPointer.moveUp();
        this.repaintGamePaintBoard();
    }

    public void moveCellPointerRight(CellPointer cellPointer) {
        cellPointer.moveRight();
        this.repaintGamePaintBoard();
    }

    public void moveCellPointerDown(CellPointer cellPointer) {
        cellPointer.moveDown();
        this.repaintGamePaintBoard();
    }

    public void moveCellPointerLeft(CellPointer cellPointer) {
        cellPointer.moveLeft();
        this.repaintGamePaintBoard();
    }

    public void moveCellPointerTo(CellPointer cellPointer, int x, int y) {
        cellPointer.moveTo(x,y);
        this.repaintGamePaintBoard();
    }

    public void setGameParameters(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
        this.gameModel.setGameParameters(gameParameters);
    }

    public GameParameters getGameParameters() {
        return this.gameParameters;
    }

    public GamePaintBoard getGamePaintBoard() {
        return gamePaintBoard;
    }
}
