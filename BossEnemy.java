package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

@SuppressWarnings("serial")
public class BossEnemy extends Enemy {

	int phase=0;//0==moves into position 2==suppression fire, 3==death mode
	Random rand;
	
	boolean release=false;
	public BossEnemy() {
	//	this.width=200;
		//this.height=200;
		rand=new Random();
		this.setSize(200, 200);
		this.setLocation((int)((Math.random()*Shooter.width/2)+this.width),-this.height);
		heat=new Missile[5];
		this.health=100;
		this.dy=(int)(this.mag*0.25);
		this.col=Color.yellow;
	}
	
	public void update() {
		if(inPosition() && this.phase==0) {
			this.phase=1;
			dy=0;
			dx=-2;
			release=true;
		}
		
		if(this.phase==0) {
			//super.update();
			dy=1;
			//System.out.println(this);
			super.update();
		}
		if(this.phase==1) {
			
			if(this.x+this.width>=Shooter.width) {
				dx*=-1;
				//this.setLocation(this.x-1,this.y);
			}
			if(this.x<=0) {
				dx=2;
				//this.setLocation(1, this.y);
			}
			for(int i=0;i<heat.length;i++) {
				if(heat[i]==null) {
					heat[i]=new HeatseekingMissile((int)this.getCenterX(),(int)this.getCenterY());
				}
			}
			for(int i=0;i<heat.length;i++) {
				if(heat[i]!=null) {
					heat[i].update();
					if(heat[i].outBounds()) heat[i]=null;
				}
			}
			
			super.update();
		}
		
	}
	
	public boolean inPosition() {
		return(this.y+this.height>=(int)(Shooter.height*0.25));
	}
	
	public void draw(Graphics2D win) {
		for(int i=0;i<heat.length;i++) {
			if(heat[i]!=null) heat[i].draw(win);
		}
		super.draw(win);
	}
	
	
	
	
	
	
	
	
	
}
