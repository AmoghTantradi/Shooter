package Shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
//NOW DO THE BOOM CRAP

@SuppressWarnings("serial")
public class Healthbar extends Rectangle {

	public Healthbar(int x, int y, int width, int height) {

		super(x, (y - 10), width, (height));
	}

	public void update(Mothership m) {

		for (int i = 0; i < m.theHive.length; i++) {
			if (m.theHive[i] != null) {
				if (m.theHive[i].intersects(Screen.gplay.getBounds()) && !Screen.gplay.shieldactive) {
					this.width -= (int) (15 * Screen.gplay.sf);
					if (m.theHive[i] instanceof BossEnemy) {
						this.width -= 50;
						m.hasBoss = false;
					}
					m.theHive[i] = null;
					Shooter.s.play(4);
				}
				if (m.theHive[i] instanceof BossEnemy) {
					for (int j = 0; j < m.theHive[i].heat.length; j++) {
						if (m.theHive[i].heat[j] != null) {
							if (m.theHive[i].heat[j].intersects(Screen.gplay.getBounds())) {
								if (!Screen.gplay.shieldactive)
									this.width -= (int) (25 * Screen.gplay.sf);
								else
									Screen.gplay.shieldactive = false;
								m.theHive[i].heat[j] = null;
								Shooter.s.play(4);
							}

						}
					}

				}
			}
		}

	}

	public void draw(Graphics2D win) {
		win.setColor(Color.red);
		win.fill(this);
		win.draw(this);
	}
}
