package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class DebrisParticle extends Rectangle {

	double dy = 0, dx = 0;
	Color col;

	public DebrisParticle(int x, int y, Color c) {
		super(x, y, 5, 5);
		col = c;
	}

	public void set(double d1, double d2) {
		dx = d1;
		dy = d2;
	}

	public boolean outBounds() {
		return (this.x + this.width < 0 || this.x > Shooter.width || this.y + this.width < 0
				|| this.y > Shooter.height);
	}

	public void update() {
		super.translate((int) dx, (int) dy);
	}

	public void draw(Graphics2D win) {
		win.setColor(col);
		win.fill(this);
	}

}
