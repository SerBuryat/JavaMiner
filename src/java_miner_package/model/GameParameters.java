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

    // default parameters constructor (10x10 table, EASY level difficulty)
    public GameParameters(InputTypeControl inputControlType) {
        this(10,10,LevelDifficulty.EASY,inputControlType);
    }

    public GameParameters(int fieldWidth, int fieldHeight,
                          LevelDifficulty levelDifficulty, InputTypeControl inputControlType) {
        this(fieldWidth, fieldHeight, 0, levelDifficulty, inputControlType);
    }

    public GameParameters(int fieldWidth, int fieldHeight, int minesCount, InputTypeControl inputControlType) {
        this(fieldWidth, fieldHeight, minesCount, null, inputControlType);
    }

    private GameParameters (int fieldWidth, int fieldHeight, int minesCount,
                            LevelDifficulty levelDifficulty, InputTypeControl inputControlType) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.blocksCount = this.fieldWidth * this.fieldHeight;

        this.inputControlType = inputControlType;

        if(levelDifficulty == null)
            this.minesCount = minesCount;
        else {
            this.levelDifficulty = levelDifficulty;
            this.minesCount = (int) (this.blocksCount * levelDifficulty.getValue());
        }
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

    public InputTypeControl getInputControlType() {
        return inputControlType;
    }

    public void setLevelDifficulty(LevelDifficulty levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
        this.minesCount = (int) (this.blocksCount * this.levelDifficulty.getValue());
        this.flagsCount = minesCount;
    }
}
