package java_miner_package.view.game_panel;

import java_miner_package.controller.GameController;
import java_miner_package.view.game_panel.game_mines_field_panel.GamePaintBoard;
import java_miner_package.view.game_panel.game_status_panel.GameStatusBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GamePanel extends JPanel {

    private final GamePaintBoard gamePaintBoard;
    private final GameStatusBoard gameStatusBoard;

    public GamePanel() { // game panel creating
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout());

        this.gamePaintBoard = new GamePaintBoard(GameController.GAME_CONTROLLER.getGameParameters().getCellsCountWidth(),
                GameController.GAME_CONTROLLER.getGameParameters().getCellsCountHeight()); // give game board count of cells (width and height)
        this.add(this.gamePaintBoard, BorderLayout.CENTER);

        this.gameStatusBoard = new GameStatusBoard(GameController.GAME_CONTROLLER.getGameParameters());
        this.add(this.gameStatusBoard, BorderLayout.EAST);

        JPanel panelButtons = new JPanel(); // panel for buttons on 'SOUTH' game field panel
        this.add(panelButtons, BorderLayout.SOUTH);
        JButton restartGameButton = new JButton("Restart game");
        restartGameButton.addActionListener(action -> GameController.GAME_CONTROLLER.gameInitializing());
        JButton backToMenuButton = new JButton("Back to menu");
        backToMenuButton.addActionListener(action -> GameController.GAME_CONTROLLER.getMainWindow().loadMenuPanel());
        panelButtons.add(backToMenuButton, BorderLayout.NORTH);
        panelButtons.add(restartGameButton,BorderLayout.SOUTH);
    }

    public GamePaintBoard getGamePaintBoard() {
        return gamePaintBoard;
    }

    public GameStatusBoard getGameStatusBoard() {
        return gameStatusBoard;
    }
}
