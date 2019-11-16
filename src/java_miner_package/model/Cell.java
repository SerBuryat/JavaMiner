package java_miner_package.model;

public class Cell {
    private final int x;
    private final int y;
    private boolean isOpen;
    private boolean hasMine;
    private boolean hasFlag;
    private int mineCounter;

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

    void setMineCounter(int mineCounter) {
        this.mineCounter = mineCounter;
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

    public int getMineCounter() {
        return mineCounter;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + "x - " + this.x + " : " + "y - " + this.y;
    }

}
