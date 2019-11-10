package java_miner_package.view.game_field.game_board.block_decorator;

public class Counter extends BlockDecorator {

    public Counter(int number) {
        super.setImg(DecoratorImageResources.COUNTER_IMAGES_OF_NMBERS.get(number));
    }
}
