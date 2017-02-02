package com.brett.ultimate;

/**
 * Created by Brett on 1/29/17.
 */
public class BigBoard {

	public static final int ROWS = 3;
	public static final int COLS = 3;
	int currentrow;
	int currentcol;
	Board[][] board;

	public BigBoard() {
		board = new Board[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				board[row][col] = new Board();
			}
		}
	}

	public void initialize(){
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				board[row][col].initializeBoard();
			}
		}
	}

	public void drawBoard() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				board[row][col].drawBoard();
				if(col < COLS - 1) {
					System.out.println("");
				}
			}
			System.out.println();
			if(row < ROWS - 1) {
			}
		}

	}



}
