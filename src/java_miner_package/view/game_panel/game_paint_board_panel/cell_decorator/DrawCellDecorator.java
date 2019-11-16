package java_miner_package.view.game_panel.game_paint_board_panel.cell_decorator;

import java_miner_package.view.game_panel.game_paint_board_panel.DrawingCell;

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
