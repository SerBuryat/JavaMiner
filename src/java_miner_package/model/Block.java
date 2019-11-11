package java_miner_package.model;

public class Block {
    private int x;
    private int y;
    private boolean isOpen;
    private boolean hasMine;
    private boolean hasFlag;
    private int mineCounter;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setIsOpen(boolean open) {
        this.isOpen = open;
    }

    public void setFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public void setMineCounter(int mineCounter) {
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
