package java_miner_package.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageResources {
    public static HashMap<Integer, Image> COUNTER_IMAGES_OF_NUMBERS;
    public static Image MINE;
    public static Image FLAG ;
    public static Image TIMER;

    static {
        try {
            COUNTER_IMAGES_OF_NUMBERS = new HashMap<>();
            for(int i = 1; i < 9; i++) { // load images to numbers from 1 to 8
                COUNTER_IMAGES_OF_NUMBERS.put(i, ImageIO.read(new File("src/assets/number"+i+".png")));
            }
            FLAG = ImageIO.read(new File("src/assets/flag.png"));
            MINE = ImageIO.read(new File("src/assets/mine.png"));
            TIMER = ImageIO.read(new File("src/assets/timer.png"));

        } catch (IOException e) {
            System.out.println("Wrong image path! (all images are in src/assets/...)");
            e.printStackTrace();
        }
    }
}
