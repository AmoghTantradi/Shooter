package Shooter;

//fix the Debris class->try the debris thingy
import java.awt.Graphics2D;
import java.util.Random;

//I'm going to restart the game If the boss is killed->lower probability
public class Mothership {
	Random rand;
	Enemy[] theHive;
	Debris[] d;
	int index = 0;
	int num_ships = 0;// this counts the number of ships created (even the ships set to null)
	int magsetter = 100;
	boolean hasBoss = false;

	public Mothership() {
		rand = new Random();
		theHive = new Enemy[150]; // reduced enemy size
		d = new Debris[theHive.length];
	}

	public void update() {

		TorpedoCollision(Screen.gplay.torpedoes);// torpedo collision
		MissileCollision(Screen.gplay.mim);// missile collision

		for (int i = 0; i < theHive.length; i++) {
			if (theHive[i] != null) {
				// bullets

				if (theHive[i] != null && theHive[i].isoffScreen()) {
					if (theHive[i] instanceof BossEnemy) {
						this.hasBoss = false;
						theHive[i] = null;

					}

					else if (num_ships == theHive.length)
						theHive[i] = null;
				}
				if (theHive[i] != null) {
					theHive[i].update();
				}
			} // end if statement
			else {// start else statement
				if (num_ships < theHive.length) {// only starts creating the enemies if all of them are dead or if the
													// number of ships generate is not equal to the length of the hive
					if (rand.nextGaussian() > 0.9 && this.hasBoss == false) {
						if (rand.nextInt(100) > 98) {
							theHive[i] = new BossEnemy();
							this.hasBoss = true;
							num_ships += 1;

						} else {
							if (Math.abs(rand.nextGaussian()) > 0.35) {

								theHive[i] = new AdvancedEnemy();
								num_ships += 1;
							} else {

								theHive[i] = new BasicEnemy();
								num_ships += 1;
							}
						}
					}
				}

			} // end else

		} // end for loop

		DebrisLogic();

	}

	public void DebrisLogic() {
		for (int i = 0; i < d.length; i++) {
			if (d[i] != null) {
				d[i].update();
				if (d[i].length == 0) {
					d[i] = null;
				}
			}
		}
	}

	public void TorpedoCollision(Torpedo[] t) {
		for (int i = 0; i < theHive.length; i++) {
			for (int j = 0; j < t.length; j++) {// first check torpedoes
				if (theHive[i] != null && t[j] != null && t[j].intersects(theHive[i])) {
					theHive[i].dmg(theHive[i].health - 5);
					if (theHive[i].health <= 0) {
						if (theHive[i] instanceof BossEnemy) {
							this.hasBoss = false;
							Shooter.score += 100;
						} else if (theHive[i] instanceof AdvancedEnemy)
							Shooter.score += 20;
						else
							Shooter.score += 10;

						d[i] = new Debris(theHive[i].x, theHive[i].y, theHive[i].col, theHive.length);
						theHive[i] = null;
						Shooter.s.play(4);
					}
					t[j] = null;
					break;
				}
			}
		}

	}

	public void MissileCollision(Missile[] m) {// missile collision
		// missle
		for (int i = 0; i < theHive.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (theHive[i] != null && m[j] != null && m[j].intersects(theHive[i])) {
					theHive[i].dmg(theHive[i].health - 20);
					if (theHive[i].health <= 0) {
						if (theHive[i] instanceof BossEnemy) {
							this.hasBoss = false;
							Shooter.score += 100;
						} else if (theHive[i] instanceof AdvancedEnemy)
							Shooter.score += 20;
						else
							Shooter.score += 10;

						d[i] = new Debris(theHive[i].x, theHive[i].y, theHive[i].col, theHive.length);
						theHive[i] = null;
						Shooter.s.play(4);
					}

					m[j] = null;

					break;
				}

			} // end missle
		}
	}

	public void draw(Graphics2D win) {
		for (int i = 0; i < theHive.length; i++) {
			if (theHive[i] != null) {
				theHive[i].draw(win);
			}
			if (d[i] != null) {
				d[i].draw(win);
			}
		}

	}

}
