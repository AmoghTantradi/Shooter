package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class AdvancedEnemy extends Enemy {
   
	

     
	public AdvancedEnemy() {
		this.dy=this.mag;
		this.col=Color.blue;
		this.health=10;
	
	}
	
    
	public void update() {
		
		if(this.isFalling==false ) {
		this.theta+=this.dTheta;
		dx=(int)(16*Math.sin(this.theta));
		dy=(int)(11*Math.cos(this.theta));
	
		
		}else {
			if(this.y>400) {
				this.isFalling=false;
			}
		}
		super.update();
	
	}
	public void setMag(int x) {
		this.mag=x;
	}
	public void draw(Graphics2D win) {
		double dely= Screen.gplay.getBounds().getCenterY()-this.getCenterY();
		double delx= Screen.gplay.getBounds().getCenterX()-this.getCenterX();
		double angle=Math.atan2(dely, delx);
		
		AffineTransform old= win.getTransform();
		
		win.rotate(angle, this.getCenterX(),this.getCenterY());
		
		super.draw(win);
		
		win.setTransform(old);
	}
}
