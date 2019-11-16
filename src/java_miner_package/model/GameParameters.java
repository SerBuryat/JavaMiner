package java_miner_package.model;

public class GameParameters {

    private final int minesFieldWidth;
    private final int minesFieldHeight;
    private final int flagsCount;
    private final int minesCount;
    private final int blocksCount;

    public GameParameters() { // default parameters constructor (15x15 table, 25 mines, 25 flags)
        this.minesFieldWidth = 15;
        this.minesFieldHeight = 15;
        this.minesCount = 25;
        this.flagsCount = minesCount;
        this.blocksCount = this.minesFieldWidth * this.minesFieldHeight;
    }

    public GameParameters(int minesFieldWidth, int minesFieldHeight, int minesCount) {
        this.minesFieldWidth = minesFieldWidth;
        this.minesFieldHeight = minesFieldHeight;
        this.minesCount = minesCount;
        this.flagsCount = this.minesCount;
        this.blocksCount = this.minesFieldWidth * this.minesFieldHeight;
    }

    public int getMinesFieldHeight() {
        return minesFieldHeight;
    }

    public int getMinesFieldWidth() {
        return minesFieldWidth;
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
}
