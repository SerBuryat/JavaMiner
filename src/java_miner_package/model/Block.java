package java_miner_package.model;

import java_miner_package.view.game_field.game_board.block_decorator.Counter;
import java_miner_package.view.game_field.game_board.block_decorator.Flag;
import java_miner_package.view.game_field.game_board.block_decorator.Mine;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int blockSizeWidth;
    private int blockSizeHeight;
    private int blockArc = 10;
    private Color openColor = Color.LIGHT_GRAY;
    private Color closedColor = Color.GRAY;
    private Color bordersColor = Color.BLACK;
    private boolean isOpen;
    private boolean hasMine;
    private boolean hasFlag;
    private int mineCounter;

    public Block(int x, int y, int blockSizeWidth, int blockSizeHeight) {
        this.x = x;
        this.y = y;
        this.blockSizeWidth = blockSizeWidth;
        this.blockSizeHeight = blockSizeHeight;
    }

    public void paintBlock(Graphics g, int blockWidth, int blockHeight) {
        if(this.isOpen) {
            g.setColor(this.openColor);
            g.fillRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, this.blockArc, this.blockArc);
            if(this.hasMine) // has mine? -> paint mine
                new Mine().paintBlockDecorator(g, this);
            if(this.mineCounter != 0) // has counter? -> paint counter
                new Counter(this.mineCounter).paintBlockDecorator(g, this);
        } else { // if closed
            g.setColor(this.closedColor);
            g.fillRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, this.blockArc, this.blockArc);
            if(hasFlag) // has flag? -> paint flag
                new Flag().paintBlockDecorator(g, this);
        }
        g.setColor(this.bordersColor);
        g.drawRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, this.blockArc,this.blockArc);
    } // painting block

    public boolean isPointInBlockBounds(Point p) {
        int xMin = this.x * this.blockSizeWidth; //  left top X of this block
        int yMin = this.y * this.blockSizeHeight;//  left top Y of this block
        int xMax = xMin + this.blockSizeWidth;//  right bottom X of this block
        int yMax = yMin + this.blockSizeHeight;//  right bottom Y of this block
        int pointX = (int)p.getX();// x on click
        int pointY = (int)p.getY();// y on click

        return ((pointX >= xMin && pointY >= yMin) && (pointX <= xMax && pointY <= yMax));
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

    public int getBlockSizeWidth() {
        return blockSizeWidth;
    }

    public int getBlockSizeHeight() {
        return blockSizeHeight;
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
        return this.getClass().getSimpleName() + " " + "x - " + this.x + " : " + "y - " + this.y + " Block size : width - " + this.blockSizeWidth + " " + "height : " + this.blockSizeHeight;
    }

    public Color getBordersColor() {
        return bordersColor;
    }

    public Color getClosedColor() {
        return closedColor;
    }

    public Color getOpenColor() {
        return openColor;
    }

    public int getBlockArc() {
        return blockArc;
    }
}
