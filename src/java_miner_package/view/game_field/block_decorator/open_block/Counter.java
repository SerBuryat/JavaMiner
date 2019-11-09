package java_miner_package.view.game_field.block_decorator.open_block;

import java_miner_package.view.game_field.block_decorator.BlockDecorator;
import java_miner_package.view.game_field.block_decorator.DecoratorImageResources;

public class Counter extends OpenBlockDecorator {

    public Counter(int number) {
        super.setImg(DecoratorImageResources.COUNTER_IMAGES_OF_NMBERS.get(number));
    }
}
