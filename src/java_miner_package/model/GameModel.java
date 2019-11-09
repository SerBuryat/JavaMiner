package java_miner_package.model;

import java_miner_package.view.game_field.Block;

import java.awt.*;
import java.util.Random;

public class GameModel {
    private GameParameters gameParameters;
    private Block[][] minesField;
    private int blocksCount;
    private int minesCount;
    private int flagsCount;
    private int fieldWidthLength;
    private int fieldHeightLength;

    public GameModel(GameParameters gameParameters, Block[][] minesField) {
        this.gameParameters = gameParameters;
        this.minesField = minesField;
        this.minesCount = this.gameParameters.getMinesCount();
        this.flagsCount = this.minesCount;
        this.fieldWidthLength = this.gameParameters.getCellsCountWidth();
        this.fieldHeightLength = this.gameParameters.getCellsCountHeight();
        this.blocksCount = this.fieldWidthLength * this.fieldHeightLength;
    }

    public void gameStart() {
        this.fillGameBoardWithMines();
    }

    public void gameOver() {

    }

    public void setBlockOpen(Point p) {
        for(Block[] arr : minesField) {
            for(Block block : arr) {
                if(block.isPointInBlockBounds(p)) {
                    block.setBlockOpen(true);
                    this.blocksCount-=1;
                    break;
                }
            }
        }
    }

    public void setAllBlocksOpen() {
        for(Block[] arr : minesField) {
            for(Block block : arr) {
                block.setBlockOpen(true);
                this.blocksCount = 0;
            }
        }
    }

    public void setFlagOnBlock(Point p) {
        for(Block[] arr : minesField) {
            for(Block block : arr) {
                if(block.isPointInBlockBounds(p)) {
                    block.setFlag(true);
                    this.flagsCount-=1;
                    break;
                }
            }
        }
    }

    private void fillGameBoardWithMines() {
        for(int i = 0; i < this.minesCount; i++) {
            int x_random = this.getRandomCoordinate(fieldWidthLength);
            int y_random = this.getRandomCoordinate(fieldHeightLength);
            while (this.minesField[x_random][y_random].getHasMine()) { // if block already has mine -> calculate another coordinate
                x_random = this.getRandomCoordinate(fieldWidthLength);
                y_random = this.getRandomCoordinate(fieldHeightLength);
            }
            this.minesField[x_random][y_random].setMine(true);
        }
    }

    private int getRandomCoordinate(int bound) {
        return new Random().nextInt(bound);
    }

}
