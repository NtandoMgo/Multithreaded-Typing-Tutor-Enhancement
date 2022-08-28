//package typingTutor;

public class WordDictionary {
	int size;
	static String [] theDict= {"litchi","banana","apple","mango","pear","orange","strawberry",
		"cherry","lemon","apricot","peach","guava","grape","kiwi","quince","plum","prune",
		"cranberry","blueberry","rhubarb","fruit","grapefruit","kumquat","tomato","berry",
		"boysenberry","loquat","avocado"}; //default dictionary*/

	/*String theDict[] = {"peach","peach","peach","kiwi","kiwi","plum","prune",
	"peach","peach","kiwi","fruit","peach","peach","tomato","berry",
	"kiwi","kiwi"}; // my new default to test if it works when there are duplicates*/

	WordDictionary(String [] tmp) {
		size = tmp.length;
		theDict = new String[size];
		for (int i=0;i<size;i++) {
			theDict[i] = tmp[i];
		}
	}
	WordDictionary() {
		size=theDict.length;
	}
	
	public synchronized String getNewWord() {
		int wdPos= (int)(Math.random() * size);
		return theDict[wdPos];
	}
	
}
