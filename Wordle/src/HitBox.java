import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import processing.core.PApplet;

public class HitBox {
	
	private int x,y;
	private int width,height;
	
	public HitBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersect(int x, int y) {
		if((x > this.x) && (y > this.y) && (x < this.width + this.x) && (y < this.height + this.y)) {
			return true;
		}
		return false;
	}
	
	public boolean intersect(HitBox other) {
	    // To check if either rectangle is actually a line
	    // For example :  l1 ={-1,0}  r1={1,1}  l2={0,-1}
	    // r2={0,1}
		Point l1 = new Point(this.x,this.y);
		Point r1 = new Point(this.x+this.width,this.y+this.height);

		Point l2 = new Point(other.x,other.y);
		Point r2 = new Point(other.x+other.width,other.y+other.height);
		return (Math.abs((this.x + this.width/2) - (other.x + other.width/2)) * 2 < (this.width + other.width)) &&
		         (Math.abs((this.y + this.height/2) - (other.y + other.height/2)) * 2 < (this.height + other.height));
	}
	
	/*public boolean intersect(HitBox other) {
		if((other.x > this.x) && (other.y > this.y) && (other.x < this.width + this.x) && (other.y< this.height + this.y)) {
			return true;
		}
		return false;
	}*/
	
	public void moveX(int x) {
		this.x = x;
	}
	
	public void moveY(int y) {
		this.y = y;
	}
	
	public void draw(PApplet p) {
		p.rect(x, y, width, height);
		
	}
	
}
