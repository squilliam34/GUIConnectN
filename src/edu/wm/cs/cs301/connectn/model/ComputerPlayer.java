package edu.wm.cs.cs301.connectn.model;

import java.awt.Color;

/**
 * ComputerPlayer class
 * 
 * A class that will represent the computer controlled player
 */

public class ComputerPlayer implements Player {
	
	private Character symbol;
	private GameBoard board;
	private Color color;
	
	public ComputerPlayer(Character symbol, GameBoard board) {
		this.symbol = symbol;
		this.board = board;
		this.color = new Color(255, 102, 102);
	}
	
	@Override
	public void takeTurn(int col) {
		for (int i = board.getRow() -1; i >= 0; i--) {
			if (board.getSymbol(i, col-1).isEmpty()){
				board.getSymbol(i, col-1).setToken(symbol);
				board.getSymbol(i, col-1).setColor(color);
				board.setLastMove(i, col-1);
				break;
			}
		}

	}
	
	public Color getColor() {
		return this.color;
	}

}
