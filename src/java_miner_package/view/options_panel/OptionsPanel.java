package java_miner_package.view.options_panel;

import java_miner_package.controller.input_control.KeyBoardControl;
import java_miner_package.controller.input_control.MouseControl;
import java_miner_package.model.GameParameters;
import java_miner_package.model.LevelDifficulty;
import java_miner_package.view.MainWindow;
import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.menu_panel.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptionsPanel extends JPanel {
    private final MainWindow mainWindow;
    private final Map<Component, Component> componentPairs = new HashMap<>();
    private final JTextField fieldWidthTextField;
    private final JTextField fieldHeightTextField;
    private final ArrayList<JCheckBox> inputControlCheckBoxesList;
    private final JCheckBox mouseControlCheckBox;
    private final JCheckBox keyBoardControlCheckBox;
    private final ArrayList<JCheckBox> levelDiffCheckBoxList;
    private final JCheckBox checkBoxEasy;
    private final JCheckBox checkBoxMiddle;
    private final JCheckBox checkBoxHard;
    private final Font font = new Font("Serif", Font.PLAIN, 40);

    public OptionsPanel(MainWindow mainWindow) {
        // panel components initializing
        this.mainWindow = mainWindow;

        JLabel labelWidthLabel = new JLabel("WIDTH:"); // labels
        JLabel labelHeightLabel = new JLabel("HEIGHT:");
        JLabel labelLevelDifficulty = new JLabel("DIFFICULTY LEVEL: ");

        this.fieldWidthTextField = this.createTextFieldWithCharLimit(2); // limited text fields. 2 -> only 2 chars in text field
        this.fieldHeightTextField = this.createTextFieldWithCharLimit(2);

        this.mouseControlCheckBox = new JCheckBox("Mouse Control");// checkBoxes
        this.mouseControlCheckBox.setSelected(true);
        this.keyBoardControlCheckBox = new JCheckBox("KeyBoardControl");

        this.inputControlCheckBoxesList = new ArrayList<>();
        this.inputControlCheckBoxesList.add(this.mouseControlCheckBox);
        this.inputControlCheckBoxesList.add(this.keyBoardControlCheckBox);
        for(JCheckBox checkBox : inputControlCheckBoxesList) {
            checkBox.addActionListener(action -> { // set level difficulty by checkBox text and 'turn off'
                if(checkBox.isSelected()) {
                    inputControlCheckBoxesList.remove(checkBox);
                    for(JCheckBox checkB : inputControlCheckBoxesList) {
                        checkB.setSelected(false);
                    }
                    inputControlCheckBoxesList.add(checkBox);
                }
            });
        }

        this.checkBoxEasy = new JCheckBox("EASY");
        this.checkBoxEasy.setSelected(true);
        this.checkBoxMiddle = new JCheckBox("MIDDLE");
        this.checkBoxHard = new JCheckBox("HARD");
        this.levelDiffCheckBoxList = new ArrayList<>();
        this.levelDiffCheckBoxList.add(this.checkBoxEasy);
        this.levelDiffCheckBoxList.add(this.checkBoxMiddle);
        this.levelDiffCheckBoxList.add(this.checkBoxHard);
        for(JCheckBox checkBox : levelDiffCheckBoxList) {
            checkBox.addActionListener(action -> { // set level difficulty by checkBox text and 'turn off'
                if(checkBox.isSelected()) {
                    mainWindow.getGameController().getGameParameters().setLevelDifficulty(LevelDifficulty.valueOf(checkBox.getText()));
                    levelDiffCheckBoxList.remove(checkBox);
                    for(JCheckBox checkB : levelDiffCheckBoxList) {
                        checkB.setSelected(false);
                    }
                    levelDiffCheckBoxList.add(checkBox);
                }
            });
        }

        JPanel checkBoxes = new JPanel(new GridLayout());
        checkBoxes.add(this.checkBoxEasy);
        checkBoxes.add(this.checkBoxMiddle);
        checkBoxes.add(this.checkBoxHard);

        JButton applyOptionsButton = new JButton("APPLY");
        JButton cancelOptionsButton = new JButton("CANCEL");
        applyOptionsButton.addActionListener(action -> this.applyButtonAction());
        cancelOptionsButton.addActionListener(action -> { // back to menu
            mainWindow.loadPanelToMainWindow(new MenuPanel(mainWindow));
        });

        // panel layout configs
        this.addComponentsInGridTableByPairs(labelWidthLabel, this.fieldWidthTextField); // left argument -> to left cell of grid table ... right -> to right
        this.addComponentsInGridTableByPairs(labelHeightLabel, this.fieldHeightTextField);
        this.addComponentsInGridTableByPairs(labelLevelDifficulty, checkBoxes);
        this.addComponentsInGridTableByPairs(this.mouseControlCheckBox, this.keyBoardControlCheckBox);
        this.addComponentsInGridTableByPairs(applyOptionsButton, cancelOptionsButton);

        this.setLayout(new GridLayout(this.componentPairs.size(), 2));

        this.setUpFontForComponents(this.getComponents());
    }

    private void applyButtonAction() {
        int width = Integer.parseInt(this.fieldWidthTextField.getText()); // board with
        int height = Integer.parseInt(this.fieldHeightTextField.getText()); // board height
        LevelDifficulty levelDifficulty = LevelDifficulty.EASY;
        for(JCheckBox levelDiffCheckBox : this.levelDiffCheckBoxList) { // set level difficulty
            if(levelDiffCheckBox.isSelected()) {
                levelDifficulty = LevelDifficulty.valueOf(levelDiffCheckBox.getText());
                break;
            }
        }
        GameParameters newGameParameters = new GameParameters(width, height, levelDifficulty);

        if(this.mouseControlCheckBox.isSelected())
            newGameParameters.setInputControlType(new MouseControl(mainWindow.getGameController()));
        if(this.keyBoardControlCheckBox.isSelected())
            newGameParameters.setInputControlType(new KeyBoardControl(mainWindow.getGameController()));

        mainWindow.getGameController().setGameParameters(newGameParameters); // load new game parameters
        mainWindow.getGameController().startGame();// initializing game
        mainWindow.loadPanelToMainWindow(new GamePanel(mainWindow));// load game field
    }

    private JTextField createTextFieldWithCharLimit(int limit) {
        JTextField textField = new JTextField();
        textField.setDocument(new JTextFieldLogic(limit));
        return textField;
    }

    private void setUpFontForComponents(Component[] components) { // set font for every component via StreamAPI
        Arrays.stream(components).forEach(component -> component.setFont(this.font));
    }

    private void addComponentsInGridTableByPairs(Component left, Component right) {
        this.componentPairs.put(left, right);
        this.add(left);
        this.add(right);
    }
}
