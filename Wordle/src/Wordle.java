import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Wordle {

	
	public static void main(String[] args) {
		Window win = new Window();
		PApplet.runSketch(new String[] { "" }, win);
		PSurfaceAWT surf = (PSurfaceAWT) win.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame swingFrame = (JFrame) canvas.getFrame();
		swingFrame.setSize(400, 650);
		swingFrame.setTitle("Wordle");
		swingFrame.setMinimumSize(new Dimension(100, 100));
		swingFrame.setResizable(true);
		swingFrame.setResizable(true);
		Timer t = new Timer();		
		TimerTask resizeable = new TimerTask() {
			@Override
			public void run() {
				swingFrame.setResizable(false);
			}
		};
		t.schedule(resizeable, 50);
	}
	
}
