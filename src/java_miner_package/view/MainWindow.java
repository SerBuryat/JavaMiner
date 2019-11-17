package java_miner_package.view;

import java_miner_package.controller.GameController;
import java_miner_package.model.GameModel;
import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.game_panel.game_paint_board_panel.GamePaintBoard;
import java_miner_package.view.menu_panel.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private final GameController gameController;
    private final GameModel gameModel;
    private GamePanel gamePanel;

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

        MenuPanel menuPanel = new MenuPanel(this);
        this.add(menuPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void loadPanelToMainWindow(JPanel panel) {
        if(panel.getClass() == GamePanel.class) // very bad decision))) Btw it's for getGamePaintBoard method working on
            this.gamePanel = (GamePanel) panel;
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

    public GamePaintBoard getGamePaintBoard() {
        return this.gamePanel.getGamePaintBoard();
    }

}
