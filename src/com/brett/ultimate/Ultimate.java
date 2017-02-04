package com.brett.ultimate;

import java.util.Scanner;

/**
 * Created by Brett on 2/2/17.
 */
public class Ultimate {
	private static final int ROWS = 3;
	private static final int COLS = 3;
	private int currentRow;
	private int currentColumn;
	private int currentMove;
	private boolean hasSwappedThisTurn = false;
	private Board currentBoard;
	private CellContents player;
	Board[][] bigBoard;
	Board ultimateDisplay;

	//Constructor that initializes the cells in the Ultimate Board array, and Ultimate display to empty
	public Ultimate() {
		bigBoard = new Board[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				bigBoard[row][col] = new Board();
				bigBoard[row][col].initializeBoard();
			}
		}
		ultimateDisplay = new Board();
		ultimateDisplay.initializeBoard();
	}

	// Resets all squares in the Board array and Ultimate display to empty
	public void resetUltimateBoard() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				bigBoard[row][col].initializeBoard();
			}
		}
		ultimateDisplay.initializeBoard();
	}

	// Prints the small-scale readout of the Ultimate board
	public void printUltimateDisplay() {
		System.out.println("Ultimate Board");
		ultimateDisplay.drawBoard();
	}

	// Prints the board array to the console
	public void printBoardArray() {
		int boardNum = 1;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if(bigBoard[row][col].state == GameState.PLAYING) {
					System.out.println("Board " + boardNum);
					bigBoard[row][col].drawBoard();
				}
				boardNum++;
				System.out.println();
			}
		}
	}

	// Gets the row index
	public int getRow(int boardIndex){
		return (boardIndex - 1) / 3;
	}

	// Gets the column index
	public int getCol(int boardIndex){
		return (boardIndex - 1) % 3;
	}

	// returns the specified board
	public Board getSpecificBoard(int boardIndex) {
		int row = getRow(boardIndex);
		int col = getCol(boardIndex);
		return bigBoard[row][col];
	}

	// Checks to see if the large board has been won by X or O
	public boolean checkGameStatus(CellContents player, int move) {
		int currentRow = getRow(move);
		int currentColumn = getCol(move);
		if(player == CellContents.X) {
			if(bigBoard[currentColumn][0].state == GameState.X_WIN
					&& bigBoard[currentColumn][1].state == GameState.X_WIN
					&& bigBoard[currentColumn][2].state == GameState.X_WIN) {
				return true;
			}
			if(bigBoard[0][currentRow].state == GameState.X_WIN
					&& bigBoard[1][currentRow].state == GameState.X_WIN
					&& bigBoard[2][currentRow].state == GameState.X_WIN) {
				return true;
			}
			if(bigBoard[0][0].state == GameState.X_WIN
					&& bigBoard[1][1].state == GameState.X_WIN
					&& bigBoard[2][2].state == GameState.X_WIN) {
				return true;
			}
			if(bigBoard[0][2].state == GameState.X_WIN
					&& bigBoard[1][1].state == GameState.X_WIN
					&& bigBoard[2][0].state == GameState.X_WIN) {
				return true;
			}
		} else {
			if(bigBoard[currentColumn][0].state == GameState.O_WIN
					&& bigBoard[currentColumn][1].state == GameState.O_WIN
					&& bigBoard[currentColumn][2].state == GameState.O_WIN) {
				return true;
			}
			if(bigBoard[0][currentRow].state == GameState.O_WIN
					&& bigBoard[1][currentRow].state == GameState.O_WIN
					&& bigBoard[2][currentRow].state == GameState.O_WIN) {
				return true;
			}
			if(bigBoard[0][0].state == GameState.O_WIN
					&& bigBoard[1][1].state == GameState.O_WIN
					&& bigBoard[2][2].state == GameState.O_WIN) {
				return true;
			}
			if(bigBoard[0][2].state == GameState.O_WIN
					&& bigBoard[1][1].state == GameState.O_WIN
					&& bigBoard[2][0].state == GameState.O_WIN) {
				return true;
			}
		}
		return false;
	}

	// Check to see if the large board is a draw
	public boolean isDraw() {
		for (int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				if(bigBoard[col][row].state == GameState.PLAYING) {
					return false;
				}
			}
		}
		return true;
	}

	// Initiates a move on the specified board, and checks if the current board is won by the player
	public void move(Board board, int move, CellContents player) {
		int col = getCol(move);
		int row = getRow(move);
		board.cells[row][col].contents = player;

		if(board.isDraw()) {
			ultimateDisplay.cells[row][col].contents = CellContents.D;
			board.state = GameState.DRAW;
		}
		if(board.isWin(player)) {
			if(player == CellContents.X) {
				ultimateDisplay.cells[row][col].contents = CellContents.X;
				board.state = GameState.X_WIN;
			} else {
				ultimateDisplay.cells[row][col].contents = CellContents.O;
				board.state = GameState.O_WIN;
			}
		}
	}

	// Validates that the move is a valid one
	public boolean validateMove(Board board, int move){
		int row = getRow(move);
		int col = getCol(move);

		if(board.cells[row][col].contents == CellContents.EMPTY) {
			return true;
		} else {
			return false;
		}
	}

	// Checks indicated cell, returns true if you can erase, false if it is yours, or is empty
	public boolean erase(Board board, CellContents player, int row, int col) {
		Cell cell = board.cells[row][col];
		if(cell.contents == player || cell.contents == CellContents.EMPTY) {
			System.out.println("Invalid square, try again!");
			return false;
		} else {
//			cell.contents = CellContents.EMPTY;
			return true;
		}
	}

	// Switches contents of board from X to O, and from O to X
	public void switchBoardContents(Board board) {
		Cell cell;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				cell = board.cells[row][col];
				if(cell.contents == CellContents.X) {
					cell.contents = CellContents.O;
				}
				if(cell.contents == CellContents.O) {
					cell.contents = CellContents.X;
				}
			}
		}
	}

	// Runs the game / main game loop
	public void playGame() {
		int newBoard;
		int swapCooldown = 0;
		int xSwap = 1;
		int oSwap = 1;
		int xErase = 3;
		int oErase = 3;
		String choice;

		Scanner inputScanner = new Scanner(System.in);
		printBoardArray();
		printUltimateDisplay();

		System.out.println("Please choose a board to play on (1-9)");

		int boardIndex = Integer.valueOf(inputScanner.nextLine());
		currentBoard = getSpecificBoard(boardIndex);
		player = CellContents.X;

		while(ultimateDisplay.state == GameState.PLAYING) {
			System.out.println("Current player: " + player);
			System.out.println("What would you like to do?");
			System.out.println("\"move\" \"switch\" \"erase\"");
			System.out.print("> ");

			boolean flag = true;
			while (flag) {
				choice = inputScanner.nextLine();
				if(choice.equalsIgnoreCase("move")) {
					System.out.println("Where would you like to place your piece? (1-9)");
					System.out.print("> ");
					currentMove = Integer.valueOf(inputScanner.nextLine());
					currentRow = getRow(currentMove);
					currentColumn = getCol(currentMove);

					if(validateMove(currentBoard, currentMove)) {
						move(currentBoard, currentMove, player);
						flag = false;
					} else {
						System.out.println("Invalid move, that space is occupied");
					}

				} else if(choice.equalsIgnoreCase("switch")) {
					if(((player == CellContents.X && xSwap > 0)
					|| (player == CellContents.O && oSwap > 0)) && swapCooldown == 0) {
						switchBoardContents(currentBoard);
						swapCooldown = 2;
						if(player == CellContents.X) {
							xSwap--;
						} else {
							oSwap--;
						}
						flag = false;
						hasSwappedThisTurn = true;
					}
				} else if(choice.equalsIgnoreCase("erase")) {
					if(erase(currentBoard, player, currentRow, currentColumn)) {
						if((player == CellContents.X && xErase > 0)
								|| (player == CellContents.O && oErase > 0)) {
							if(player == CellContents.X) {
								xErase--;
							} else {
								oErase--;
							}
							currentBoard.cells[currentRow][currentColumn].contents = CellContents.EMPTY;
							flag = false;
						}

					} else {
						System.out.println("The tile is either empty, or your own. Please try again.");
					}
				} else {
					System.out.println("Please enter either \"move\", \"switch\", or \"erase\"");
				}

			}

			if(checkGameStatus(player, currentMove)) {
				if(player == CellContents.X) {
					ultimateDisplay.state = GameState.X_WIN;
					System.out.println("Congratulations, X wins!");
				} else {
					ultimateDisplay.state = GameState.O_WIN;
					System.out.println("Congratulations, O wins!");
				}
			}

			if(isDraw()) {
				ultimateDisplay.state = GameState.DRAW;
				System.out.println("Game is a draw!");
			}

			if((bigBoard[currentColumn][currentRow].state != GameState.PLAYING) || hasSwappedThisTurn) {
				boolean isBoardValid = false;

				while(!isBoardValid) {
					printBoardArray();
					System.out.println("Please choose a valid board (1-9)");
					newBoard = Integer.valueOf(inputScanner.nextLine());
					currentBoard = getSpecificBoard(newBoard);
					if(currentBoard.state == GameState.PLAYING) {
						isBoardValid = true;
					} else {
						System.out.println("Please choose a valid board that has not been won");
					}
					if(hasSwappedThisTurn) {
						hasSwappedThisTurn = false;
					}
				}

			} else {
				currentBoard = getSpecificBoard(currentMove);
				printBoardArray();
			}

			if(player == CellContents.X) {
				player = CellContents.O;
			} else {
				player = CellContents.X;
			}

			if(swapCooldown > 0) {
				swapCooldown--;
			}

			System.out.println("Current Board is Board " + currentMove);
			currentBoard.drawBoard();

		}
	}

}
