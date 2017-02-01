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
	BigCell bigCell;


	@Before
	public void setUp() throws Exception {
		cell = new Cell(0, 0);
		bigCell = new BigCell(0, 0);
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
		cell.draw();
		assertEquals(" X ", cell.drawContents);
		cell.clear();

		cell.contents = CellContents.O;
		cell.draw();
		assertEquals(" O ", cell.drawContents);
		cell.clear();

		cell.contents = CellContents.EMPTY;
		cell.draw();
		assertEquals("   ", cell.drawContents);
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

	@Test
	public void boardState() throws Exception {
		myRunner.myBoard = new Board();
		myRunner.myBoard.cells[0][0].contents = CellContents.X;
		myRunner.myBoard.cells[0][1].contents = CellContents.O;
		myRunner.myBoard.cells[0][2].contents = CellContents.X;
		myRunner.myBoard.cells[1][0].contents = CellContents.O;
		myRunner.myBoard.cells[1][1].contents = CellContents.O;
		myRunner.myBoard.cells[1][2].contents = CellContents.X;
		myRunner.myBoard.cells[2][0].contents = CellContents.X;
		myRunner.myBoard.cells[2][1].contents = CellContents.X;
		myRunner.myBoard.cells[2][2].contents = CellContents.O;

		myRunner.checkBoardState(CellContents.O);
		assertEquals(GameState.DRAW, myRunner.state);

		myRunner.myBoard.initializeBoard();
		myRunner.myBoard.cells[0][0].contents = CellContents.X;
		myRunner.myBoard.cells[0][1].contents = CellContents.X;
		myRunner.myBoard.cells[0][2].contents = CellContents.X;
		myRunner.checkBoardState(CellContents.X);
		assertEquals(GameState.X_WIN, myRunner.state);

		myRunner.myBoard.initializeBoard();
		myRunner.myBoard.cells[0][2].contents = CellContents.O;
		myRunner.myBoard.cells[1][1].contents = CellContents.O;
		myRunner.myBoard.cells[2][0].contents = CellContents.O;
		myRunner.checkBoardState(CellContents.O);
		assertEquals(GameState.O_WIN, myRunner.state);
	}

	@Test
	public void swap() throws Exception {
		myRunner.myBoard = new Board();
		myRunner.myBoard.initializeBoard();

		myRunner.myBoard.cells[0][0].contents = CellContents.X;
		myRunner.myBoard.cells[0][1].contents = CellContents.O;

		assertEquals(CellContents.X, myRunner.myBoard.cells[0][0].contents);
		assertEquals(CellContents.O, myRunner.myBoard.cells[0][1].contents);

		myRunner.swap(myRunner.myBoard);

		assertEquals(CellContents.O, myRunner.myBoard.cells[0][0].contents);
		assertEquals(CellContents.X, myRunner.myBoard.cells[0][1].contents);
	}

	@Test
	public void bigCell() {
		bigCell.clear();
		assertEquals(CellContents.EMPTY, bigCell.contents);

		bigCell.draw();
		assertEquals("   ", bigCell.drawContents);
		bigCell.clear();
		bigCell.contents = CellContents.X;
		bigCell.draw();
		assertEquals(" X ", bigCell.drawContents);

		bigCell.contents = CellContents.O;
		bigCell.draw();
		assertEquals(" O ", bigCell.drawContents);

		bigCell.contents = CellContents.D;
		bigCell.draw();
		assertEquals(" D ", bigCell.drawContents);

	}

}