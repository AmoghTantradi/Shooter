package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Debris {
	DebrisParticle[] p;
	Color col;
	int length;
	Random rand;

	public Debris(int x, int y, Color c, int l) {
		p = new DebrisParticle[100];
		rand = new Random();
		col = c;
		for (int i = 0; i < p.length; i++) {
			p[i] = new DebrisParticle(x, y, col);
			p[i].set((Math.abs((rand.nextGaussian())) + 0.05) * Math.pow(-1, rand.nextInt()) * 30,
					(Math.abs((rand.nextGaussian())) + 0.05) * Math.pow(-1, rand.nextInt()) * 30);
		}
		length = l;
	}

	public void update() {
		for (int i = 0; i < p.length; i++) {
			if (p[i] != null) {
				p[i].update();
				if (p[i].outBounds()) {
					p[i] = null;
					length--;
				}
			}

		}
	}

	public void draw(Graphics2D win) {
		for (int i = 0; i < p.length; i++) {
			if (p[i] != null)
				p[i].draw(win);
		}
	}

}
