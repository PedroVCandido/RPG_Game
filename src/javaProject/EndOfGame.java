package javaProject;

import TopScores.TopScores;

public class EndOfGame {
	// INPUT VARIABLES
	int remainingHealth;
	int remainingFood; 
	boolean foundPrincess; 
	String name; 
	int difficulty;
	
	// OUTPUT VARIABLES
	boolean gameOver; 
	boolean foundPrincess1;
	int score;

	// CONSTRUCTOR
	EndOfGame(int rh, int rf, boolean fp, String n, int d) {
		
		// use input variables
		this.remainingFood = rf; 
		this.remainingHealth = rh;
		this.foundPrincess = fp;
		this.name = n; 
		this.difficulty = d;
		
		// create output variables
		score = remainingHealth * difficulty;
		
		// define weather game has finished or not, if finished determine of game is won or lost
		// depending on won/lost call according gui windows
		if (remainingFood <= 0 || remainingHealth <= 0 || foundPrincess == true ) {
			gameOver = true;

			// the case in which the game is won (could also be doen with cases to avoid inner if statements
			if (foundPrincess == true) {
				// create TopScore object, save the score and make window
				TopScores newScore = new TopScores(name, score, false); // create TopScore class
				newScore.readWrite.saveAndShow();  // save data to txtfile
				newScore.makeGui();                // make gui window to show achieved score and topscores
			}
			else if(foundPrincess == false) {
				// create Lost object, make lost window
				 new Lost();
			}
		}
		// if these conditions are not met the game just continues
		else {
			gameOver = false;	
		}	
	}
}