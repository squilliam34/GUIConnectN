package edu.wm.cs.cs301.connectn.model;

/**
 * 
 * ColorResponse Class
 * 
 * A class for the different Location objects to use to display
 * colors on the board
 * 
 */

import java.awt.Color;

public class ColorResponse {
	
	private final Color backgroundColor, foregroundColor;

	public ColorResponse(Color backgroundColor, Color foregroundColor) {
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

}
