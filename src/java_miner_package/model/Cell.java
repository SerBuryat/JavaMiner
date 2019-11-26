package java_miner_package.model;

public class Cell {
    private final int x;
    private final int y;
    private boolean isOpen;
    private boolean hasMine;
    private boolean hasFlag;
    private int mineCount;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setOpen() {
        this.isOpen = true;
    }

    void setFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    void setMine() {
        this.hasMine = true;
    }

    void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public boolean getHasMine() {
        return this.hasMine;
    }

    public boolean getHasFlag() {
        return this.hasFlag;
    }

    public int getMineCount() {
        return mineCount;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + "x - " + this.x + " : " + "y - " + this.y;
    }

}
