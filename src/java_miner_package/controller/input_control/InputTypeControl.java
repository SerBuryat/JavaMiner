package java_miner_package.controller.input_control;

import java_miner_package.controller.GameController;
import java_miner_package.view.game_panel.game_paint_board_panel.CurrentDrawingCell;
import java_miner_package.view.game_panel.game_paint_board_panel.GamePaintBoard;

public abstract class InputTypeControl {
    GameController gameController;
    CurrentDrawingCell currentCell;

    InputTypeControl(GameController gameController) {
        this.gameController = gameController;
    }

    public void setCurrentCell(CurrentDrawingCell currentCell) {
        this.currentCell = currentCell;
    }

    public abstract void addControlToGamePaintBoard(GamePaintBoard gamePaintBoard);
}
