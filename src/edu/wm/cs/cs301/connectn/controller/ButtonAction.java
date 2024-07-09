package edu.wm.cs.cs301.connectn.controller;

/**
 * 
 * ButtonAction
 * 
 * Class that handles what happens whenever a different
 * column button is pressed, i.e. making a move
 * 
 */

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import edu.wm.cs.cs301.connectn.model.ComputerPlayer;
import edu.wm.cs.cs301.connectn.model.ConnectNModel;
import edu.wm.cs.cs301.connectn.model.GameBoard;
import edu.wm.cs.cs301.connectn.model.HumanPlayer;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;
import edu.wm.cs.cs301.connectn.view.PlayAgainDialog;

public class ButtonAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;

	private final ConnectNModel model;
	
	private ConnectNFrame frame;
	
	private HumanPlayer humanPlayer;
	
	private ComputerPlayer computerPlayer;
	
	private final GameBoard board;
	
	public ButtonAction(ConnectNModel model, ConnectNFrame frame) {
		this.model = model;
		this.frame = frame;
		this.board = model.getGameBoard();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
	    String actionCommand = button.getActionCommand();

	    // Extract the column number from the action command
	    String[] parts = actionCommand.split(" ");
	    String col = parts[1];
		
		// try and catch block so that user can try multiple inputs if their 
		// original column is full
	    int winCondition = model.getWinCondition();
		boolean valid = false;
        while (!valid) {
        	try {
        		int colNum = Integer.parseInt(col);
	        	// check if the column is full. if so, try again
	        	if (board.getSymbol(0, colNum-1).isEmpty() == false) {
	        		throw new IllegalArgumentException("Column is full. Try again:");
	        	}
	        	humanPlayer = frame.getHumanPlayer();
	        	humanPlayer.takeTurn(colNum);
	        	frame.updateGameBoard(humanPlayer);
	        	// updating turn
	        	model.incrementTurn();
	        	frame.updateTurnLabel();
                valid = true;
        	} catch (IllegalArgumentException e) {
        		System.out.println(e.getMessage());
        	} 
        	// win condition check for the player
        	if (board.totalWinCheck(winCondition, 'O')) {
        		frame.promptAndDisplay(model.getTurn() - 1);
        		new PlayAgainDialog(frame, "Congratulations! You win!"); 
                frame.shutdown(); 
                
                return; 
            }
	
	        // computer "AI"
	        
	        // computer checks if it can make a winning move or if it 
	        // can stop player from making a winning move
	        // if not, it chooses a random column
	        
	        int bound = model.getCols();
	        computerPlayer = frame.getComputerPlayer();
	        // booleans to see if rest of algorithm is needed
	        boolean trials = false;
	   	    boolean needed = true;
	   	    // checks if the computer can make a winning move
	        for (int i = 1; i <=bound; i++) {
	        	computerPlayer.takeTurn(i);
	        	if (!board.totalWinCheck(winCondition, 'X')) {
	        		board.undo();
	        	}
	        	else {
	        		frame.updateGameBoard(computerPlayer);
	        		trials = true;
	        		needed = false;
	        		break;
	        	}
	        }
	        // checks if the player can make a winning move and makes that move
	        if (needed) {
	        	for (int i = 1; i <=bound; i++){
	        		humanPlayer.takeTurn(i);
	        		if (board.totalWinCheck(winCondition, 'O')) {
	        			board.undo();
	        			computerPlayer.takeTurn(i);
	        			frame.updateGameBoard(computerPlayer);
	        			trials = true;
	        			break;
	        		}
	        		else {
	        			board.undo();
	        		}
	        	}
	        }
	        // if neither of the previous are true, we guess a random number
			if (!trials) {
				Random rand = new Random();
		        int input = rand.nextInt(bound) + 1;
		        // check for if the column is already full
		        int test = input;
		        if (board.getSymbol(0, test-1).isEmpty() == false) {
		        	// reassign input value to a column that isn't full
		        	// also a way to check for a tie
		        	// if the board is full with the win condition not met
		        	// the game is a tie
	        		for (int i = 0; i  < bound; i++) {
	        			if (board.getSymbol(0, i).isEmpty()) {
	        				input = i + 1;
	        				break;
	        			}
	        		}
		        }
		        computerPlayer.takeTurn(input);
		        frame.updateGameBoard(computerPlayer);
			}
	        
	        // win condition check for the computer
	        if (board.totalWinCheck(winCondition, 'X')) {
	        	new PlayAgainDialog(frame, "Nice try! You lost!"); 
	        	frame.shutdown(); 
                return; 
	        }
        }
        
	}
}
