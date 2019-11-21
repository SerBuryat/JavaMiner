package java_miner_package.view.game_paint_board.cell_decorator;

import java_miner_package.view.game_paint_board.DrawingCell;

import java.awt.*;

public abstract class DrawCellDecorator {
    private Image img= null;

    public void paintDrawCellDecorator(Graphics g, DrawingCell drawingCell) {
        g.setColor(Color.WHITE);
        g.drawImage(img, drawingCell.getX() * drawingCell.getCellWidth(), drawingCell.getY() * drawingCell.getCellHeight(), drawingCell.getCellWidth(), drawingCell.getCellHeight(), null);
    }

    void setImg(Image img) {
        this.img = img;
    }
}
