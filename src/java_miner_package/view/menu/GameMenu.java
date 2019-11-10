package java_miner_package.view.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameMenu extends JPanel  {
    private MenuStartButton menuStartButton;
    private JButton buttonGameOptions;
    private JButton buttonGameExit;
    private JLabel menuTitle;

   public GameMenu() { // initializing game start menu

        this.menuStartButton = new MenuStartButton();
        this.buttonGameOptions = new JButton("Options");
        this.buttonGameExit = new ExitGameButton();
        this.menuTitle = new JLabel("Java Miner");


        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;// set title on the top
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(menuTitle, gbc);

        gbc.anchor = GridBagConstraints.CENTER; // set buttons on the center
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.add(menuStartButton, gbc);
        buttons.add(buttonGameOptions, gbc);
        buttons.add(buttonGameExit, gbc);
        gbc.weighty = 1;
        this.add(buttons, gbc);
    }
}
