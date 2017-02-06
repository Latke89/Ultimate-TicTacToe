# Ultimate-TicTacToe

Instructions:
When the game starts, the user is prompted to choose one of the 9 boards to start on. The large board is laid out like so:

1 | 2 | 3
----------
4 | 5 | 6 
----------
7 | 8 | 9
----------

And inside each of those, is it's own board.

After the user chooses a board, they are prompted to choose their action for the turn.
The user can either "Move", erase a tile that is not empty, or owned by them with "Erase", switch the contents of a non-finished game board with "Switch", or get the status of the game with "Status".

If "Move" is chosen, the player chooses to set down their tile in the same way they chose a board - by choosing a number 1-9.

If "Erase" is chosen, the player chooses a non-empty square that is occupied by the opponent, and erases that tile. This option keeps the opponent on the same board.

If "Switch" is chosen, all of the tiles in the current game board switch from X to O, and vice-versa. This option allows the opponent to choose to move to any open game board.

If "Status" is chosen, a board that holds the status of the game is printed to the console, allowing the players to keep track of how they are doing, and which boards they have won, and which boards ended in a draw.

Wherever the current user places their tile determines what board the opponent places their tile, e.g. Player one places their tile in square 5, right in the middle of the board. Player two then has to play their tile in Board 5, and places it in square 8, the bottom center square. Player One then has to play on Board 8, in the bottom center board. The moves continue in this fashion until a player is sent to a board that has been completed, then they are given a list of boards to choose from, and are freely allowed to move to whichever board they choose.

The game is over when either player wins three boards in a row, column, or diagonally, or when there is a draw.

####Known issues / bugs

Issue with the UltimateDisplay not updating properly when winning three boards in the same row. Does not happen with column, diagonal, or reverse diagonal.
