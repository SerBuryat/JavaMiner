package java_miner_package.view.menu_panel;

import java_miner_package.model.LevelDifficulty;
import java_miner_package.view.MainWindow;
import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.options_panel.OptionsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MenuPanel extends JPanel  {

    public MenuPanel(MainWindow mainWindow) { // initializing game start menu

        //Panel components initializing
       JLabel menuTitle = new JLabel("Java Miner");

        JCheckBox checkBoxEasy = new JCheckBox("EASY");
        checkBoxEasy.setSelected(true);
        JCheckBox checkBoxMiddle = new JCheckBox("MIDDLE");
        JCheckBox checkBoxHard = new JCheckBox("HARD");

        ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
        checkBoxList.add(checkBoxEasy);
        checkBoxList.add(checkBoxMiddle);
        checkBoxList.add(checkBoxHard);
        for(JCheckBox checkBox : checkBoxList) {
            checkBox.addActionListener(action -> { // set level difficulty and 'turn off'  other checkBoxes
                if(checkBox.isSelected()) {
                    mainWindow.getGameController().getGameParameters().setLevelDifficulty(LevelDifficulty.valueOf(checkBox.getText()));
                    checkBoxList.remove(checkBox);
                    for(JCheckBox checkB : checkBoxList) {
                        checkB.setSelected(false);
                    }
                    checkBoxList.add(checkBox);
                }
            });
        }

        JButton menuStartButton = new JButton("Start game");
        JButton menuOptionsButton = new JButton("Options");
        JButton menuExitButton = new JButton("Exit");
        menuStartButton.addActionListener(action -> {
            mainWindow.getGameController().startGame();
            mainWindow.loadPanelToMainWindow(new GamePanel(mainWindow));
        });
        menuOptionsButton.addActionListener(action -> mainWindow.loadPanelToMainWindow(new OptionsPanel(mainWindow)));
        menuExitButton.addActionListener(action -> System.exit(1));

        // panel layout configs
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(menuTitle, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());

        JPanel checkBoxes = new JPanel(new GridLayout());
        checkBoxes.add(checkBoxEasy);
        checkBoxes.add(checkBoxMiddle);
        checkBoxes.add(checkBoxHard);

        buttons.add(checkBoxes, gbc);
        buttons.add(menuStartButton, gbc);
        buttons.add(menuOptionsButton, gbc);
        buttons.add(menuExitButton, gbc);

        gbc.weighty = 1;
        this.add(buttons, gbc);
    }
}
