package TopScores;

/* 
this class:
 - reads in the txt file
 - add new record
 - ranks the records
 -writes output to txt file
*/

import java.util.ArrayList;
import java.util.List;
//imports
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class ReadWrite {
	
	// VARIABLES
	
	// file in src folder: this is the txtfile where scores and playername will be stored
	File topScores = new File("src/topscores.txt");                     // file.txt

	// new score and name to be added
	int newTopScore;
	String playerName;
	
	// data container for previous scores to be extracted from .txt file
	List<SingleScoreRecord> scores = new ArrayList<SingleScoreRecord>();
	
	// data in string
	public String dataInString;

	// CONSTRUCTOR	
	public ReadWrite(String playerName, int newTopScore){
		this.newTopScore =newTopScore;
		this.playerName = playerName;
	}
	
	// METHOD: READ FILE AS MAP (DATA CONTAINER)
	public void readFile () {
		File myFile = new File("topScores.txt");
		if (myFile.exists()== true){
			try {
				//introduce scanner
				Scanner sc = new Scanner(new FileReader ("topScores.txt"));    
				sc.useDelimiter(Pattern.compile("( )|(\r\n)"));
				sc.useLocale(Locale.ENGLISH);
				
				// make a list to save previous topscores
							
				//scan file
				while(sc.hasNext()) {
					
					//scan data
					String name = sc.next();
					int points = sc.nextInt();
					
					// add data to file
					scores.add(new SingleScoreRecord(name, points));
				}
				sc.close();
			}
		catch(IOException e) {} 
		}
	}
	
	// METHOD: ADD NEW HIGHSCORE AND NAME TO DATA
	public void addNew() {
		scores.add(new SingleScoreRecord(playerName, newTopScore));
	}
	
	// METHOD: RANKING AND KEEP TOP 10
	public void rank() {
		Collections.sort(scores, new SortByScore());
		if (scores.size() <= 10) {
			scores = scores.subList(0, scores.size());
		}
		if (scores.size() > 10 ) {
			scores = scores.subList(0,10);
		}
	}
	
	// METHOD: SAVE DATA IN STRING
	public void writeString() {
		dataInString="HIGH SCORES: <br/>";
		for (SingleScoreRecord o: scores) {
			dataInString = dataInString + o.getName()+ "		" + o.getScore()+ "<br/>";
		}
		dataInString = dataInString + "<br/>";
		dataInString = "<html>" + dataInString + "<html>";
	}
	
	// METHOD: OVER WRITE FILE WITH HIGH SCORE
	public void writeFile() {
		try {
			Writer out = new BufferedWriter(
			new FileWriter("topScores.txt"));
			for (SingleScoreRecord scoreRecords : scores) {
				out.write(scoreRecords.getName() + " " + scoreRecords.getScore()+ "\r\n");
			}
			out.close();
		}
		catch(IOException e) {}
	}
	
	//COMBINE ALL METHODS TO EXTRACT DATA IN 1 METHOD: this is used when the game has ended
	public void saveAndShow() {
		readFile();
		addNew();
		rank();
		writeString();
		writeFile();
	}
	
	// COMBINE ALL METHODS FOR SHOWING HIGHSCORES: this method is used when user want to see highScores
	public void show() {
		readFile();
		writeString();
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
