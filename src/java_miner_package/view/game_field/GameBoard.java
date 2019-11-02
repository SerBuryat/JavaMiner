package java_miner_package.view.game_field;

import java_miner_package.controller.MouseController;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private int blockWidth;
    private int blockHeight;
    private int countCellsWidth;
    private int countCellsHeight;

    public GameBoard (int countCellsWidth, int countCellsHeight) {
        this.countCellsWidth = countCellsWidth;
        this.countCellsHeight = countCellsHeight;
        addMouseListener(new MouseController());
    }

    public void paint(Graphics g) { // drawing gameField
        super.paint(g);

        for(int i = 0; i < countCellsWidth; i++) {
            for(int j = 0; j < countCellsHeight; j++) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRoundRect(i * getBlockWidth(), j * getBlockHeight(), getBlockWidth(), getBlockHeight(), 10, 10);
                g.setColor(Color.BLACK);
                g.drawRoundRect(i * getBlockWidth(), j * getBlockHeight(), getBlockWidth(), getBlockHeight(), 10, 10);
            }
        }
    }

    public int getBlockWidth() {
        return blockWidth = (int) (this.getSize().getWidth() / this.countCellsWidth);
    }

    public int getBlockHeight() {
        return blockHeight = (int) (this.getSize().getHeight() / this.countCellsHeight);
    }
}
