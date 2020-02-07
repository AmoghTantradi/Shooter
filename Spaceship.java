package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import Utilities.GDV5;

@SuppressWarnings("serial")
public class Spaceship extends Polygon {
	int[] xps;
	int[] yps;
	int count = 0;
	int points;
	int dx = 0;
	int dy = 0;
	int speed = 5;
	double sf;
	Color col = Color.white;
	Torpedo[] torpedoes;
	Missile[] mim;
	Polygon[] boosters;
	Polygon[] burners;
	Healthbar health;
	int[] bxps1;
	int[] byps1;
	int[] bxps2;
	int[] byps2;
	int[] burnxps1;
	int[] burnxps2;
	int[] burnyps1;
	int[] burnyps2;
	boolean burn = false;
	boolean shieldactive = false;

	public Spaceship(double nsf) {
		super();
		sf = nsf;
		xps = new int[] { (int) (0 * sf), (int) (200 * sf), (int) (400 * sf), (int) (600 * sf), (int) (800 * sf),
				(int) (400 * sf) };
		yps = new int[] { (int) (800 * sf) + 100, (int) (300 * sf) + 100, (int) (0 * sf) + 100, (int) (300 * sf) + 100,
				(int) (800 * sf) + 100, (int) (450 * sf) + 100 };
		points = 6;
		this.xpoints = xps;
		this.ypoints = yps;
		this.npoints = points;
		boosters = new Polygon[2];
		torpedoes = new Torpedo[20];
		mim = new Missile[2];
		burners = new Polygon[2];
		init();
		health = new Healthbar(this.getBounds().x, (int) (this.getBounds().y - 250 * this.sf), this.getBounds().width,
				(int) (30 * this.sf));
		this.moveShip(Shooter.width / 2 - this.getBounds().width / 2, Shooter.height / 2);

	}

	public Spaceship() {
		this(0.1);
	}

	public void init() {
		boosters[0] = new Polygon();
		boosters[1] = new Polygon();
		burners[0] = new Polygon();
		burners[1] = new Polygon();
		bxps1 = new int[] { (int) (xps[0] - 10 * sf), (int) (xps[0] + 200 * sf), (int) (xps[0] + 200 * sf),
				(int) (xps[0] + 100 * sf), (int) (xps[0]) };
		byps1 = new int[] { (int) (yps[0] + 50 * sf), (int) (yps[0] + 50 * sf), (int) (yps[0] - 400 * sf),
				(int) (yps[0] - 500 * sf), (int) (yps[0] - 400 * sf) };
		bxps2 = new int[] { (int) (xps[4] + 10 * sf), (int) (xps[4]), (int) (xps[4] - 100 * sf),
				(int) (xps[4] - 200 * sf), (int) (xps[4] - 200 * sf) };
		byps2 = new int[] { (int) (yps[0] + 50 * sf), (int) (yps[0] - 400 * sf), (int) (yps[0] - 500 * sf),
				(int) (yps[0] - 400 * sf), (int) (yps[0] + 50 * sf) };
		boosters[0].xpoints = this.bxps1;
		boosters[0].ypoints = this.byps1;
		boosters[0].npoints = 5;
		boosters[1].xpoints = this.bxps2;
		boosters[1].ypoints = this.byps2;
		boosters[1].npoints = 5;
		burnxps1 = new int[] { bxps1[0], bxps1[3], bxps1[1] };
		burnxps2 = new int[] { bxps2[3], bxps2[2], bxps2[0] };
		burnyps1 = new int[] { byps1[0], byps1[0] + 25, byps1[0] };
		burnyps2 = new int[] { byps1[0], byps1[0] + 25, byps1[0] };
		burners[0].xpoints = this.burnxps1;
		burners[0].ypoints = this.burnyps1;
		burners[0].npoints = 3;
		burners[1].xpoints = this.burnxps2;
		burners[1].ypoints = this.burnyps2;
		burners[1].npoints = 3;

	}

	public void update(Mothership m) {
		dx = 0;
		dy = 0;
		burn = false;
		// dTheta=0;

		if (GDV5.KeysPressed[KeyEvent.VK_LEFT] && bxps1[0] > 0) {
			dx = -speed;
		}

		if (GDV5.KeysPressed[KeyEvent.VK_RIGHT] && bxps2[0] < Shooter.width) {
			dx = Math.abs(speed);
		}

		if (GDV5.KeysPressed[KeyEvent.VK_DOWN] && byps2[0] < Shooter.height) {
			dy = Math.abs(speed);
		}
		if (GDV5.KeysPressed[KeyEvent.VK_UP] && this.yps[2] - (int) (200 * sf) > 0) {
			dy = -speed;
			burn = true;

		}

		releaseT();
		releaseGm();

		for (int i = 0; i < torpedoes.length; i++) {// logic for updating the torpedoes
			if (torpedoes[i] != null) {
				torpedoes[i].update();
				if (torpedoes[i].outBounds())
					torpedoes[i] = null;
			}
		}
		for (int i = 0; i < mim.length; i++) { // logic for updating the guided missiles
			if (mim[i] != null) {
				mim[i].update();
				if (mim[i].outBounds())
					mim[i] = null;
				else if (mim[i].intersects(this.getBounds()) && mim[i].theta != 0)
					mim[i] = null;
			}
		}

		this.moveShip(this.getBounds().x + dx, this.getBounds().y + dy);
		health.update(m);

	}

	public boolean isDead() {
		return (health.width <= 0);
	}

	public void releaseT() {// fires torpedoes
		if (GDV5.KeysTyped[KeyEvent.VK_SPACE]) {// does the logic for torpedo release

			for (int i = 0; i < torpedoes.length; i++) {
				if (torpedoes[i] == null && count == 0) {
					torpedoes[i] = new Torpedo((int) (this.getBounds2D().getX()), (int) (this.getBounds2D().getY()));
					count += 1;
				}
				if (torpedoes[i] == null && count == 1) {
					torpedoes[i] = new Torpedo((int) (this.getBounds2D().getX() + this.getBounds2D().getWidth()),
							(int) (this.getBounds2D().getY()));
					count = 0;
					break;
				}
			}

			GDV5.KeysTyped[KeyEvent.VK_SPACE] = false;
		}

	}

	public void releaseGm() {// fires guided missiles
		if (GDV5.KeysTyped[KeyEvent.VK_BACK_SPACE]) {// logic for guided missile release
			for (int i = 0; i < mim.length; i++) {
				if (mim[i] == null) {
					mim[i] = new GuidedMissile((int) (this.getBounds().getCenterX()),
							(int) (this.getBounds().getCenterY()));
					break;
				}
			}
			GDV5.KeysTyped[KeyEvent.VK_BACK_SPACE] = false;
		}
		for (int i = 0; i < mim.length; i++) { // logic for updating the guided missiles
			if (mim[i] != null) {
				mim[i].update();
				if (mim[i].outBounds())
					mim[i] = null;
				else if (mim[i].intersects(this.getBounds()) && mim[i].theta != 0)
					mim[i] = null;
			}
		}
	}

	public void moveShip(int x, int y) {// moves the ship

		int k = (x - this.getBounds().x);
		int w = (y - this.getBounds().y);
		this.translate(k, w);
		health.translate(k, w);
		for (Polygon p : boosters) {
			p.translate(k, w);
		}
		for (Polygon p : burners) {
			p.translate(k, w);
		}

	}

	public void draw(Graphics2D win) {

		for (int i = 0; i < torpedoes.length; i++) {
			if (torpedoes[i] != null)
				torpedoes[i].draw(win);
		}
		for (int i = 0; i < mim.length; i++) {
			if (mim[i] != null)
				mim[i].draw(win);
		}

		win.setColor(col);

		win.fill(this);
		win.fillArc(xps[0], yps[2] - (int) (200 * sf), (int) ((xps[4] - xps[0])), (int) (475 * sf), 0, 360);// kinda
																											// hardcoded
																											// it but
																											// whatev
		win.setColor(Color.black);
		win.drawArc(xps[0], yps[2] - (int) (200 * sf), (int) ((xps[4] - xps[0])), (int) (475 * sf), 0, 360);
		win.setColor(Color.GRAY);
		win.fill(boosters[0]);
		win.fill(boosters[1]);

		win.setColor(Color.magenta);
		if (burn) {
			for (Polygon p : burners) {
				win.fill(p);
			}

		}
		health.draw(win);
		if (shieldactive) {
			win.setColor(Color.blue);
			win.drawArc((int) (this.getBounds().x - 20), (int) (this.getBounds().y - 55),
					(int) (this.getBounds().width * 1.5), this.getBounds().width * 2, 0, 360);
		}
	}
}
