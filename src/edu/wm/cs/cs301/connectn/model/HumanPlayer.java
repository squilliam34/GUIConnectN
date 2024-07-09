package edu.wm.cs.cs301.connectn.model;

import java.awt.Color;

/**
 * HumanPlayer class
 * 
 * A class that will represent the user controlled player
 */

public class HumanPlayer implements Player {
	
	private Character symbol;
	private GameBoard board;
	private Color color;
	
	public HumanPlayer(Character symbol, GameBoard board) {
		this.symbol = symbol;
		this.board = board;
		this.color = new Color(51,153,255);
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
