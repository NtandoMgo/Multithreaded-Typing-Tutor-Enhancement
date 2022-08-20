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
	}

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
		int i=0;
		while (i<noWords) {		
			while(pause.get()) {};
			if (words[i].matchWord(target)) {
				System.out.println( " score! '" + target); //for checking
				score.caughtWord(target.length());	
				//FallingWord.increaseSpeed();
				break;
			}
		   i++;
		}
		
	}	
}
