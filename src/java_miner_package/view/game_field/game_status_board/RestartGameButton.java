package java_miner_package.view.game_field.game_status_board;

import java_miner_package.controller.GameController;

import javax.swing.*;

public class RestartGameButton extends JButton {

    public RestartGameButton() {
        this.setText("Restart game");
        this.addActionListener(action -> {
            GameController.GAME_CONTROLLER.start(); // restart
        });
    }
}
