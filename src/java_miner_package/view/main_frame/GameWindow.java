package java_miner_package.view.main_frame;

import java_miner_package.controller.GameController;
import java_miner_package.view.game_field.GameField;
import java_miner_package.view.menu.GameMenu;
import java_miner_package.view.options.Options;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    private GameField gameField;
    private GameMenu gameMenu;
    private Options menuOptions;

    public GameWindow() { // initializing main frame
        this.setTitle("Java Miner (by SerBuryat)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        this.gameMenu = new GameMenu();
        this.gameField = new GameField();
        this.menuOptions = new Options();

        this.add(this.gameMenu, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public GameField getGameField() {
        return this.gameField;
    }

    public void loadGameField() {
        this.setContentPane(this.gameField);
        this.invalidate();
        this.validate();
    }

    public void loadGameMenu() {
        this.setContentPane(this.gameMenu);
        this.invalidate();
        this.validate();
    }

    public void loadMenuOptions() {
        this.setContentPane(this.menuOptions);
        this.invalidate();
        this.validate();
    }
}
