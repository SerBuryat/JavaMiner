package java_miner_package.view.game_field;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int blockSizeWidth;
    private int blockSizeHeight;
    private boolean isOpen;
    private boolean hasMine;
    private boolean hasFlag;

    public Block(int x, int y, int blockSizeWidth, int blockSizeHeight) {
        this.x = x;
        this.y = y;
        this.blockSizeWidth = blockSizeWidth;
        this.blockSizeHeight = blockSizeHeight;
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

    public boolean isInBlockBounds(int x, int y) {
        return ((x >= this.x * blockSizeWidth && y >= this.y * blockSizeHeight)  && (x <= this.x * blockSizeWidth + blockSizeWidth && y <= this.y * blockSizeHeight + blockSizeHeight));
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
}
