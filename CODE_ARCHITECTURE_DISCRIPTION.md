# JavaMiner (Code Architecture description)
Here is an explanation of the code architecture (Packages, Classes, etc. . Their work and goals).

## Code hierarchy overview
Project has built on the MVC pattern.
- *Model* - Model represents an object of carrying data, 
also have logic to update controller if its data changes.

- *View* - View represents the visualization of the data that model contains.

- *Controller* - Controller acts on both model and view. 
It controls the data flow into model object and updates the view whenever data changes. 

### *java_miner_package* 
**All source code** located here 

### *assets*
Here is located .png **image resousace**

### Overview *java_miner_package* hierarchy 
1. **model** *package*
   - **Cell** *class*
   - **GameModel** *class*
   - **GameParameters** *class*
   - **LevelDifficulty** *enum*
   - **ModelObserver** *interface*
   - **ModelSubjectForObservers** *interface*
2. **view** *package*  
   - **game_paint_board** *package*
     - **cell_decorator** *package*
       - **Counter** *class*
       - **CellDecorator** *class*
       - **Flag** *class*
       - **Mine** *class*
     - **CellPointer** *class*
     - **DrawingCell** *class*
     - **GamePaintBoard** *class*
   - **game_panel** *package*
     - **GamePanel** *class*
     - **GameStatusBoard** *class*
   - **menu_panel** *package*
     - **MenuPanel** *class*
   - **options_panel** *package*
     - **JTextFieldLogic** *class*
     - **OptionsPanel** *class*
   - **ImageResources** *class*
   - **MainWindow** *class*
3. **controller** *package*  
   - **input_control** *package*
     - **InputTypeControl** *class*
     - **KeyBoardControl** *class*
     - **MouseControl** *class*
   - **GameController** *class*
   - **GameTimer** *class*
4. **JavaMinerStart** *class*
5. **MessageToUser** *class*

## Detailed code description


### 1. *model package*
All game logic located in this *package*.

#### *GameModel* *class* 
Represent a game logic:

- create mine field
- fill mine field with cells, mines, counters, etc.
- open cells,set flags, etc.
- implements **ModelSubjectForObservers** *interface* for give 'observers' info about some changing data(mines,cells, etc.) for 'observers'

#### *GameParameters* *class*
Represent game parameters for game initializing:

-field size
-level difficulty
-cells, flags, mines count
-input type control (mouse, keyboard)

#### *Cell* *class*
Entity which fill mines field.

#### *LevelDifficulty* *enum*
Create levels difficulty with description.

#### *ModelSubjectForObservers* *interface* and *ModelObserver* *interface*
Represent a **Observer** *pattern*.
In this project *Subject* is GameModel gives an info about changing some data (count of flags or closed cells).


### 2.*view* *package*
All game view-represantation located in this *package*. 

#### *game_paint_board package*
Contains classes for 'drawing game'.

- **cell_decorator** *package* - has entities for drawing:
  - Flag
  - Counter
  - Mine
  - CellDecorator

- **GamePaintBoard** *class* - drawing minefield and everything on it(open cells, close cells, mines, flags, etc.)

- **DrawingCell** *class* - for represnting Cell(model entity) on GamePaintBoard.

- **CellPointer** *class* - pointer for input controller which 'walking' on minesfield and user control it by mouse or keyboard.

#### *game_panel package*
Contains classes for represent game panel (loading when game strats)

- **GamePanel** *class* - show gamefield after game starts

- **GameStatusBoard** *class* - show game status (count of flags or close cells , timer or else)


#### *menu_panel package*

- **MenuPanel** *class* - show main game menu after starting this application.

#### *options_panel package*

- **OptionsPanel** *class* - show game options for change game parameters manually

- **JTextFieldLogic** *class* - logic for textfield from OptionsPanel (to check input data corectness)

#### *ImageResources class*

Has static fields whic representing .png images for mine, flag, cell, etc.

#### *MainWindow class*

Represent main frame of application. Every panel loading here.


### 3.*controller* *package*
Control and make 'work' Model and View together. 

#### *input_control package*

- **InputTypeControl** *class* - abstract extends when you need a new control game type (see MouseControl class or KeyBoard control class).

- **MouseControl** *class* - represent input control type in the game by mouse.

- **KeyBoardControl** *class* - represent input control type in the game by keyboard.

#### *GameController class*

Represent a main controller : 
- Initialize and start game (model and view representation together)
- Repaint GamePaintBoard after 'actions' (set flag, open cell, pointer moves, etc.)
- Move CellPoiner which controlling by user

#### *GameTimer class*

Entity for GameStatusBoard which show a game time. (how much user playing in the current game) 


### 4. *JavaMinerStart* *class*
Has *main* method. Application runs here.


### 5. *MessageToUser* *class*
 Class - 'helper'. Has only one static method for getting message in shortcut window. 
