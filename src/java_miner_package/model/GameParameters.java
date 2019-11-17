package java_miner_package.model;

import java_miner_package.controller.input_control.InputTypeControl;

public class GameParameters {

    private final int fieldWidth;
    private final int fieldHeight;
    private final int flagsCount;
    private final int minesCount;
    private final int blocksCount;
    private InputTypeControl inputControlType;

    public GameParameters() { // default parameters constructor (15x15 table, 25 mines, 25 flags)
        this.fieldWidth = 15;
        this.fieldHeight = 15;
        this.minesCount = 25;
        this.flagsCount = minesCount;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
    }

    public GameParameters(int fieldWidth, int fieldHeight, int minesCount) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.minesCount = minesCount;
        this.flagsCount = this.minesCount;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
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

    int getMinesCount() {
        return minesCount;
    }

    int getCellsCount() {
        return blocksCount;
    }

    public void setInputControlType(InputTypeControl inputControlType) {
        this.inputControlType = inputControlType;
    }

    public InputTypeControl getInputControlType() {
        return inputControlType;
    }
}
