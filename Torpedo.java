package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Torpedo extends Rectangle {
	static int width = 2, height = 75;
	Color col = Color.cyan;
	int dy = 0;
	int dx = 0;
	public static int speed = 20;

	public Torpedo(int x, int y) {// pass in the centerx of the bounding rectangle and the y coordinate of the
									// bounding rectangle
		super(x, y, width, height);
		dy = -speed;
		Shooter.s.play(0);
	}

	public void setSpeed(int x) {
		speed = x;
	}

	public void update() {// pass in the centerx of the bounding rectangle and the y coordinate of the
							// bounding rectangle

		this.translate(dx, dy);

	}

	public boolean outBounds() {
		return (this.y + height < 0 || this.x > Shooter.width || this.y > Shooter.height || this.x + width < 0);
	}

	public void draw(Graphics2D win) {// pass in the bounding rectangle of the polygon

		win.setColor(col);
		win.fill(this);
		win.draw(this);

	}

}
