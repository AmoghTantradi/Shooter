package Shooter;

import java.awt.Graphics2D;
import java.util.Random;
//I'm going to restart the game If the boss is killed
public class Mothership {
	Random rand;
	Enemy[] theHive;
	int index = 0;
	int num_ships=0;//this counts the number of ships created (even the ships set to null) 
	int magsetter = 100;
	boolean hasBoss=false;
	public Mothership() {
		rand = new Random();
		theHive = new Enemy[150]; //reduced enemy size
	}

	
	public void update(Torpedo [] t, Missile [] m) {

		 	for (int i=0;i<theHive.length;i++) {
			 	if (theHive[i]!= null) {
			 		for(int j=0;j<t.length;j++) {//first check torpedoes
			 			if (t[j]!=null && t[j].intersects(theHive[i])) {
			 				theHive[i].dmg(theHive[i].health-3);
			 				if(theHive[i].health<=0) {
			 					if(theHive[i] instanceof BossEnemy) {
			 					//	this.hasBoss=false;
			 						Shooter.score+=100;
			 					}
			 					else if(theHive[i] instanceof AdvancedEnemy) Shooter.score+=20;
			 					else Shooter.score+=10;
			 					
			 					theHive[i]=null;
					    }
						t[j]=null; 				              
						break;
					}
				}
				
				for(int j=0;j<m.length;j++) {
					if (theHive[i]!=null && m[j]!=null && m[j].intersects(theHive[i])) {
						theHive[i].dmg(theHive[i].health-5);
						if(theHive[i].health<=0) {
							if(theHive[i] instanceof BossEnemy) {
								//this.hasBoss=false;
								Shooter.score+=100;
							}
							else if(theHive[i] instanceof AdvancedEnemy) Shooter.score+=20;
						    else Shooter.score+=10;
							
							theHive[i]=null;
						}
						
					    m[j]=null;					              
						break;
						}
				
				}
				 if( theHive[i]!=null && theHive[i].isoffScreen() && num_ships==theHive.length) {
					 
					 theHive[i]=null;
				 }
				 if(theHive[i]!=null) { theHive[i].update();}
				}
			else  {
				if(num_ships<theHive.length) {//only starts creating the enemies if all of them are dead or if the number of ships generate is not equal to the length of the hive
				if (rand.nextGaussian() > 0.9 /*&& this.hasBoss==false*/ ) {
					if(rand.nextGaussian()>0.5) {
						theHive[i]=new BossEnemy();
						this.hasBoss=true;
						num_ships+=1;
						System.out.print("Boss is created");
					}
      
					if (rand.nextGaussian() > 0.35) {
				
						theHive[i]=new AdvancedEnemy();
						num_ships+=1;
					} else {
		
						theHive[i]=new BasicEnemy();
						num_ships+=1;
					}
				}
			}
			

		}
		 
		}

	}

	public void draw(Graphics2D win) {
		for (int i = 0; i < theHive.length; i++) {
			if (theHive[i] != null) {
				theHive[i].draw(win);
			}

		}
	}

}
