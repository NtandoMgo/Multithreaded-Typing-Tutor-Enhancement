import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.*;

public class HungryWordMover extends Thread{
    private HungryWord myHWord;
    private AtomicBoolean done;
	private AtomicBoolean pause; 
	private Score score;
	CountDownLatch startLatch; //so all can start at once

    HungryWord hunWords;
    FallingWord[] words;
    int noWords;

    Font wordFont;

    // Constructor
    HungryWordMover (HungryWord word) {myHWord = word;}

    // 
    
    HungryWordMover(HungryWord word, WordDictionary dict, Score score,
    CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
        this(word);
		this.startLatch = startLatch;
		this.score=score;
		this.done=d;
		this.pause=p;
    }
    public  void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}

	// Hungry words settor
	public void setHWords(HungryWord Words) {
		hunWords = Words;
	}

    public void run(){
        wordFont = new Font("Arial",Font.BOLD, 26);
		Canvas screen = new Canvas();
		FontMetrics fMetrics = screen.getFontMetrics(wordFont);
		FontMetrics fMetrics2 = Toolkit.getDefaultToolkit().getFontMetrics(wordFont);

        try {
            System.out.println(myHWord.getWord() + " waiting to move");
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myHWord.getWord() + " moved");

        while (!done.get()) {
            while (!myHWord.slided() && !done.get()) {
                myHWord.slide(10);
                try {

                    sleep(myHWord.getSpeed());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while(pause.get()&&!done.get()) {};
                
               /* for (int i = 0; i < words.length; i++) {
                    int xStart = myHWord.getX(), xEnd = xStart + fMetrics2.stringWidth(myHWord.getWord());      // left-up corner and right-up coner (x-vals) of hungry word
                    int yStart = myHWord.getY(), yEnd = yStart + fMetrics.getHeight();  // (y-vals) for hungry word

                    int xInit = words[i].getX(), xFinal = xInit + fMetrics2.stringWidth(words[i].getWord());     // left-up corner and right-up coner (x-vals) of falling word
                    int yInit = words[i].getX(), yFinal = yInit + fMetrics.getHeight();     // (y-vals) for falling word

                    if (xInit>=xStart && xInit<=xEnd){System.out.println("crushed condition 1");}
                }
                */
            }
            if (!done.get() && myHWord.slided()) {
				score.missedWord();
				myHWord.resetWord();
			}
			myHWord.resetWord();
        }
    }
}
