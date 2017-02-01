package com.brett.ultimate;

import com.sun.tools.classfile.ConstantPool;

import java.util.Scanner;

/**
 * Created by Brett on 1/26/17.
 */
public class Runner {
	Board myBoard;
	private CellContents currentPlayer;
	GameState state;
	private Scanner inputScanner = new Scanner(System.in);
	private int xCounter = 3;
	private int oCounter = 3;
	private int xSwap = 1;
	private int oSwap = 1;
	int swapCooldown;
	private String choice = "";

	BigBoard bigBoard;
	public static void main(String[] args) throws Exception {

		Runner myRunner = new Runner();
		myRunner.tictactoe();
//		myRunner.bigBoard();
	}

	public void tictactoe() throws Exception {
		myBoard = new Board();
		currentPlayer = CellContents.X;
		state = GameState.PLAYING;

		myBoard.initializeBoard();


		while (state == GameState.PLAYING) {
			try {
				myBoard.drawBoard();
				System.out.println("Current player: " + currentPlayer);
				if (currentPlayer == CellContents.X) {
					System.out.println("Erases remaining: " + xCounter + ", Swaps remaining: " + xSwap);
				} else {
					System.out.println("Erases remaining: " + oCounter + ", Swaps remaining: " + oSwap);
				}
				System.out.println("What would you like to do?");
				System.out.println("\"move\", \"erase\", or \"swap\"?");
				boolean flag = true;

				while(flag) {
					choice = inputScanner.nextLine();

					if (choice.equalsIgnoreCase("move")) {
						move(currentPlayer);
						flag = false;
					} else if (choice.equalsIgnoreCase("erase")) {

						if ((currentPlayer == CellContents.X && xCounter != 0)
								|| (currentPlayer == CellContents.O && oCounter != 0)) {
								eraseContents();
							flag = false;
						} else {
							System.out.println("You are not able to erase any more tiles.");
						}

					} else if (choice.equalsIgnoreCase("swap")) {

						if ((currentPlayer == CellContents.X && xSwap > 0)
								|| (currentPlayer == CellContents.O && oSwap > 0)) {
							swap(myBoard);
							flag = false;
						} else {
							System.out.println("You are not able to swap tiles anymore.");
						}

					} else {
						System.out.println("Please type either \"move\", \"erase\", or \"swap\".");
					}

				}

				if (currentPlayer == CellContents.X) {
					currentPlayer = CellContents.O;
				} else {
					currentPlayer = CellContents.X;
				}

			} catch (ArrayIndexOutOfBoundsException arrEx){
//				arrEx.printStackTrace();
				System.out.println("Please choose a number between 1 and 3.");
			} catch (NumberFormatException numEx) {
//				numEx.printStackTrace();
				System.out.println("Please choose a number.");
			}

		}

	}

	public void move(CellContents current) {
		boolean flag = true;

		while(flag) {

			if (current == CellContents.X) {
				System.out.println("Choose where to place your \"X\" (col[1-3], row[1-3])");
			} else {
				System.out.println("Choose where to place your \"O\" (col[1-3], row[1-3])");
			}

			System.out.print("> ");
			int col = Integer.valueOf(inputScanner.nextLine()) - 1;
			System.out.print("> ");
			int row = Integer.valueOf(inputScanner.nextLine()) - 1;

//		System.out.println(col + ", " + row);

			if (myBoard.cells[row][col].contents == CellContents.EMPTY) {
				myBoard.cells[row][col].contents = current;
				flag = false;
			} else {
				System.out.println("Please choose an empty square.");
			}
		}

		checkBoardState(current);

	}

	public void eraseContents() {

		System.out.println("What square would you like to erase? (col[1-3], row[1-3]");
		boolean flag = true;

		while(flag) {

			System.out.print("> ");
			int col = Integer.valueOf(inputScanner.nextLine()) - 1;
			System.out.print("> ");
			int row = Integer.valueOf(inputScanner.nextLine()) - 1;

			if (currentPlayer == CellContents.X) {
				xCounter--;
			} else {
				oCounter--;
			}

			if (myBoard.cells[row][col].contents != CellContents.EMPTY && myBoard.cells[row][col].contents != currentPlayer) {
				myBoard.cells[row][col].clear();
				flag = false;
			} else {
				System.out.println("Please choose a non-empty square that you do not own to erase");
			}
		}

	}

	public void swap(Board board) {
		for (int row = 0; row < board.cells.length; row++) {
			for (int col = 0; col < board.cells.length; col++) {

				if(board.cells[row][col].contents == currentPlayer.X) {
					board.cells[row][col].contents = currentPlayer.O;
				} else if (board.cells[row][col].contents == currentPlayer.O) {
					board.cells[row][col].contents = currentPlayer.X;
				}
			}
		}
		if(currentPlayer == currentPlayer.X) {
			xSwap -= 1;
		} else {
			oSwap -= 1;
		}

	}

	public void checkBoardState(CellContents current){
		if(myBoard.isDraw()){
			state = GameState.DRAW;
		} else if(myBoard.isWin(current)) {
			if (current == CellContents.X) {
				myBoard.drawBoard();
				state = GameState.X_WIN;
				System.out.println("Congratulations, X's win!");
			} else {
				myBoard.drawBoard();
				state = GameState.O_WIN;
				System.out.println("Congratulations, O's win!");
			}
		}
	}

//	public void bigBoard() {
//		bigBoard = new BigBoard();
//		bigBoard.initialize();
//		bigBoard.drawBoard();
//	}

}
