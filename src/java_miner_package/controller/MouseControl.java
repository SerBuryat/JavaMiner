package java_miner_package.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseControl implements MouseListener {
    private final GameController gameController;

    public MouseControl(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            Point p = e.getPoint();
            this.gameController.openCell(p);
        }
        if(e.getButton() == MouseEvent.BUTTON2) {
            this.gameController.openAllCells();
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            Point p = e.getPoint();
            this.gameController.setFlag(p);
        }
    }

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
}
