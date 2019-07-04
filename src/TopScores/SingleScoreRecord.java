package TopScores;

/* 
this class:
 - presents a data record of a previous played game. each object has the player name and the highscore
 - is used in the TopScores class to load in all previous highscores
*/


public class SingleScoreRecord {
	
	// variables
	public int score;
	public String name;
	
	//constructor
	public SingleScoreRecord(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	// getters
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
}
