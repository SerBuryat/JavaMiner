package java_miner_package.view.game_paint_board;

import java_miner_package.model.Cell;
import java_miner_package.view.game_paint_board.cell_decorator.Counter;
import java_miner_package.view.game_paint_board.cell_decorator.Flag;
import java_miner_package.view.game_paint_board.cell_decorator.Mine;

import java.awt.*;

public class DrawingCell {
    private final Cell cell;
    private final int x;
    private final int y;
    private final int cellWidth;
    private final int cellHeight;
    private final int cellArc = 10; // cell corners arc (just for cell design)
    private final Color openColor = Color.LIGHT_GRAY;
    private final Color closedColor = Color.GRAY;
    private final Color bordersColor = Color.BLACK;

    public DrawingCell(Cell cell, int cellWidth, int cellHeight) {
        this.cell = cell;
        this.x = this.cell.getX();
        this.y = this.cell.getY();
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    void paintDrawingCell(Graphics graphics, int cellWidth, int cellHeight) {
        if(this.cell.getIsOpen()) {
            graphics.setColor(this.openColor);
            graphics.fillRoundRect(this.x * cellWidth, this.y * cellHeight, cellWidth, cellHeight, this.cellArc, this.cellArc);
            if(this.cell.getHasMine()) // has mine? -> paint mine
                new Mine().paintCellDecorator(graphics, this);
            if(this.cell.getMineCount()!= 0) // has counter? -> paint counter
                new Counter(this.cell.getMineCount()).paintCellDecorator(graphics, this);
        } else {
            graphics.setColor(this.closedColor);
            graphics.fillRoundRect(this.x * cellWidth, this.y * cellHeight, cellWidth, cellHeight, this.cellArc, this.cellArc);
            if(this.cell.getHasFlag()) // has flag? -> paint flag
                new Flag().paintCellDecorator(graphics, this);
        }
        graphics.setColor(this.bordersColor);
        graphics.drawRoundRect(this.x * cellWidth, this.y * cellHeight, cellWidth, cellHeight, this.cellArc, this.cellArc);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + "x - " + this.x + " : " + "y - " + this.y
                + " Cell size : width - " + this.cellWidth + " " + "height : " + this.cellHeight;
    }

    public Color getBordersColor() {
        return bordersColor;
    }

    public Color getClosedColor() {
        return closedColor;
    }

    public int getCellArc() {
        return cellArc;
    }
}
