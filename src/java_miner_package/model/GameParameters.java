package java_miner_package.model;

public class GameParameters {

    private int cellsCountWidth;
    private int cellsCountHeight;
    private int flagsCount;
    private int minesCount;
    private int blocksCount;

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

    public int getBlocksCount() {
        return blocksCount;
    }

    public void setCellsCountWidth(int cellsCountWidth) {
        this.cellsCountWidth = cellsCountWidth;
    }

    public void setCellsCountHeight(int cellsCountHeight) {
        this.cellsCountHeight = cellsCountHeight;
    }

    public void setMinesCount(int minesCount) {
        this.minesCount = minesCount;
    }
}
