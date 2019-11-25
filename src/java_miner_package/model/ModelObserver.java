package java_miner_package.model;

public interface ModelObserver {
    void setGameModelChanges(int cellsCount, int flagsCount, int minesCount, boolean isGameStopped);
}
