package java_miner_package.view.menu_panel;

import java_miner_package.model.GameParameters;
import java_miner_package.view.MainWindow;
import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.options_panel.OptionsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuPanel extends JPanel  {
    private final MainWindow mainWindow;
    private final JCheckBox checkBoxEasy = new JCheckBox("EASY(10x10 field, 10 mines)");
    private final JCheckBox checkBoxMiddle = new JCheckBox("MIDDLE(16x16 field, 40 mines)");
    private final JCheckBox checkBoxHard = new JCheckBox("HARD(22x22 field, 100 mines)");

    public MenuPanel(MainWindow mainWindow) {
        // panel components initializing
        this.mainWindow = mainWindow;

        JLabel menuTitle = new JLabel("Java Miner");

        List<JCheckBox> checkBoxList = Arrays.stream(new JCheckBox[]{checkBoxEasy, checkBoxMiddle, checkBoxHard})
                .collect(Collectors.toList());
        this.levelDifficultyCheckBoxAction(checkBoxList);


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

        JPanel checkBoxes = new JPanel(new BorderLayout());
        checkBoxes.add(checkBoxEasy, BorderLayout.NORTH);
        checkBoxes.add(checkBoxMiddle, BorderLayout.CENTER);
        checkBoxes.add(checkBoxHard, BorderLayout.SOUTH);

        buttons.add(checkBoxes, gbc);
        buttons.add(menuStartButton, gbc);
        buttons.add(menuOptionsButton, gbc);
        buttons.add(menuExitButton, gbc);

        gbc.weighty = 1;
        this.add(buttons, gbc);
    }

    private void levelDifficultyCheckBoxAction(List<JCheckBox> checkBoxList) {
        checkBoxList.get(0).setSelected(true);
        checkBoxList.get(0).setEnabled(false);

        for(JCheckBox checkBox : checkBoxList) {
            checkBox.addActionListener(action -> { // create new gameParameters by .getText()
                if(checkBox.isSelected()) {
                    checkBox.setEnabled(false);// after selecting make this disable
                    Scanner scanner = new Scanner(checkBox.getText().replaceAll("[^0-9]+", " ")); // remains only numbers
                    mainWindow.getGameController().
                            setGameParameters(new GameParameters(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                                    mainWindow.getGameController().getGameParameters().getInputControlType()));
                    checkBoxList.remove(checkBox);
                    for(JCheckBox checkB : checkBoxList) { // set other !selected && enable
                        checkB.setSelected(false);
                        checkB.setEnabled(true);
                    }
                    checkBoxList.add(checkBox);
                }
            });
        }
    }
}
