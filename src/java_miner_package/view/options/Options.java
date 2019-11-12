package java_miner_package.view.options;

import java_miner_package.controller.GameController;
import java_miner_package.model.GameParameters;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Options extends JPanel {
    private Map<Component, Component> componentPairs = new HashMap();
    private JLabel fieldWidthLabel;
    private JLabel fieldHeightLabel;
    private JLabel minesCountLabel;
    private JTextField fieldWidthTextField;
    private JTextField fieldHeightTextField;
    private JTextField minesCountTextField;
    private JButton applyOptionsButton;
    private JButton cancelOptionsButton;
    private Font font = new Font("Serif", Font.PLAIN, 40);

    public Options() {
        this.fieldWidthLabel = new JLabel("WIDTH:"); // labels
        this.fieldHeightLabel = new JLabel("HEIGHT:");
        this.minesCountLabel = new JLabel("MINES COUNT:");
        this.fieldWidthTextField = this.createTextFieldWithCharLimit(2); // limited text fields
        this.fieldHeightTextField = this.createTextFieldWithCharLimit(2);
        this.minesCountTextField = this.createTextFieldWithCharLimit(3);

        this.applyOptionsButton = new JButton("APPLY"); // buttons
        this.applyOptionsButton.addActionListener(action -> { // apply text fields for game parameters

            int width = Integer.parseInt(this.fieldWidthTextField.getText()); // board with
            int height = Integer.parseInt(this.fieldHeightTextField.getText()); // board height
            int minesCount = Integer.parseInt(this.minesCountTextField.getText()); // mines count
            GameParameters newGameParameters = new GameParameters(width, height, minesCount);

            GameController.GAME_CONTROLLER.setGameParameters(newGameParameters); // load new game parameters
            GameController.GAME_CONTROLLER.getGameWindow().loadGameField();// load game field
            GameController.GAME_CONTROLLER.gameInitialize();// initializing game
        });

        this.cancelOptionsButton = new JButton("CANCEL");
        this.cancelOptionsButton.addActionListener(action -> { // back to menu
            GameController.GAME_CONTROLLER.getGameWindow().loadGameMenu();
        });

        this.addComponentsInGridTableByPairs(this.fieldWidthLabel, this.fieldWidthTextField); // left argument -> to left cell of table ... right -> to right
        this.addComponentsInGridTableByPairs(this.fieldHeightLabel, this.fieldHeightTextField);
        this.addComponentsInGridTableByPairs(this.minesCountLabel, this.minesCountTextField);
        this.addComponentsInGridTableByPairs(this.applyOptionsButton, this.cancelOptionsButton);

        this.setLayout(new GridLayout(this.componentPairs.size(), 2));

        this.setUpFontForComponents(this.getComponents());
    }

    private JTextField createTextFieldWithCharLimit(int limit) {
        JTextField textField = new JTextField();
        textField.setDocument(new JTextFieldLimit(limit));
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
