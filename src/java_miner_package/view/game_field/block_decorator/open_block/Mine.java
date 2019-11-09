package java_miner_package.view.game_field.block_decorator.open_block;

import java_miner_package.view.game_field.block_decorator.BlockDecorator;
import java_miner_package.view.game_field.block_decorator.DecoratorImageResources;

public class Mine extends OpenBlockDecorator{
    public Mine() {
        super.setImg(DecoratorImageResources.MINE);
    }
}
