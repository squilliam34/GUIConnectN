package edu.wm.cs.cs301.connectn.view;

/**
 * 
 * PlayAgainDialog
 * 
 * Displays the Play Again screen
 * 
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class PlayAgainDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private final PlayAgainAction playAgainAction;

    private final ExitAction exitAction;

    private final ConnectNFrame frame;

    public PlayAgainDialog(ConnectNFrame frame, String resultMessage) {
        super(frame.getFrame(), "Play Again", true);
        this.frame = frame;
        this.playAgainAction = new PlayAgainAction();
        this.exitAction = new ExitAction();
        this.addWindowListener((WindowListener) new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                exit();
            }
        });

        add(createMainPanel(resultMessage), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(frame.getFrame());
        setVisible(true);
    }

    private JPanel createMainPanel(String resultMessage) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        JLabel messageLabel = new JLabel(resultMessage);
        messageLabel.setFont(new Font("Dialog", Font.BOLD, 36));
        panel.add(messageLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exitAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "playAgainAction");
        ActionMap actionMap = panel.getActionMap();
        actionMap.put("playAgainAction", playAgainAction);
        actionMap.put("exitAction", exitAction);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(playAgainAction);
        panel.add(playAgainButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(exitAction);
        panel.add(exitButton);

        return panel;
    }

    private class PlayAgainAction extends AbstractAction {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent event) {
            startNewGame();
        }

    }

    private class ExitAction extends AbstractAction {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent event) {
        	exit();
        }

    }

    private void startNewGame() {
    	dispose();
        frame.reset();
    }

    private void exit() {
        dispose();
        frame.shutdown();
        System.exit(0);
    }
}

