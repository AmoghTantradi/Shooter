package Shooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import Utilities.GDV5;

//work on resetting 
public class Screen {
	static Spaceship gplay;
	Spaceship display;
	Cluster c;
	Mothership m;
	Powerup p;
	Powerup p2;

	public Screen() {
		gplay = new Spaceship();
		display = new Spaceship(0.5);
		c = new Cluster();
		p = new HealthPack();
		p2 = new ShieldPack();// generated method is called in the child constructor
		m = new Mothership();
		Shooter.s.play(2);
	}

	public void update() {
		if (GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
			Shooter.state = Shooter.Gamestate2;
		}

		if (Shooter.state == Shooter.Gamestate2) {
			m.update();
			gplay.update(m);

			if (gplay.isDead()) {
				Shooter.state = Shooter.Gamestate1;
				m.hasBoss = false;
				restart();
				Screen.gplay.health.width = Screen.gplay.getBounds().width;
			}

			for (int i = 0; i < m.theHive.length; i++) {

				if (m.theHive[i] != null)
					break;
				if (m.theHive[i] == null && i == m.theHive.length - 1) {// if all the ships are destroyed, then restart
																		// the thing(respawn the enemies)

					restart();
				}
			}
			if (p != null)
				p.update();
			if (p2 != null)
				p2.update();
			if (p != null && p.outBounds())
				p = null;
			if (p2 != null && p2.outBounds())
				p2 = null;

		}

		c.update(); // draws the stars no matter what
		soundManager();

	}

	public void soundManager() {
		if (!Shooter.s.isPlaying(3) && !m.hasBoss) { // Background music
			if (Shooter.state == Shooter.Gamestate2) {

				Shooter.s.play(3);
			} else {
				Shooter.s.stop(3);
			}

		}
		if (m.hasBoss)
			Shooter.s.stop(3);
		else
			Shooter.s.stop(5);
	}

	public void restart() {
		for (int i = 0; i < m.theHive.length; i++) {
			m.theHive[i] = null;

		}
		m.num_ships = 0;
		gplay.shieldactive = false;
		for (int i = 0; i < Shooter.sounds.length; i++) {
			Shooter.s.stop(i);
		}
		Shooter.s.play(3);
		if (Shooter.state == Shooter.Gamestate1)
			gplay.moveShip(Shooter.width / 2 - gplay.getBounds().width / 2, Shooter.height / 2);
		p = new HealthPack();
		p2 = new ShieldPack();
		if (gplay.isDead()) {
			Shooter.level = 0;
			Shooter.score = 0;
		} else
			Shooter.level += 1;
	}

	public void draw(Graphics2D win) {
		if (Shooter.state == Shooter.Gamestate1) {
			Font font = new Font("TimesNewRoman", Font.BOLD, 150);
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 40);
			win.setFont(font);
			win.setColor(Color.white);
			win.drawString("Space Shooter", 60, 150);
			win.setFont(font2);
			win.drawString("Press arrow keys to move the ship and space bar to shoot", 60, 225);
			win.drawString("Press Enter to Play!", 60, 275);
			c.draw(win);
			display.draw(win);

		}
		if (Shooter.state == Shooter.Gamestate2) {
			c.draw(win);
			gplay.draw(win);
			m.draw(win);
			if (p != null)
				p.draw(win);
			if (p2 != null)
				p2.draw(win);
			Font font = new Font("TimesNewRoman", Font.BOLD, 50);
			win.setFont(font);
			win.setColor(Color.white);
			win.drawString("Level : " + Shooter.level, (int) (Shooter.width * 0.75), (int) (Shooter.height * 0.75));
			win.drawString("Score :" + Shooter.score, (int) (Shooter.width * 0.75), (int) (Shooter.height * 0.8));

		}
	}

}
