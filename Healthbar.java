package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Healthbar extends Rectangle {

	public Healthbar(Spaceship s) {
		super(s.getBounds().x,(int)(s.getBounds().y-250*s.sf),s.getBounds().width,(int)(20*s.sf));
	}
	
	public void update(Spaceship s, Mothership m) {
		//ship is passed in to see whether the enemy has hit the ship
		for(int i=0;i<m.theHive.length;i++) {
		if(m.theHive[i]!=null) {
			if(m.theHive[i].intersects(s.getBounds()) && !s.shieldactive) {
				this.width-=1;
				m.theHive[i]=null;
			}
		}
		}
	}
   
	public void draw(Graphics2D win) {
		win.setColor(Color.red);
		win.fill(this);
		win.draw(this);
	}
}

