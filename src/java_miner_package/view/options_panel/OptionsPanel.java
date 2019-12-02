package java_miner_package.view.options_panel;

import java_miner_package.MessageToUser;
import java_miner_package.controller.input_control.InputTypeControl;
import java_miner_package.controller.input_control.KeyBoardControl;
import java_miner_package.controller.input_control.MouseControl;
import java_miner_package.model.GameParameters;
import java_miner_package.view.MainWindow;
import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.menu_panel.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsPanel extends JPanel {
    private final MainWindow mainWindow;
    private final JTextField widthTextField;
    private final JTextField heightTextField;
    private final JTextField minesCountTextField;
    private final List<JCheckBox> inputControlCheckBoxesList;
    private final JCheckBox mouseControlCheckBox;
    private final JCheckBox keyBoardControlCheckBox;
    private final Font font = new Font("Serif", Font.PLAIN, 40);

    public OptionsPanel(MainWindow mainWindow) {
        // panel and components initializing
        this.mainWindow = mainWindow;

        JLabel labelWidth = new JLabel("WIDTH:"); // labels
        JLabel labelHeight = new JLabel("HEIGHT:");
        JLabel labelMinesCount = new JLabel("MINES COUNT:");

        this.widthTextField = this.createTextFieldWithCharLimit(2);// textFields
        this.heightTextField = this.createTextFieldWithCharLimit(2);
        this.minesCountTextField = this.createTextFieldWithCharLimit(3);

        this.mouseControlCheckBox = new JCheckBox("Mouse Control");// controlCheckBoxes
        this.keyBoardControlCheckBox = new JCheckBox("KeyBoard Control");
        this.inputControlCheckBoxesList = Arrays
                .stream(new JCheckBox[]{this.mouseControlCheckBox, this.keyBoardControlCheckBox})
                .collect(Collectors.toList());
        this.setOneCheckBoxSelectedAtSameTime(this.inputControlCheckBoxesList);

        JButton applyOptionsButton = new JButton("APPLY"); // Buttons
        JButton cancelOptionsButton = new JButton("CANCEL");
        applyOptionsButton.addActionListener(action -> this.applyButtonAction());
        cancelOptionsButton.addActionListener(action -> mainWindow.loadPanelToMainWindow(new MenuPanel(mainWindow)));

        // panel layout configs
        Component[][] componentsPairs = {
                {labelWidth, this.widthTextField},
                {labelHeight, this.heightTextField},
                {labelMinesCount, this.minesCountTextField},
                {mouseControlCheckBox, keyBoardControlCheckBox},
                {applyOptionsButton, cancelOptionsButton}
        };
        this.loadComponentsOnPanel(componentsPairs);

        this.setLayout(new GridLayout(componentsPairs.length, 2));

        this.setUpFontForComponents(this.getComponents());
    }

    private void applyButtonAction() {
        int width = this.getSizeFromTextField(this.widthTextField);
        int height = this.getSizeFromTextField(this.heightTextField);
        int minesCount = this.getSizeFromTextField(this.minesCountTextField);

        if(this.isCorrectSize(width, height, minesCount)) {
            InputTypeControl inputTypeControl = getInputTypeControl(this.inputControlCheckBoxesList);

            GameParameters newGameParameters = new GameParameters(width, height, minesCount, inputTypeControl);

            mainWindow.getGameController().setGameParameters(newGameParameters); // load new game parameters
            mainWindow.getGameController().startGame();// initializing game
            mainWindow.loadPanelToMainWindow(new GamePanel(mainWindow));// load game field
        }
    }

    private JTextField createTextFieldWithCharLimit(int limit) {
        JTextField textField = new JTextField();
        textField.setDocument(new JTextFieldLogic(limit));
        return textField;
    }

    private void setUpFontForComponents(Component[] components) { // set font for every component via StreamAPI
        Arrays.stream(components).forEach(component -> component.setFont(this.font));
    }

    private void loadComponentsOnPanel(Component[][] componentsPairs) {
        for (Component[] components : componentsPairs)
            this.addComponentsByPairs(components[0], components[1]);
    }

    private void addComponentsByPairs(Component left, Component right) {
        this.add(left);
        this.add(right);
    }

    private boolean isCorrectSize(int width, int height, int minesCount) {
        if(width < 10 || height < 10) {
            MessageToUser.getMessage("Numbers from 10 to 30 available for mines field size!");
            return false;
        }
        if(width > 30 || height > 30) {
            MessageToUser.getMessage("Numbers from 10 to 30 available for mines field size!");
            return false;
        }
        if(Math.abs(width-height) > 5) {
            MessageToUser.getMessage("Width and Height range must be <= 5");
            return false;
        }
        return this.isCorrectMinesCount(width, height, minesCount);
    }

    private boolean isCorrectMinesCount(int width, int height, int minesCount) {
        int minMinesCount = width * height / 10; //10% mines
        int maxMinesCount = width * height / 2; //50% mines

        if(minesCount >= minMinesCount && minesCount <= maxMinesCount)
            return true;
        else {
            MessageToUser.getMessage("Wrong number of mines. Diapason for this width and height: "
                    + "min - " + minMinesCount + " : " + "max -" + maxMinesCount);
            return false;
        }
    }

    private void setOneCheckBoxSelectedAtSameTime(List<JCheckBox> checkBoxList) {
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

    private int getSizeFromTextField(JTextField textField) {
        if(textField.getText().isEmpty())
            return 0;
        else
            return Integer.parseInt(textField.getText());
    }
}
