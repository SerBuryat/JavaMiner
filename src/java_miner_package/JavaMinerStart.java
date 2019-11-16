package java_miner_package;

import java_miner_package.controller.GameController;
import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;

class JavaMinerStart {
    public static void main(String[] args) { // Game starts here!
        GameParameters gameParameters = new GameParameters(); // create default game parameters
        GameModel gameModel = new GameModel(gameParameters); // create game model with this parameters
        new GameController(gameModel, gameParameters); // create game controller with this model
    }
}
