package java_miner_package.controller.input_control;

import java_miner_package.controller.GameController;
import java_miner_package.view.game_paint_board.CellPointer;
import java_miner_package.view.game_paint_board.GamePaintBoard;

public abstract class InputTypeControl {
    GameController gameController;
    CellPointer cellPointer;

    InputTypeControl(GameController gameController) {
        this.gameController = gameController;
    }

    public void setCellPointer(CellPointer cellPointer) {
        this.cellPointer = cellPointer;
    }

    public abstract void addControlToGamePaintBoard(GamePaintBoard gamePaintBoard);
}
