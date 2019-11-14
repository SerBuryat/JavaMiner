package java_miner_package.model;

import java.util.LinkedList;
import java.util.Random;

public class GameModel {
    private final GameParameters gameParameters;
    private final Cell[][] minesField;
    private int cellCount;
    private int flagsCount;
    private final int minesCount;
    private final int fieldWidth;
    private final int fieldHeight;
    private boolean isGameStopped;

    public GameModel(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
        this.minesField = new Cell[this.gameParameters.getCellsCountWidth()][this.gameParameters.getCellsCountHeight()];
        this.minesCount = this.gameParameters.getMinesCount();
        this.flagsCount = this.minesCount;
        this.fieldWidth = this.gameParameters.getCellsCountWidth();
        this.fieldHeight = this.gameParameters.getCellsCountHeight();
        this.cellCount = this.fieldWidth * this.fieldHeight;
        this.isGameStopped = false;
    }

    public void gameStart() {
        this.fillMinesFieldWithCells(); // fill mines field with blocks
        this.fillMinesFieldWithMines(); //  with mines
        this.fillMinesFieldWithCounters(); // with counters
    }

    private void fillMinesFieldWithCells() {
        for(int x = 0; x < this.gameParameters.getCellsCountWidth(); x++) {
            for(int y = 0; y < this.gameParameters.getCellsCountHeight(); y++) {
                this.minesField[x][y] = new Cell(x, y);
            }
        }
    }

    private void fillMinesFieldWithMines() { // fill randomly mines field with mines
        for(int i = 0; i < this.minesCount; i++) {
            int x_random = this.getRandomCoordinate(fieldWidth);
            int y_random = this.getRandomCoordinate(fieldHeight);
            while (this.minesField[x_random][y_random].getHasMine()) { // if block already has mine -> calculate another coordinate
                x_random = this.getRandomCoordinate(fieldWidth);
                y_random = this.getRandomCoordinate(fieldHeight);
            }
            this.minesField[x_random][y_random].setMine(true);
        }
    }

    private void fillMinesFieldWithCounters() { // fill mines field with counters
        for(Cell[] cells : this.minesField) {
            for(Cell cell : cells) {
                this.getCellNeighbors(cell);
                this.countNearMines(cell);
            }
        }
    }

    public void setCellOpen(Cell cell) {
        if(!this.isGameStopped) { // check game status
            if(!cell.getIsOpen() && !cell.getHasFlag()) { // check cell status (!open && !flag)
                cell.setIsOpen(true); // open this cell

                if(cell.getHasMine()) {// mine picked -> lose
                    gameOverLose();
                } else {
                    this.cellCount -=1; // - 1 closed cell

                    if((this.cellCount - this.minesCount) == 0) {// all blocks(without mines) open -> win
                        gameOverWin();
                        return;
                    }

                    if(cell.getMineCounter() == 0) // if this cell is empty (without counter or mine)
                        this.openEmptyNeighborsCells(cell);
                }
            }
        }
    }

    public void setAllCellsOpen() { /// for test
        for(Cell[] arr : this.minesField) {
            for(Cell cell : arr) {
                cell.setIsOpen(true);
                this.cellCount = this.gameParameters.getMinesCount();
            }
        }
    }

    public void setFlagOnBlock(Cell cell) { // or delete it if block already has it
        if(!this.isGameStopped) {
            if(cell.getHasFlag()) {
                cell.setFlag(false);
                this.flagsCount+=1;
            } else {
                if(this.flagsCount > 0 && !cell.getIsOpen()) {
                    cell.setFlag(true);
                    this.flagsCount-=1;
                }
            }
        }
    }

    private void openEmptyNeighborsCells(Cell cell) { // open all empty neighbors
            for(Cell b : this.getCellNeighbors(cell)) {
                if(!(b.getIsOpen() && b.getHasMine()))
                    this.setCellOpen(b);
            }
    }

    private LinkedList<Cell> getCellNeighbors(Cell cell) { // get every 'neighbor-block' for current cell
        int x = cell.getX();
        int y = cell.getY();
        LinkedList<Cell> cellNeighbors = new LinkedList<>();

        for (int i = x-1;i < x+2;i++) { // add every cell from minesField[x-1][y-1] (top left neighbor) to minesField[x+2][y+2] (bottom right neighbor)
            for (int j = y-1;j < y+2;j++) {
                if (!(i < 0 || j < 0 || i > (this.fieldWidth - 1) || j > (this.fieldHeight - 1)))//cell is not running out of mines field
                    if (!(i == y && j == x)) // if not current cell
                        cellNeighbors.add(minesField[i][j]);
            }
        }

        return cellNeighbors;

    }

    private void countNearMines(Cell cell) { // count every near mine for current block
        if(!cell.getHasMine()) {
            for(Cell neighbor : this.getCellNeighbors(cell)) {
                if(neighbor.getHasMine())
                    cell.setMineCounter(cell.getMineCounter() + 1);
            }
        }
    }

    private int getRandomCoordinate(int bound) {
        return new Random().nextInt(bound);
    }

    private void gameOverWin() {
        this.isGameStopped = true;
        System.out.println("Game is over. You WIN!");
    }

    private void gameOverLose() {
        this.isGameStopped = true;
        System.out.println("Game is over. You LOSE!");
    }

    public int getFlagsCount() {
        return flagsCount;
    }

    public int getCellCount() {
        return cellCount;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public Cell[][] getMinesField() {
        return minesField;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }
}
