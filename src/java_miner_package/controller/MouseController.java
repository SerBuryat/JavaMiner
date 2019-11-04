package java_miner_package.controller;

import java_miner_package.view.game_field.Block;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {
    

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();

        Block[][] blocks = GameController.GAME_CONTROLLER.getGameWindow().getGameField().getGameBoard().getBoardArr();
        for(Block[] arr : blocks) {
            for(Block block : arr) {
                if(block.isInBlockBounds((int)p.getX(), (int)p.getY())) {
                    System.out.println(block);//test which block is chosen
                    GameController.GAME_CONTROLLER.openBlock(block.getX(), block.getY(), blocks);
                }
            }
        }
        System.out.println(GameController.GAME_CONTROLLER.getGameWindow().getGameField().getGameBoard().getSize().toString());//test gameBoard panel size
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
