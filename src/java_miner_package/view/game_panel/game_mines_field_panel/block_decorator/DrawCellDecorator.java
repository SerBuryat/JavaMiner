package java_miner_package.view.game_panel.game_mines_field_panel.block_decorator;

import java_miner_package.view.game_panel.game_mines_field_panel.DrawCell;

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
