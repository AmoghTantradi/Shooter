package Shooter;

import java.awt.Graphics2D;
import java.util.Random;

public class Cluster {
	Stardust[] cluster;
	Random xval;
	Random yval;

	public Cluster() {
		cluster = new Stardust[500];
		xval = new Random();
		yval = new Random();
		this.init();
	}

	public void init() {
		for (int i = 0; i < cluster.length; i++) {
			cluster[i] = new Stardust((int) (xval.nextGaussian() * (Shooter.width - 10)),
					(int) (yval.nextGaussian() * (Shooter.height - 10)));
		}
	}

	public void update() {
		for (Stardust c : cluster) {
			c.update();
		}
	}

	public void draw(Graphics2D win) {
		for (Stardust c : cluster) {
			c.draw(win);
		}
	}
}
