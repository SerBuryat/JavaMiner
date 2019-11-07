package java_miner_package.view.game_field.block_decorator;

public class Counter extends BlockDecorator{

    public Counter(int number) {
        super.img = DecoratorImageResources.COUNTER_IMAGES_OF_NMBERS.get(number);
    }
}
