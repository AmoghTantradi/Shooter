package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Enemy extends Rectangle2D.Double {// mother class
	Color col = Color.red;
	double dx = 0;
	double dy = 0;
	int mag = 10;
	double theta = 0, dTheta = Math.PI / 120;// could be put in enemy class
	Missile[] heat;
	Torpedo[] rain;
	BufferedImage img = null;
	boolean isFalling = true;
	int health = 10;

	public Enemy() {
		super(0, 0, 20, 20);
	}

	public Enemy(int x, int y) {
		super(x, y, 20, 20);
	}

	public boolean isoffScreen() {
		return (this.x + this.width < 0 || this.x > Shooter.width || this.y > Shooter.height);// not doing the other
																								// case since they start
																								// off screen
	}

	public void dmg(int num) {
		health -= num;
	}

	public void update() {

		// this.translate(dx, dy);
		this.x += this.dx;
		this.y += this.dy;

		this.setRect(x, y, this.width, this.height);

		// System.out.println(dx + ":" + dy);

	}

	public void draw(Graphics2D win) {

		if (img == null) {
			win.setColor(col);
			win.fill(this);
		} else {
			win.drawImage(img, null, (int) this.x, (int) this.y);
		}

	}

}
