package java_miner_package.view.game_panel;

import java_miner_package.model.Cell;
import java_miner_package.model.GameModel;
import java_miner_package.model.ModelObserver;
import java_miner_package.view.game_panel.game_paint_board_panel.cell_decorator.DecoratorImageResources;
import java_miner_package.view.game_panel.game_paint_board_panel.DrawingCell;

import javax.swing.*;
import java.awt.*;

public class GameStatusBoard extends JPanel implements ModelObserver {
    private final JLabel flagsCount;
    private final JLabel minesCount;
    private final JLabel cellsCount;
    private final int fontSize = 22;
    private final int iconSize = 30;

    GameStatusBoard(GameModel gameModel) { // not a flexible panel, need to regroup a panel after making changes
        Font font = new Font("Serif", Font.PLAIN, this.fontSize);
        this.flagsCount = this.labelCreator("x " + gameModel.getFlagsCount(), DecoratorImageResources.FLAG, font);
        this.minesCount = this.labelCreator("x " + gameModel.getMinesCount(), DecoratorImageResources.MINE, font);
        this.cellsCount = this.labelCreator("x " + gameModel.getCellsCount(), font);

        this.setLayout(new GridLayout(4,1)); // change if need more components in status bar
        this.add(this.flagsCount);
        this.add(this.minesCount);
        this.add(this.cellsCount);
        gameModel.registerObserver(this);
    }

    @Override
    public void setGameModelChanges(int cellsCount, int flagsCount, int minesCount, int fieldWidth, int fieldHeight) {
        this.flagsCount.setText("x " + flagsCount);
        this.cellsCount.setText("x " + cellsCount);
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

        DrawingCell drawBlock = new DrawingCell(new Cell(this.cellsCount.getX(), this.cellsCount.getY() + this.fontSize), this.iconSize, this.iconSize);
        g.setColor(drawBlock.getClosedColor());
        g.fillRoundRect(drawBlock.getX(), drawBlock.getY(), drawBlock.getCellWidth(), drawBlock.getCellHeight(), drawBlock.getCellArc(), drawBlock.getCellArc());
        g.setColor(drawBlock.getBordersColor());
        g.drawRoundRect(drawBlock.getX(), drawBlock.getY(), drawBlock.getCellWidth(), drawBlock.getCellHeight(), drawBlock.getCellArc(), drawBlock.getCellArc());
    }
}
