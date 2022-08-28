//package typingTutor;

import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.*;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static HungryWord hWord;
	//private static HungryWord hWord;
	private static int noWords; //how many
	private static Score score; //user score
	//
	Font wordFont;
	
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
		
		/*int p = -1;
		for (int j = 0; j < noWords; j++) {
			while(pause.get()) {};	// will not execute whats inside till user press start

			if (words[j].matchWord(target)) {		// condition = true if the typedWord is in words(falling words)
				if (p < 0) {		// no duplicates 
					p = j;
				} else if (words[j].getY() > words[p].getY()){
					//there are duplicates and keep the index position of the lowest words (greater height-y)
					p = j;
				}
			}
		}
		if (p < 0){
			return;
		}else {
			System.out.println( " score! '" + target); //for checking
			words[p].resetWord(); //System.out.println(Arrays.toString(hWords));
			score.caughtWord(target.length());
		}*/
	
	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}
	


	// my run code
	/* int p = -1,p1 = -1, p2 = -1, position = 0, posMax = noWords/4;
	for (int pos=0;pos<posMax;pos++){
		for (int j = 0; j < noWords; j++) {
			while(pause.get()) {};	// will not execute whats inside till user press start

			if (words[j].matchWord(target)) {		// condition = true if the typedWord is in words(falling words)
				if (p < 0) {		// no duplicates 
					p = j;
				} else if (words[j].getY() > words[p].getY()){
					//there are duplicates and keep the index position of the lowest words (greater height-y)
					p = j;
				}
			}
			int wX = words[j].getX(), wY = words[j].getY();
			int hX = hWords[pos].getX(), hY = hWords[pos].getY();
			// making sure all letters make collision
			int wEnd = wX + words[j].getLentght(), hEnd = hX + hWords[pos].getLentght();
			if (((hX >= wX && hX <= wEnd) || (wX >= hX && wX<= hEnd) || (wEnd>=hX && wEnd<=hEnd) || (hEnd>=wX && hEnd<=wEnd))  && wY == hY){	// add y comparison
				p1 = j;
			}else if (hWords[pos].matchWord(target)){
				p2 = pos;
			}
		if (p < 0){
				return;
			}else {
				System.out.println( " score! '" + target); //for checking
				words[p].resetWord(); //System.out.println(Arrays.toString(hWords));
				score.caughtWord(target.length());
			}

		}
	}*/
	public void run() {
		
		wordFont = new Font("Arial",Font.BOLD, 26);
		Canvas screen = new Canvas();
		FontMetrics fMetrics = screen.getFontMetrics(wordFont);
		FontMetrics fMetrics2 = Toolkit.getDefaultToolkit().getFontMetrics(wordFont);

		int p = -1,pos = 0, posMax = noWords/4;
		// collision variables
		int r = -1;		// initially no collision takes place
		//System.out.println(" Hun: "+hWords[0].getWord());
		//for (int pos=0;pos<posMax;pos++){
			//System.out.println("Inside the loop: "+hWords[0].getWord());
			for (int j = 0; j < noWords; j++) {
				while(pause.get()) {};	// will not execute whats inside till user press start

				if (words[j].matchWord(target)) {		// condition = true if the typedWord is in words(falling words)
					if (p < 0) {		// no duplicates 
						p = j;
					} else if (words[j].getY() > words[p].getY()){
						//there are duplicates and keep the index position of the lowest words (greater height-y)
						p = j;
					}
				}else if(hWord.matchWord(target)){
						r = j;
				}
				pos++;
			
			}
			//System.out.println("P: "+p+", R: "+r);

			if (p < 0 && r < 0) {return;}
			else if(p>=0 && r<=0){
				// catch falling words
				System.out.println( " score! '" + target); //for checking
				words[p].resetWord(); //System.out.println(Arrays.toString(hWords));
				score.caughtWord(target.length());
			}else if (p<=0 && r>=0){
				System.out.println( " score! '" + target); //for checking
				hWord.resetWord(); //System.out.println(Arrays.toString(hWords));
				score.caughtWord(target.length());
			}else if (p>=0 && r>=0){
				if (!hWord.matchWord(words[p].getWord())){
					System.out.println( " score! '" + target); //for checking
					hWord.resetWord(); //System.out.println(Arrays.toString(hWords));
					score.caughtWord(target.length());
				}
			}
			// hWord crashed with falling word
			// if (p1<0){
			// 	return;
			// }else{
			// 	System.out.println( " score! '" + words[p1]);
			// 	words[p1].resetWord(); 
			// 	score.caughtWord(words[p1].getLength());
			// }
			// // user caught the hWord
			// if (p2<0){
			// 	return;
			// }else{
			// 	System.out.println( " score! '" + hWords[p2]); 
			// 	hWords[p2].resetWord(); 
			// 	score.caughtWord(hWords[p2].getLength());
			// }

		//}
	}	
}
