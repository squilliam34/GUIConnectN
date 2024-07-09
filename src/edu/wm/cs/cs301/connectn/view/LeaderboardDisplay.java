package edu.wm.cs.cs301.connectn.view;

/**
 * 
 * LeaderboardDisplay
 * 
 * Displays the leaderboard
 * 
 */

import java.util.List;

import javax.swing.JOptionPane;

import edu.wm.cs.cs301.connectn.model.ScoreEntry;

public class LeaderboardDisplay {
	
	public void showLeaderboardDisplay(List<ScoreEntry> scores){
		StringBuilder header = new StringBuilder("Leaderboard:\n");
		for (ScoreEntry score : scores) {
			header.append(String.format("%s: %d\n", score.getUsername(), score.getScore()));
		}
		JOptionPane.showMessageDialog(null, header.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static String usernamePrompt() {
		return JOptionPane.showInputDialog("Congrats! Enter your name to be added to the leaderboard:");
	}
	
}
