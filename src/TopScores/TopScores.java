package TopScores;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// this class combines ReadWrite class with a gui
// the main of this will have to be in the main class of the program

public class TopScores{
	
	// VARIABLES
	String playerName;
	int newTopScore;
	public ReadWrite readWrite;
	boolean beforePlay;
	
	// CONSTRUCTOR
	public TopScores(String playerName, int newTopScore, boolean beforePlay) {
		this.playerName = playerName;
		this.newTopScore = newTopScore;
		this.beforePlay = beforePlay;
		readWrite = new ReadWrite(playerName, newTopScore);
	}
	// METHOD MAKE GUI
	public void makeGui() {
		JFrame f = new JFrame("Top Scores");
		f.setSize(500,500);
		
		// panels
		JPanel p = new JPanel();

		// labels
		JLabel congrats = new JLabel("Congrats " + playerName + " your score is: " + newTopScore);
		JLabel topscores = new JLabel(readWrite.dataInString, JLabel.CENTER);
		
		// buttons
				JButton button = new JButton("close");
				button.setPreferredSize(new Dimension(50,50));
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event) {
						f.setVisible(false);
					}
				}
				);	
				
		// add labels and buttons to panes
		p.setLayout(new BorderLayout());
		if (beforePlay == false) {
			p.add(congrats, BorderLayout.NORTH);  // congrats window should only be showed if this gui is used at the end of the game
		}
		
		p.add(topscores, BorderLayout.CENTER);
		p.add(button, BorderLayout.SOUTH);
			
		// add everything
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	} 

	
/*
	// MAIN for testing
	public static void main(String[] args) {
		TopScores top = new TopScores("Willem", 999, false);
		top.readWrite.saveAndShow();
		top.makeGui();
	}
*/
}

	

