package java_miner_package.view.menu;

import java_miner_package.controller.GameController;

import javax.swing.*;

public class MenuStartButton extends JButton {

    public MenuStartButton() {
        this.setText("Start Game");
        this.addActionListener(action -> {
            GameController.GAME_CONTROLLER.getGameWindow().loadGameField();
            GameController.GAME_CONTROLLER.gameInitialize();
        });
    }
}
