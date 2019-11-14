package java_miner_package.model;

public class GameParameters {

    private final int cellsCountWidth;
    private final int cellsCountHeight;
    private final int flagsCount;
    private final int minesCount;
    private final int blocksCount;

    public GameParameters() { // default parameters constructor (15x15 table, 25 mines, 25 flags)
        this.cellsCountWidth = 15;
        this.cellsCountHeight = 15;
        this.minesCount = 25;
        this.flagsCount = minesCount;
        this.blocksCount = this.cellsCountWidth * this.cellsCountHeight;
    }

    public GameParameters(int countCellsWidth, int countCellsHeight, int minesCount) {
        this.cellsCountWidth = countCellsWidth;
        this.cellsCountHeight = countCellsHeight;
        this.minesCount = minesCount;
        this.flagsCount = this.minesCount;
        this.blocksCount = this.cellsCountWidth * this.cellsCountHeight;
    }

    public int getCellsCountHeight() {
        return cellsCountHeight;
    }

    public int getCellsCountWidth() {
        return cellsCountWidth;
    }

    public int getFlagsCount() {
        return flagsCount;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public int getCellsCount() {
        return blocksCount;
    }
}
