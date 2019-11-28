package java_miner_package.model;

public enum LevelDifficulty {
    // minesCount = allCellsCount * EASY(10); (10% mines from all cells count on the mine field)
    EASY(0.1f, "10% of mines"),
    MIDDLE(0.15f, "15% of mines"),
    HARD(0.2f, "20% of mines");

    private final float value;
    private final String description;

    LevelDifficulty(float value, String description) {
        this.value = value;
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public String getDescription() {
        return this.toString() + "-" + description;
    }
}
