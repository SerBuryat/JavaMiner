package java_miner_package.view.game_field;

import java_miner_package.controller.GameController;
import java_miner_package.view.game_field.game_board.GameBoard;
import java_miner_package.view.game_field.game_status_board.GameStatusBoard;
import java_miner_package.view.game_field.game_status_board.RestartGameButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameField extends JPanel {

    private GameBoard gameBoard;
    private GameStatusBoard gameStatusBoard;
    private RestartGameButton restartGameButton;

    public GameField() { // initializing game field
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout());

        this.gameBoard = new GameBoard(GameController.GAME_CONTROLLER.getGameParameters().getCellsCountWidth(),
                GameController.GAME_CONTROLLER.getGameParameters().getCellsCountHeight()); // give game board count of cells (width and height)
        this.add(this.gameBoard, BorderLayout.CENTER);

        this.gameStatusBoard = new GameStatusBoard(GameController.GAME_CONTROLLER.getGameParameters());
        this.add(this.gameStatusBoard, BorderLayout.EAST);

        this.restartGameButton = new RestartGameButton();
        this.add(this.restartGameButton, BorderLayout.SOUTH);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameStatusBoard getGameStatusBoard() {
        return gameStatusBoard;
    }
}
