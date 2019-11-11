package java_miner_package.view.game_field.game_board;

import java_miner_package.model.Block;
import java_miner_package.view.game_field.game_board.block_decorator.Counter;
import java_miner_package.view.game_field.game_board.block_decorator.Flag;
import java_miner_package.view.game_field.game_board.block_decorator.Mine;

import java.awt.*;

public class DrawBlock {
    private Block block;
    private int x;
    private int y;
    private int blockSizeWidth;
    private int blockSizeHeight;
    private int blockArc = 10;
    private Color openColor = Color.LIGHT_GRAY;
    private Color closedColor = Color.GRAY;
    private Color bordersColor = Color.BLACK;

    public DrawBlock(Block block, int blockSizeWidth, int blockSizeHeight) {
        this.block = block;
        this.x = this.block.getX();
        this.y = this.block.getY();
        this.blockSizeWidth = blockSizeWidth;
        this.blockSizeHeight = blockSizeHeight;
    }

    public DrawBlock(int x, int y, int blockSizeWidth, int blockSizeHeight) {
        this.x = x;
        this.y = y;
        this.blockSizeWidth = blockSizeWidth;
        this.blockSizeHeight = blockSizeHeight;
    }

    public void paintBlock(Graphics g, int blockWidth, int blockHeight) {
        if(this.block.getIsOpen()) {
            g.setColor(this.openColor);
            g.fillRoundRect(this.x * blockWidth, this.y * blockHeight, blockWidth, blockHeight, this.blockArc, this.blockArc);
            if(this.block.getHasMine()) // has mine? -> paint mine
                new Mine().paintBlockDecorator(g, this);
            if(this.block.getMineCounter()!= 0) // has counter? -> paint counter
                new Counter(this.block.getMineCounter()).paintBlockDecorator(g, this);
        } else { // if closed
            g.setColor(this.closedColor);
            g.fillRoundRect(this.x * blockWidth, this.y * blockHeight, blockWidth, blockHeight, this.blockArc, this.blockArc);
            if(this.block.getHasFlag()) // has flag? -> paint flag
                new Flag().paintBlockDecorator(g, this);
        }
        g.setColor(this.bordersColor);
        g.drawRoundRect(this.x * blockWidth, this.y * blockHeight, blockWidth, blockHeight, this.blockArc, this.blockArc);
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

    public Block getBlock() {
        return block;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getBlockSizeWidth() {
        return blockSizeWidth;
    }

    public int getBlockSizeHeight() {
        return blockSizeHeight;
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
