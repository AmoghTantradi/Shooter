package Shooter;

import java.awt.Graphics2D;

public class Screen {
	Spaceship s;
	Cluster c;
	Blackhole b;
	public Screen() {
		s=new Spaceship();
		c=new Cluster();
		b=new Blackhole();
	}
	
	public void update() {
		s.update();
		c.update();
		b.update();
	}
	
	public void draw(Graphics2D win) {
		c.draw(win);
		s.draw(win);
		b.draw(win);
	}
	
	
	
	
	
	
	
	
}
