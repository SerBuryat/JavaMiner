package java_miner_package.view.game_field;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int xMin; // min X position on a board // all this 4 parameters need to find block position on a screen
    private int yMin; // min Y position on a board
    private int xMax; // man X position on a board
    private int yMax; // max Y position on a board
    private int blockSizeWidth;
    private int blockSizeHeight;
    private boolean isOpen;
    private boolean hasMine;

    public Block(int x, int y, int blockSizeWidth, int blockSizeHeight) {
        this.x = x;
        this.y = y;
        this.blockSizeWidth = blockSizeWidth;
        this.blockSizeHeight = blockSizeHeight;
        this.xMin = this.x * this.blockSizeWidth;
        this.yMin = this.y * this.blockSizeHeight;
        this.xMax = xMin + this.blockSizeWidth;
        this.yMax = yMin + this.blockSizeHeight;
    }

    public void paintBlock(Graphics g, int blockWidth, int blockHeight, int blockArc) {
        if(isOpen) {
            g.setColor(Color.WHITE);
            g.fillRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc, blockArc);
        } else {
            g.setColor(Color.GRAY);
            g.fillRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc, blockArc);
        }
        g.setColor(Color.BLACK);
        g.drawRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc,blockArc);
    }

    public boolean isPointInBlockBounds(Point p) {
        int pointX = (int)p.getX();
        int pointY = (int)p.getY();
        return ((pointX >= xMin && pointY >= yMin) && (pointX <= xMax && pointY <= yMax));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + "x - " + this.x + " : " + "y - " + this.y + " Block size : width - " + this.blockSizeWidth + " " + "height : " + this.blockSizeHeight;
    }

    public void setBlockOpen(boolean open) {
        this.isOpen = open;
    }

    public boolean getIsBlockOpen() {
        return this.isOpen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBlockSizeWidth() {
        return blockSizeWidth;
    }

    public int getBlockSizeHeight() {
        return blockSizeHeight;
    }
}
