package edu.wm.cs.cs301.connectn.view;

/**
 * 
 * ConnectNFrame
 * 
 * A class for the GUI frame of the game. This is the main display
 * that is updated when changes are made to the game.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import edu.wm.cs.cs301.connectn.controller.ButtonAction;
import edu.wm.cs.cs301.connectn.model.ComputerPlayer;
import edu.wm.cs.cs301.connectn.model.ConnectNModel;
import edu.wm.cs.cs301.connectn.model.GameBoard;
import edu.wm.cs.cs301.connectn.model.HumanPlayer;
import edu.wm.cs.cs301.connectn.model.LeaderboardDialog;
import edu.wm.cs.cs301.connectn.model.Location;

public class ConnectNFrame {
	
private final JFrame frame;
	
private final ConnectNModel model;

private final HumanPlayer humanPlayer;

private final ComputerPlayer computerPlayer;

private GameBoard board;

private JPanel boardPanel;

	public ConnectNFrame(ConnectNModel model, boolean showInstructionsInitially) {
		this.model = model;
	    this.boardPanel = createBoardPanel();
	    this.frame = createAndShowGUI();
	    this.board = model.getGameBoard();
	    this.humanPlayer = new HumanPlayer('O', board);
	    this.computerPlayer = new ComputerPlayer('X', board);
	    if (showInstructionsInitially) {
            new InstructionsDialog(this, true);
        }
	}
	
	private JFrame createAndShowGUI() {
		displayLeaderboard();
	    JFrame frame = new JFrame("Connect " + model.getWinCondition());
	    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    frame.setResizable(false);
	    frame.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	            shutdown();
	        }
	    });

	    JMenuBar menuBar = createMenuBar();
	    frame.setJMenuBar(menuBar);

	    JPanel columnButtonPanel = new JPanel(new GridLayout(1, model.getCols(), 5, 5));
	    for (int col = 0; col < model.getCols(); col++) {
	        JButton columnButton = new JButton("Column " + (col + 1));
	        columnButton.setPreferredSize(new Dimension(100, 30));
	        columnButton.addActionListener(new ButtonAction(model, this));
	        columnButtonPanel.add(columnButton);
	    }

	    JPanel turnPanel = new JPanel();
	    JLabel turnLabel = new JLabel("Turn: " + model.getTurn());
	    turnPanel.add(turnLabel);

	    frame.add(boardPanel, BorderLayout.CENTER);
	    frame.add(columnButtonPanel, BorderLayout.NORTH); 
	    frame.add(turnPanel, BorderLayout.SOUTH); 

	    frame.pack();
	    frame.setLocationByPlatform(true);
	    frame.setVisible(true);

	    return frame;
	}


	
	private JPanel createBoardPanel() {
		JPanel boardPanel = new JPanel(new GridLayout(model.getRows(), model.getCols(), 5, 5)); // Add some padding between cells
	    for (int col = 0; col < model.getCols(); col++) {
	        for (int row = 0; row < model.getRows(); row++) {
	            JButton cellButton = new JButton();
	            cellButton.setPreferredSize(new Dimension(50, 50));
	            boardPanel.add(cellButton);
	        }
	    }
	    return boardPanel;
	}
	
	private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
		JMenu difficultyMenu = new JMenu("Difficulty");
		menuBar.add(difficultyMenu);
		JMenuItem small = new JMenuItem("Small");
		small.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ConnectNModel newModel = new ConnectNModel();
                newModel.setMode("Small");
                new ConnectNFrame(newModel, false);
            }});
		difficultyMenu.add(small);
        
		JMenuItem medium = new JMenuItem("Medium");
		medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ConnectNModel newModel = new ConnectNModel();
                newModel.setMode("Medium");
                new ConnectNFrame(newModel, false);
            }});
		difficultyMenu.add(medium);
        
		JMenuItem large = new JMenuItem("Large");
		large.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ConnectNModel newModel = new ConnectNModel();
                newModel.setMode("Large");
                new ConnectNFrame(newModel, false);
            }});
		difficultyMenu.add(large);
		
		JButton instructions = new JButton("Instructions");
		instructions.addActionListener(event -> new InstructionsDialog(this, true));
		menuBar.add(instructions);
		
		JButton leaderboard = new JButton("Leaderboard");
		leaderboard.addActionListener(event -> displayLeaderboard());
		menuBar.add(leaderboard);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(event -> shutdown());
		
		menuBar.add(exit);
		
        return menuBar;
    }
	
	public void shutdown() {
		frame.dispose();
	}
	
	public HumanPlayer getHumanPlayer() {
		return humanPlayer;
	}
	
	public ComputerPlayer getComputerPlayer() {
		return computerPlayer;
	}
	
	public void updateGameBoard(HumanPlayer player) {
		int row = board.getLastRow();
	    int col = board.getLastCol();
	    JButton button = getButtonAt(row, col);
	    Location symbol = board.getSymbol(row, col); 
	    Color color = player.getColor(); 
	            
	    updateButtonAppearance(button, symbol, color);
	}
	
	public void updateGameBoard(ComputerPlayer player) {
	    int row = board.getLastRow();
	    int col = board.getLastCol();
	    JButton button = getButtonAt(row, col);
	    Location symbol = board.getSymbol(row, col); 
	    Color color = player.getColor(); 
        
        updateButtonAppearance(button, symbol, color);
	   
	}

	private JButton getButtonAt(int row, int col) {
	    int index = row * model.getCols() + col;
	    Component[] components = boardPanel.getComponents();
	    if (index >= 0 && index < components.length && components[index] instanceof JButton) {
	        return (JButton) components[index];
	    } else {
	        return null;
	    }
	}
	
	private void updateButtonAppearance(JButton button, Location location, Color color) {
	    button.setBackground(color);

	    if (!location.isEmpty()) {
	        char symbol = location.getToken();
	        button.setText(String.valueOf(symbol));
	    } else {
	        button.setText("");
	    }
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void reset() {
	    // Reset each Location object to empty and reset colors to default
	    for (int row = 0; row < model.getRows(); row++) {
	        for (int col = 0; col < model.getCols(); col++) {
	            Location location = board.getSymbol(row, col);
	            location.setToken(' '); 
	            location.setColor(Color.WHITE); 
	            Color foregroundColor = (row + col) % 2 == 0 ? Color.BLACK : Color.WHITE;
	            location.setColor(foregroundColor);
	        }
	    }
	    // Update the appearance of all buttons on the board
	    updateAllButtonAppearances();
	    ConnectNModel newModel = new ConnectNModel(); 
	    new ConnectNFrame(newModel, false);
	}

	private void updateAllButtonAppearances() {
	    Component[] components = boardPanel.getComponents();
	    for (Component component : components) {
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            int row = button.getY() / button.getHeight();
	            int col = button.getX() / button.getWidth();
	            Location location = board.getSymbol(row, col);
	            updateButtonAppearance(button, location, location.getBackgroundColor());
	        }
	    }
	}
	
	public void updateTurnLabel() {
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] panelComponents = panel.getComponents();
                for (Component panelComponent : panelComponents) {
                    if (panelComponent instanceof JLabel) {
                        JLabel label = (JLabel) panelComponent;
                        if (label.getText().startsWith("Turn:")) {
                            label.setText("Turn: " + model.getTurn());
                            return; // Exit the method once the label is updated
                        }
                    }
                }
            }
        }
    }
	
	public void displayLeaderboard() {
        LeaderboardDialog leaderboardDialog = new LeaderboardDialog(model.getMode());
        LeaderboardDisplay leaderboardDisplay = new LeaderboardDisplay();
        leaderboardDisplay.showLeaderboardDisplay(leaderboardDialog.getScores());
    }

    public void promptAndDisplay(int score) {
        String username = LeaderboardDisplay.usernamePrompt();
        LeaderboardDialog leaderboardDialog = new LeaderboardDialog(model.getMode());
        leaderboardDialog.addScore(username, score);
        displayLeaderboard();
    }

}
