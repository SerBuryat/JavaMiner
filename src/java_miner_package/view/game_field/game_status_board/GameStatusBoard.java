package java_miner_package.view.game_field.game_status_board;

import java_miner_package.model.GameParameters;
import java_miner_package.model.Block;
import java_miner_package.view.game_field.game_board.block_decorator.DecoratorImageResources;

import javax.swing.*;
import java.awt.*;

public class GameStatusBoard extends JPanel {
    private JLabel flagsCount;
    private JLabel minesCount;
    private JLabel blocksCount;
    private int fontSize = 25;

    public GameStatusBoard(GameParameters gameParameters) { // not a flexible panel, need to regroup a panel after make changes
        this.flagsCount = new JLabel();
        this.flagsCount.setText("x " + gameParameters.getFlagsCount());
        this.flagsCount.setFont(new Font("Serif", Font.PLAIN, this.fontSize));

        this.minesCount = new JLabel();
        this.minesCount.setText("x " + gameParameters.getMinesCount());
        this.minesCount.setFont(new Font("Serif", Font.PLAIN, this.fontSize));

        this.blocksCount = new JLabel();
        this.blocksCount.setText("x " + gameParameters.getBlocksCount());
        this.blocksCount.setFont(new Font("Serif", Font.PLAIN, this.fontSize));

        this.setLayout(new GridLayout(4,1)); // change if need more components in status bar
        this.add(this.flagsCount);
        this.add(this.minesCount);
        this.add(this.blocksCount);
    }

    public void setFlagsCount(int flagsCount) {
        this.flagsCount.setText("x " + flagsCount);
    }

    public void setBlocksCount(int blocksCount) {
        this.blocksCount.setText("x " + blocksCount);;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(DecoratorImageResources.FLAG, this.flagsCount.getX(), this.flagsCount.getY() + this.fontSize, this.getWidth(), this.getWidth(), this); // draw Flag image

        g.drawImage(DecoratorImageResources.MINE, this.minesCount.getX(), this.minesCount.getY() + this.fontSize, this.getWidth(), this.getWidth(), this); // draw Mine image

        Block block = new Block(this.blocksCount.getX(), this.blocksCount.getY() + this.fontSize, this.getWidth()-1, this.getWidth());// draw Block image
        g.setColor(block.getClosedColor());
        g.fillRoundRect(block.getX(), block.getY(), block.getBlockSizeWidth(), block.getBlockSizeHeight(), block.getBlockArc(), block.getBlockArc());
        g.setColor(block.getBordersColor());
        g.drawRoundRect(block.getX(), block.getY(), block.getBlockSizeWidth(), block.getBlockSizeHeight(), block.getBlockArc(), block.getBlockArc());
    }
}
