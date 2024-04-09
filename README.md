# Falling Words Game

## Description
This game operates as follows:
- When the start button is pressed, a specified number of words (a command line parameter) start falling at the same time from the top of the panel, but fall at different speeds – some faster, some slower.
- Words disappear when they reach the pink zone, whereupon the Missed counter is incremented by one and a new word starts falling (with a different speed).
- The user attempts to type all the words before they hit the pink zone, pressing enter after each one.
- If a user types a word correctly, that word disappears, then the Caught counter is incremented by one and the Score counter is incremented by the length of the word. A new word then starts falling (with a different speed).
- If a user types a word incorrectly, it is ignored.
- The game continues until either of the following situations:
  1. The specified maximum number of words (also a command-line parameter) have been typed in correctly. When this occurs, the user has won and the screen displays “Well done!”
  2. The user misses three words. When this occurs, the user has lost and the screen displays “Game Over!”
  3. The user presses the Quit Game button, stops the current game and display “Game Over!”.
- The user can start again at any time, beginning a new game by pressing the Start button.
- The user presses the Exit button to exit the app completely.
- At any point, the user can press the Pause button to halt the game. The game then restarts from the same point when the Start button is pressed again. However, they user should not be able to type in words (i.e. cheat) when game is paused.

## Usage
- Specify the number of words and maximum number of words as command-line parameters.
- Press the Start button to begin the game.
- Type the falling words correctly before they reach the pink zone.
- Press Enter after typing each word.
- Win the game by typing the specified maximum number of words correctly or lose by missing three words.
- Press the Quit Game button to stop the current game.
- Press the Exit button to exit the application completely.
- Press the Pause button to halt the game. Press the Start button to resume.

## Installation
1. Clone or download the repository.
2. Ensure you have Python installed on your system.
3. Run the program with the specified command line parameters as described in the Usage section.

## Dictionary File Format
The dictionary file should contain one word per line. The first line of the file should specify the total number of words in the file.

## Example
To run the program with 50 total words, 5 words falling at any point, and using the provided dictionary file `words.txt`, execute the following command: java TypingTutorApp 50 5 words.txt
