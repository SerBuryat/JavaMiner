package java_miner_package.view.game_field.game_board.block_decorator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DecoratorImageResources {
    public static HashMap<Integer, Image> COUNTER_IMAGES_OF_NMBERS;
    public static Image MINE;
    public static Image FLAG ;

    static {
        try {
            COUNTER_IMAGES_OF_NMBERS = new HashMap<>();
            COUNTER_IMAGES_OF_NMBERS.put(1, ImageIO.read(new File("src/assets/number1.png")));
            COUNTER_IMAGES_OF_NMBERS.put(2, ImageIO.read(new File("src/assets/number2.png")));
            COUNTER_IMAGES_OF_NMBERS.put(3, ImageIO.read(new File("src/assets/number3.png")));
            COUNTER_IMAGES_OF_NMBERS.put(4, ImageIO.read(new File("src/assets/number4.png")));
            COUNTER_IMAGES_OF_NMBERS.put(5, ImageIO.read(new File("src/assets/number5.png")));
            COUNTER_IMAGES_OF_NMBERS.put(6, ImageIO.read(new File("src/assets/number6.png")));
            COUNTER_IMAGES_OF_NMBERS.put(7, ImageIO.read(new File("src/assets/number7.png")));
            COUNTER_IMAGES_OF_NMBERS.put(8, ImageIO.read(new File("src/assets/number8.png")));
            FLAG = ImageIO.read(new File("src/assets/flag.png"));
            MINE = ImageIO.read(new File("src/assets/mine.png"));
        } catch (IOException e) {
            System.out.println("Wrong image path! (all images are in src/assets/...)");
            e.printStackTrace();
        }
    }
}
