package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

@SuppressWarnings("serial")
public class Missile extends Polygon {

    int theta=0;
    int dTheta =1;
    int speed=5;
    int dx=0;
    int dy=0;
    Color col;//orange is guided RED is heat seeking
	public Missile(int x, int y) { //x,y are those of the ship's center
		int [] arrx=new int [] {x-10,x-10,x,x+10,x+10};
		int [] arry=new int [] {y+30,y+30,y,y+30,y+30};
		this.xpoints=arrx;
		this.ypoints=arry;
		this.npoints=5;
	}
	
	public void update() {
	//the child class will do the math
	//System.out.print("Reached here \n");
	//	if(this instanceof GuidedMissile) this.translate((int)(speed*Math.sin(Math.toRadians(theta))), (int)(-speed*Math.cos(Math.toRadians(theta))));
	//	else 
	this.translate(dx, dy);
	}
	public boolean outBounds() {
		return (this.getBounds().y+this.getBounds().height<0 ||this.getBounds().x>Shooter.width||this.getBounds().y>Shooter.height||this.getBounds().x+this.getBounds().width<0);
		
	}
	
	public void draw(Graphics2D win) {
    win.fill(this);
 
    }
	}
	
	
