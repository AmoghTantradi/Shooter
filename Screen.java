package Shooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import Utilities.GDV5;

public class Screen {
	static Spaceship gplay;
	Spaceship display;
	Cluster c;
	Mothership m;
	Powerup p;
	Powerup p2;
	int temp;

	public Screen() {
		gplay = new Spaceship();
		c = new Cluster();
		p = new HealthPack();
		p2 = new ShieldPack();// generated method is called in the child constructor
		m = new Mothership();
		Shooter.s.play(2);
	}

	public void update() {
		if (GDV5.KeysPressed[KeyEvent.VK_ENTER] && Shooter.state != Shooter.Gamestate2) {
			Shooter.state = Shooter.Gamestate2;
		}
		if (Shooter.state == Shooter.Gamestate4 && GDV5.KeysPressed[KeyEvent.VK_E]) {
			Shooter.endless = true;
			Shooter.state = Shooter.Gamestate2;
		}

		if (Shooter.state == Shooter.Gamestate2) {
			if (!hasWon()) {

				m.update();
				gplay.update(m);

				if (gplay.isDead()) {// 1st case where game will refresh

					restart();// restarts if it is dead
				} else {// only other case where GPLAY will have to restart: progression of levels

					for (int i = 0; i < m.theHive.length; i++) {

						if (m.theHive[i] != null)
							break;
						if (m.theHive[i] == null && i == m.theHive.length - 1) {// if all the ships are destroyed, then
																				// restart
																				// the thing(respawn the enemies)

							restart();
						}
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

			} else {

				restart();// restarts if it has won
			}
		}

		c.update(); // draws the stars no matter what
		soundManager();// manages sound no matter what

	}

	public boolean hasWon() {
		if (Shooter.endless == false)
			return (Shooter.level == 6);
		else
			return false;
	}

	public void soundManager() {
		if (!Shooter.s.isPlaying(3) && !m.hasBoss) { // Background music
			Shooter.s.play(3);
		}
		if (m.hasBoss)
			Shooter.s.stop(3);
		else
			Shooter.s.stop(5);
	}

	public void restart() {// restarts the game, checks edge cases, formats layout.
		for (int i = 0; i < m.theHive.length; i++) {
			m.theHive[i] = null;
			m.d[i] = null;
		}
		m.num_ships = 0;
		m.hasBoss = false;
		gplay.shieldactive = false;
		gplay.shieldhealth = 0;
		for (int i = 0; i < Shooter.sounds.length; i++) {
			Shooter.s.stop(i);
		}
		if (Shooter.state == Shooter.Gamestate1)
			gplay.moveShip(Shooter.width / 2 - gplay.getBounds().width / 2, (int) (Shooter.height * 0.65));
		p = new HealthPack();
		p2 = new ShieldPack();
		if (gplay.isDead()) {
			if (!Shooter.endless)
				Shooter.state = Shooter.Gamestate3;
			else
				Shooter.state = Shooter.Gamestate5;
			temp = Shooter.score;
			Shooter.level = 1;
			Shooter.score = 0;
			Shooter.endless = false;
			Screen.gplay.health.width = Screen.gplay.getBounds().width;
		} else {
			if (!hasWon())
				Shooter.level += 1;
			else {
				Shooter.state = Shooter.Gamestate4;
				Shooter.level = 1;
				Shooter.score = 0;
				Screen.gplay.health.width = Screen.gplay.getBounds().width;
			}
		}
	}

	public void draw(Graphics2D win) {
		if (Shooter.state == Shooter.Gamestate1) {
			Font font = new Font("TimesNewRoman", Font.BOLD, 150);
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 35);
			win.setFont(font);
			win.setColor(Color.white);
			win.drawString("Space Shooter", 60, 150);
			win.setFont(font2);
			win.drawString("Use arrow keys to move the ship and press space bar to shoot", 85, 225);
			win.drawString("Press Backspace to fire Guided Missiles and use WASD to control them", 25,
					(int) (Shooter.height * 0.45));
			c.draw(win);
			win.setColor(Color.MAGENTA);
			win.drawString("Press Enter to Play!", Shooter.width / 2 - 175, 500);
			win.setColor(Color.GRAY);
			win.drawString("By Amogh", (int) (Shooter.width * 0.75), (int) (Shooter.height * (0.85)));

		} else if (Shooter.state == Shooter.Gamestate2) {
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
			win.drawString("Score :" + Shooter.score, (int) (Shooter.width * 0.75), (int) (Shooter.height * 0.85));

		} else if (Shooter.state == Shooter.Gamestate3) {
			c.draw(win);
			Font font = new Font("TimesNewRoman", Font.BOLD, 200);
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 35);
			win.setFont(font);
			win.drawString("YOU LOSE", 50, 200);
			win.setFont(font2);
			win.drawString("Press Enter to play again", 60, (int) (Shooter.height * (0.75)));

		} else if (Shooter.state == Shooter.Gamestate4) {
			c.draw(win);
			Font font = new Font("TimesNewRoman", Font.BOLD, 200);
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 35);
			win.setFont(font);
			win.drawString("YOU WON!", 50, 200);
			win.setFont(font2);
			win.drawString("Press Enter to play again", 60, (int) (Shooter.height * (0.75)));
			win.drawString("Press E for Endless Mode", 60, (int) (Shooter.height * 0.85));
		} else {
			c.draw(win);
			Font font = new Font("TimesNewRoman", Font.BOLD, 200);
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 35);
			win.setFont(font);
			win.drawString("" + temp, 50, 200);
			win.setFont(font2);
			win.drawString("Press Enter to play again", 60, (int) (Shooter.height * (0.75)));
		}
	}

}
