package java_miner_package.view;

import java_miner_package.controller.GameController;
import java_miner_package.model.GameModel;
import java_miner_package.view.menu_panel.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private final GameController gameController;
    private final GameModel gameModel;

    public MainWindow(GameController gameController, GameModel gameModel) {
        // panel and components initializing
        this.gameController = gameController;
        this.gameModel = gameModel;
        this.setTitle("Java Miner (by SerBuryat)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        // panel layout config
        MenuPanel menuPanel = new MenuPanel(this);
        this.add(menuPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void loadPanelToMainWindow(JPanel panel) {
        this.getContentPane().remove(0); // delete current panel
        this.getContentPane().add(panel);
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
