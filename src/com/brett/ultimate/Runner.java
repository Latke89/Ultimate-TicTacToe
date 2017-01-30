package com.brett.ultimate;

import java.util.Scanner;

/**
 * Created by Brett on 1/26/17.
 */
public class Runner {
	Board myBoard;
	CellContents currentPlayer;
	GameState state;
	Scanner inputScanner = new Scanner(System.in);
	int xCounter = 3;
	int oCounter = 3;
	int xSwap = 1;
	int oSwap = 1;

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
				String choice = inputScanner.nextLine();
				switch (choice) {
					case "move":
						move(currentPlayer);
						break;
					case "erase":
						if ((currentPlayer == CellContents.X && xCounter != 0)
								|| (currentPlayer == CellContents.O && oCounter != 0)) {
							eraseContents();
						}
						break;
					case "swap":
						if ((currentPlayer == CellContents.X && xSwap != 0)
								|| (currentPlayer == CellContents.O && oSwap != 0)) {
							swap(myBoard);
						}
						break;
					default:
						throw new Exception();

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

		myBoard.cells[row][col].contents = current;

		checkBoardState(current);

	}

	public void eraseContents() {

		System.out.println("What square would you like to erase? (col[1-3], row[1-3]");
		int col = Integer.valueOf(inputScanner.nextLine()) - 1;
		int row = Integer.valueOf(inputScanner.nextLine()) - 1;

		if(currentPlayer == CellContents.X) {
			xCounter--;
		} else {
			oCounter--;
		}
		myBoard.cells[col][row].clear();

	}

	public void swap(Board board) {
		for (int row = 0; row < board.cells.length; row++) {
			for (int col = 0; col < board.cells.length; col++) {
				if(board.cells[row][col].contents == currentPlayer.X) {
					board.cells[row][col].contents = currentPlayer.O;
					oSwap--;
				} else if (board.cells[row][col].contents == currentPlayer.O) {
					board.cells[row][col].contents = currentPlayer.X;
					xSwap--;
				}
			}
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
