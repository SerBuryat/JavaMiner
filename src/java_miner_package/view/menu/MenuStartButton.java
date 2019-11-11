package java_miner_package.view.menu;

import java_miner_package.controller.GameController;

import javax.swing.*;

public class MenuStartButton extends JButton {

    public MenuStartButton() {
        this.setText("Start Game");
        this.addActionListener(action -> {
            this.hideMenuAndLoadGameField();
            GameController.GAME_CONTROLLER.gameInitialize();
        });
    }

    private void hideMenuAndLoadGameField() {
        GameController.GAME_CONTROLLER.getGameWindow().hideGameMenu();
        GameController.GAME_CONTROLLER.getGameWindow().loadGameField();
    }
}
