package java_miner_package.view;

import java_miner_package.controller.GameController;
import java_miner_package.model.GameModel;
import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.game_panel.game_paint_board_panel.GamePaintBoard;
import java_miner_package.view.menu_panel.MenuPanel;
import java_miner_package.view.options_panel.OptionsPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private final GameController gameController;
    private final GameModel gameModel;
    private final GamePanel gamePanel;
    private final MenuPanel menuPanel;
    private final OptionsPanel optionsPanel;

    public MainWindow(GameController gameController, GameModel gameModel) {
        this.gameController = gameController;
        this.gameModel = gameModel;
        this.setTitle("Java Miner (by SerBuryat)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        this.menuPanel = new MenuPanel(this);
        this.gamePanel = new GamePanel(this);
        this.optionsPanel = new OptionsPanel(this);

        this.add(this.menuPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public GamePaintBoard getGamePaintBoard() {
        return this.gamePanel.getGamePaintBoard();
    }

    public void loadGamePanel() {// load game panel , hide current panel
        this.setContentPane(this.gamePanel);
        this.invalidate();
        this.validate();
    }

    public void loadMenuPanel() {// load menu panel , hide current panel
        this.setContentPane(this.menuPanel);
        this.invalidate();
        this.validate();
    }

    public void loadOptionsPanel() {// load game panel , hide current panel
        this.setContentPane(this.optionsPanel);
        this.invalidate();
        this.validate();
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
