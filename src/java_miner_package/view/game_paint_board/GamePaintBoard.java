package java_miner_package.view.game_paint_board;

import java_miner_package.controller.input_control.InputTypeControl;
import java_miner_package.model.Cell;
import java_miner_package.view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class GamePaintBoard extends JPanel {
    private final int paintBoardWidth;
    private final int paintBoardHeight;
    private final int fieldWidth;
    private final int fieldHeight;
    private final DrawingCell[][] paintBoardField;
    private final CellPointer cellPointer;
    private final InputTypeControl controlType;

    public GamePaintBoard(MainWindow mainWindow, InputTypeControl controlType) {
        // panel and components initializing
        this.paintBoardWidth = 700;
        this.paintBoardHeight = 700;
        this.setSize(this.paintBoardWidth, this.paintBoardHeight);
        this.fieldWidth = mainWindow.getGameController().getGameParameters().getFieldWidth();
        this.fieldHeight = mainWindow.getGameController().getGameParameters().getFieldHeight();
        this.paintBoardField = new DrawingCell[fieldWidth][fieldHeight];
        this.controlType = controlType;
        this.cellPointer = new CellPointer(fieldWidth / 2, fieldHeight / 2, this.getDrawingCellWidth(), this.getDrawingCellHeight(), mainWindow.getGameController().getGameParameters());
        this.loadPaintBoard(mainWindow.getGameModel().getMinesField());
        this.loadPaintBoardControl();
    }

    public void paint(Graphics g) { // drawing pain board
        super.paint(g);
        this.requestFocus();
        for(int x = 0; x < this.fieldWidth; x++) {
            for(int y = 0; y < this.fieldHeight; y++) {
                this.paintBoardField[x][y].paintDrawingCell(g, this.getDrawingCellWidth(), this.getDrawingCellHeight());
            }
        }
        this.cellPointer.paintCurrentDrawingCell(g, this.getDrawingCellWidth(), this.getDrawingCellHeight());
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

    private void loadPaintBoard(Cell[][] minesField) {
        for(int x = 0; x < this.fieldWidth; x++) {
            for(int y = 0; y < this.fieldHeight; y++) {
                this.paintBoardField[x][y] = new DrawingCell(minesField[x][y], getDrawingCellWidth(), getDrawingCellHeight());
            }
        }
    }

    private void loadPaintBoardControl() {
        this.controlType.setCellPointer(this.cellPointer);
        this.controlType.addControlToGamePaintBoard(this);
    }
}