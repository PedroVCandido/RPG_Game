package javaProject;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Arrays;
import java.io.IOException;

import javax.imageio.ImageIO;
//import javax.swing.JOptionPane;
import javax.swing.*;
//import javax.swing.border.LineBorder;
import javax.swing.border.Border;

import javaProject.StretchIcon;

public class GridUi extends JFrame implements ActionListener {
	// VARIABLES
		public Character player;  // player class is a variable object within this object
		
		public Border border;     // border
		public int[] coord = new int[2];  // coords of a single tile
			
		public String[] labelArray;   // this is an array of labels to put on the buttons
		public String[] newLabelArray;
		public JButton[] buttonArray; // this is an array of buttons
		
		private static final long serialVersionUID = 1L; // ???
		
		JPanel pGrid;
		JPanel pStats = new JPanel();
		JLabel currentHealth = new JLabel();
		JLabel currentFood = new JLabel();

		public Image mainIcon;

	// CONSTRUCTOR
	public GridUi(Character player) {
		
		// variables
		this.player = player;	  // player class is only variable that needs to assigned
		
		currentFood.setText("you have: " + Integer.toString(player.food) + " provisions");
		currentHealth.setText("your health is: " +  Integer.toString(player.health));
	
		// settings
		this.setTitle("A new World to Discover!"); // title
		getContentPane();
		
		// pGrid layout
		this.pGrid = new JPanel();
		pGrid.setLayout(new GridLayout(this.player.myGrid.length, this.player.myGrid.length)); // layout is 2d array
		pGrid.setBackground(Color.white); // set color
		this.border = BorderFactory.createEmptyBorder();  // borderfactory
		
		// pGrid buttons
		this.buttonArray =  new JButton[this.player.myGrid.length * this.player.myGrid.length]; // array of buttons, size is defined
		this.labelArray = makeArray();  // this is method defined below, it creates a 1d string array of the myGrid values
		this.createButtons(); // createButtons is a method defined below, based on labelArray it makes buttons
		this.setIcon(); //set the icon for the main character
		this.setView();
		this.validate();
		
		//pStats
		pStats.setLayout(new GridLayout(1,2));
		pStats.add(currentFood);
		pStats.add(currentHealth);
		
		// add together
		//pGrid.setSize(new Dimension(900,900));
		
		//pStats.setSize(new Dimension(100,900));
		setLayout(new BorderLayout());
		add(pGrid, BorderLayout.CENTER);
		add(pStats, BorderLayout.SOUTH);
		
		// final settings
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//METHOD: get an icon from the resources and sets on the JButton where the main player is
	public void setIcon() {
		
		int ref = (this.player.position[0] * this.player.myGrid.length) + this.player.position[1];
		
		try {
			this.mainIcon = ImageIO.read(getClass().getResource("/images/main2.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.buttonArray[ref].setIcon(new StretchIcon (this.mainIcon));

	}
	
	
	
	
	
	
	//METHOD MAKE ARRAY: used in constructor, makes the myGrid 2d array into a 1 d string array
	public String[] makeArray() {
		String[] stringArray = new String[this.player.myGrid.length * this.player.myGrid.length];
		int k = 0;
		for (int i = 0; i < this.player.myGrid.length; i++) {
			for (int j = 0; j < this.player.myGrid.length; j++) {
				stringArray[k] = this.player.myGrid[i][j];
				k++;
			}
		}
		return stringArray;
	}
	
	
	// METHOD CREATE BUTTONS: used in constructor, makes buttons based on 1d array of labels
	public void createButtons() {
		
		int i = 0;
		for (String label : this.labelArray) {  // iterate throug all labels in labelArray
			
			// define coords of button
//			int coordinate1 = i % 25;   //x
//			int coordinate2 = (i / 25); //y					
//			int[] newCoordinate = {coordinate2,coordinate1};
		
			this.buttonArray[i] = new JButton(); 		// implement coordinates for the JButtons, new JButton class
							
			this.labelButton(this.buttonArray[i], label); // this is a method defined below, it adds a label to a button
			
			// layout settings of button
			this.buttonArray[i].setBackground(Color.black);//Black By Default
			this.buttonArray[i].setForeground(Color.black);	
			this.buttonArray[i].setBorder(this.border);
			this.buttonArray[i].setOpaque(true);
			
			// add action listener
			this.buttonArray[i].addActionListener(this);
			
			// add buttons to contentPane
			pGrid.add(this.buttonArray[i]);	
			
			// iterate to next button
			i++; 					
		}				
	}
	
	// METHODD: this is submethod for the createButtons, it provides a txt label to a JButton
	public void labelButton(JButton btn, String label) {
		btn.setText(label);	
	}
	
	// ACTION LISTENER 
	public void actionPerformed(ActionEvent ev) {	
		// iterate over all buttons
		for (int i = 0; i < this.buttonArray.length; i++) {
			
			// define the coords of the button based on its position in array
			int[] newCoord = new int[2];
			newCoord[1] = i % player.myGrid.length;   // y coord
			newCoord[0] = (i / player.myGrid.length); // x coord					
			// if a particular button was clicked: move the player in his grid
			
			
			if (ev.getSource() == buttonArray[i]) {
				
				
				this.player.move(newCoord);	// move the player to this coord
				this.setView();
				this.reloadButtons(i, newCoord);
				
			}
		}	
			
		this.reloadLabels();
		
		// object to define weather this was the last move or not
		EndOfGame end = new EndOfGame(player.health, player.food, player.foundPrincess, player.name, player.myGrid.length);	
		if(end.gameOver == true) {
			this.setVisible(false);
		}		
	}
	
	
	public void setView() {				
		int j  = 0;
		for (int i = 0; i < this.buttonArray.length; i++) {					
			int coord1 = i % player.myGrid.length;   //x
			int coord2 = (i / player.myGrid.length); //y					//local variable 
			int[] newcoord = {coord2,coord1};					
													
			if(j>=5) { 
				this.buttonArray[i].setBackground(Color.black);
				this.buttonArray[i].setForeground(Color.black);
				this.buttonArray[i].setOpaque(true);						
				}
			
			if (this.player.checkBounds(newcoord) && this.player.getViewSpace(3, newcoord)) {
				this.buttonArray[i].setBackground(Color.white);
				this.buttonArray[i].setForeground(Color.black);
				this.buttonArray[i].setOpaque(true);
				j++;
			}	
													
			this.validate();					
		}
	}
	
	
	
	
	
	
	
	// METHOD RELOAD: sub-method of action listener: updates the buttons, icon & labels after moving in th grid.
	public void reloadButtons(int ref, int[] coord) {
		// make again array of labels based on the updated myGrid
		this.newLabelArray = makeArray(); 
		
		// iterate through labels and update them on the buttons
		int i = 0;
		for (String label : this.newLabelArray) {
			this.labelButton(this.buttonArray[i], label);		
			i++;
		}	
		
		if (this.player.checkBounds(coord) && this.player.getViewSpace(1, coord) ) {
		
			this.buttonArray[ref].setIcon(new StretchIcon (this.mainIcon));		
			int oldRef = (this.player.oldPosition[0] * this.player.myGrid.length) + this.player.oldPosition[1];
			this.buttonArray[oldRef].setIcon(null);
		}
		
	}
	
	
	public void reloadLabels() {
		String newHealth = Integer.toString(this.player.health);	
		String newFood = Integer.toString(this.player.food);
		currentHealth.setText("Your health is: " + newHealth);
		currentFood.setText("You have: " + newFood + " provisions");	
	}

	
	
	@SuppressWarnings("serial")
	class GridButton extends JButton{
		
			int[] coordinate = new int[2];
			
			GridButton(int[] coordinate){
				super();
				this.coordinate = coordinate;	
				
			}				
	}

	
	
		
	
}


