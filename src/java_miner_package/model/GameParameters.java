package java_miner_package.model;

import java_miner_package.controller.input_control.InputTypeControl;

public class GameParameters {

    private final int fieldWidth;
    private final int fieldHeight;
    private final int blocksCount;
    private final int flagsCount;
    private final int minesCount;
    private final InputTypeControl inputControlType;

    // default parameter constructor
    public GameParameters(InputTypeControl inputControlType) {
        this(10,10,10,inputControlType);
    }

    public GameParameters(int fieldWidth, int fieldHeight, int minesCount, InputTypeControl inputControlType) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
        this.inputControlType = inputControlType;
        this.minesCount = minesCount;
        this.flagsCount = this.minesCount;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    int getFlagsCount() {
        return flagsCount;
    }

    public int getMinesCount() {
        return minesCount;
    }

    int getCellsCount() {
        return blocksCount;
    }

    public InputTypeControl getInputControlType() {
        return inputControlType;
    }
}
