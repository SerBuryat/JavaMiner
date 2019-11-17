package java_miner_package.controller.input_control;

import java_miner_package.controller.GameController;
import java_miner_package.view.game_panel.game_paint_board_panel.GamePaintBoard;

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
                super.currentCell.moveLeft();
                super.gameController.repaintGamePaintBoard();
                break;
            case KeyEvent.VK_RIGHT:
                super.currentCell.moveRight();
                super.gameController.repaintGamePaintBoard();
                break;
            case KeyEvent.VK_UP:
                super.currentCell.moveUp();
                super.gameController.repaintGamePaintBoard();
                break;
            case KeyEvent.VK_DOWN:
                super.currentCell.moveDown();
                super.gameController.repaintGamePaintBoard();
                break;
            case KeyEvent.VK_SPACE:
                super.gameController.openAllCells();
                break;
            case KeyEvent.VK_ENTER:
                super.gameController.openCell(currentCell);
                break;
            case KeyEvent.VK_F:
                super.gameController.setFlag(currentCell);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}