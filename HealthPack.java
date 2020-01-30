package Shooter;

import java.awt.Color;

//just do the powerup and health pack stuff later
@SuppressWarnings("serial")
public class HealthPack extends Powerup{

	
	public HealthPack() {
		super();
		this.col=Color.green;
		this.generate();
	}
	
	
}
