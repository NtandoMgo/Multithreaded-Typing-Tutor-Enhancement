//package typingTutor;

import java.util.concurrent.atomic.AtomicBoolean;
//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static HungryWord hWord;
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}

	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}

	// Hungry words settor
	public static void setHWord(HungryWord Word) {
		hWord = Word;
	}
	
	public static void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}
	public void run() {
		int p = -1;	// initially no collision takes place
		int r = -1;		
			for (int j = 0; j < noWords; j++) {
				while(pause.get()) {};	// will not execute whats inside till user press start

				if (words[j].matchWord(target)) {	// condition = true if the typedWord is in words(falling words)
					if (p < 0) {		// no duplicates 
						p = j;			// keep the position of the current word - first word thats a match
					} else if (words[j].getY() > words[p].getY()){
						//there are duplicates and keep the index position of the lowest words (greater height-y)
						p = j;
					}
				}else if(hWord.matchWord(target)){	// hungry word matches the typedword
						r = j;
				}
			}
			//System.out.println("P: "+p+", R: "+r);

			if (p < 0 && r < 0) {return;}
			else if(p>=0 && r<=0){
				// catch falling words
				System.out.println( " score! '" + target);
				words[p].resetWord();
				score.caughtWord(target.length());
			}else if (p<=0 && r>=0){
				// catch hungry word
				System.out.println( " score! '" + target);
				hWord.resetWord(); 
				score.caughtWord(target.length());
			}else if (p>=0 && r>=0){
				// catch hungry words if there are valid p and r and word in falling words is not the same as hungry word
				if (!hWord.matchWord(words[p].getWord())){
					System.out.println( " score! '" + target); 
					hWord.resetWord(); 
					score.caughtWord(target.length());
				}else {
					System.out.println( " score! '" + target); 
					hWord.resetWord(); 
					score.caughtWord(target.length());
				}
			}
	}	
}
