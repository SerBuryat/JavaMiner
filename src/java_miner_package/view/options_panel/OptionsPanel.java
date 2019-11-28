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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OptionsPanel extends JPanel {
    private final MainWindow mainWindow;
    private final Map<Component, Component> componentPairs = new HashMap<>();
    private final JTextField fieldWidthTextField;
    private final JTextField fieldHeightTextField;
    private final List<JCheckBox> inputControlCheckBoxesList;
    private final JCheckBox mouseControlCheckBox;
    private final JCheckBox keyBoardControlCheckBox;
    private final List<JCheckBox> levelDiffCheckBoxList;
    private final Font font = new Font("Serif", Font.PLAIN, 40);

    public OptionsPanel(MainWindow mainWindow) {
        // panel and components initializing
        this.mainWindow = mainWindow;

        JLabel labelWidth = new JLabel("WIDTH:"); // labels
        JLabel labelHeight = new JLabel("HEIGHT:");
        JLabel labelLevelDifficulty = new JLabel("DIFFICULTY LEVEL: ");

        this.fieldWidthTextField = this.createTextFieldWithCharLimit(2);// textFields
        this.fieldHeightTextField = this.createTextFieldWithCharLimit(2);
        this.fieldWidthTextField.setText(String.valueOf(mainWindow.getGameController().getGameParameters().getFieldWidth()));
        this.fieldHeightTextField.setText(String.valueOf(mainWindow.getGameController().getGameParameters().getFieldHeight()));

        this.mouseControlCheckBox = new JCheckBox("Mouse Control");// controlCheckBoxes
        this.keyBoardControlCheckBox = new JCheckBox("KeyBoard Control");

        this.inputControlCheckBoxesList = Arrays
                .stream(new JCheckBox[]{this.mouseControlCheckBox, this.keyBoardControlCheckBox})
                .collect(Collectors.toList());
        this.turnOffOtherCheckBoxesIfOneSelected(this.inputControlCheckBoxesList);


        JCheckBox checkBoxEasy = new JCheckBox("EASY"); // levelDifficultyCheckBoxes
        JCheckBox checkBoxMiddle = new JCheckBox("MIDDLE");
        JCheckBox checkBoxHard = new JCheckBox("HARD");

        this.levelDiffCheckBoxList = Arrays.stream(new JCheckBox[]{checkBoxEasy, checkBoxMiddle, checkBoxHard})
                .collect(Collectors.toList());
        this.turnOffOtherCheckBoxesIfOneSelected(this.levelDiffCheckBoxList);

        JPanel checkBoxes = this.createLevelDifficultyCheckBoxesPanel(this.levelDiffCheckBoxList);
        JPanel infoPanel = this.createLevelDifficultyDescriptionPanel(LevelDifficulty.values());

        JButton applyOptionsButton = new JButton("APPLY"); // Buttons
        JButton cancelOptionsButton = new JButton("CANCEL");
        applyOptionsButton.addActionListener(action -> this.applyButtonAction());
        cancelOptionsButton.addActionListener(action -> { // back to menu
            mainWindow.loadPanelToMainWindow(new MenuPanel(mainWindow));
        });

        // panel layout configs
        // left argument -> to left cell of grid table ... right -> to right
        this.addComponentsInGridTableByPairs(labelWidth, this.fieldWidthTextField);
        this.addComponentsInGridTableByPairs(labelHeight, this.fieldHeightTextField);
        this.addComponentsInGridTableByPairs(labelLevelDifficulty, checkBoxes);
        this.addComponentsInGridTableByPairs(new JLabel("INFO: "), infoPanel);
        this.addComponentsInGridTableByPairs(mouseControlCheckBox, keyBoardControlCheckBox);
        this.addComponentsInGridTableByPairs(applyOptionsButton, cancelOptionsButton);

        this.setLayout(new GridLayout(this.componentPairs.size(), 2));

        this.setUpFontForComponents(this.getComponents());
    }

    private void applyButtonAction() {
        // load default size if text fields -> ""
        int width = (this.fieldWidthTextField.getText().equals(""))
                ? this.mainWindow.getGameController().getGameParameters().getFieldWidth()
                : Integer.parseInt(this.fieldWidthTextField.getText());
        int height = (this.fieldHeightTextField.getText().equals(""))
                ? this.mainWindow.getGameController().getGameParameters().getFieldHeight()
                : Integer.parseInt(this.fieldHeightTextField.getText());
        if(!(this.checkFieldSizeNumbers(width, height))) {
            return;
        }

        LevelDifficulty levelDifficulty = this.getLevelDifficulty(this.levelDiffCheckBoxList);
        InputTypeControl inputTypeControl = getInputTypeControl(this.inputControlCheckBoxesList);

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

    private void turnOffOtherCheckBoxesIfOneSelected(List<JCheckBox> checkBoxList) {
        checkBoxList.get(0).setSelected(true);
        checkBoxList.get(0).setEnabled(false);

        for(JCheckBox checkBox : checkBoxList) {
            checkBox.addActionListener(action -> {
                if(checkBox.isSelected()) {
                    checkBox.setEnabled(false);
                    checkBoxList.remove(checkBox);
                    for(JCheckBox checkB : checkBoxList) {
                        checkB.setSelected(false);
                        checkB.setEnabled(true);
                    }
                    checkBoxList.add(checkBox);
                }
            });
        }
    }

    private JPanel createLevelDifficultyDescriptionPanel(LevelDifficulty[] difficulties) {
        JPanel panel = new JPanel(new GridLayout());

        for(LevelDifficulty levelDifficulty : difficulties) {
            panel.add(new JLabel(levelDifficulty.getDescription()));
        }

        return panel;
    }

    private JPanel createLevelDifficultyCheckBoxesPanel(List<JCheckBox> checkBoxList) {
        JPanel panel = new JPanel(new GridLayout());

        for(JCheckBox checkBox : checkBoxList)
            panel.add(checkBox);

        return panel;
    }

    private LevelDifficulty getLevelDifficulty(List<JCheckBox> levelDiffCheckBoxList) {
        LevelDifficulty levelDifficulty = LevelDifficulty.EASY;
        for(JCheckBox levelDiffCheckBox : levelDiffCheckBoxList) {
            if(levelDiffCheckBox.isSelected()) {
                levelDifficulty = LevelDifficulty.valueOf(levelDiffCheckBox.getText());
                break;
            }
        }
        return levelDifficulty;
    }

    private InputTypeControl getInputTypeControl(List<JCheckBox> checkBoxList) {
        InputTypeControl inputTypeControl = null;

        for(JCheckBox checkBox : checkBoxList) {
            if(checkBox.isSelected() && checkBox == this.mouseControlCheckBox)
                inputTypeControl = new MouseControl(this.mainWindow.getGameController());
            if(checkBox.isSelected() && checkBox == this.keyBoardControlCheckBox)
                inputTypeControl = new KeyBoardControl(this.mainWindow.getGameController());
        }

        return inputTypeControl;
    }
}
