package java_miner_package.view.game_field.game_status_board;

import java_miner_package.model.GameParameters;
import java_miner_package.view.game_field.game_board.block_decorator.DecoratorImageResources;
import java_miner_package.view.game_field.game_board.DrawBlock;

import javax.swing.*;
import java.awt.*;

public class GameStatusBoard extends JPanel {
    private JLabel flagsCount;
    private JLabel minesCount;
    private JLabel blocksCount;
    private Font font;
    private int fontSize = 22;
    private int iconSize = 30;

    public GameStatusBoard(GameParameters gameParameters) { // not a flexible panel, need to regroup a panel after make changes
        this.font = new Font("Serif", Font.PLAIN, this.fontSize);

        this.flagsCount = this.labelCreator("x " + gameParameters.getFlagsCount(), DecoratorImageResources.FLAG, this.font);
        this.minesCount = this.labelCreator("x " + gameParameters.getMinesCount(), DecoratorImageResources.MINE, this.font);
        this.blocksCount = this.labelCreator("x " + gameParameters.getBlocksCount(), this.font);

        this.setLayout(new GridLayout(4,1)); // change if need more components in status bar
        this.add(this.flagsCount);
        this.add(this.minesCount);
        this.add(this.blocksCount);
    }

    public void setFlagsCount(int flagsCount) {
        this.flagsCount.setText("x " + flagsCount);
    }

    public void setBlocksCount(int blocksCount) {
        this.blocksCount.setText("x " + blocksCount);
    }

    public void setMinesCount(int minesCount) {
        this.minesCount.setText("x " + minesCount);
    }

    private JLabel labelCreator(String text, Image image, Font font) {
        JLabel label = new JLabel(text);
        label.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(this.iconSize, this.iconSize, Image.SCALE_DEFAULT)));
        label.setFont(font);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);

        return label;
    }

    private JLabel labelCreator(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);

        return label;
    }

    @Override
    public void paintComponent(Graphics g) { // drawing icon block
        super.paintComponent(g);
        DrawBlock drawBlock = new DrawBlock(this.blocksCount.getX(), this.blocksCount.getY() + this.fontSize, this.iconSize, this.iconSize);
        g.setColor(drawBlock.getClosedColor());
        g.fillRoundRect(drawBlock.getX(), drawBlock.getY(), drawBlock.getBlockSizeWidth(), drawBlock.getBlockSizeHeight(), drawBlock.getBlockArc(), drawBlock.getBlockArc());
        g.setColor(drawBlock.getBordersColor());
        g.drawRoundRect(drawBlock.getX(), drawBlock.getY(), drawBlock.getBlockSizeWidth(), drawBlock.getBlockSizeHeight(), drawBlock.getBlockArc(), drawBlock.getBlockArc());
    }
}
