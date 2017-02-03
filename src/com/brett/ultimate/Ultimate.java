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
	private int lastRow;
	private int lastColumn;
	private CellContents player;
	Board[][] bigBoard;
	Board ultimateDisplay;

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

	public void resetUltimateBoard() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				bigBoard[row][col].initializeBoard();
			}
		}
		ultimateDisplay.initializeBoard();
	}

	public void printUltimateDisplay() {
		System.out.println("Ultimate Board");
		ultimateDisplay.drawBoard();
	}

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

	public int getRow(int boardIndex){
		return (boardIndex - 1) / 3;
	}

	public int getCol(int boardIndex){
		return (boardIndex - 1) % 3;
	}

	public Board getSpecificBoard(int boardIndex) {
		int row = getRow(boardIndex);
		int col = getCol(boardIndex);
		return bigBoard[col][row];
	}

	public boolean checkGameStatus(CellContents player, int currentRow, int currentColumn) {
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

	public void move(Board board, int move, CellContents player) {
		int col = getCol(move);
		int row = getRow(move);
		board.cells[row][col].contents = player;
	}

	public void playGame() {
		int row;
		int column;
		Scanner inputScanner = new Scanner(System.in);
		printBoardArray();
		printUltimateDisplay();
		System.out.println("Please choose a board to play on (1-9)");
		int boardIndex = Integer.valueOf(inputScanner.nextLine());
		Board currentBoard = getSpecificBoard(boardIndex);
		player = CellContents.X;

		while(ultimateDisplay.state == GameState.PLAYING) {
			System.out.println("Where would you like to place your piece? (1-9)");
			int move = Integer.valueOf(inputScanner.nextLine());
			row = getRow(move);
			column = getCol(move);

			move(currentBoard, move, player);

			if(currentBoard.isDraw()) {
				ultimateDisplay.cells[column][row].contents = CellContents.D;
			}
			if(currentBoard.isWin(player)) {
				if(player == CellContents.X) {
					ultimateDisplay.cells[column][row].contents = CellContents.X;
				} else {
					ultimateDisplay.cells[column][row].contents = CellContents.O;
				}
			}

			if(checkGameStatus(player, row, column)) {
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

			if(bigBoard[column][row].state != GameState.PLAYING) {
				System.out.println("Please choose a board (1-9)");
				int newBoard = Integer.valueOf(inputScanner.nextLine());
				currentBoard = getSpecificBoard(newBoard);
			} else {
				currentBoard = getSpecificBoard(move);
			}

			if(player == CellContents.X) {
				player = CellContents.O;
			} else {
				player = CellContents.X;
			}

//			printBoardArray();
//			printUltimateDisplay();
			currentBoard.drawBoard();

		}
	}

}
