package java_miner_package.view.game_panel;


import java_miner_package.view.MainWindow;
import java_miner_package.view.game_paint_board.GamePaintBoard;
import java_miner_package.view.menu_panel.MenuPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(MainWindow mainWindow) {
        // panel and components initializing
        GameStatusBoard gameStatusBoard = new GameStatusBoard(mainWindow.getGameModel());
        GamePaintBoard gamePaintBoard = mainWindow.getGameController().getGamePaintBoard();

        JButton restartGameButton = new JButton("Restart game");
        JButton backToMenuButton = new JButton("Back to menu");
        restartGameButton.addActionListener(action -> {
            mainWindow.getGameController().startGame();
            mainWindow.loadPanelToMainWindow(new GamePanel(mainWindow));
        });
        backToMenuButton.addActionListener(action -> mainWindow.loadPanelToMainWindow(new MenuPanel(mainWindow)));

        // Panel layout configs
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout());

        JPanel panelButtons = new JPanel(); // panel for buttons
        panelButtons.add(backToMenuButton, BorderLayout.NORTH);
        panelButtons.add(restartGameButton,BorderLayout.SOUTH);

        this.add(panelButtons, BorderLayout.SOUTH);
        this.add(gamePaintBoard, BorderLayout.CENTER);
        this.add(gameStatusBoard, BorderLayout.EAST);
    }


}
