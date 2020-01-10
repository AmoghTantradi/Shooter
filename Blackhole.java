package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Blackhole {
int theta=0;
int radius=theta;
Color col=Color.ORANGE;
Rectangle [] lines;

public Blackhole() {

}

public void update() {
	
	theta++;
	radius=theta;
}
public void draw(Graphics2D win) {
	win.setColor(Color.orange);
	win.rotate(Math.toRadians(theta));
}
}
