package java_miner_package.model;

public class GameParametrs {

    private int countCellsWidth;
    private int countCellsHeight;

   public GameParametrs(int countCellsWidth, int countCellsHeight) {
        this.countCellsWidth = countCellsWidth;
        this.countCellsHeight = countCellsHeight;
    }

    public int getCountCellsHeight() {
        return countCellsHeight;
    }

    public int getCountCellsWidth() {
        return countCellsWidth;
    }
}
