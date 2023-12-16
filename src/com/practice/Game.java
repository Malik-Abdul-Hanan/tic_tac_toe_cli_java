package com.practice;

import util.ScannerUtil;

/**
 * @author Abdul Hanan
 * @created_at 16-Dec-2023
 */
public class Game {

	private int[][] board = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	private Player[] players = { new Player(1, "O"), new Player(2, "X") };

	/**
	 * Function to start Game
	 * @return winner no. ( 1 or 2 ) and 0 if no winner
	 */
	public int start() {
		do {
			for(Player player: players) {
				displayBoard();
				System.out.println("\nTurn of Player "+ player.playerNo + " Symbol: ( " + player.symbol + " )");
				do{
					System.out.print("\nChoose a valid index: ");
					int index = ScannerUtil.getInteger();
					int[] rowCol = getRowAndColFromIndex(index);
					int rowNo = rowCol[0];
					int colNo = rowCol[1];
					if(board[rowNo][colNo] == 0) {						
						updateBoard(player.playerNo, rowNo, colNo);
						break;
					}
				}while(true);
				if(isGameFinished()){
					System.out.println("!! Congratulations Winner is Player " + player.playerNo);
					return player.playerNo;
				}
				if(isBoardCompletelyFilled()) {
					System.out.println("!! Game Drawn: No Winner");
					return 0;
				}
			}
		}while(true);
	}

	private void displayBoard() {
		System.out.println(" ------------ ");
		for (int rowNo = 0; rowNo < board.length; rowNo++) {
			int[] row = board[rowNo];
			// Displaying border Line
			System.out.println("|   |   |   |");
			for (int colNo = 0; colNo < row.length; colNo++) {
				System.out.print("| " + getSymbol(rowNo, colNo) + " ");
			}
			System.out.println("|\n|   |   |   |\n ------------ ");
		}
	}

	private boolean isGameFinished() {
		// Checking If any row is completed
		for (int rowNo = 0; rowNo < board.length; rowNo++) {
			int[] row = board[rowNo];
			if (row[0] == row[1] && row[1] == row[2] && row[2] != 0)
				return true;
		}
		// Checking If any row is completed
		for (int colNo = 0; colNo < board.length; colNo++) {
			if (board[0][colNo] == board[1][colNo] && board[1][colNo] == board[2][colNo] && board[2][colNo] != 0)
				return true;
		}
		// Checking first diagnol
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != 0)
			return true;
		// Checking second diagnol
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != 0)
			return true;
		return false;
	}

	private boolean isBoardCompletelyFilled() {
		for (int rowNo = 0; rowNo < board.length; rowNo++) {
			for (int colNo = 0; colNo < board[rowNo].length; colNo++) {
				if(board[rowNo][colNo] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private String getSymbol(int rowNo, int colNo) {
		int valueAtIndex = board[rowNo][colNo];
		for (Player player : players) {
			if (player.playerNo == valueAtIndex) {
				return player.symbol;
			}
		}
		int index = getIndexFromRowAndCol(rowNo, colNo);
		return Integer.toString(index);
	}

	private int getIndexFromRowAndCol(int row, int col) {
		return row * (board[col].length) + (col + 1);
	}

	private int[] getRowAndColFromIndex(int index) {
		index--;
		int[] rowCol = { 0, 0 };
		int rowNo = index/3;
		int colNo = index%3;
		rowCol[0] = rowNo;
		rowCol[1] = colNo;
		return rowCol;
	}

	private void updateBoard(int value, int rowNo, int colNo) {
		board[rowNo][colNo] = value;
	}

	private class Player {
		String symbol;
		int playerNo;

		Player(int playerNo, String symbol) {
			this.playerNo = playerNo;
			this.symbol = symbol;
		}
	}
}