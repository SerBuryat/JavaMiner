package java_miner_package.view.game_field;

import java_miner_package.controller.GameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameField extends JPanel {

    private GameBoard gameBoard;

    public GameField() { // initializing game field
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(GameController.GAME_CONTROLLER.getGameParameters().getCountCellsWidth(),
                GameController.GAME_CONTROLLER.getGameParameters().getCountCellsHeight()); // give game board count of cells (width and height)
        this.add(gameBoard, BorderLayout.CENTER);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
