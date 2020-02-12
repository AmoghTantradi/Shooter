package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class HeatseekingMissile extends Missile {

	double requiredtheta;

	public HeatseekingMissile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.col = Color.MAGENTA;
		dTheta = 2;
		theta = 180;
		dy = 2 * speed;
	}

	public void update() {

		requiredtheta = (90
				+ (180 / Math.PI) * Math.atan2((Screen.gplay.getBounds().getCenterY() - this.getBounds().getCenterY()),
						(Screen.gplay.getBounds().getCenterX() - this.getBounds().getCenterX())));
		requiredtheta += 360;
		requiredtheta %= 360;

		if (Screen.gplay.getBounds().x != this.getBounds().x) {
			if (!(theta - requiredtheta < dTheta && theta - requiredtheta > -dTheta)) {
				if (requiredtheta < theta) {
					if (requiredtheta + 360 - theta < Math.abs(theta - requiredtheta)) {
						theta += dTheta;
					} else
						theta -= dTheta;
				} else {
					if (theta + 360 - requiredtheta < requiredtheta - theta) {
						theta -= dTheta;
					} else
						theta += dTheta;
				}
				// this chooses the most optimal path: whether to add degrees or to substract

			}
		}

		theta += 360;
		theta %= 360;
		dx = (int) ((2.5) * speed * Math.sin(Math.toRadians(theta)));
		dy = (int) (-(2.5) * speed * Math.cos(Math.toRadians(theta)));

		super.update();
	}

	public void draw(Graphics2D win) {
		win.setColor(col);
		AffineTransform old = win.getTransform();
		win.setColor(col);
		win.rotate(Math.toRadians(theta), this.getBounds().getCenterX(), this.getBounds().getCenterY());
		super.draw(win);
		win.setTransform(old);
	}

}
