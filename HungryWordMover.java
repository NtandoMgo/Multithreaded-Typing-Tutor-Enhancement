import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread{
    private HungryWord myHWord;
    private AtomicBoolean done;
	private AtomicBoolean pause; 
	private Score score;
	CountDownLatch startLatch; //so all can start at once

    HungryWord hunWords;
    FallingWord[] words;
    int noWords;

    ThreadLocalRandom random = ThreadLocalRandom.current();

    // Constructor
    HungryWordMover (HungryWord word) {myHWord = word;}

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
	/*public void setHWords(HungryWord Words) {
		hunWords = Words;
	}*/

    // Random sleep method
    public void threadSleep(){
        int MillisecondsToSleep = random.nextInt(1500, 4000);
        try {

            sleep(MillisecondsToSleep); // thread sleeps before next word pops up
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run(){
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
            }
            if (!done.get() && myHWord.slided()) {
				score.missedWord();
                threadSleep();      // sleep before new words comes
				myHWord.resetWord();
			}
			myHWord.resetWord();
        }
    }
}
