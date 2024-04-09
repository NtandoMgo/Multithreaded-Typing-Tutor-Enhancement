//package typingTutor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;
//import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
		private AtomicBoolean done ; //REMOVE
		private AtomicBoolean started ; //REMOVE
		private AtomicBoolean won ; //REMOVE

		private FallingWord[] words; private HungryWord hWord;
		private int noWords;
		Rectangle rH, rF;
		private final static int borderWidth=25; //appearance - border

		GamePanel(FallingWord[] words, HungryWord hWord, int maxY,	
				 AtomicBoolean d, AtomicBoolean s, AtomicBoolean w) {
			this.words=words; //shared word list
			this.hWord = hWord;
			noWords = words.length; //only need to do this once
			done=d; //REMOVE
			started=s; //REMOVE
			won=w; //REMOVE
		}
		//GamePanel(){}
		public void paintComponent(Graphics g) {
		    int width = getWidth()-borderWidth*2;
			
		    int height = getHeight()-borderWidth*2;
		    g.clearRect(borderWidth,borderWidth,width,height);//the active space--// not clrearing the whole game panel...
			
			g.clearRect(0,0,borderWidth,height);
			g.clearRect(width+borderWidth,0,borderWidth,height);

		    g.setColor(Color.pink); //change colour of pen
		    g.fillRect(borderWidth,height,width,borderWidth); //draw danger zone

		    g.setColor(Color.black);
		    g.setFont(new Font("Arial", Font.PLAIN,26));
		   //draw the words
		    if (!started.get()) {
		    	g.setFont(new Font("Arial", Font.BOLD, 26));
				g.drawString("Type all the words before they hit the red zone,press enter after each one.",borderWidth*2,height/2);	
		    	
		    }
		    else if (!done.get()) {
				//g.drawString(hWord.getWord(),hWord.getY()+borderWidth,hWord.getX());
				//int pos = 0, posMax = noWords/4;
		    	
				for (int i=0;i<noWords;i++){	    	
		    		g.drawString(words[i].getWord(),words[i].getX()+borderWidth,words[i].getY());	
					int txtWidth = g.getFontMetrics().stringWidth(words[i].getWord());	// width of falling words
					
					g.setColor(Color.green);
					g.drawString(hWord.getWord(),hWord.getX(), hWord.getY());
						
				  	int textWidth = g.getFontMetrics().stringWidth(hWord.getWord());	//to GET WIDTH of hungry words
					rH = new Rectangle(hWord.getX(), hWord.getY() - borderWidth,textWidth,36);
					rF = new Rectangle(words[i].getX()+borderWidth,words[i].getY() - borderWidth,txtWidth,36);
					
					// if the invisible rectangles of dimensions of the words intersect, the falling words dissapears
					if(rH.intersects(rF)){
					 	words[i].resetWord();
						TypingTutorApp.score.missedWord();
					}
					g.setColor(Color.black);
		    	}
		    	g.setColor(Color.lightGray); //change colour of pen
				
		    	g.fillRect(borderWidth,0,width,borderWidth);
		   }
		   else { if (won.get()) {
			   g.setFont(new Font("Arial", Font.BOLD, 36));
			   g.drawString("Well done!",width/3,height/2);	
		   } else {
			   g.setFont(new Font("Arial", Font.BOLD, 36));
			   g.drawString("Game over!",width/2,height/2);	
		   }
		   }
		}
		
		public int getValidXpos() {
			int width = getWidth()-borderWidth*4;
			int x= (int)(Math.random() * width);
			return x;
		}
		// My added method
		public int getValidYPos(){
			int height = getHeight() - borderWidth*4;
			int y = height/2;	// middle position

			//making sure y never below 200, make game fair for the user/player
			if (y> 150 && y < 200){y += 60;}
			else if (y>100 && y<150){y += 110;}
			else if (y<=100){y += 200;}
			return y;
		}
		
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(10); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				};
			}
		}

}

