package java_miner_package.model;

import java_miner_package.MessageToUser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameModel implements ModelSubjectForObservers{
    private GameParameters gameParameters;
    private Cell[][] minesField;
    private int cellsCount;
    private int flagsCount;
    private int minesCount;
    private int fieldWidth;
    private int fieldHeight;
    private boolean isGameStopped = false;
    private final ArrayList<ModelObserver> observers = new ArrayList<>();

    public GameModel(GameParameters gameParameters) {
        this.setGameParameters(gameParameters);
    }

    public void createGame() {
        this.fillMinesFieldWithCells(); // fill mines field with blocks
        this.fillMinesFieldWithMines(); //  with mines
        this.fillMinesFieldWithCounters(); // with counters
        this.isGameStopped = false;
    }

    private void fillMinesFieldWithCells() {
        for(int x = 0; x < this.fieldWidth; x++) {
            for(int y = 0; y < this.fieldHeight; y++) {
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
            this.minesField[x_random][y_random].setMine();
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

    public void openCell(Cell cell) {
        if(!this.isGameStopped) {
            if(!cell.getIsOpen() && !cell.getHasFlag()) {
                cell.setOpen();
                this.setCellsCount(cellsCount -=1);

                if(cell.getHasMine()) {
                    gameOverLose();
                } else {

                    if(this.cellsCount == this.minesCount) {
                        gameOverWin();
                        return;
                    }

                    if(cell.getMineCounter() == 0) // if this cell is empty (without counter or mine)
                        this.openEmptyNeighborsCells(cell);
                }
            }
        }
    }

    public void openAllCells() { /// for test
        for(Cell[] arr : this.minesField) {
            for(Cell cell : arr) {
                cell.setOpen();
                this.setCellsCount(cellsCount = this.gameParameters.getMinesCount());
            }
        }
    }

    public void setFlagOnCell(Cell cell) { // or delete it if block already has it
        if(!this.isGameStopped) {
            if(cell.getHasFlag()) {
                cell.setFlag(false);
                this.setFlagsCount(flagsCount+=1);
            } else {
                if(this.flagsCount > 0 && !cell.getIsOpen()) {
                    cell.setFlag(true);
                    this.flagsCount-=1;
                    this.notifyObservers();
                }
            }
        }
    }

    private void openEmptyNeighborsCells(Cell cell) { // open all empty neighbors
            for(Cell b : this.getCellNeighbors(cell)) {
                if(!(b.getIsOpen() && b.getHasMine()))
                    this.openCell(b);
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
        this.openAllCells();
        this.gameStop();
        MessageToUser.getMessage("Congratulations! You WIN! All " + (this.gameParameters.getCellsCount() - this.minesCount) +  " cells is open!"
                + "\n" +  "Click 'Restart game' for restart or 'Back to Menu' for change options!");
    }

    private void gameOverLose() {
        this.openAllCells();
        this.gameStop();
        MessageToUser.getMessage("Boooooom! That's was mine) Sry, but you LOSE( " + "\n" + " Click 'Restart game' for restart or 'Back to Menu' for change options!");
    }

    private void gameStop() {
        isGameStopped = true;
        this.notifyObservers();
    }



    public int getFlagsCount() {
        return flagsCount;
    }

    public int getCellsCount() {
        return cellsCount;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public Cell[][] getMinesField() {
        return minesField;
    }

    private void setFlagsCount(int flagsCount) {
        this.flagsCount = flagsCount;
        this.notifyObservers();
    }

    private void setCellsCount(int cellsCount) {
        this.cellsCount = cellsCount;
        this.notifyObservers();
    }

    private void setMinesCount(int minesCount) {
        this.minesCount = minesCount;
        this.notifyObservers();
    }

    private void setFieldSize(int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.notifyObservers();
    }

    public void setGameParameters(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
        this.minesField = new Cell[gameParameters.getFieldWidth()][gameParameters.getFieldHeight()];
        this.setFieldSize(gameParameters.getFieldWidth(), gameParameters.getFieldHeight());
        this.setCellsCount(gameParameters.getCellsCount());
        this.setFlagsCount(gameParameters.getFlagsCount());
        this.setMinesCount(gameParameters.getMinesCount());
    }

    @Override
    public void registerObserver(ModelObserver observer) {
        this.observers.add(observer);
    }
    @Override
    public void deleteObserver(ModelObserver observer) {
        this.observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for(ModelObserver observer : this.observers)
            observer.setGameModelChanges(this.cellsCount, this.flagsCount, this.minesCount, this.fieldWidth, this.fieldHeight, this.isGameStopped);
    }
}
