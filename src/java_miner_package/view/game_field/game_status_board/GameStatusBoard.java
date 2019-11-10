package java_miner_package.view.game_field.game_status_board;

import java_miner_package.model.GameParameters;
import java_miner_package.view.game_field.game_board.block_decorator.DecoratorImageResources;

import javax.swing.*;
import java.awt.*;

public class GameStatusBoard extends JPanel {
    private JLabel flagsCount;
    private JLabel minesCount;

    public GameStatusBoard(GameParameters gameParameters) {
        this.flagsCount = new JLabel();
        this.flagsCount.setText("x " + gameParameters.getFlagsCount());
        this.flagsCount.setFont(new Font("Serif", Font.PLAIN, 25));

        this.minesCount = new JLabel();
        this.minesCount.setText("x " + gameParameters.getMinesCount());
        this.minesCount.setFont(new Font("Serif", Font.PLAIN, 25));

        this.setLayout(new GridLayout(4,1));
        this.add(this.flagsCount);
        this.add(this.minesCount);
    }

    public void setFlagsCount(int flagsCount) {
        this.flagsCount.setText("x " + flagsCount);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(DecoratorImageResources.FLAG, this.flagsCount.getX(), this.flagsCount.getY() + 25, this.getWidth(), this.getWidth(), this);
        g.drawImage(DecoratorImageResources.MINE, this.minesCount.getX(), this.minesCount.getY() + 25, this.getWidth(), this.getWidth(), this);
    }
}
