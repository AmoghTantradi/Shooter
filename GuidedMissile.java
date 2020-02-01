package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import Utilities.GDV5;

@SuppressWarnings("serial")
public class GuidedMissile extends Missile {
	
	public GuidedMissile(int x, int y) {
		super(x, y);
		this.col=Color.DARK_GRAY;
		// TODO Auto-generated constructor stub
	}
    
	public void update() {
	//	System.out.print("CHILD UPDATE\n");
		if(GDV5.KeysPressed[KeyEvent.VK_A]) {
			theta-=dTheta;
		}
		
		if(GDV5.KeysPressed[KeyEvent.VK_D]) {
			theta+=dTheta;
		}
		
		theta%=360;//keeps the angle small
		
		
		super.update();
		
	}
	
	public void draw(Graphics2D win) {
		win.setColor(col);
		AffineTransform old=win.getTransform();
		win.setColor(col);
		win.rotate(Math.toRadians(theta),this.getBounds().getCenterX(),this.getBounds().getCenterY());
		super.draw(win);
		win.setTransform(old);
	}
	

}
