package java_miner_package.view.game_panel.game_paint_board_panel;

import java_miner_package.model.Cell;
import java_miner_package.view.game_panel.game_paint_board_panel.cell_decorator.Counter;
import java_miner_package.view.game_panel.game_paint_board_panel.cell_decorator.Flag;
import java_miner_package.view.game_panel.game_paint_board_panel.cell_decorator.Mine;

import java.awt.*;

public class DrawingCell {
    private Cell cell;
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

    void paintDrawingCell(Graphics g, int cellWidth, int cellHeight) {
        if(this.cell.getIsOpen()) {
            g.setColor(this.openColor);
            g.fillRoundRect(this.x * cellWidth, this.y * cellHeight, cellWidth, cellHeight, this.cellArc, this.cellArc);
            if(this.cell.getHasMine()) // has mine? -> paint mine
                new Mine().paintDrawCellDecorator(g, this);
            if(this.cell.getMineCounter()!= 0) // has counter? -> paint counter
                new Counter(this.cell.getMineCounter()).paintDrawCellDecorator(g, this);
        } else { // if closed
            g.setColor(this.closedColor);
            g.fillRoundRect(this.x * cellWidth, this.y * cellHeight, cellWidth, cellHeight, this.cellArc, this.cellArc);
            if(this.cell.getHasFlag()) // has flag? -> paint flag
                new Flag().paintDrawCellDecorator(g, this);
        }
        g.setColor(this.bordersColor);
        g.drawRoundRect(this.x * cellWidth, this.y * cellHeight, cellWidth, cellHeight, this.cellArc, this.cellArc);
    }

    public boolean isPointInCellBounds(Point p) {
        int xMin = this.x * this.cellWidth; //  left top X of this block
        int yMin = this.y * this.cellHeight;//  left top Y of this block
        int xMax = xMin + this.cellWidth;//  right bottom X of this block
        int yMax = yMin + this.cellHeight;//  right bottom Y of this block
        int pointX = (int)p.getX();// x on click
        int pointY = (int)p.getY();// y on click

        return ((pointX >= xMin && pointY >= yMin) && (pointX <= xMax && pointY <= yMax));
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
        return this.getClass().getSimpleName() + " " + "x - " + this.x + " : " + "y - " + this.y + " Cell size : width - " + this.cellWidth + " " + "height : " + this.cellHeight;
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

    public int getCellArc() {
        return cellArc;
    }
}
