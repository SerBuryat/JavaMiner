package java_miner_package.model;

public enum LevelDifficulty {
    // minesCount = allCellsCount * EASY(10); (10% mines from all cells count on the mine field)
    EASY(10, 10, 10, "10x10, 10 mines"),
    MIDDLE(16, 16, 40, "16x16, 40 mines"),
    HARD(22, 22, 100, "22x22, 100 mines");

    private final int mineFieldWidth;
    private final int mineFieldHeight;
    private final int minesCount;
    private final String description;

    LevelDifficulty(int mineFieldWidth, int mineFieldHeight, int minesCount, String description) {
        this.mineFieldWidth = mineFieldWidth;
        this.mineFieldHeight = mineFieldHeight;
        this.minesCount = minesCount;
        this.description = description;
    }

    public int getMineFieldWidth() {
        return mineFieldWidth;
    }

    public int getMineFieldHeight() {
        return mineFieldHeight;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public String getDescription() {
        return this.toString() + "-" + description;
    }
}
