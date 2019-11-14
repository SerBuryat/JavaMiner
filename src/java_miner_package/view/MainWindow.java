package java_miner_package.view;

import java_miner_package.view.game_panel.GamePanel;
import java_miner_package.view.menu_panel.MenuPanel;
import java_miner_package.view.options_panel.OptionsPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private final GamePanel gamePanel;
    private final MenuPanel menuPanel;
    private final OptionsPanel optionsPanel;

    public MainWindow() { // initializing window 800 x 800
        this.setTitle("Java Miner (by SerBuryat)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        this.menuPanel = new MenuPanel();
        this.gamePanel = new GamePanel();
        this.optionsPanel = new OptionsPanel();

        this.add(this.menuPanel, BorderLayout.CENTER); // menu panel is first after start

        this.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
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
}
