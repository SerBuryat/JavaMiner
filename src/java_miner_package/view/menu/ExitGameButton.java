package java_miner_package.view.menu;


import javax.swing.*;

public class ExitGameButton extends JButton {

    ExitGameButton() {
        this.setText("Exit Game");
        this.addActionListener(action -> {
            System.exit(1);
        });
    }
}
