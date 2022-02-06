import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JOptionPane;

import processing.core.PApplet;


/**
 * Main window that contains the PApplet surface
 * 
 * @author zabzabdoda
 *
 */
public class Window extends PApplet {
	
	// Keyboard letters at the bottom of the screen
	private ArrayList<Key> letters;
	// the backspace and enter key at the bottom of the screen
	private Key backspace, enter;
	// the words that are drawn on the screen
	private Word[] words;
	// the current guess the user is on
	private int wordIndex;
	// lists of Scrabble and common words that the key word is picked from
	private HashSet<String> correctWords, commonWords;
	// the key word
	private String word;
	// an instance of Random
	private Random random;
	// enables or disables typing after the game is done
	private boolean lettersEnabled;
	// the button to reset the game and generate a new word
	private HitBox restartButton;
	
	
	/**
	 * Called once at start
	 */
	public void setup() {
		random = new Random();
		letters = new ArrayList<Key>();
		//Reads in the word lists from text files
		correctWords = readInWords("correctWords.txt");
		commonWords = readInWords("commonWords.txt");
		restartButton = new HitBox(150,550,100,30);
		// sets the key word to be a random word from the commonWords list
		word = (String) commonWords.toArray()[(random.nextInt(commonWords.size()))];
		words = new Word[6];
		word = "CREEP";
		wordIndex = 0;
		lettersEnabled = true;
		//initializes the words being drawn to the screen
		for(int i = 0; i < 6; i++) {
			words[i] = new Word(50,20+(i*60));
		}

		setupLetters();
	}
	
	/**
	 * Gets called once per frame
	 */
	public void draw() {
		background(255);
		//Draws all the keyboard letters
		for(Key k : letters) {
			k.draw(this);
		}
		//draws all the word boxes
		for(Word w : words) {
			w.draw(this);
		}
		backspace.draw(this);
		enter.draw(this);
		restartButton.draw(this);
		this.pushStyle();
		this.textAlign(PApplet.CENTER,PApplet.CENTER);
		this.textSize(20);
		this.fill(0);
		this.text("Restart", 150,550,100,30);
		this.popStyle();
	}
	
	/**
	 * Gets called when a mouse button is pressed
	 */
	@Override
	public void mousePressed() {
		//checks if the game is still going
		if(lettersEnabled) {
			//checks if the mouse position matches any of the keyboard keys
			for(Key k : letters) {
				if(k.getHitbox().intersect(mouseX, mouseY)) {
					pressLetter(k.getLetter().charAt(0));
				}
			}
			if(backspace.getHitbox().intersect(mouseX, mouseY)) {
				pressBackspace();
			} else if(enter.getHitbox().intersect(mouseX, mouseY)) {
				pressEnter();
			}
		}
		//resets the game
		if(restartButton.intersect(mouseX, mouseY)) {
			setup();
		}
	}
	
	/**
	 * Gets called when the enter button is pressed
	 * submits the typed word for judging and checks
	 * if the player won or lost
	 * 
	 */
	public void pressEnter() {
		// checks if the entered word is a real word based on the correctWords list
		if(correctWords.contains(words[wordIndex].getFullWord())) {
			// judges the word and returns a map of letters to their colors
			HashMap<Character,Color> b = words[wordIndex].judgeWord(word);
			for(Key l : letters) {
				// sets the letter's color on the keyboard
				if(b.get(l.getLetter().charAt(0)) != null) {
					l.setColor(b.get(l.getLetter().charAt(0)));
				}
			}
			// checks for win condition
			int count = 0;
			for(Color c : b.values()) {
				if(c.equals(Color.GREEN)) {
					count++;
				}
				if(count == 5) {
					lettersEnabled = false;
					JOptionPane.showMessageDialog(null, "You won in "+(wordIndex+1)+" guesses!");
					return;
				}
			}
			if(wordIndex == 5) {
				lettersEnabled = false;
				JOptionPane.showMessageDialog(null, "Game over! the word was "+word);
			}
			wordIndex++;
			
		}
	}
	
	/**
	 * Gets called when the backspace button
	 * is pressed. Removes the last letter
	 * typed
	 */
	public void pressBackspace() {
		words[wordIndex].removeLetter();
	}
	
	/**
	 * adds a letter to the current word
	 * @param letter the letter to be added
	 */
	public void pressLetter(char letter) {
		words[wordIndex].addLetter(letter);
	}

	/**
	 * Called when any key is pressed
	 * checks if it is correct and then 
	 * passes it on to pressLetter to add it to the word
	 */
	@Override
	public void keyPressed() {
		if(Character.isAlphabetic(key)) {
			pressLetter(Character.toUpperCase(key));
		}
		if(key==PApplet.BACKSPACE) {
			pressBackspace();
		}
		if(key==PApplet.ENTER) {
			pressEnter();
		}
	}
	
	/**
	 * Sets up all the keyboard letters and their positions
	 */
	public void setupLetters() {
		letters.add(new Key(50,400,25,25,"Q"));
		letters.add(new Key(80,400,25,25,"W"));
		letters.add(new Key(110,400,25,25,"E"));
		letters.add(new Key(140,400,25,25,"R"));
		letters.add(new Key(170,400,25,25,"T"));
		letters.add(new Key(200,400,25,25,"Y"));
		letters.add(new Key(230,400,25,25,"U"));
		letters.add(new Key(260,400,25,25,"I"));
		letters.add(new Key(290,400,25,25,"O"));
		letters.add(new Key(320,400,25,25,"P"));
		
		letters.add(new Key(53,430,28,25,"A"));
		letters.add(new Key(86,430,28,25,"S"));
		letters.add(new Key(119,430,28,25,"D"));
		letters.add(new Key(152,430,28,25,"F"));
		letters.add(new Key(185,430,28,25,"G"));
		letters.add(new Key(218,430,28,25,"H"));
		letters.add(new Key(251,430,28,25,"J"));
		letters.add(new Key(284,430,28,25,"K"));
		letters.add(new Key(317,430,28,25,"L"));
		
		letters.add(new Key(85,460,25,25,"Z"));
		letters.add(new Key(115,460,25,25,"X"));
		letters.add(new Key(145,460,25,25,"C"));
		letters.add(new Key(175,460,25,25,"V"));
		letters.add(new Key(205,460,25,25,"B"));
		letters.add(new Key(235,460,25,25,"N"));
		letters.add(new Key(265,460,25,25,"M"));
		
		backspace = new Key(50,460,30,25,"<---");
		enter = new Key(295,460,50,25,"Enter");
	}
	
	/**
	 * Reads a text file and returns a set of words
	 * @param wordList the text file to read
	 * @return the set of words from the file
	 */
	public HashSet<String> readInWords(String wordList) {
		HashSet<String> words = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(wordList)));
			String line = br.readLine();
			do{
				if(line.length() == 5) {
					words.add(line);
				}
				line = br.readLine();
			}while(line != null);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
	
}
