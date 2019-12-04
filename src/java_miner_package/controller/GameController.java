package java_miner_package.controller;

import java_miner_package.controller.input_control.InputTypeControl;
import java_miner_package.controller.input_control.MouseControl;
import java_miner_package.model.Cell;
import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;
import java_miner_package.view.game_paint_board.CellPointer;
import java_miner_package.view.game_paint_board.DrawingCell;
import java_miner_package.view.MainWindow;
import java_miner_package.view.game_paint_board.GamePaintBoard;


public class GameController {
    private final MainWindow mainWindow;
    private final GameModel gameModel;
    private GamePaintBoard gamePaintBoard;
    private GameParameters gameParameters;

    public GameController() {
        this.gameParameters = new GameParameters(new MouseControl(this)); // sets MouseControl by default
        this.gameModel = new GameModel(this.gameParameters);
        this.mainWindow = new MainWindow(this, this.gameModel);
    }

    public void startGame() {
        this.setGameParameters(this.gameParameters);
        this.gameModel.createGame();
        this.createGamePaintBoard(this.mainWindow, this.gameParameters.getInputControlType());
    }

    public void openCell(CellPointer cellPointer) {
        this.gameModel.openCell(this.getCellPointedByCellPointer(cellPointer));
        this.repaintGamePaintBoard();
    }

    public void setFlag(CellPointer cellPointer) {
        this.gameModel.setFlagOnCell(this.getCellPointedByCellPointer(cellPointer));
        this.repaintGamePaintBoard();
    }

    public void moveCellPointer(CellPointer cellPointer, Direction direction) {
        cellPointer.move(direction);
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

    private boolean isCoordinatesEquals(CellPointer cellPointer, DrawingCell drawingCell) {
        return cellPointer.getX() == drawingCell.getX() && cellPointer.getY() == drawingCell.getY();
    }

    private Cell getCellPointedByCellPointer(CellPointer cellPointer) {
        Cell cell = null;
        for(DrawingCell[] arr : this.gamePaintBoard.getPaintBoardField()) {
            for(DrawingCell drawingCell : arr) {
                if(this.isCoordinatesEquals(cellPointer, drawingCell)) {
                    cell =drawingCell.getCell();
                }
            }
        }
        return cell;
    }

    private GamePaintBoard createGamePaintBoard(MainWindow mainWindow, InputTypeControl inputTypeControl) {
        GamePaintBoard gamePaintBoard = new GamePaintBoard(mainWindow, inputTypeControl);
        this.gamePaintBoard = gamePaintBoard;
        return gamePaintBoard;
    }

    private void repaintGamePaintBoard() {
        this.gamePaintBoard.repaint();
    }
}
