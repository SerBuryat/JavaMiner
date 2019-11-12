package java_miner_package.view.game_field.game_status_board;

import java_miner_package.controller.GameController;

import javax.swing.*;

public class BackToMenuButton extends JButton {

    public BackToMenuButton() {
        this.setText("Back to menu");
        this.addActionListener(action -> {
            GameController.GAME_CONTROLLER.getGameWindow().loadGameMenu();
        });
    }
}
