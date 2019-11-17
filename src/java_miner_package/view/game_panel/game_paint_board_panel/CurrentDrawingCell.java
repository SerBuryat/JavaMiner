package java_miner_package.view.game_panel.game_paint_board_panel;

import java.awt.*;

public class CurrentDrawingCell {
    private int x;
    private int y;
    private int fieldWidth;
    private int fieldHeight;
    private int cellWidth;
    private int cellHeight;

    CurrentDrawingCell(int x, int y, int fieldWidth, int fieldHeight, int cellWidth, int cellHeight) {
        this.x = x;
        this.y = y;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    void paintCurrentDrawingCell(Graphics g, int cellWidth, int cellHeight) {
        Graphics2D g2 = (Graphics2D) g.create();
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
        if(x >= 0 && x < this.fieldWidth)
            this.x = x;
    }

    private void setY(int y) {
        if(y >= 0 && y < this.fieldHeight)
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

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    @Override
    public String toString() {
        return "Current cell : " + "x - " + this.x + " y - " + this.y;
    }
}