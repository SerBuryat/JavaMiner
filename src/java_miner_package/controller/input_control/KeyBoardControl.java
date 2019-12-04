package java_miner_package.controller.input_control;

import java_miner_package.controller.Direction;
import java_miner_package.controller.GameController;
import java_miner_package.view.game_paint_board.GamePaintBoard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardControl extends InputTypeControl implements KeyListener{

    public KeyBoardControl(GameController gameController) {
        super(gameController);
    }

    @Override
    public void addControlToGamePaintBoard(GamePaintBoard gamePaintBoard) {
        gamePaintBoard.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        switch (keycode) {
            case KeyEvent.VK_LEFT:
                super.gameController.moveCellPointer(super.cellPointer, Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                super.gameController.moveCellPointer(super.cellPointer, Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                super.gameController.moveCellPointer(super.cellPointer, Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                super.gameController.moveCellPointer(super.cellPointer, Direction.DOWN);
                break;
            case KeyEvent.VK_SPACE:
                super.gameController.openCell(this.cellPointer);
                break;
            case KeyEvent.VK_F:
                super.gameController.setFlag(cellPointer);
                break;
        }
    }

    ////////////////////////////////////////////

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}