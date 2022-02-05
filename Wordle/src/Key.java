import java.awt.Color;

import processing.core.PApplet;

public class Key {
	
	private int x,y,width,height;
	private String text;
	private HitBox hb;
	private Color color;
	
	public Key(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		color = Color.WHITE;
		hb = new HitBox(x,y,width,height);
	}
	
	public void draw(PApplet p) {
		p.pushStyle();
		p.stroke(Color.BLACK.getRGB());
		p.fill(color.getRGB());
		p.rect(x, y, width, height);
		p.fill(Color.BLACK.getRGB());
		p.text(text, x+10, y+5, x+width, y+height);
		p.popStyle();
	}
	
	public HitBox getHitbox() {
		return hb;
	}

	public String getLetter() {
		return text;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
