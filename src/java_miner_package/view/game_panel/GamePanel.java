package java_miner_package.view.game_panel;


import java_miner_package.view.MainWindow;
import java_miner_package.view.game_paint_board.GamePaintBoard;
import java_miner_package.view.menu_panel.MenuPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(MainWindow mainWindow) { // game panel creating
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout());

        this.add(mainWindow.getGameController().getGamePaintBoard(), BorderLayout.CENTER);

        GameStatusBoard gameStatusBoard = new GameStatusBoard(mainWindow.getGameModel());
        this.add(gameStatusBoard, BorderLayout.EAST);

        JPanel panelButtons = new JPanel(); // panel for buttons on 'SOUTH' game field panel
        this.add(panelButtons, BorderLayout.SOUTH);
        JButton restartGameButton = new JButton("Restart game");
        restartGameButton.addActionListener(action -> {
            mainWindow.getGameController().startGame();
            mainWindow.loadPanelToMainWindow(new GamePanel(mainWindow));
        });
        JButton backToMenuButton = new JButton("Back to menu");
        backToMenuButton.addActionListener(action -> mainWindow.loadPanelToMainWindow(new MenuPanel(mainWindow)));
        panelButtons.add(backToMenuButton, BorderLayout.NORTH);
        panelButtons.add(restartGameButton,BorderLayout.SOUTH);
    }
}
