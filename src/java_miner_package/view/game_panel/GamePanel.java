package java_miner_package.view.game_panel;

import java_miner_package.view.MainWindow;
import java_miner_package.view.game_panel.game_paint_board_panel.GamePaintBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GamePanel extends JPanel {

    private final GamePaintBoard gamePaintBoard;

    public GamePanel(MainWindow mainWindow) { // game panel creating
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout());

        this.gamePaintBoard = new GamePaintBoard(mainWindow);
        this.add(this.gamePaintBoard, BorderLayout.CENTER);

        GameStatusBoard gameStatusBoard = new GameStatusBoard(mainWindow.getGameModel());
        this.add(gameStatusBoard, BorderLayout.EAST);

        JPanel panelButtons = new JPanel(); // panel for buttons on 'SOUTH' game field panel
        this.add(panelButtons, BorderLayout.SOUTH);
        JButton restartGameButton = new JButton("Restart game");
        restartGameButton.addActionListener(action -> mainWindow.getGameController().startGame());
        JButton backToMenuButton = new JButton("Back to menu");
        backToMenuButton.addActionListener(action -> mainWindow.loadMenuPanel());
        panelButtons.add(backToMenuButton, BorderLayout.NORTH);
        panelButtons.add(restartGameButton,BorderLayout.SOUTH);
    }

    public GamePaintBoard getGamePaintBoard() {
        return gamePaintBoard;
    }
}
