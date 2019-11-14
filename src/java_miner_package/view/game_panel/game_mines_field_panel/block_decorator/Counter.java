package java_miner_package.view.game_panel.game_mines_field_panel.block_decorator;

public class Counter extends DrawCellDecorator {

    public Counter(int number) {
        super.setImg(DecoratorImageResources.COUNTER_IMAGES_OF_NUMBERS.get(number));
    }
}
