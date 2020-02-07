package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
//doesn't work very well tbh

public class Blackhole {

Color col=Color.ORANGE;
double centerx,centery,radius=50,theta=0,dTheta=5;

public Blackhole() {

}

public void update() {
	
	theta+=dTheta;
}
public void draw(Graphics2D win) {
	win.setColor(Color.orange);
	win.drawArc(centerx, centery,radius,radius);
	win.rotate(Math.toRadians(theta),centerx,centery);
}
}
