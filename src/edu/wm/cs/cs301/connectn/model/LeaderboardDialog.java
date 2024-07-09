package edu.wm.cs.cs301.connectn.model;


/**
 * 
 * LeaderboardDialog Class
 * 
 * A class for the leaderboard to pop-up
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LeaderboardDialog {

    private List<ScoreEntry> scores;
    private String path;

    public LeaderboardDialog(String size) {
        this.path = "leaderboard_" + size.toLowerCase() + ".txt";
        this.scores = new ArrayList<>();
        try {
			loadScores();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    public void loadScores() throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File does not exist: " + path);
            return;
        }

        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] components = line.split(",");
            if (components.length == 2) {
                scores.add(new ScoreEntry(components[0], Integer.parseInt(components[1])));
            }
        }
        scan.close();
    }

    public void addScore(String name, int score) {
        scores.add(new ScoreEntry(name, score));
        Collections.sort(scores);
        try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void save() throws IOException {
        File file = new File(path);
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for (ScoreEntry score : scores) {
            writer.println(score.getUsername() + "," + score.getScore());
        }
        writer.close();
    }

    public List<ScoreEntry> getScores() {
        return new ArrayList<>(scores);
    }

    public ScoreEntry getLowestScore() {
        if (scores.isEmpty()) {
            return null;
        } else {
            return scores.get(0);
        }
    }
}