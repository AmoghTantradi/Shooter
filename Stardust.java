package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Stardust extends Rectangle {

	Color c = Color.white;
	int dy = 5;
	int initialx = 0, initialy = 0;

	public Stardust(int x, int y) {
		super(x, y, 2, 2);
		initialx = x;
		initialy = y;
	}

	public void update() {
		if (this.y + this.width < Shooter.height)
			this.translate(0, dy);
		if (this.y + this.width >= Shooter.height)
			this.reset();

	}

	public void draw(Graphics2D win) {
		win.setColor(c);
		win.fill(this);
	}

	public void reset() {
		this.x = initialx;
		this.y = initialy;
	}

}
