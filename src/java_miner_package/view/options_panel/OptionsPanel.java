package java_miner_package.view.options_panel;

import java_miner_package.MessageToUser;
import java_miner_package.controller.input_control.InputTypeControl;
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
    private final Font font = new Font("Serif", Font.PLAIN, 40);

    public OptionsPanel(MainWindow mainWindow) {
        // panel and components initializing
        this.mainWindow = mainWindow;

        JLabel labelWidthLabel = new JLabel("WIDTH:"); // labels
        JLabel labelHeightLabel = new JLabel("HEIGHT:");
        JLabel labelLevelDifficulty = new JLabel("DIFFICULTY LEVEL: ");

        this.fieldWidthTextField = this.createTextFieldWithCharLimit(2); // limited text fields. 2 -> only 2 chars in text field
        this.fieldHeightTextField = this.createTextFieldWithCharLimit(2);
        this.fieldWidthTextField.setText(String.valueOf(mainWindow.getGameController().getGameParameters().getFieldWidth()));
        this.fieldHeightTextField.setText(String.valueOf(mainWindow.getGameController().getGameParameters().getFieldHeight()));

        this.mouseControlCheckBox = new JCheckBox("Mouse Control");// checkBoxes
        this.mouseControlCheckBox.setSelected(true);
        this.mouseControlCheckBox.setEnabled(false);
        this.keyBoardControlCheckBox = new JCheckBox("KeyBoardControl");

        this.inputControlCheckBoxesList = new ArrayList<>();
        this.inputControlCheckBoxesList.add(this.mouseControlCheckBox);
        this.inputControlCheckBoxesList.add(this.keyBoardControlCheckBox);
        for(JCheckBox checkBox : inputControlCheckBoxesList) {
            checkBox.addActionListener(action -> { // set level difficulty by checkBox text and 'turn off' others
                if(checkBox.isSelected()) {
                    checkBox.setEnabled(false);
                    inputControlCheckBoxesList.remove(checkBox);
                    for(JCheckBox checkB : inputControlCheckBoxesList) {
                        checkB.setSelected(false);
                        checkB.setEnabled(true);
                    }
                    inputControlCheckBoxesList.add(checkBox);
                }
            });
        }

        JCheckBox checkBoxEasy = new JCheckBox("EASY");
        checkBoxEasy.setSelected(true);
        checkBoxEasy.setEnabled(false);
        JCheckBox checkBoxMiddle = new JCheckBox("MIDDLE");
        JCheckBox checkBoxHard = new JCheckBox("HARD");
        this.levelDiffCheckBoxList = new ArrayList<>();
        this.levelDiffCheckBoxList.add(checkBoxEasy);
        this.levelDiffCheckBoxList.add(checkBoxMiddle);
        this.levelDiffCheckBoxList.add(checkBoxHard);
        for(JCheckBox checkBox : levelDiffCheckBoxList) {
            checkBox.addActionListener(action -> { // set level difficulty by checkBox text and 'turn off'
                if(checkBox.isSelected()) {
                    mainWindow.getGameController().getGameParameters().setLevelDifficulty(LevelDifficulty.valueOf(checkBox.getText()));
                    checkBox.setEnabled(false);
                    levelDiffCheckBoxList.remove(checkBox);
                    for(JCheckBox checkB : levelDiffCheckBoxList) {
                        checkB.setSelected(false);
                        checkB.setEnabled(true);
                    }
                    levelDiffCheckBoxList.add(checkBox);
                }
            });
        }

        JPanel checkBoxes = new JPanel(new GridLayout());
        checkBoxes.add(checkBoxEasy);
        checkBoxes.add(checkBoxMiddle);
        checkBoxes.add(checkBoxHard);

        JPanel infoPanel = new JPanel(new GridLayout());
        infoPanel.add(new JLabel(LevelDifficulty.EASY.getDescription()));
        infoPanel.add(new JLabel(LevelDifficulty.MIDDLE.getDescription()));
        infoPanel.add(new JLabel(LevelDifficulty.HARD.getDescription()));

        JButton applyOptionsButton = new JButton("APPLY");
        JButton cancelOptionsButton = new JButton("CANCEL");
        applyOptionsButton.addActionListener(action -> this.applyButtonAction());
        cancelOptionsButton.addActionListener(action -> { // back to menu
            mainWindow.loadPanelToMainWindow(new MenuPanel(mainWindow));
        });

        // panel layout configs
        this.addComponentsInGridTableByPairs(labelWidthLabel, this.fieldWidthTextField); // left argument -> to left cell of grid table ... right -> to right
        //noinspection SuspiciousNameCombination
        this.addComponentsInGridTableByPairs(labelHeightLabel, this.fieldHeightTextField);
        this.addComponentsInGridTableByPairs(labelLevelDifficulty, checkBoxes);
        this.addComponentsInGridTableByPairs(new JLabel("INFO: "), infoPanel);
        this.addComponentsInGridTableByPairs(this.mouseControlCheckBox, this.keyBoardControlCheckBox);
        this.addComponentsInGridTableByPairs(applyOptionsButton, cancelOptionsButton);

        this.setLayout(new GridLayout(this.componentPairs.size(), 2));

        this.setUpFontForComponents(this.getComponents());
    }

    private void applyButtonAction() {
        int width = (this.fieldWidthTextField.getText().equals(""))
                ? this.mainWindow.getGameController().getGameParameters().getFieldWidth()
                : Integer.parseInt(this.fieldWidthTextField.getText());
        int height = (this.fieldHeightTextField.getText().equals(""))
                ? this.mainWindow.getGameController().getGameParameters().getFieldHeight()
                : Integer.parseInt(this.fieldHeightTextField.getText());

        if(!(this.checkFieldSizeNumbers(width, height))) {
            return;
        }

        LevelDifficulty levelDifficulty = null;
        for(JCheckBox levelDiffCheckBox : this.levelDiffCheckBoxList) {
            if(levelDiffCheckBox.isSelected()) {
                levelDifficulty = LevelDifficulty.valueOf(levelDiffCheckBox.getText());
                break;
            }
        }

        InputTypeControl inputTypeControl = null;
        if(this.mouseControlCheckBox.isSelected())
            inputTypeControl = new MouseControl(mainWindow.getGameController());
        if(this.keyBoardControlCheckBox.isSelected())
            inputTypeControl = new KeyBoardControl(mainWindow.getGameController());

        GameParameters newGameParameters = new GameParameters(width, height, levelDifficulty, inputTypeControl);

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

    private boolean checkFieldSizeNumbers(int width, int height) {
        if(width < 10 || height < 10) {
            MessageToUser.getMessage("Numbers from 10 to 30 available");
            return false;
        }
        if(width > 30 || height > 30) {
            MessageToUser.getMessage("Numbers from 10 to 30 available");
            return false;
        }
        if(Math.abs(width-height) > 5) {
            MessageToUser.getMessage("Width and Height range must be <= 5");
            return false;
        }

        return true;
    }
}
