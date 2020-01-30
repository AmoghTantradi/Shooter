package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class AdvancedEnemy extends Enemy {
   
	double theta=0, dTheta=Math.PI/120;//could be put in enemy class

     
	public AdvancedEnemy() {
		this.dy=this.mag;
		this.col=Color.blue;
		
	
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