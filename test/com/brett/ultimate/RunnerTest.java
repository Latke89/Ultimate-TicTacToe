package com.brett.ultimate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Brett on 1/30/17.
 */
public class RunnerTest {
	Board myBoard;
	CellContents current;
	Cell cell;
	GameState gameState;
	Runner myRunner;


	@Before
	public void setUp() throws Exception {
		cell = new Cell(0, 0);
		myBoard = new Board();
		myRunner = new Runner();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void prepareCell() throws Exception {
		cell.clear();
		assertEquals(current.EMPTY, cell.contents);
	}

	@Test
	public void drawCell() throws Exception {
		cell.contents = current.X;
//		assertEquals(" X ", cell.draw());
	}

	@Test
	public void setupBoard() {
		myBoard.initializeBoard();
		for (int row = 0; row < myBoard.cells.length; row++) {
			for (int col = 0; col < myBoard.cells.length; col++) {
				assertEquals (current.EMPTY, myBoard.cells[row][col].contents);
			}
		}
	}

	@Test
	public void isDrawTest() throws Exception {
		myBoard.initializeBoard();
		myBoard.cells[0][0].contents = current.O;
		myBoard.cells[0][1].contents = current.X;
		myBoard.cells[0][2].contents = current.O;

		assertEquals(false, myBoard.isDraw());

		myBoard.cells[1][0].contents = current.X;
		myBoard.cells[1][1].contents = current.X;
		myBoard.cells[1][2].contents = current.O;
		myBoard.cells[2][0].contents = current.X;
		myBoard.cells[2][1].contents = current.O;
		myBoard.cells[2][2].contents = current.O;

		assertEquals(true, myBoard.isDraw());
	}

	@Test
	public void isWin() throws Exception {
		myBoard.initializeBoard();
		myBoard.cells[0][0].contents = current.O;
		myBoard.cells[0][1].contents = current.O;
		myBoard.cells[0][2].contents = current.O;
		assertEquals(false, myBoard.isWin(current.X));
		assertEquals(true, myBoard.isWin(current.O));

		myBoard.initializeBoard();
		myBoard.cells[0][0].contents = current.X;
		myBoard.cells[1][0].contents = current.X;
		myBoard.cells[2][0].contents = current.X;
		assertEquals(false, myBoard.isWin(current.O));
		assertEquals(true, myBoard.isWin(current.X));

		myBoard.initializeBoard();
		myBoard.cells[0][0].contents = current.X;
		myBoard.cells[1][1].contents = current.X;
		myBoard.cells[2][2].contents = current.X;
		assertEquals(false, myBoard.isWin(current.O));
		assertEquals(true, myBoard.isWin(current.X));

		myBoard.initializeBoard();
		myBoard.cells[2][0].contents = current.O;
		myBoard.cells[1][1].contents = current.O;
		myBoard.cells[0][2].contents = current.O;
		assertEquals(false, myBoard.isWin(current.X));
		assertEquals(true, myBoard.isWin(current.O));
	}
	
}