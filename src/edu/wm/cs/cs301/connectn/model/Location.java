package edu.wm.cs.cs301.connectn.model;

/**
 * Location
 * 
 * A class to represent a single square on a GameBoard
 */

import java.awt.Color;
public class Location {
private Character symbol;

private ColorResponse colorResponse;
	
	// constructor that sets default "empty" value
	public Location(Color backgroundColor, Color foregroundColor) {
		symbol = ' ';
		this.colorResponse = new ColorResponse(backgroundColor, foregroundColor);
	}
	
	public boolean isEmpty() {
		if (this.getToken() == ' ') {
			return true;
		}
		return false;	//placeholder, replace with correct code
	}
	
	public Character getToken() {
		return symbol;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this.getToken() == other) {
			return true;
		}
		return false;	//placeholder, replace with correct code
	}
	
	public void setToken(Character symbol) {
		this.symbol = symbol;
	}
	
	public Color getBackgroundColor() {
		return colorResponse.getBackgroundColor();
	}

	public Color getForegroundColor() {
		return colorResponse.getForegroundColor();
	}
	
	public void setColor(Color color) {
		this.colorResponse = new ColorResponse(color, getForegroundColor());
	}
}
