package java_miner_package.controller.input_control;

import java_miner_package.controller.GameController;
import java_miner_package.view.game_paint_board.GamePaintBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseControl extends InputTypeControl implements MouseListener, MouseMotionListener {

    public MouseControl(GameController gameController) {
        super(gameController);
    }

    @Override
    public void addControlToGamePaintBoard(GamePaintBoard gamePaintBoard) {
        gamePaintBoard.addMouseListener(this);
        gamePaintBoard.addMouseMotionListener(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        int pointX = (int)point.getX() + 15;// +15 -> accuracy correction
        int pointY = (int)point.getY() + 15;
        int x = (pointX - super.gameController.getGameParameters().getFieldWidth()) / super.cellPointer.getCellWidth();
        int y = (pointY - super.gameController.getGameParameters().getFieldHeight()) / super.cellPointer.getCellHeight();
        super.gameController.moveCellPointerTo(super.cellPointer, x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            super.gameController.openCell(this.cellPointer);
        }
        if(e.getButton() == MouseEvent.BUTTON2) {
            super.gameController.openAllCells();
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            super.gameController.setFlag(this.cellPointer);
        }
    }



    /////////////////////
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
