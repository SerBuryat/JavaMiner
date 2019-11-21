package java_miner_package.view.game_paint_board.cell_decorator;

public class Counter extends DrawCellDecorator {

    public Counter(int number) {
        super.setImg(DecoratorImageResources.COUNTER_IMAGES_OF_NUMBERS.get(number));
    }
}
