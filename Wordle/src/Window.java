import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JOptionPane;

import processing.core.PApplet;

public class Window extends PApplet {
	
	private ArrayList<Key> letters;
	private Key backspace, enter;
	private Word[] words;
	private int wordIndex;
	private HashSet<String> correctWords, commonWords;
	private String word;
	private Random random;
	private boolean lettersEnabled;
	private HitBox restartButton;
	
	public void setup() {
		random = new Random();
		letters = new ArrayList<Key>();
		correctWords = readInWords("correctWords.txt");
		commonWords = readInWords("commonWords.txt");
		restartButton = new HitBox(150,550,100,30);
		word = (String) commonWords.toArray()[(random.nextInt(commonWords.size()))];
		words = new Word[6];
		wordIndex = 0;
		lettersEnabled = true;
		for(int i = 0; i < 6; i++) {
			words[i] = new Word(50,20+(i*60));
		}

		setupLetters();
	}
	
	public void draw() {
		background(255);
		for(Key k : letters) {
			k.draw(this);
		}
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
		//System.out.println(wordIndex);
		
		/*for(Word w : words) {
			System.out.println(w.getFullWord());
		}*/
	}
	
	public void mousePressed() {
		if(lettersEnabled) {
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
		if(restartButton.intersect(mouseX, mouseY)) {
			setup();
		}
	}
	
	public void pressEnter() {
		if(correctWords.contains(words[wordIndex].getFullWord())) {
			HashMap<Character,Color> b = words[wordIndex].judgeWord(word);
			for(Key l : letters) {
				if(b.get(l.getLetter().charAt(0)) != null) {
					l.setColor(b.get(l.getLetter().charAt(0)));
				}
			}
			if(b.values().toArray().equals(new Color[] {Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN})) {
				lettersEnabled = false;
				JOptionPane.showMessageDialog(null, "You won in "+(wordIndex+1)+" guesses!");
			}else if(wordIndex == 5) {
				lettersEnabled = false;
				JOptionPane.showMessageDialog(null, "Game over! the word was "+word);
			}
			wordIndex++;
			
		}
	}
	
	public void pressBackspace() {
		words[wordIndex].removeLetter();
	}
	
	public void pressLetter(char letter) {
		words[wordIndex].addLetter(letter);
	}

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
