package java_miner_package.view.game_field.game_board.block_decorator;

import java_miner_package.view.game_field.game_board.Block;

import java.awt.*;

public abstract class BlockDecorator {
    private Image img= null;

    public void paintBlockDecorator(Graphics g, Block block) {
        g.setColor(Color.WHITE);
        g.drawImage(img, block.getX() * block.getBlockSizeWidth(), block.getY() * block.getBlockSizeHeight(), block.getBlockSizeWidth(), block.getBlockSizeHeight(), null);
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
