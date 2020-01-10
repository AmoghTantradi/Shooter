package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;

import Utilities.GDV5;

@SuppressWarnings("serial")
public class Shooter extends GDV5{
Screen screen;
static final int width=1200;
static final int height=800;
public Shooter(){
	super();
	screen=new Screen();
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
		screen.update();
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
	screen.draw(win);
	
	}

}
