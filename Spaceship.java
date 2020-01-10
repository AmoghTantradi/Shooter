package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;


import Utilities.GDV5;

@SuppressWarnings("serial")
public class Spaceship extends Polygon   {
int [] xps;
int [] yps;
int points;
int dx=0;
int dy=0;
int speed=5;
Color col=Color.white;
double sf=0.1;
Polygon [] boosters;
int [] bxps1;
int [] byps1;
int [] bxps2;
int [] byps2;
public Spaceship(int [] xps1, int [] yps1, int pts ) {
	super();
	xps=xps1;
	yps=yps1;
	points=pts;
	boosters=new Polygon[2];
	init();
	
	
}
public Spaceship() {
	super();
	xps=new int[] {(int)(0*sf),(int)(200*sf),(int)(400*sf),(int)(600*sf),(int)(800*sf),(int)(400*sf)};
	yps=new int [] {(int)(800*sf)+100,(int)(300*sf)+100,(int)(0*sf)+100,(int)(300*sf)+100,(int)(800*sf)+100,(int)(450*sf)+100};
	points=6;
	boosters=new Polygon[2];
	init();
}

public void init() {
	boosters[0]=new Polygon();
	boosters[1]=new Polygon();
	bxps1=new int[5];
	byps1=new int[5];
	bxps2=new int [5];
	byps2=new int[5];
	changevals(this.xps,this.yps);
}
public void changevals(int[] ar1x,int[] ar1y) {
	bxps1[0]= (int)(ar1x[0]-10*sf/*+100*sf*/);
	bxps1[1]=(int)(ar1x[0]+200*sf);
	bxps1[2]=(int)(ar1x[0]+200*sf);
	bxps1[3]=(int)(ar1x[0]+100*sf);
	bxps1[4]=(int)(ar1x[0]/*+100*sf*/);
	byps1[0]=(int)(ar1y[0]+50*sf);
	byps1[1]=(int)(ar1y[0]+50*sf);
	byps1[2]=(int)(ar1y[0]-400*sf);
	byps1[3]=(int)(ar1y[0]-500*sf);
	byps1[4]=(int)(ar1y[0]-400*sf);
	bxps2[0]=(int)(ar1x[4]+10*sf/*-100*sf*/);
	bxps2[1]=(int)(ar1x[4]/*-100*sf*/);
	bxps2[2]=(int)(ar1x[4]-100*sf);
	bxps2[3]=(int)(ar1x[4]-200*sf);
	bxps2[4]=(int)(ar1x[4]-200*sf);
	byps2[0]=(int)(ar1y[0]+50*sf);
	byps2[1]=(int)(ar1y[0]-400*sf);
	byps2[2]=(int)(ar1y[0]-500*sf);
	byps2[3]=(int)(ar1y[0]-400*sf);
	byps2[4]=(int)(ar1y[0]+50*sf);
}
public void update() {
	dx=0;
	dy=0;
	if(GDV5.KeysPressed[KeyEvent.VK_LEFT] && bxps1[0]>0){
		dx=-speed;
	}
	
	if(GDV5.KeysPressed[KeyEvent.VK_RIGHT] && bxps2[0]<Shooter.width) {
		dx=Math.abs(speed);
	}
	
	if(GDV5.KeysPressed[KeyEvent.VK_DOWN] && byps2[0]<Shooter.height) {
		dy=Math.abs(speed);
	}
	if(GDV5.KeysPressed[KeyEvent.VK_UP] && this.yps[2]-(int)(200*sf)>0) {
		dy=-speed;
	}
	
	
	//updates all x and y values
	for(int i=0;i<points;i++) {
		xps[i]+=dx;
		yps[i]+=dy;
	}
	changevals(xps,yps);//changes the booster positions with respect to the position of the spaceship

}
	public void draw(Graphics2D win) {
		
	

	boosters[0].xpoints=this.bxps1;
	boosters[0].ypoints=this.byps1;
	boosters[0].npoints=5;
	boosters[1].xpoints=this.bxps2;
	boosters[1].ypoints=this.byps2;
	boosters[1].npoints=5;
	this.xpoints=xps;
	this.ypoints=yps;
	this.npoints=points;
	win.setColor(col);
	//fills the entire spaceship
	
	win.fill(this);
	win.fillArc(xps[0],yps[2]-(int)(200*sf),xps[4]-xps[0],(int)(475*sf),0,360);//kinda hardcoded it but whatev
	win.setColor(Color.GRAY);
	win.fill(boosters[0]);
	win.fill(boosters[1]);

	
}
}
