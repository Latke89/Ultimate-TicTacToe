package com.brett.ultimate;

/**
 * Created by Brett on 1/27/17.
 */
public class Board {
	public static final int ROWS = 3;
	public static final int COLS = 3;

	Cell[][] cells;
	int currentRow;
	int currentCol;

	public Board() {
		cells = new Cell[ROWS][COLS];
		for (int row = 0; row < ROWS; row++){
			for (int col = 0; col < COLS; col++){
				cells[row][col] = new Cell(row, col);
			}
		}
	}


	public void initializeBoard() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				cells[row][col].clear();
			}
		}
	}


	public boolean isDraw() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if(cells[row][col].contents == CellContents.EMPTY) {
					return false; // if empty cell, continue game
				}
			}
		}
		return true; // if no empty cell, game ends in Draw
	}

	public boolean isWin(CellContents current) {
		if ((cells[currentRow][0].contents == current
				&& cells[currentRow][1].contents == current
				&& cells[currentRow][2].contents == current)
				|| (cells[0][currentCol].contents == current
				&& cells[1][currentCol].contents == current
				&& cells[2][currentCol].contents == current)
				|| (cells[0][0].contents == current
				&& cells[1][1].contents == current
				&& cells[2][2].contents == current)
				|| (cells[0][2].contents == current
				&& cells[1][1].contents == current
				&& cells[2][0].contents == current)){
			return true;
		}
		return false;
	}

	public void drawBoard() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				cells[row][col].draw();
				if(col < COLS - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if(row < ROWS - 1) {
				System.out.println("-----------");
			}
		}
	}
}
