package java_miner_package.view.menu_panel;

import java_miner_package.MessageToUser;
import java_miner_package.controller.input_control.InputTypeControl;
import java_miner_package.model.GameParameters;
import java_miner_package.model.LevelDifficulty;
import java_miner_package.view.MainWindow;
import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.options_panel.OptionsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuPanel extends JPanel  {
    private final MainWindow mainWindow;

    public MenuPanel(MainWindow mainWindow) {
        // panel components initializing
        this.mainWindow = mainWindow;

        JLabel menuTitle = new JLabel("Java Miner");

        JCheckBox checkBoxEasy = new JCheckBox();
        JCheckBox checkBoxMiddle = new JCheckBox();
        JCheckBox checkBoxHard = new JCheckBox();

        List<JCheckBox> checkBoxList = Arrays.stream(new JCheckBox[]{checkBoxEasy, checkBoxMiddle, checkBoxHard})
                .collect(Collectors.toList());

        Map<JCheckBox, LevelDifficulty> checkBoxLevelDifficultyMap = new HashMap<>();
        this.connectCheckBoxToLevelDifficulty(checkBoxList, checkBoxLevelDifficultyMap);

        this.setCheckBoxesAction(checkBoxList, checkBoxLevelDifficultyMap);
        this.setCheckBoxTextByLevelDifficultyDescription(checkBoxList, checkBoxLevelDifficultyMap);


        JButton menuStartButton = new JButton("Start game");
        JButton menuOptionsButton = new JButton("Options");
        JButton menuExitButton = new JButton("Exit");

        menuStartButton.addActionListener(action -> {
            if(this.isAtLeastOneCheckBoxSelected(checkBoxList)) {
                mainWindow.getGameController().startGame();
                mainWindow.loadPanelToMainWindow(new GamePanel(mainWindow));
            }
            else
                MessageToUser.getMessage("Choose, level difficulty plz!");
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

    private void setCheckBoxesAction(List<JCheckBox> checkBoxList, Map<JCheckBox,
            LevelDifficulty> checkBoxLevelDifficultyMap) {
        for (JCheckBox checkBox : checkBoxList) {
            checkBox.addActionListener(action -> {
                if(checkBox.isSelected()) {
                    checkBox.setEnabled(false);
                    this.setNewGameParameter(this.getNewGameParameter(checkBox, checkBoxLevelDifficultyMap));
                    this.setCheckBoxesSelectedOneAtSameTime(checkBoxList, checkBox);
                }
            });
        }
    }

    private void setCheckBoxesSelectedOneAtSameTime(List<JCheckBox> checkBoxList, JCheckBox checkBox) {
        checkBoxList.remove(checkBox);
        for (JCheckBox checkB : checkBoxList) {
            this.setCheckBoxNotSelectedAndEnable(checkB);
        }
        checkBoxList.add(checkBox);
    }

    private void setCheckBoxNotSelectedAndEnable(JCheckBox checkBox) {
        checkBox.setSelected(false);
        checkBox.setEnabled(true);
    }

    private void setNewGameParameter(GameParameters gameParameter) {
        this.mainWindow.getGameController().setGameParameters(gameParameter);
    }

    private GameParameters getNewGameParameter(JCheckBox checkBox, Map<JCheckBox,
            LevelDifficulty> checkBoxLevelDifficultyMap) {
        LevelDifficulty levelDifficulty = checkBoxLevelDifficultyMap.get(checkBox);

        int mineFieldWidth = levelDifficulty.getMineFieldWidth();
        int mineFieldHeight = levelDifficulty.getMineFieldHeight();
        int minesCount = levelDifficulty.getMinesCount();

        InputTypeControl inputTypeControl = this.mainWindow.getGameController().getGameParameters()
                .getInputControlType();

        return new GameParameters(mineFieldWidth,mineFieldHeight,minesCount,inputTypeControl);
    }

    private void connectCheckBoxToLevelDifficulty(List<JCheckBox> checkBoxList, Map<JCheckBox,
            LevelDifficulty> checkBoxLevelDifficultyMap) {
        for(int i = 0; i < checkBoxList.size(); i++) {
            checkBoxLevelDifficultyMap.put(checkBoxList.get(i), LevelDifficulty.values()[i]);
        }
    }

    private void setCheckBoxTextByLevelDifficultyDescription(List<JCheckBox> checkBoxList, Map<JCheckBox,
            LevelDifficulty> checkBoxLevelDifficultyMap) {
        checkBoxList.forEach(checkBox -> checkBox.setText(checkBoxLevelDifficultyMap.get(checkBox).getDescription()));
    }

    private boolean isAtLeastOneCheckBoxSelected(List<JCheckBox> checkBoxList) {
        return checkBoxList
                .stream()
                .anyMatch(checkBox -> checkBox.isSelected());
    }

}
