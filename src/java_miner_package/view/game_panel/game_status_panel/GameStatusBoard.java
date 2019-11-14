package java_miner_package.view.game_panel.game_status_panel;

import java_miner_package.model.GameParameters;
import java_miner_package.view.game_panel.game_mines_field_panel.block_decorator.DecoratorImageResources;
import java_miner_package.view.game_panel.game_mines_field_panel.DrawCell;

import javax.swing.*;
import java.awt.*;

public class GameStatusBoard extends JPanel {
    private final JLabel flagsCount;
    private final JLabel minesCount;
    private final JLabel cellsCount;
    private final int fontSize = 22;
    private final int iconSize = 30;

    public GameStatusBoard(GameParameters gameParameters) { // not a flexible panel, need to regroup a panel after make changes
        Font font = new Font("Serif", Font.PLAIN, this.fontSize);
        this.flagsCount = this.labelCreator("x " + gameParameters.getFlagsCount(), DecoratorImageResources.FLAG, font);
        this.minesCount = this.labelCreator("x " + gameParameters.getMinesCount(), DecoratorImageResources.MINE, font);
        this.cellsCount = this.labelCreator("x " + gameParameters.getCellsCount(), font);

        this.setLayout(new GridLayout(4,1)); // change if need more components in status bar
        this.add(this.flagsCount);
        this.add(this.minesCount);
        this.add(this.cellsCount);
    }

    public void setFlagsCount(int flagsCount) {
        this.flagsCount.setText("x " + flagsCount);
    }

    public void setCellsCount(int blocksCount) {
        this.cellsCount.setText("x " + blocksCount);
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
        DrawCell drawBlock = new DrawCell(this.cellsCount.getX(), this.cellsCount.getY() + this.fontSize, this.iconSize, this.iconSize);
        g.setColor(drawBlock.getClosedColor());
        g.fillRoundRect(drawBlock.getX(), drawBlock.getY(), drawBlock.getCellSizeWidth(), drawBlock.getCellSizeHeight(), drawBlock.getCellArc(), drawBlock.getCellArc());
        g.setColor(drawBlock.getBordersColor());
        g.drawRoundRect(drawBlock.getX(), drawBlock.getY(), drawBlock.getCellSizeWidth(), drawBlock.getCellSizeHeight(), drawBlock.getCellArc(), drawBlock.getCellArc());
    }
}
