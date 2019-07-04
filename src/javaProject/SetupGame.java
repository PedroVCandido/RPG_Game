package javaProject;

/* class description:
 *  - A GUI for initializing the optional characteristics of the player, which include 
 *    the name, difficulty, and additional health or food
 *  - getter can be used to extract data from this class towards the playerclass
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import TopScores.TopScores;

public class SetupGame extends JFrame implements ActionListener{
	
// VARIABLES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1419437158702364185L;
	// variables that will be important for the player class
	String newPlayerName;
	int difficultyLevel;
	int additionalFood;
	int additionalHealth;
	boolean gameOver;
	boolean gameWone;
	int score;
	
			
	// PANEL 1: NAME
	JLabel nameLabel = new JLabel ("Name: ", JLabel.RIGHT);
	JTextField nameField = new JTextField("anonymous");
	
	// PANEL 2: DIFFICULTY
	JLabel easyLevel = new JLabel("Easy", JLabel.LEFT);
	JLabel moderateLevel = new JLabel("Easy", JLabel.CENTER);
	JLabel difficultyLabel = new JLabel("Easy                                                         Moderate                                                         Hard", JLabel.CENTER); // temporary solution since hashmap labeltable not working
	JLabel hardLevel = new JLabel("Hard", JLabel.RIGHT);
	JSlider sliderDifficulty = new JSlider (1,3,2);

	// PANEL 3: SKILLS
	JLabel skillLabel = new JLabel("extra health        <====================== Pegasus ======================>     extra food", JLabel.CENTER);
	JLabel armourLabel = new JLabel("Health", JLabel.LEFT);
	JLabel foodLabel = new JLabel("Food", JLabel.RIGHT);
	JSlider sliderSkills = new JSlider(0,100,50);
	
	// PANEL 4: HIGHSCORES & START
	JButton highScoreButton = new JButton("Show HighScores");
	JButton startButton = new JButton("Start game");
	
	// PANELS
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	
	// CONSTRUCTOR
	@SuppressWarnings("deprecation")
	public SetupGame(){
		super("Choose your settings");
		
		this.setSize(1500,1500);
		// panel 1
		p1.add(nameLabel);   // add label that indicates name should be typed here
		p1.add(nameField);	 // add text field where user can type name
		p1.setLayout(new GridLayout (1,2)); // define layout
		p1.setBorder(BorderFactory.createEmptyBorder(50, 120, 50, 120));
		
		// panel 2
		Hashtable<Integer, JLabel> labelsSliderDif = new Hashtable<>();   // hashtabel to connect values of slider with labels
		labelsSliderDif.put(new Integer(1), easyLevel);           // int 1 = easy
		labelsSliderDif.put(new Integer(2), moderateLevel);		 // int 2 = moderate
		labelsSliderDif.put(new Integer(3), hardLevel);			 // int 3 = hard
		
		sliderDifficulty.setPaintTicks(true);
		sliderDifficulty.setMajorTickSpacing(1); // set space between labels to 1
		
		sliderDifficulty.setLabelTable(labelsSliderDif); // add labels to slider
		sliderDifficulty.setPaintLabels(true);
		sliderDifficulty.setSnapToTicks(true); 			 // discrete values only, eg: no levels between hard and moderate
		
		
		p2.add(difficultyLabel);                 // add a title
		p2.add(sliderDifficulty);                // add difficulty slider
		p2.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
		p2.setLayout(new GridLayout (2,1));      // define layout
		
		// panel 3
		Hashtable<Integer, JLabel> labelsSliderSkills = new Hashtable<>(); // hashtabel to connect values of slider with labels 
		labelsSliderSkills.put(0, armourLabel);							   // int 0 = chose all armour
		labelsSliderSkills.put(100, foodLabel);							   // int 100 = chose all food
		
		sliderSkills.setPaintTicks(true);    // add indicating bar
		sliderSkills.setMajorTickSpacing(25); // set space between labels to 1
		
		sliderDifficulty.setLabelTable(labelsSliderSkills); // add labels to slider
		
		p3.add(skillLabel);        				// add title
		p3.add(sliderSkills);					// add skills slider
		p3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
		p3.setLayout(new GridLayout (2,1));		// define layout
		
		// panel 4
		highScoreButton.addActionListener(this);
		startButton.addActionListener(this);
		p4.add(highScoreButton);                 // add exit button
		p4.add(startButton);				// start button
		p4.setBorder(BorderFactory.createEmptyBorder(7, 15, 7, 15));
		p4.setLayout(new GridLayout (1,2));	// define layout
		
		setLayout(new GridLayout(4,1)); // define layout for JFrame
		add(p1);  						// add the panels
		add(p2);
		add(p3);
		add(p4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		JOptionPane.showMessageDialog(null,"<html> <b> SAVE THE PRINCESS </b> <br/><br/> <html>"
				+ "<html> Welcome to the Kingdom of Angmar. Evil spritis have kidnapped the princess and taken her to the castle of Doom.<br/><html>"
				+ "<html> The king has summoned you, a king's knight, to save the princess.<br/><html>"
				+ "<html> Be aware that the castle of Doom is well protected by archers and an impregnable wall.<br/><html>" 
				+ "<html> The castle has 4 entrances that are not protected, find one of these and enter the Castle.<br/><html>"
				+ "<html> You will start with full health and some proviand for your travel.<br/><html>"
				+ "<html> Every move you make will cost you energy which results in a consumption of food.<br/><html>"
		        + "<html> You can find additional food by hunting the dragons who are spread over the land.<br/> <html>"
		        + "<html> However, hunting dragons is a tough job and will lead to a decreased health.<br/> <html>"
		        + "<html> Your goal is to reach the princess before you run out of food or health.<br/><html>"
		        + "<html> The king knows this is a difficult task and will provide you with his best unicorn named Pegasus.<br/><html>"
		        + "<html> Pegasus can carry extra food for you and you can ride him like a true knight, which will increase your health.<br/><html>"
		        + "<html> <br/>However there must be balance between both, the more you chose to ride Pegasus, the less proviand he can carry.<br/><html>"
		        + "<html> The king advices you to visit his local vasal Lancelot, he will help you.<br/><html>"
		        + "<html> At last there are also kind farmers in the area who will help you with hospitality.<br/><html>"
		        + "<html> <br/><b> GL & HF <b/><html>");
	
	}
	
	public void actionPerformed(ActionEvent ev) {
		
		// if showHighScores button is clicked
		if (ev.getActionCommand().equals(highScoreButton.getText())) {    
			TopScores ts = new TopScores("", 0, true); // open topscore class, since no game is played yet, add player without name an score 
			ts.readWrite.show(); // read file and write to string
			ts.makeGui(); // make window gui
		}
		
		// if start button is clicked
		else if (ev.getActionCommand().equals(startButton.getText())) {
			
			// save values to player class
			this.newPlayerName = nameField.getText(); 
			this.difficultyLevel = sliderDifficulty.getValue();  
			this.additionalHealth = sliderSkills.getValue();
			this.additionalFood = (100 - sliderSkills.getValue());
			
			// exit window
			dispose();  // close 1st window
			
			// create Character and GridUi objects 
			Difficulty dif = new Difficulty(difficultyLevel); // create variables based on difficultyLevel
			Character PlayerChar = new Character(dif.health + additionalHealth,dif.food + additionalFood, dif.enemies, dif.friends ,dif.size, newPlayerName);
			GridUi board = new GridUi(PlayerChar); 
			board.setVisible(true);
		}
	}
		
}
