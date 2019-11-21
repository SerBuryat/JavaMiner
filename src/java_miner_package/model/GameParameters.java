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

    public GameParameters() { // default parameters constructor (15x15 table, EASY level difficulty)
        this.levelDifficulty = LevelDifficulty.EASY;
        this.fieldWidth = 15;
        this.fieldHeight = 15;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
        this.minesCount = (int) (this.blocksCount * this.levelDifficulty.getValue());
        this.flagsCount = minesCount;
    }

    public GameParameters(int fieldWidth, int fieldHeight, LevelDifficulty levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.blocksCount = this.fieldWidth * this.fieldHeight;
        this.minesCount = (int) (this.blocksCount * levelDifficulty.getValue());
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

    public void setLevelDifficulty(LevelDifficulty levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
        this.minesCount = (int) (this.blocksCount * this.levelDifficulty.getValue());
        this.flagsCount = minesCount;
    }
    public LevelDifficulty getLevelDifficulty() {
        return levelDifficulty;
    }
}
