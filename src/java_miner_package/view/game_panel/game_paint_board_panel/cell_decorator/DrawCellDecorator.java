package java_miner_package.view.game_panel.game_paint_board_panel.cell_decorator;

import java_miner_package.view.game_panel.game_paint_board_panel.DrawCell;

import java.awt.*;

public abstract class DrawCellDecorator {
    private Image img= null;

    public void paintDrawCellDecorator(Graphics g, DrawCell drawCell) {
        g.setColor(Color.WHITE);
        g.drawImage(img, drawCell.getX() * drawCell.getCellSizeWidth(), drawCell.getY() * drawCell.getCellSizeHeight(), drawCell.getCellSizeWidth(), drawCell.getCellSizeHeight(), null);
    }

    void setImg(Image img) {
        this.img = img;
    }
}
