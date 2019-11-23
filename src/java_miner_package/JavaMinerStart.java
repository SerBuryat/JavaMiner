package java_miner_package;

import java_miner_package.controller.GameController;
import java_miner_package.controller.input_control.MouseControl;
import java_miner_package.model.GameModel;
import java_miner_package.model.GameParameters;

class JavaMinerStart {
    public static void main(String[] args) { // Game starts here!
        GameParameters gameParameters = new GameParameters(); // create default game parameters 15 x 15 table and EASY level difficulty
        GameModel gameModel = new GameModel(gameParameters);
        GameController gameController = new GameController(gameModel, gameParameters);
        gameParameters.setInputControlType(new MouseControl(gameController)); // set mouse control by default
    }
}
