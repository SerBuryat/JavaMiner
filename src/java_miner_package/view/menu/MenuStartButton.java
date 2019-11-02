package java_miner_package.view.menu;

import java_miner_package.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MenuStartButton extends JButton {

    public MenuStartButton() {
        this.setText("Start Game");
        this.addActionListener(action -> {
            GameController.GAME_CONTROLLER.getGameWindow().getGameMenu().setVisible(false);
            GameController.GAME_CONTROLLER.getGameWindow().getContentPane().add(GameController.GAME_CONTROLLER.getGameWindow().getGameField(), BorderLayout.CENTER);
        });
    }
}
