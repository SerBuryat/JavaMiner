package java_miner_package.model;

public class Player {
    private GameParameters gameParameters;
    private int flagsCount;

    public Player(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
        this.flagsCount = gameParameters.getFlagsCount();
    }
}
