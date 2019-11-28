package java_miner_package.view.game_paint_board;

import java_miner_package.model.GameParameters;

import java.awt.*;

public class CellPointer {
    private final GameParameters gameParameters;
    private int x;
    private int y;
    private final int cellWidth;
    private final int cellHeight;

    CellPointer(int x, int y, int cellWidth, int cellHeight, GameParameters gameParameters) {
        this.x = x;
        this.y = y;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.gameParameters = gameParameters;
    }

    void paintCellPointer(Graphics graphics, int cellWidth, int cellHeight) {
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(this.x * cellWidth, this.y * cellHeight, cellWidth, cellHeight, 10, 10);
    }

    public void moveRight() {
        this.setX(this.getX()+1);
    }

    public void moveDown() {
        this.setY(this.getY()+1);
    }

    public void moveLeft() {
        this.setX(this.getX()-1);
    }

    public void moveUp() {
        this.setY(this.getY()-1);
    }

    public void moveTo(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    private void setX(int x) {
        if(x >= 0 && x < this.gameParameters.getFieldWidth())
            this.x = x;
    }

    private void setY(int y) {
        if(y >= 0 && y < this.gameParameters.getFieldHeight())
            this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    @Override
    public String toString() {
        return "Current cell : " + "x - " + this.x + " y - " + this.y;
    }
}
