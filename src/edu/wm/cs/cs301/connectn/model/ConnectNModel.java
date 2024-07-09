package edu.wm.cs.cs301.connectn.model;
/**
 * ConnectNModel
 * 
 * A class to represent the current state of a Connect N game
 */

public class ConnectNModel {
	private String mode;
	private int turn;
	public GameBoard board;
	private int winCondition;
	
	public ConnectNModel() {
		this.mode = "Medium";
		setConditions();
		this.turn = 1;	
		this.board = initializeGameBoard();
	}
	
	public void setConditions() {
		if (mode.equals("Small")) {
			winCondition = 3;
			board = new GameBoard(4, 5);
		}
		else if (mode.equals("Medium")) {
			winCondition = 4;
			board = new GameBoard(6, 7);
		}
		else {
			winCondition = 5;
			board = new GameBoard(8, 9);
		}
	}
	
	public GameBoard initializeGameBoard() {
		board.initializeBoard();
		return board;
	}
	
	public GameBoard getGameBoard() {
		return board;
	}
	
	public int getRows() {
		return board.getRow();
	}
	
	public int getCols() {
		return board.getCol();
	}
	
	public void setMode(String mode) {
		this.mode = mode;
		setConditions();
	}
	
	public int getWinCondition() {
		return winCondition;
	}
	
	public void incrementTurn() {
		turn++;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public String getMode() {
		return mode;
	}
}
