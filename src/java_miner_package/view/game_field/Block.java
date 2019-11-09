package java_miner_package.view.game_field;

import java_miner_package.view.game_field.block_decorator.closed_block.ClosedBlockDecorator;
import java_miner_package.view.game_field.block_decorator.open_block.Counter;
import java_miner_package.view.game_field.block_decorator.closed_block.Flag;
import java_miner_package.view.game_field.block_decorator.open_block.Mine;
import java_miner_package.view.game_field.block_decorator.open_block.OpenBlockDecorator;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int blockSizeWidth;
    private int blockSizeHeight;
    private boolean isOpen;
    private boolean hasMine;
    private boolean hasFlag;
    private int mineCounter;
    private OpenBlockDecorator openBlockDecorator; // decorators when block OPEN
    private ClosedBlockDecorator closedBlockDecorator; // decorators when block CLOSED

    public Block(int x, int y, int blockSizeWidth, int blockSizeHeight) {
        this.x = x;
        this.y = y;
        this.blockSizeWidth = blockSizeWidth;
        this.blockSizeHeight = blockSizeHeight;
    }

    public void paintBlock(Graphics g, int blockWidth, int blockHeight, int blockArc) {
        if(this.isOpen) {
            g.setColor(Color.WHITE);
            g.fillRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc, blockArc);
            if(this.openBlockDecorator != null) // if has some open decorator -> paint it
                this.openBlockDecorator.paintBlockDecorator(g, this);
        } else {
            g.setColor(Color.GRAY);
            g.fillRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc, blockArc);
            if(this.closedBlockDecorator != null) // if has some closed decorator -> paint it
                this.closedBlockDecorator.paintBlockDecorator(g , this);
        }
        g.setColor(Color.BLACK);
        g.drawRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc,blockArc);
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + "x - " + this.x + " : " + "y - " + this.y + " Block size : width - " + this.blockSizeWidth + " " + "height : " + this.blockSizeHeight;
    }

    public void setBlockOpen(boolean open) {
        this.isOpen = open;
    }

    public void setFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
        this.closedBlockDecorator = new Flag();
    }

    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
        this.openBlockDecorator = new Mine();
    }

    public void setMineCounter(int mineCounter) {
        this.mineCounter = mineCounter;
        this.openBlockDecorator = new Counter(mineCounter);
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

    public boolean getHasMine() {
        return this.hasMine;
    }

    public boolean getHasFlag() {
        return this.hasFlag;
    }

    public void setClosedBlockDecorator(ClosedBlockDecorator closedBlockDecorator) {
        this.closedBlockDecorator = closedBlockDecorator;
    }

    public void setOpenBlockDecorator(OpenBlockDecorator openBlockDecorator) {
        this.openBlockDecorator = openBlockDecorator;
    }

}
