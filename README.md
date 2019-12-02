# JavaMiner
JavaMiner - clone of the classic MinesWeeper game.
## Getting started
- **Need jdk 1.8 or higher**
- Clone this repository and run
```
GitBush: git clone https://github.com/SerBuryat/JavaMiner.git

Command line: C:\(name project folder)\JavaMiner\src\java_miner_package> java JavaMinerStart.java
(or start it in your IDE)
```
## Game Rules
You are presented with a board of squares. Some squares contain mines (bombs), others don't. 
If you click on a square containing a bomb, you lose. 
If you manage to click all the squares (without clicking on any bombs) you win.
Clicking a square which doesn't have a bomb reveals the number of neighbouring squares containing bombs.
Use this information plus some guess work to avoid the bombs.
## Game Status
Showing on the right side of the game window.

Current game status:
- Timer
- Closed cells
- Mines count
- Flags remains

## Game Сontrol
##### Mouse
- To open a square, point at the square and click on it. 
- To mark a square you think is a bomb, point and right-click.
##### KeyBoard
- To navigate cell pointer use arrows.
- To open a square press Space. 
- To mark a square you think is a bomb, press F. 
##### Buttons
- Click 'Restart game' for restart current game.
- Click 'Back to menu' for going to main menu.
- Click 'Options' to change game parameters manually.
- Click 'Accept' or 'Cancel' for accept or cancel manually game parameters.


## Code Architecture
To see code architecture description, open [CODE_ARCHITECTURE_DESCRIPTION.md](CODE_ARCHITECTURE_DESCRIPTION.md).

## Author

* **Anosov Artem** - *All project work* - [SerBuryat](https://github.com/SerBuryat)
