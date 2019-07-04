package TopScores;

/* 
this class:
 - ranks the player names and scores in the readwrite class
*/


import java.util.Comparator;

class SortByScore implements Comparator<SingleScoreRecord>{ 

	@Override
	public int compare(SingleScoreRecord s1, SingleScoreRecord s2) {
		return s2.score - s1.score;
	} 
} 