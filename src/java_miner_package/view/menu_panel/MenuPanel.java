package java_miner_package.view.menu_panel;

import java_miner_package.view.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuPanel extends JPanel  {

    public MenuPanel(MainWindow mainWindow) { // initializing game start menu

       JButton menuStartButton = new JButton("Start game");
        menuStartButton.addActionListener(action -> {
            mainWindow.loadGamePanel();
            mainWindow.getGameController().startGame();
        });

        JButton menuOptionsButton = new JButton("Options");
        menuOptionsButton.addActionListener(action -> mainWindow.loadOptionsPanel());

        JButton menuExitButton = new JButton("Exit");
        menuExitButton.addActionListener(action -> System.exit(1));

        JLabel menuTitle = new JLabel("Java Miner");

        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;// set title on the top
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(menuTitle, gbc);

        gbc.anchor = GridBagConstraints.CENTER; // set buttons on the center
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.add(menuStartButton, gbc); // start button action
        buttons.add(menuOptionsButton, gbc); // options button action
        buttons.add(menuExitButton, gbc); // exit button action
        gbc.weighty = 1;
        this.add(buttons, gbc);
    }
}
