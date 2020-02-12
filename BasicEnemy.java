package Shooter;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

@SuppressWarnings("serial")
public class BasicEnemy extends Enemy {
	
	public BasicEnemy() {
		this.dy = (int) (this.mag * 0.25);
		Random r1 = new Random();
		int newx = r1.nextInt(Shooter.width);
		this.setLocation(newx, this.height);
		this.theta = Math.PI / 2;
		this.dTheta = Math.PI / 30;
	}

	public void update() {

		super.update();
		this.theta += this.dTheta;
		this.theta %= (Math.PI * 2);// just to save memory-keeps the number small

	}

	public void draw(Graphics2D win) {

		AffineTransform old = win.getTransform();
		win.rotate(theta, this.getCenterX(), this.getCenterY());
		super.draw(win);

		win.setTransform(old);

	}

}
