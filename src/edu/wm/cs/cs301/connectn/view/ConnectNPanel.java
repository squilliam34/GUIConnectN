package edu.wm.cs.cs301.connectn.view;

/**
 * 
 * ConnectNPanel Class
 * 
 * The panel that represents the game board.
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import edu.wm.cs.cs301.connectn.model.ConnectNModel;
import edu.wm.cs.cs301.connectn.model.GameBoard;
import edu.wm.cs.cs301.connectn.model.Location;

public class ConnectNPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	ConnectNModel model;
	private final Rectangle[][] grid;
	private final int topMargin, leftMargin, letterWidth;
	private final Insets insets;
	
	public ConnectNPanel(ConnectNModel model, int width) {
		this.model = model;

		this.topMargin = 0;
		this.letterWidth = 64;
		this.insets = new Insets(0, 6, 6, 6);
		this.grid = calculateRectangles();

		int wordWidth = (letterWidth + insets.right) * model.getCols();
		this.leftMargin = (width - wordWidth) / 2;
		int height = (letterWidth + insets.bottom) * model.getRows()
				+ 2 * topMargin;
		this.setPreferredSize(new Dimension(width, height));
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        Font titleFont = new Font("Dialog", Font.BOLD, 36);
        GameBoard connectNGrid = model.getGameBoard();
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Rectangle r = grid[row][column];
                Location token = connectNGrid.getSymbol(row, column); // Replace WordleResponse with ConnectNToken
                drawOutline(g2d, r);
                drawConnectNToken(g2d, token, r, titleFont); // Method to draw ConnectNToken instead of WordleResponse
            }
        }
    }
	
	private void drawOutline(Graphics2D g2d, Rectangle r) {
		int x = r.x + 1;
		int y = r.y + 1;
		int width = r.width - 2;
		int height = r.height - 2;
		g2d.setColor(new Color(211, 214, 218));
		g2d.setStroke(new BasicStroke(3f));
		g2d.drawLine(x, y, x + width, y);
		g2d.drawLine(x, y + height, x + width, y + height);
		g2d.drawLine(x, y, x, y + height);
		g2d.drawLine(x + width, y, x + width, y + height);
	}
	
	private void drawConnectNToken(Graphics2D g2d, Location location, Rectangle r, Font titleFont) {
		if (location != null) {
			g2d.setColor(location.getBackgroundColor());
			g2d.fillRect(r.x, r.y, r.width, r.height);
			g2d.setColor(location.getForegroundColor());
			drawCenteredString(g2d,
					Character.toString(location.getToken()), r, titleFont);
		}
	}
	
	private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect,
			Font font) {
		FontMetrics metrics = g2d.getFontMetrics(font);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
				+ metrics.getAscent();

		g2d.setFont(font);
		g2d.drawString(text, x, y);
	}
	
	private Rectangle[][] calculateRectangles() {
		Rectangle[][] grid = new Rectangle[model.getRows()][model
				.getCols()];

		int x = leftMargin;
		int y = topMargin;

		for (int row = 0; row < model.getRows(); row++) {
			for (int column = 0; column < model.getCols(); column++) {
				grid[row][column] = new Rectangle(x, y, letterWidth,
						letterWidth);
				x += letterWidth + insets.right;
			}
			x = leftMargin;
			y += letterWidth + insets.bottom;
		}

		return grid;
	}
	
}
