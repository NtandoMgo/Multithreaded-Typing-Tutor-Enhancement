//import java.awt.Color;

//import javax.swing.JLabel;

public class HungryWord{
    
    private String word; // the word
	private int x; //position - width---- should be the one changing for the word to slide
	private int y; // postion - height----- this should stay constant or in a specific range
	private int maxX; //maximum width
	private boolean slided; //flag for if user does not manage to catch word in time

    private int slidingSpeed; //how fast this word is
	private static int maxWait=1000;
	private static int minWait=100;

	public static WordDictionary dict;

    HungryWord(){ // default constructor
        word = ""; // not used..
        x = 0;
        y = 400;
        maxX = 300;
        slided = false;
        slidingSpeed = (int) (Math.random()*(maxWait-minWait) + minWait);
        //slidingSpeed = 80;
    }

    HungryWord(String text){
        this();
        this.word = text;
    }

    HungryWord(String text, int y, int maxX){
        this(text);
        this.y = y;     // only x changes, word is at the left at start
        this.maxX = maxX;
    }

    public static void increaseSpeed( ) {
		minWait+=50;
		maxWait+=50;
	}
	
	public static void resetSpeed( ) {
		maxWait=1000;
		minWait=100;
	}

    public synchronized void setX(int x){
        if (x > maxX) {
            x = maxX;
            slided = true;   //slided till end--user did not manage to catch this word
        }else{this.x = x;}
    }

    public synchronized void setY(int y) {this.y = y;}

    public synchronized  void setWord(String text) {this.word=text;}

	public synchronized  String getWord() {
        //String myWord = word;
        /*JLabel myWord =new JLabel(word);
        myWord.setForeground(Color.green);
        return word;*/
        return word;
    }
	
	public synchronized  int getX() {return x;}	
	
	public synchronized  int getY() {return y;}
   
    public synchronized  int getSpeed() {return slidingSpeed;}

	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}

    public synchronized void resetPos() {
		setX(0);
	}

    public synchronized void resetWord() {
        resetPos();
        word = dict.getNewWord();
        slided = false;
        slidingSpeed = (int) (Math.random() * (maxWait-minWait)+minWait);
    }

    public synchronized boolean matchWord(String typedWord) {
        if (this.word.equals(typedWord)) {
            return true;
        } else
            return false;
    }

    public synchronized void slide(int incr) {
        setX(x+incr);
    }

    public synchronized boolean slided() {return slided;}

    public synchronized int getLength(){
		int len = word.length();
		return len;
	}

    /*public void run() {
        // TODO Auto-generated method stub
        System.out.println(wdict.getNewWord());
        
    }*/

    /*public static void main(String[] args) {
        HungryWordMover thread = new HungryWordMover();
        thread.run();
    } */
    
}
