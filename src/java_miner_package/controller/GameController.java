package java_miner_package.controller;

import java_miner_package.model.GameParametrs;
import java_miner_package.view.main_frame.GameWindow;


public class GameController {
    public static final GameController GAME_CONTROLLER = new GameController();
    private GameWindow gameWindow;
    private GameParametrs gameParametrs;

   public GameController() {
        gameParametrs = new GameParametrs(10,1);
    }

    public void gameStart(GameParametrs gameParametrs) {
        ;
    }

    public void initialize() { // initializing game parameters
        gameWindow = new GameWindow();
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public GameParametrs getGameParametrs() {
        return gameParametrs;
    }
}
