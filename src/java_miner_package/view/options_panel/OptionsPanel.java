package java_miner_package.view.options_panel;

import java_miner_package.model.GameParameters;
import java_miner_package.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptionsPanel extends JPanel {
    private final Map<Component, Component> componentPairs = new HashMap<>();
    private final JTextField fieldWidthTextField;
    private final JTextField fieldHeightTextField;
    private final JTextField minesCountTextField;
    private final Font font = new Font("Serif", Font.PLAIN, 40);

    public OptionsPanel(MainWindow mainWindow) {
        JLabel fieldWidthLabel = new JLabel("WIDTH:"); // labels
        JLabel fieldHeightLabel = new JLabel("HEIGHT:");
        JLabel minesCountLabel = new JLabel("MINES COUNT:");

        this.fieldWidthTextField = this.createTextFieldWithCharLimit(2); // limited text fields
        this.fieldHeightTextField = this.createTextFieldWithCharLimit(2);
        this.minesCountTextField = this.createTextFieldWithCharLimit(3);

        JButton applyOptionsButton = new JButton("APPLY"); // buttons
        applyOptionsButton.addActionListener(action -> { // apply text fields for game parameters

            int width = Integer.parseInt(this.fieldWidthTextField.getText()); // board with
            int height = Integer.parseInt(this.fieldHeightTextField.getText()); // board height
            int minesCount = Integer.parseInt(this.minesCountTextField.getText()); // mines count
            GameParameters newGameParameters = new GameParameters(width, height, minesCount);

            mainWindow.getGameController().setGameParameters(newGameParameters); // load new game parameters
            mainWindow.loadGamePanel();// load game field
            mainWindow.getGameController().startGame();// initializing game
        });

        JButton cancelOptionsButton = new JButton("CANCEL");
        cancelOptionsButton.addActionListener(action -> { // back to menu
            mainWindow.loadMenuPanel();
        });

        this.addComponentsInGridTableByPairs(fieldWidthLabel, this.fieldWidthTextField); // left argument -> to left cell of grid table ... right -> to right
        this.addComponentsInGridTableByPairs(fieldHeightLabel, this.fieldHeightTextField);
        this.addComponentsInGridTableByPairs(minesCountLabel, this.minesCountTextField);
        this.addComponentsInGridTableByPairs(applyOptionsButton, cancelOptionsButton);

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
