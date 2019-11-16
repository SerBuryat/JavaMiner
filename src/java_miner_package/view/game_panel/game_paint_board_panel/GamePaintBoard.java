package java_miner_package.view.game_panel.game_paint_board_panel;

import java_miner_package.controller.MouseControl;
import java_miner_package.model.Cell;
import java_miner_package.model.ModelObserver;
import java_miner_package.view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class GamePaintBoard extends JPanel implements ModelObserver {
    private final int paintBoardWidth;
    private final int paintBoardHeight;
    private int fieldWidth;
    private int fieldHeight;
    private DrawingCell[][] paintBoardField;

    public GamePaintBoard(MainWindow mainWindow) {
        this.paintBoardWidth = 700;
        this.paintBoardHeight = 700;
        this.setSize(this.paintBoardWidth, this.paintBoardHeight);
        this.fieldWidth = mainWindow.getGameController().getGameParameters().getMinesFieldWidth();
        this.fieldHeight = mainWindow.getGameController().getGameParameters().getMinesFieldHeight();
        this.paintBoardField = new DrawingCell[fieldWidth][fieldHeight];

        this.addMouseListener(new MouseControl(mainWindow.getGameController())); // set mouse control on this board

        mainWindow.getGameModel().registerObserver(this);
    }

    public void paint(Graphics g) { // drawing pain board
        super.paint(g);
        for(int x = 0; x < this.fieldWidth; x++) {
            for(int y = 0; y < this.fieldHeight; y++) {
                this.paintBoardField[x][y].paintDrawingCell(g, this.getDrawingCellWidth(), this.getDrawingCellHeight());
            }
        }
    }

    public DrawingCell[][] getPaintBoardField() {
        return paintBoardField;
    }

    private int getDrawingCellWidth() {
        return  this.paintBoardWidth / this.fieldWidth;
    }

    private int getDrawingCellHeight() {
        return  this.paintBoardHeight / this.fieldHeight;
    }

    public void loadPaintBoard(Cell[][] minesField) { // fill paint board with DrawCell
        this.paintBoardField = new DrawingCell[this.fieldWidth][this.fieldHeight];
        for(int x = 0; x < this.fieldWidth; x++) {
            for(int y = 0; y < this.fieldHeight; y++) {
                this.paintBoardField[x][y] = new DrawingCell(minesField[x][y], getDrawingCellWidth(), getDrawingCellHeight());
            }
        }
    }

    @Override
    public void setGameModelChanges(int cellsCount, int flagsCount, int minesCount, int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }
}