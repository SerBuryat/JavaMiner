package java_miner_package.view.game_paint_board.cell_decorator;

import java_miner_package.view.ImageResources;

public class Counter extends CellDecorator {

    public Counter(int number) {
        super.setImg(ImageResources.COUNTER_IMAGES_OF_NUMBERS.get(number));
    }
}
