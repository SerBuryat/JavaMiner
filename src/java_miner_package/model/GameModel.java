package java_miner_package.model;

import java.util.LinkedList;
import java.util.Random;

public class GameModel {
    private GameParameters gameParameters;
    private Block[][] minesField;
    private int blocksCount;
    private int minesCount;
    private int flagsCount;
    private int fieldWidthLength;
    private int fieldHeightLength;
    private boolean isGameStopped;

    public GameModel(GameParameters gameParameters, Block[][] minesField) {
        this.gameParameters = gameParameters;
        this.minesField = minesField;
        this.minesCount = this.gameParameters.getMinesCount();
        this.flagsCount = this.minesCount;
        this.fieldWidthLength = this.gameParameters.getCellsCountWidth();
        this.fieldHeightLength = this.gameParameters.getCellsCountHeight();
        this.blocksCount = this.fieldWidthLength * this.fieldHeightLength;
        this.isGameStopped = false;
    }

    public void gameStart() {
        this.fillMinesFieldWithMines(); // fill mines field with mines
        this.fillMinesFieldWithCounters(); // fill mines field with counters
    }

    private void fillMinesFieldWithMines() { // fill randomly mines field with mines
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

    private void fillMinesFieldWithCounters() { // fiil mines field with counters
        for(Block[] blockArr : this.minesField) {
            for(Block block : blockArr) {
                this.getBlockNeighbors(block);
                this.countNearMines(block);
            }
        }
    }

    public void setBlockOpen(Block block) {
        if(!this.isGameStopped) { // check game status
            if(!block.getIsOpen() && !block.getHasFlag()) { // check block status (!open && !flag)
                block.setIsOpen(true); // open this block

                if(block.getHasMine()) {// mine picked -> lose
                    gameOverLose();
                } else {
                    this.blocksCount-=1; // - 1 closed block

                    if((this.blocksCount - this.minesCount) == 0) {// all blocks(without mines) open -> win
                        gameOverWin();
                        return;
                    }

                    if(block.getMineCounter() == 0) // if this block is empty (without counter or mine)
                        this.openEmptyNeighborsBlocks(block);
                }
            }
        }
    }

    public void setAllBlocksOpen() { /// for test
        for(Block[] arr : this.minesField) {
            for(Block block : arr) {
                block.setIsOpen(true);
                this.blocksCount = this.gameParameters.getMinesCount();
            }
        }
    }

    public void setFlagOnBlock(Block block) { // or delete it if block already has it
        if(!this.isGameStopped) {
            if(block.getHasFlag()) {
                block.setFlag(false);
                this.flagsCount+=1;
            } else {
                if(this.flagsCount > 0 && !block.getIsOpen()) {
                    block.setFlag(true);
                    this.flagsCount-=1;
                }
            }
        }
    }

    private void openEmptyNeighborsBlocks(Block block) { // open all empty neighbors
            for(Block b : this.getBlockNeighbors(block)) {
                if(!(b.getIsOpen() && b.getHasMine()))
                    this.setBlockOpen(b);
            }
    }

    private LinkedList<Block> getBlockNeighbors(Block block) { // get every 'neighbor-block' for current block
        int x = block.getX();
        int y = block.getY();
        LinkedList<Block> blockNeighbors = new LinkedList<>();

        for (int i = x-1;i < x+2;i++) { // add every block from minesField[x-1][y-1] (top left neighbor) to minesField[x+2][y+2] (bottom right neighbor)
            for (int j = y-1;j < y+2;j++) {
                if (!(i < 0 || j < 0 || i > (this.fieldWidthLength - 1) || j > (this.fieldHeightLength - 1)))//block is not running out of mines field
                    if (!(i == y && j == x)) // if not current block
                        blockNeighbors.add(minesField[i][j]);
            }
        }

        return blockNeighbors;

    }

    private void countNearMines(Block block) { // count every near mine for current block
        if(!block.getHasMine()) {
            for(Block neighbor : this.getBlockNeighbors(block)) {
                if(neighbor.getHasMine())
                    block.setMineCounter(block.getMineCounter() + 1);
            }
        }
    }

    private int getRandomCoordinate(int bound) {
        return new Random().nextInt(bound);
    }

    private void gameOverWin() {
        this.isGameStopped = true;
        System.out.println("Game is over. You WIN!");
    }

    private void gameOverLose() {
        this.isGameStopped = true;
        System.out.println("Game is over. You LOSE!");
    }

    public int getFlagsCount() {
        return flagsCount;
    }

    public int getBlocksCount() {
        return blocksCount;
    }
}
