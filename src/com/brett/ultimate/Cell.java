package com.brett.ultimate;

/**
 * Created by Brett on 1/27/17.
 */
public class Cell {

	CellContents contents;
	int row;
	int column;
	String drawContents = "";


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
				drawContents = " X ";
				System.out.print(" X ");
				break;
			case O:
				drawContents = " O ";
				System.out.print(" O ");
				break;
			case EMPTY:
				drawContents = "   ";
				System.out.print("   ");
				break;
		}
	}
}
