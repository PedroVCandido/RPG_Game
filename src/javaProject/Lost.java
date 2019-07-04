package javaProject;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Lost extends JFrame {
	private static final long serialVersionUID = -4289165764779615724L;
	// VARIABLES
	JLabel lostMessage = new JLabel("<html> Your deeds were brave but the quest was to hard, you failed. <br/><html>");
	JButton exitGame = new JButton("Exit Game");


	// constructor
	Lost(){
		getContentPane();
		setLayout(new BorderLayout());
		add(lostMessage, BorderLayout.NORTH);
		add(exitGame, BorderLayout.SOUTH);
		exitGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		setSize(new Dimension(500,500));
		setVisible(true);
	}	
/*	
	// MAIN METHOD FOR TESTING
	public static void main (String[]args) {
		Lost l = new Lost();
	}
*/
}
