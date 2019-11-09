package java_miner_package.model;

public class GameParameters {

    private int cellsCountWidth;
    private int cellsCountHeight;
    private int flagsCount;
    private int minesCount;

    public GameParameters() { // default parameters constructor (10x10 table, 10 mines, 10 flags)
        this.cellsCountWidth = 10;
        this.cellsCountHeight = 10;
        this.minesCount = cellsCountHeight * cellsCountHeight / 10;
        this.flagsCount = minesCount;
    }

    public GameParameters(int countCellsWidth, int countCellsHeight, int minesCount) {
        this.cellsCountWidth = countCellsWidth;
        this.cellsCountHeight = countCellsHeight;
        this.minesCount = minesCount;
        this.flagsCount = this.minesCount;
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
}
