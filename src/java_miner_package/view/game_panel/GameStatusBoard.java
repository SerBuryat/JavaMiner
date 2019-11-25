package java_miner_package.view.game_panel;

import java_miner_package.controller.GameTimer;
import java_miner_package.model.Cell;
import java_miner_package.model.GameModel;
import java_miner_package.model.ModelObserver;
import java_miner_package.view.ImageResources;
import java_miner_package.view.game_paint_board.DrawingCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStatusBoard extends JPanel implements ActionListener,ModelObserver {
    private final JLabel flagsCount;
    private final JLabel minesCount;
    private final JLabel cellsCount;
    private final JLabel timerCount;
    private final GameTimer gameTimer;
    private final int fontSize = 22;
    private final int iconSize = 30;

    GameStatusBoard(GameModel gameModel) { // not a flexible panel, need to regroup a panel after making changes
        // panel and components initializing
        Font font = new Font("Serif", Font.PLAIN, this.fontSize);
        this.gameTimer = new GameTimer(this);
        this.timerCount = this.labelCreator(gameTimer.getTime(), ImageResources.TIMER, font);
        this.flagsCount = this.labelCreator("x " + gameModel.getFlagsCount(), ImageResources.FLAG, font);
        this.minesCount = this.labelCreator("x " + gameModel.getMinesCount(), ImageResources.MINE, font);
        this.cellsCount = this.labelCreator("x " + gameModel.getCellsCount(), font);
        //panel layout configs
        this.setLayout(new GridLayout(4,1)); // change if need more components in status bar
        this.add(this.timerCount);
        this.add(this.flagsCount);
        this.add(this.minesCount);
        this.add(this.cellsCount);
        gameModel.registerObserver(this);
    }

    @Override
    public void setGameModelChanges(int cellsCount, int flagsCount, int minesCount, boolean isGameStopped) {
        if(isGameStopped)
            this.gameTimer.timerStop();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameTimer.timerPlusSecond();
        this.timerCount.setText(this.gameTimer.getTime());
    }
}
