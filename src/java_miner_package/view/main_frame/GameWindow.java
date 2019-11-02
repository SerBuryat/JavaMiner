package java_miner_package.view.main_frame;

import java_miner_package.view.game_field.GameField;
import java_miner_package.view.menu.GameMenu;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    private GameField gameField;
    private GameMenu gameMenu;

    public GameWindow() { // initializing main frame
        this.setTitle("Java Miner (by SerBuryat)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setVisible(true);

        gameMenu = new GameMenu();
        gameField = new GameField();

        this.getContentPane().add(gameMenu, BorderLayout.CENTER);
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public GameField getGameField() {
        return gameField;
    }

}