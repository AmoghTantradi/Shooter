package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import Utilities.GDV5;
import Utilities.SoundDriver;

@SuppressWarnings("serial")
public class Shooter extends GDV5{
Screen screen;
static final int width=1230;
static final int height=850;
static final int Gamestate1=1,Gamestate2=2,Gamestate3=3; //1 ==start screen, 2==gameplay, 3==endscreen
static int state=Gamestate1;
static int level=1;
static int score=0;
String [] sounds;
static SoundDriver s;
public Shooter(){
	super();
    screen=new Screen();
    sounds=new String[1];
    //sounds[0]="LaserSound.wav";

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
	//System.out.print(score+"\n");
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
	screen.draw(win);
	}

}
