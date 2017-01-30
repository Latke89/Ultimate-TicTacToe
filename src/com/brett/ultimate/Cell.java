package com.brett.ultimate;

/**
 * Created by Brett on 1/27/17.
 */
public class Cell {

	CellContents contents;
	int row;
	int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void clear() {
		contents = CellContents.EMPTY;
	}

	public void draw() {
		switch(contents) {
			case X:
				System.out.print(" X ");
				break;
			case O:
				System.out.print(" O ");
				break;
			case EMPTY:
				System.out.print("   ");
				break;
		}
	}
}
