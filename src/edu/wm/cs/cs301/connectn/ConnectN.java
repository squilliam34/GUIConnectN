package edu.wm.cs.cs301.connectn;

/**
 * 
 * The ConnectN Class
 * 
 * Runs the game
 * 
 */

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import edu.wm.cs.cs301.connectn.model.ConnectNModel;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

public class ConnectN implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ConnectN());
		
		//Can't use the Cross-Platform Look and Feel on Windows - Needs investigation
		if (!System.getProperty("os.name").contains("Windows")) {
			//Must use cross-platform look and feel so button backgrounds work on Mac
			try {
			    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new ConnectNFrame(new ConnectNModel(), true);
	}

}
