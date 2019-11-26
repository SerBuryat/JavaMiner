package java_miner_package;

import java_miner_package.controller.GameController;
import java_miner_package.controller.input_control.MouseControl;
import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;

class JavaMinerStart {
    public static void main(String[] args) { // Game starts here!
        // create default game parameters 10x10 table and 10 mines
        GameParameters gameParameters = new GameParameters(10,10,10);
        GameModel gameModel = new GameModel(gameParameters);
        GameController gameController = new GameController(gameModel, gameParameters);
        gameParameters.setInputControlType(new MouseControl(gameController)); // set mouse control by default
    }
}
