package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class AdvancedEnemy extends Enemy {

	double dely;
	double delx;
	double angle;

	public AdvancedEnemy() {
		super(Shooter.width / 2, 0);
		this.dTheta = Math.PI / 450;
		this.dy = this.mag;
		this.col = Color.blue;
		this.health = 20;
		this.rain = new Torpedo[20];

	}

	public void update() {

		if (this.isFalling == false) {
			this.theta += this.dTheta;
			this.theta %= (2 * Math.PI);// stays small and between 0 and 2pi
			dy = (9 * Math.cos(theta * 3) * (0.95));// this is the lissajous curve
			dx = (16 * Math.cos(4 * theta) * (0.95));
			setAngle();

		}

		else {
			if (this.y >= Shooter.height / 2) {
				this.isFalling = false;
			}
		}

		super.update();
	}

	public void setMag(int x) {
		this.mag = x;
	}

	public void setAngle() {
		dely = Screen.gplay.getBounds().getCenterY() - this.getCenterY();
		delx = Screen.gplay.getBounds().getCenterX() - this.getCenterX();
		angle = Math.atan2(dely, delx);
	}

	public void draw(Graphics2D win) {

		AffineTransform old = win.getTransform();

		win.rotate(angle, this.getCenterX(), this.getCenterY());

		super.draw(win);

		win.setTransform(old);

	}
}
