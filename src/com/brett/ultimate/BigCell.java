package com.brett.ultimate;

/**
 * Created by Brett on 2/1/17.
 */
public class BigCell {
	CellContents contents;
	int row;
	int column;
	String drawContents = "";

	public BigCell(int row, int column) {
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
				System.out.println(" X ");
				break;
			case O:
				drawContents = " O ";
				System.out.println(" O ");
				break;
			case EMPTY:
				drawContents = "   ";
				System.out.println("   ");
				break;
			case D:
				drawContents = " D ";
				System.out.println(" D ");
				break;
		}

	}

}
