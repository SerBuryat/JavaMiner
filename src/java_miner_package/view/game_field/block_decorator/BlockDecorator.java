package java_miner_package.view.game_field.block_decorator;

import java_miner_package.view.game_field.Block;

import java.awt.*;

public abstract class BlockDecorator {
    Image img= null;

    public void paintBlockDecorator(Graphics g, Block block) {
        g.setColor(Color.WHITE);
        g.drawImage(img, block.getX() * block.getBlockSizeWidth(), block.getY() * block.getBlockSizeHeight(), block.getBlockSizeWidth(), block.getBlockSizeHeight(), null);
    }
}
