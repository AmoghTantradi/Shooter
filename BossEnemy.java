package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class BossEnemy extends Enemy {

	int phase=0;//0==moves into position 2==suppression fire, 3==death mode
	
	public BossEnemy() {
	//	this.width=200;
		//this.height=200;
		this.setSize(200, 200);
		this.setLocation(200,200);
		this.health=50;
		this.dy=this.mag;
		this.col=Color.yellow;
	}
	
	public void update() {
		if(this.phase==0) {
			super.update();
		}
	}
	
	
	
	public void draw(Graphics2D win) {
		super.draw(win);
	}
	
	
	
	
	
	
	
	
	
}
