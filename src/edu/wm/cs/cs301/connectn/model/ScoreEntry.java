package edu.wm.cs.cs301.connectn.model;

/**
 * 
 * ScoreEntry
 * 
 * A class that allows for score input to the leaderboard
 * 
 */

public class ScoreEntry implements Comparable<ScoreEntry> {
	
	private String username;
	
	private int score;
	
	public ScoreEntry(String username, int score) {
		this.username = username;
		this.score = score;
	}
	
	public String getUsername() {
		return username;
	}

	public int getScore() {
		return score;
	}
	
	@Override
	public int compareTo(ScoreEntry otherScore) {
		return Integer.compare(this.score, otherScore.score);
	}

}
