//package typingTutor;

import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}
/* 
	// make it catch a word lowest if there's a duplicate
	public void Catch(FallingWord[] wordList, String typedWord) {
		int track = 0;
		for (int i = 0; i < wordList.length; i++) {
			for (int index = 0; index < wordList.length; index++) {
				if (wordList[index].equals(wordList[i])){
					track++;
				}
			}
		}
		// catched words there are duplicates
		if (track > 1){

		}
	}*/

	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}
	
	public static void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}
	
	public void run() {
		/*int i=0;
		while (i<noWords) {		
			while(pause.get()) {};

			if (words[i].matchWord(target)) {
				System.out.println( " score! '" + target); //for checking
				score.caughtWord(target.length());	
				//FallingWord.increaseSpeed();
				break;
			}
		   i++;
		}*/
		/*for (int i = 0; i < noWords; i++) {
			while(pause.get()) {};
			if (words[i].matchWord(target)) {
				System.out.println( " score! '" + target); //for checking
				score.caughtWord(target.length());	
				//FallingWord.increaseSpeed();
				break;
			}
		}*/
		//int k = 0;
		int p = -1;
		for (int j = 0; j < noWords; j++) {
			while(pause.get()) {};	// will not execute whats inside till user press start

			if (words[j].matchWord(target)) {		// condition = true if the typedWord is in words(falling words)
				if (p < 0) {		// no duplicates 
					p = j;
				} else if (words[j].getY() > words[p].getY()){
					//there are duplicates and keep the index position of the lowest words (greater height-y)
					p = j;
				}
				//k++;
				
			}
		}
		if (p < 0) {	// no duplicate
			return;
		}else{
			System.out.println( " score! '" + target); //for checking
			words[p].resetWord();
			score.caughtWord(target.length());
		}
	}	
}
