package java_miner_package.model;

import java_miner_package.controller.input_control.InputTypeControl;

public class GameParameters {

    private final int fieldWidth;
    private final int fieldHeight;
    private final int blocksCount;
    private int flagsCount;
    private int minesCount;
    private LevelDifficulty levelDifficulty;
    private InputTypeControl inputControlType;

    public GameParameters(InputTypeControl inputControlType) { // default parameters constructor (10x10 table, EASY level difficulty)
        this.levelDifficulty = LevelDifficulty.EASY;
        this.fieldWidth = 10;
        this.fieldHeight = 10;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
        this.minesCount = (int) (this.blocksCount * this.levelDifficulty.getValue());
        this.flagsCount = minesCount;
        this.inputControlType = inputControlType;
    }

    public GameParameters(int fieldWidth, int fieldHeight, LevelDifficulty levelDifficulty, InputTypeControl inputControlType) {
        this.levelDifficulty = levelDifficulty;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
        this.minesCount = (int) (this.blocksCount * levelDifficulty.getValue());
        this.flagsCount = this.minesCount;
        this.inputControlType = inputControlType;
    }

    public GameParameters(int fieldWidth, int fieldHeight, int minesCount, InputTypeControl inputControlType) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
        this.minesCount = minesCount;
        this.flagsCount = this.minesCount;
        this.inputControlType = inputControlType;
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

    public InputTypeControl getInputControlType() {
        return inputControlType;
    }

    public void setLevelDifficulty(LevelDifficulty levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
        this.minesCount = (int) (this.blocksCount * this.levelDifficulty.getValue());
        this.flagsCount = minesCount;
    }
}
