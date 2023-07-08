# Duck Hunt Game

This project is a simplified version of the Duck Hunt game developed using the JavaFX framework. Duck Hunt is a classic light gun shooter video game originally released in 1984 for the Nintendo Entertainment System (NES). The objective of the game is to shoot ducks that appear on the screen using a gun-shaped controller.

## Introduction

In this assignment, you will gain practice in developing a Graphical User Interface (GUI) application using JavaFX. The GUI allows users to interact with the game using mouse and keyboard inputs, such as aiming at ducks and shooting them. The game consists of multiple levels with increasing difficulty.

## JavaFX Framework

JavaFX is a software platform for creating and delivering desktop applications and Rich Internet Applications (RIAs). It provides support for desktop computers and web browsers on various operating systems, including Windows, Linux, and macOS. For this project, you will be using JavaFX to develop the Duck Hunt game.

## Title Screen

The game starts with a title screen where the user is presented with the game's title, "HUBBM Duck Hunt," and a flashing text informing them to press the "ENTER" key to play or the "ESC" key to exit. The title screen plays a background music loop, "assets/effects/Title.mp3." The game's favicon is set to "assets/favicon/1.png." The title and favicon remain consistent throughout the game.

## Background Selection Screen

After the title screen, the user is taken to the background selection screen. Here, they can change the background and crosshair options using the arrow keys. Instructions are displayed on the screen, informing the user to navigate using the arrow keys, start the game by pressing "ENTER," or exit by pressing "ESC." The background selection screen also provides an option to go back to the title screen. The background music from the title screen continues playing without interruption during this screen.

## Game

The game itself features a gameplay area where ducks fly across the screen, and the player must aim and shoot them using the mouse. The cursor is changed to a selected crosshair icon during the game. If the user moves the mouse outside the game window, the cursor returns to the system's default icon. The game consists of multiple levels, each with a different number of ducks and difficulty. Ducks can move in six directions (left, right, across the screen) and must be shot down with the limited ammo available for each level. If the player runs out of ammo before shooting all the ducks, the game ends, and a game over sound effect, "assets/effects/GameOver.mp3," is played.

Successful completion of a level (shooting all the ducks) triggers a level completed sound effect, "assets/effects/LevelCompleted.mp3." If the player completes the final level, a game completed sound effect, "assets/effects/GameCompleted.mp3," is played. In both cases, the player is informed of their achievement and given the option to play again from the first level by pressing "ENTER" or exit to the title screen by pressing "ESC."

## Scaling

The game supports scaling, allowing the user to adjust the size of the game window. A scale parameter, `SCALE` (double format), is provided in the main class (DuckHunt), which scales the game elements accordingly. The default value for `SCALE` is 3, but it can be modified to suit different screen resolutions.

## Adjusting Volume

The game also supports adjusting the volume of sound effects. A volume parameter, `VOLUME` (double format), is provided in the main class (DuckHunt), allowing the user to control the volume level. The value of `VOLUME` ranges from 0 to 1, where 0 corresponds to no sound, and 1 corresponds to maximum volume. The default value for `VOLUME` is 0.025, but it can be adjusted based on the user's sound system.

## Usage

To play the Duck Hunt game, follow these steps:

1. Launch the game.
2. On the title screen, press "ENTER" to start the game or "ESC" to exit.
3. On the background selection screen, navigate using the arrow keys to select backgrounds and crosshair options.
4. Press "ENTER" to start the game with the selected configurations or "ESC" to go back to the title screen.
5. During the game, use the mouse to aim at ducks and click to shoot.
6. Pay attention to the ammo left and level information displayed on the screen.
7. Complete each level by shooting all the ducks within the given ammo limit.
8. If you run out of ammo before completing the level, it's game over.
9. If you successfully complete all the levels, the game is completed.
10. Press "ENTER" to play again from the first level or "ESC" to exit to the title screen.

Note: The game uses asset files for sound effects, backgrounds, and other visual elements. Please ensure that you have the required asset files in the specified directory structure for the game to run successfully.

## Acknowledgements

This project was implemented using JavaFX and adheres to the principles of object-oriented programming. The game design and implementation follow the guidelines provided in the assignment.

# Demo Video

 https://www.youtube.com/watch?v=qdmMKZqPPxQ&t=170s



[BBM104_S23_PA4_v1.pdf](https://github.com/SerhatAkbulut1/Assignment-7/files/11971746/BBM104_S23_PA4_v1.pdf)
