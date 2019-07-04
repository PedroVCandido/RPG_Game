package javaProject;

public class Difficulty {
	
	// VARIABLES
	public int difficutlyLevel;  // what difficutly? ranges from 1,2,3
	
	// variables for Character
	public int health;
	public int food; 
	
	// variables for myGrid (within Character)
	public int size; 
	public int friends;
	public int enemies;	
	
	// CONSTRUCTOR
	public Difficulty(int difficultyLevel){
		// set difficultyLevel
		
		// set other variables based on difficultyLevel
		if (difficultyLevel == 1) {
			health = 150;
			food = 150;
			size = 25;
			friends = 100;
			enemies = 100;
		}
		
		else if (difficultyLevel == 2) {
			health = 100;
			food = 100;
			size = 30;
			friends = 75;
			enemies = 110;
		}
		
		else if (difficultyLevel == 3) {
			health = 75;
			food = 75;
			size = 35;
			friends = 50;
			enemies = 120;
		}
	}	
}
