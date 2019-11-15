package java_miner_package.model;

public interface ModelObserver {
    void setChanges(int cellsCount, int flagsCount, int minesCount, int fieldWidth, int fieldHeight);
}
