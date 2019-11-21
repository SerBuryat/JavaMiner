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
    private final JCheckBox mouseControlCheckBox;
    private final JCheckBox keyBoardControlCheckBox;
    private final JCheckBox checkBoxEasy;
    private final JCheckBox checkBoxMiddle;
    private final JCheckBox checkBoxHard;
    private final Font font = new Font("Serif", Font.PLAIN, 40);

    public OptionsPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        JLabel labelWidthLabel = new JLabel("WIDTH:"); // labels
        JLabel labelHeightLabel = new JLabel("HEIGHT:");
        JLabel labelLevelDifficulty = new JLabel("DIFFICULTY LEVEL: ");

        this.fieldWidthTextField = this.createTextFieldWithCharLimit(2); // limited text fields. 2 -> only 2 chars in text field
        this.fieldHeightTextField = this.createTextFieldWithCharLimit(2);

        this.checkBoxEasy = new JCheckBox("EASY");
        this.checkBoxEasy.setSelected(true);
        this.checkBoxMiddle = new JCheckBox("MIDDLE");
        this.checkBoxHard = new JCheckBox("HARD");
        this.checkBoxEasy.addActionListener(action -> {
            if(checkBoxEasy.isSelected()) {
                mainWindow.getGameController().getGameParameters().setLevelDifficulty(LevelDifficulty.EASY);
                this.checkBoxMiddle.setSelected(false);
                this.checkBoxHard.setSelected(false);
            }
        });
        this.checkBoxMiddle.addActionListener(action -> {
            if(checkBoxMiddle.isSelected()) {
                mainWindow.getGameController().getGameParameters().setLevelDifficulty(LevelDifficulty.MIDDLE);
                this.checkBoxEasy.setSelected(false);
                this.checkBoxHard.setSelected(false);
            }
        });
        this.checkBoxHard.addActionListener(action -> {
            if(checkBoxHard.isSelected()) {
                mainWindow.getGameController().getGameParameters().setLevelDifficulty(LevelDifficulty.HARD);
                this.checkBoxEasy.setSelected(false);
                this.checkBoxMiddle.setSelected(false);
            }
        });

        JPanel checkBoxes = new JPanel(new GridLayout());
        checkBoxes.add(this.checkBoxEasy);
        checkBoxes.add(this.checkBoxMiddle);
        checkBoxes.add(this.checkBoxHard);

        this.mouseControlCheckBox = new JCheckBox("Mouse Control");// checkBoxes
        this.mouseControlCheckBox.setSelected(true);
        this.keyBoardControlCheckBox = new JCheckBox("KeyBoardControl");

        this.mouseControlCheckBox.addActionListener(action -> {
            if(this.mouseControlCheckBox.isSelected())
                this.keyBoardControlCheckBox.setSelected(false);
        }); // if one check box selected another !selected
        this.keyBoardControlCheckBox.addActionListener(action -> {
            if(this.keyBoardControlCheckBox.isSelected())
                this.mouseControlCheckBox.setSelected(false);
        });

        JButton applyOptionsButton = new JButton("APPLY"); // buttons
        applyOptionsButton.addActionListener(action -> this.applyButtonAction());

        JButton cancelOptionsButton = new JButton("CANCEL");
        cancelOptionsButton.addActionListener(action -> { // back to menu
            mainWindow.loadPanelToMainWindow(new MenuPanel(mainWindow));
        });

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
        GameParameters newGameParameters = new GameParameters(width, height, LevelDifficulty.EASY);

        if(this.checkBoxEasy.isSelected()) {
            newGameParameters.setLevelDifficulty(LevelDifficulty.EASY);
        }
        if(this.checkBoxMiddle.isSelected()) {
            newGameParameters.setLevelDifficulty(LevelDifficulty.MIDDLE);
        }
        if(this.checkBoxHard.isSelected()) {
            newGameParameters.setLevelDifficulty(LevelDifficulty.HARD);
        }

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
