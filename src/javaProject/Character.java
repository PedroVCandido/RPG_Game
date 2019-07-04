package javaProject;

import javaProject.BasicGrid;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;

public class Character extends BasicGrid{
	
	//VARIABLES
	
	public String name;
	protected int food;  			 // amount of food player possesses
	public int health;  			 // amount of health player possesses
	protected int step = 10;   	     // food consumption
	protected int wounded = -10; 	 // health reduce
	protected int dragonMeat = + 25; // food will be increased by this when slaying dragon
	protected int friendsMeal = 15;  // increase food with 15 if encountered friend
	protected int friendsHelp = + 10;// increase health with 10 if encountered friend 
	protected int lancelotsMeal = +100;
	protected int lancelotsHelp = + 100;
	int position[];                  // start position of player
	int newpos[];
	public int[] oldPosition; 		//previous position used in cleanup operations
	public boolean foundPrincess;
	
	//CONSTRUCTOR
	public Character(int food, int health, int enemies, int friends, int size, String name){
		
		// values for BasicGrid
		super(enemies, friends ,size);
		

		this.position = selectRandomLocation(23, 23, 0, 0);		
		this.myGrid[this.position[0]][this.position[1] ]= "X";
		
		// adress new variables with values
		this.food = food;
		this.health = health;
		this.name = name;
		
		// call functions from BasicGrid to setup gameboard
		this.initializeCastle();  // build castle
		this.initializeVillage(); // build village
		this.initializeRandoms(); // build friends, enemies, playerlocation	
		
		// extract current location from myGrid to Character class
		
	}
	
	// getter
	String getName() {
		return name;
	}
	//METHOD FIND CURRENT LOCATION: coords of X in grid will be added to position variable
	void findCurrentLocation() {
		for (int i=0; i <= this.size -1; i++) {
			for (int j=0; j <= size -1; j++) {
				if(myGrid[i][j] == "X") {
					this.position[0]=i;
					this.position[1]=j;
				}
			}	
		}
	}
	
	
	
	// METHOD TO MOVE X: this method is a sub method of move method
	void moveXInGrid(int[] newLocation) {
		
		int oldPosition0 = this.position[0];
		int oldPosition1= this.position[1];		
		this.oldPosition = new int[] {oldPosition0,oldPosition1};
		
		
		// set current position
		myGrid[position[0]][position[1]] = null; // remove current position
		myGrid[newLocation[0]][newLocation[1]] = "X";		   // set new location
		this.position = newLocation; 
		
		
	}	

	
	// METHOD TO MOVE THE PLAYER IN THE GRID
	void move(int[] newLocation) {	
			

		// check weather new coords are cardinal to current position

		if (this.checkBounds(newLocation) && this.getViewSpace(1, newLocation) ) {	
		
			// dont move
			if (this.myGrid[newLocation[0]][newLocation[1]] == "X") {
				// nothing happens
			}
			
			// move to empty cell
			if (this.myGrid[newLocation[0]][newLocation[1]] == null || this.myGrid[newLocation[0]][newLocation[1]] == " ") {
				// consume food
				this.food = this.food-this.step;
				// update location
				moveXInGrid(newLocation);
			}
			
			// move to wall
			else if (this.myGrid[newLocation[0]][newLocation[1]] == "#") {	//These need to be made into methods
				JOptionPane.showMessageDialog(null,"<html> You cannot climb walls! <br/> Find a way to go around. <html>");
			}
			
			// move to Lancelot
			else if (this.myGrid[newLocation[0]][newLocation[1]] == "Lancelot") {
				// consume some food
				this.food = this.food-this.step;	
				// increase health and food
				this.food = this.food + lancelotsMeal;
				this.health = this.health + lancelotsHelp;
				// update location
				moveXInGrid(newLocation);
				JOptionPane.showMessageDialog(null, "<html>Lancelot, vasal to the king speaks to you: <br/><br/><html>"
						+ "<html> <i>Welcome brave knight. The king informed me about the princess and your quest. </i><br/> <html> "
						+ "<html> <i>I will help you, you can rest here and enjoy a meal. </i><br/> <html>"
						+ "<html> <br/> Your healt has increased and you are provided off extra food. <html>");
			}
			
			// hunt dragon
			else if (this.myGrid[newLocation[0]][newLocation[1]] == "-") {
				// consume some food
				this.food = this.food-this.step;
				// reduce health
				this.health = this.health+this.wounded;
				// increase food 
				this.food = this.food + this.dragonMeat; 
				// update location
				moveXInGrid(newLocation);
				JOptionPane.showMessageDialog(null,"<html>You have slain a dragon! <br/>You suffered some damage, but at least now you can eat.<html>");
			}
			
			// meet a friend
			else if (this.myGrid[newLocation[0]][newLocation[1]] == "+") {
				// consume some food
				this.myGrid[newLocation[0]][newLocation[1]] = "X";
				// increase food and health
				this.food = this.food + this.friendsMeal; 
				this.health = this.health + this.friendsHelp;
				//update location
				moveXInGrid(newLocation);
				JOptionPane.showMessageDialog(null,"<html>You encountered friendly farmers. <br/> They offer you food and rest. <br/> Your health increased and you are provided of some extra food.<html>");
			}	
			
			// be hit by arrows (outer buffer)
			else if (this.myGrid[newLocation[0]][newLocation[1]] == "  ") {
				// consume some food
				this.food = this.food-this.step;
				// reduce health
				this.health = this.health + this.wounded; 
				// update location
				moveXInGrid(newLocation);
				JOptionPane.showMessageDialog(null,"You are being hit by arraws, get out of here!");
			}
			
			// be hit by arrows (inner buffer)
			else if (this.myGrid[newLocation[0]][newLocation[1]] == "   ") {
				// consume some food
				this.food = this.food-this.step;
				// reduce health
				this.health = this.health+this.wounded;
				// update location
				moveXInGrid(newLocation);
				JOptionPane.showMessageDialog(null,"You close to the castle wall! Its raining arrows, get out of here");
			}
			
			// find the princess
			else if (this.myGrid[newLocation[0]][newLocation[1]] == "Princes") {
				// no food consumption to avoid arriving and dying, would be confusing
				foundPrincess = true;
				// update location
				moveXInGrid(newLocation);
			}				
		}				
		else  {
			JOptionPane.showMessageDialog(null, "<html>This move is not possible! <br/> Click one of the surrounding cells to move.<html>");
		}
	}				 

	
	//METHOD TO SET VIEW OF PLAYER: gathers the coords of tiles that should be visible
	boolean getViewSpace(int size, int[] location) {
			
			int[] oldLocation = this.position;
			
			ArrayList<Integer>  x = new ArrayList<Integer>(); 
			ArrayList<Integer> y = new ArrayList<Integer>();
				
					
			for (int j = 0; j <= size; j++)	{
				for (int i = -1 * j ; i <= j; i++) {	
					
					boolean inBounds1 = ((oldLocation[0] + i) >= 0) && ((oldLocation[0] + i) < this.myGrid.length); //x				
					boolean inBounds2 = (oldLocation[1] + i >= 0) && (oldLocation[1] + i < this.myGrid.length);	//y				
					if (inBounds1 && inBounds2) {				
						y.add(oldLocation[1] + i);						
						x.add(oldLocation[0] + i);							
					}				
				}	
			}				
			boolean inBoundsX = (location[0] >= Collections.min(x)) && (location[0] <= Collections.max(x)) ; //x			
			boolean inBoundsY = (location[1] >= Collections.min(y)) && (location[1]  <= Collections.max(y)) ;	//y
					
			return inBoundsX && inBoundsY;
	}
	
	
	
boolean checkBounds(int[] location) {
		
		boolean inBoundsx = ((location[0]) >= 0) && ((location[0]) < this.myGrid.length); //make method check bounds
		boolean inBoundsy = ((location[1]) >= 0) && ((location[1]) < this.myGrid.length);
		
		return inBoundsx && inBoundsy;
	}
		
		
	// MAIN METHOD 
/*	public static void main(String[]args) {
		
		//create character
		Character pedro = new Character(100,100);
		
		// display values
		System.out.println(pedro.health + " "  + pedro.food + " " + Arrays.toString(pedro.position));
		
		// check move method
		int[] coord= new int[] {1,1};
		pedro.move(coord);		
		pedro.printGrid();
		
		// display updated values
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(pedro.health + " " + pedro.food + " " + Arrays.toString(pedro.position));
		
		System.out.println(" ");
		System.out.println(" ");
		//pedro.printGrid();
	}
*/		
}
	
