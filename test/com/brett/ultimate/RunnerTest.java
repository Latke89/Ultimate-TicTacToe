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
	Ultimate ultimate;

	@Before
	public void setUp() throws Exception {
		cell = new Cell(0, 0);
		bigCell = new BigCell(0, 0);
		myBoard = new Board();
		myRunner = new Runner();
		ultimate = new Ultimate();
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
		myRunner.myBoard.initializeBoard();
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
		assertEquals(GameState.DRAW, myRunner.myBoard.state);

		myRunner.myBoard.initializeBoard();
		myRunner.myBoard.cells[0][0].contents = CellContents.X;
		myRunner.myBoard.cells[0][1].contents = CellContents.X;
		myRunner.myBoard.cells[0][2].contents = CellContents.X;
		myRunner.checkBoardState(CellContents.X);
		assertEquals(GameState.X_WIN, myRunner.myBoard.state);

		myRunner.myBoard.initializeBoard();
		myRunner.myBoard.cells[0][2].contents = CellContents.O;
		myRunner.myBoard.cells[1][1].contents = CellContents.O;
		myRunner.myBoard.cells[2][0].contents = CellContents.O;
		myRunner.checkBoardState(CellContents.O);
		assertEquals(GameState.O_WIN, myRunner.myBoard.state);
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

	@Test
	public void getIndex() {
		int boardIndex = 5;
		int row = ultimate.getRow(boardIndex);
		int col = ultimate.getCol(boardIndex);

		assertEquals(1, row);
		assertEquals(1, col);

		boardIndex  = 9;
		row = ultimate.getRow(boardIndex);
		col = ultimate.getCol(boardIndex);

		assertEquals(2, row);
		assertEquals(2, col);
	}

	@Test
	public void getBoard(){
		int boardIndex = 2;
		myBoard = ultimate.getSpecificBoard(boardIndex);

		assertEquals(ultimate.bigBoard[1][0], myBoard);
	}

//	@Test
//	public void ultimateBoardWin() {
//		int currentColumn = 0;
//		int currentRow = 2;
//		ultimate.bigBoard[0][0].state = GameState.X_WIN;
//		ultimate.bigBoard[0][1].state = GameState.X_WIN;
//		ultimate.bigBoard[0][2].state = GameState.X_WIN;
//		boolean win = ultimate.checkGameStatus(CellContents.X, currentRow, currentColumn);
//		assertEquals(true, win);
//
//		ultimate.resetUltimateBoard();
//
//		currentColumn = 1;
//		currentRow = 2;
//		ultimate.bigBoard[1][0].state = GameState.O_WIN;
//		ultimate.bigBoard[1][1].state = GameState.O_WIN;
//		ultimate.bigBoard[1][2].state = GameState.O_WIN;
//		win = ultimate.checkGameStatus(CellContents.O, currentRow, currentColumn);
//		assertEquals(true, win);
//
//		ultimate.resetUltimateBoard();
//
//		currentColumn = 1;
//		currentRow = 1;
//		ultimate.bigBoard[0][0].state = GameState.X_WIN;
//		ultimate.bigBoard[1][1].state = GameState.X_WIN;
//		ultimate.bigBoard[2][2].state = GameState.X_WIN;
//		win = ultimate.checkGameStatus(CellContents.X, currentRow, currentColumn);
//		assertEquals(true, win);
//
//		ultimate.resetUltimateBoard();
//
//		ultimate.bigBoard[0][2].state = GameState.O_WIN;
//		ultimate.bigBoard[1][1].state = GameState.O_WIN;
//		ultimate.bigBoard[2][0].state = GameState.O_WIN;
//		win = ultimate.checkGameStatus(CellContents.O, currentRow, currentColumn);
//		assertEquals(true, win);
//	}

	public void ultimateDraw() {
		ultimate.bigBoard[0][0].state = GameState.X_WIN;
		ultimate.bigBoard[0][1].state = GameState.O_WIN;
		ultimate.bigBoard[0][2].state = GameState.DRAW;
		ultimate.bigBoard[1][0].state = GameState.DRAW;
		ultimate.bigBoard[1][1].state = GameState.DRAW;
		ultimate.bigBoard[1][2].state = GameState.X_WIN;
		ultimate.bigBoard[2][0].state = GameState.X_WIN;
		ultimate.bigBoard[2][1].state = GameState.DRAW;
		ultimate.bigBoard[2][2].state = GameState.O_WIN;
		boolean isDraw = ultimate.isDraw();
		assertEquals(true, isDraw);
	}
}