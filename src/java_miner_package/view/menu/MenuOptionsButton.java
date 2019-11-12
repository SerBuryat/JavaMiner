package java_miner_package.view.menu;

import java_miner_package.controller.GameController;

import javax.swing.*;

public class MenuOptionsButton extends JButton {

   public MenuOptionsButton() {
        this.setText("Options");
        this.addActionListener(action -> {
            GameController.GAME_CONTROLLER.getGameWindow().loadMenuOptions();
        });
    }
}
