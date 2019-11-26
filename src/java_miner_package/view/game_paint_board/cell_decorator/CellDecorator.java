package java_miner_package.view.game_paint_board.cell_decorator;

import java_miner_package.view.game_paint_board.DrawingCell;

import java.awt.*;

public abstract class CellDecorator {
    private Image img= null;

    public void paintCellDecorator(Graphics graphics, DrawingCell drawingCell) {
        graphics.setColor(Color.WHITE);
        graphics.drawImage(img, drawingCell.getX() * drawingCell.getCellWidth(),
                drawingCell.getY() * drawingCell.getCellHeight(),
                drawingCell.getCellWidth(), drawingCell.getCellHeight(),null);
    }

    void setImg(Image img) {
        this.img = img;
    }
}
