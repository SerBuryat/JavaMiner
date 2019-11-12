package java_miner_package.view.menu;


import javax.swing.*;

public class MenuExitButton extends JButton {

    MenuExitButton() {
        this.setText("Exit Game");
        this.addActionListener(action -> {
            System.exit(1);
        });
    }
}
