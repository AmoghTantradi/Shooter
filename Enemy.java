package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Enemy extends Rectangle {// mother class

	Color col = Color.red;
	int dx = 0;
	int dy = 0;
	int mag = 10;
//	boolean move=true;
	// image
	BufferedImage img = null;

	// health
	boolean isFalling = true;

	public Enemy() {
		super(0, 0, 20, 20);
	}

	

	public boolean isoffScreen() {
		return (this.x + this.width < 0 || this.x > Shooter.width || this.y > Shooter.height);// not doing the other
																								// case since they start
																								// off screen
	}
	
	

	public void update() {

    this.translate(dx, dy);

	}

	public void draw(Graphics2D win) {
		if (img == null) {
			win.setColor(col);
			win.fill(this);
		} else {
			win.drawImage(img, null, this.x, this.y);
		}

	}

}
