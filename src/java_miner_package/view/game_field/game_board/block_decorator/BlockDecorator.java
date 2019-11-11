package java_miner_package.view.game_field.game_board.block_decorator;

import java_miner_package.view.game_field.game_board.DrawBlock;

import java.awt.*;

public abstract class BlockDecorator {
    private Image img= null;

    public void paintBlockDecorator(Graphics g, DrawBlock drawBlock) {
        g.setColor(Color.WHITE);
        g.drawImage(img, drawBlock.getX() * drawBlock.getBlockSizeWidth(), drawBlock.getY() * drawBlock.getBlockSizeHeight(), drawBlock.getBlockSizeWidth(), drawBlock.getBlockSizeHeight(), null);
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
