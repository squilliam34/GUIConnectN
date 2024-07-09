package edu.wm.cs.cs301.connectn.model;

import java.awt.Color;

/**
 * GameBoard
 * 
 * A class to represent the board of a Connect N game
 */

public class GameBoard {
	private Location[][] board; // do not change!
	private int row;
	private int col;
	private int lastMoveRow;
	private int lastMoveCol;

	public GameBoard(int row, int col) {
		// row and col values will be decided with conditionals
		// depending on easy, medium, or hard game modes in main file
		this.row = row;
		this.col = col;
		board = new Location[row][col];
		// board needs to be initialized to "empty" Location objects
		// or it displays null objects
		initializeBoard();
	}

	public void initializeBoard() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = new Location(new Color(128, 128, 128), new Color(128, 128, 128));
			}
		}
	}

	public Location getSymbol(int r, int c) {
		return board[r][c];
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setLastMove(int r, int c) {
		this.lastMoveRow = r;
		this.lastMoveCol = c;
	}

	public int getLastRow() {
		return lastMoveRow;
	}

	public int getLastCol() {
		return lastMoveCol;
	}

	public void undo() {
		board[lastMoveRow][lastMoveCol].setToken(' ');
	}

	// methods for checking for wins
	public boolean horWinCheck(int n, char symbol) {
	    for (int r = row - 1; r >= 0; r--) {
	        int count = 1;
	        for (int c = 0; c < col; c++) {
	            if (board[r][c].equals(symbol)) {
	                if (c < col - 1 && board[r][c].equals(board[r][c + 1].getToken())) {
	                    count += 1;
	                    if (count == n) {
	                        return true;
	                    }
	                } else {
	                    count = 1;
	                }
	            }
	        }
	    }
	    return false;
	}

	public boolean vertWinCheck(int n, char symbol) {
	    for (int c = 0; c < col; c++) {
	        int count = 1;
	        for (int r = row - 1; r >= 0; r--) {
	            if (board[r][c].equals(symbol)) {
	                if (r > 0 && board[r][c].equals(board[r - 1][c].getToken())) {
	                    count += 1;
	                    if (count == n) {
	                        return true;
	                    }
	                } else {
	                    count = 1;
	                }
	            }
	        }
	    }
	    return false;
	}

	public boolean downRightDiagWinCheck(int n, char symbol) {
	    for (int r = 0; r < row - (n - 1); r++) {
	        for (int c = 0; c < col - (n - 1); c++) {
	            int count = 1;
	            if (board[r][c].equals(symbol)) {
	                for (int i = 1; i < n; i++) {
	                    if (board[r][c].equals(board[r + i][c + i].getToken())) {
	                        count += 1;
	                        if (count == n) {
	                            return true;
	                        }
	                    } else {
	                        count = 1;
	                        break; // Break the inner loop if the sequence is broken
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}

	public boolean upRightDiagWinCheck(int n, char symbol) {
	    for (int r = row - 1; r >= n - 1; r--) {
	        for (int c = 0; c < col - (n - 1); c++) {
	            int count = 1;
	            if (board[r][c].equals(symbol)) {
	                for (int i = 1; i < n; i++) {
	                    if (board[r][c].equals(board[r - i][c + i].getToken())) {
	                        count += 1;
	                        if (count == n) {
	                            return true;
	                        }
	                    } else {
	                        count = 1;
	                        break; // Break the inner loop if the sequence is broken
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}


	public boolean totalWinCheck(int n, Character symbol) {
		if (horWinCheck(n, symbol)) {
			return true;
		}
		if (vertWinCheck(n, symbol)) {
			return true;
		}
		if (downRightDiagWinCheck(n, symbol)) {
			return true;
		}
		if (upRightDiagWinCheck(n, symbol)) {
			return true;
		}
		return false;
	}
}