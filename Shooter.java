package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;

import Utilities.GDV5;

@SuppressWarnings("serial")
public class Shooter extends GDV5{
Spaceship s;//eventually make a screen class and declare these objects there
Cluster c;
Blackhole b;
static final int width=1200;
static final int height=800;
public Shooter(){
	super();
	s=new Spaceship();
	c=new Cluster();
	b=new Blackhole();

}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shooter game=new Shooter();
		game.setTitle("game");
		game.setSize(Shooter.width, Shooter.height);
		game.setBackground(Color.black);
		game.start();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		s.update();
		c.update();
		b.update();
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		c.draw(win);
		s.draw(win);
		b.draw(win);
	
	}

}
