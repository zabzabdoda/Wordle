import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class Word {
	
	// the current letter the player is on
	private int currentLetterIndex;
	private Letter[] letters;
	
	public Word(int x, int y) {
		currentLetterIndex = 0;
		letters = new Letter[5];
		for(int i = 0; i < 5; i++) {
			letters[i] = new Letter('_',x+(i*55),y);
		}
	}
	
	public String getFullWord() {
		String fullWord = "";
		for(Letter l : letters) {
			fullWord+=l.getLetter();
		}
		return fullWord;
	}
	
	public void addLetter(char letter) {
		if(currentLetterIndex < 5) {
			letters[currentLetterIndex].setLetter(letter);
			currentLetterIndex++;
		}
	}
	
	public void removeLetter() {
		if(currentLetterIndex > 0) {
			letters[currentLetterIndex-1].setLetter('_');
			currentLetterIndex--;
		}
	}
	
	public HashMap<Character,Color> judgeWord(String word) {
		HashMap<Character,Color> colors = new HashMap<Character,Color>();
		for(int i = 0; i < word.length(); i++) {
			if(word.contains(letters[i].getLetter()+"")) {
				letters[i].setColor(Color.YELLOW);
				colors.put(letters[i].getLetter(), Color.YELLOW);
				if(letters[i].getLetter() == word.charAt(i)) {
					letters[i].setColor(Color.GREEN);
					colors.put(letters[i].getLetter(), Color.GREEN);
				}
			}else {
				letters[i].setColor(Color.LIGHT_GRAY);
				colors.put(letters[i].getLetter(), Color.LIGHT_GRAY);
			}
		}
		return colors;
	}
	
	public void draw(PApplet p) {
		for(Letter l : letters) {
			l.draw(p);
		}	
	}
	
	
}
