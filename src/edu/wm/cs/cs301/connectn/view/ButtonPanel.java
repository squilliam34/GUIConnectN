package edu.wm.cs.cs301.connectn.view;

/** 
 * 
 * ButtonPanel
 * 
 * A class that represents the buttons for each column
 * 
 */

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wm.cs.cs301.connectn.controller.ButtonAction;
import edu.wm.cs.cs301.connectn.model.ConnectNModel;


public class ButtonPanel {

	private final JPanel panel;

	private final ConnectNModel model;
	
	private final ConnectNFrame frame;
	
	private final JButton[] columnButtons;

	public ButtonPanel(ConnectNFrame frame, ConnectNModel model) {
		this.model = model;
        this.columnButtons = new JButton[model.getCols()];
        this.frame = frame;
        this.panel = createMainPanel();
        
	}
	
	private JPanel createMainPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        for (int col = 0; col < model.getCols(); col++) {
            JButton button = new JButton("Column " + (col + 1));
            button.setActionCommand(Integer.toString(col)); 
            button.addActionListener(new ButtonAction(model, frame));
            columnButtons[col] = button;
            panel.add(button);
        }

        return panel;
    }


	public JPanel getPanel() {
		return panel;
	}
}
	
