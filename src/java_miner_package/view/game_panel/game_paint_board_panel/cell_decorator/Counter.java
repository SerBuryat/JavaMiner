package java_miner_package.view.game_panel.game_paint_board_panel.cell_decorator;

public class Counter extends DrawCellDecorator {

    public Counter(int number) {
        super.setImg(DecoratorImageResources.COUNTER_IMAGES_OF_NUMBERS.get(number));
    }
}
