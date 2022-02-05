import java.awt.Color;

import processing.core.PApplet;

public class Letter {
	
	private char letter;
	private Color color;
	private int x, y;
	
	public Letter(char letter, int x, int y) {
		this.letter = letter;
		this.x = x;
		this.y = y;
		this.color = Color.WHITE;
	}
	
	public char getLetter() {
		return letter;
	}
	
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void draw(PApplet p) {
		p.pushStyle();
		p.fill(color.getRGB());
		p.rect(x, y, 50, 50);
		p.textAlign(PApplet.CENTER,PApplet.CENTER);
		p.textSize(20);
		p.fill(0);
		p.text(letter+"", x, y,50,50);
		p.popStyle();
	}
	
	public String toString() {
		return letter+"";
	}
	
}
