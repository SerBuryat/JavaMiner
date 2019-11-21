package java_miner_package.model;

public enum LevelDifficulty {
    EASY(0.1f), // minesCount = allCellsCount * EASY(10); (10% mines from all cells count on the mine field)
    MIDDLE(0.15f),
    HARD(0.2f);

    private final float value;

    LevelDifficulty(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
