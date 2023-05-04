package model.characters;

import exceptions.InvalidTargetException;

public class Zombie extends Character {
	
	private static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT++ , 40, 10); 
		// TODO Auto-generated constructor stub
	}

	public void attack() throws InvalidTargetException {

		if(this.getTarget() == null) {
			throw new InvalidTargetException("No target selected");
		}

		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}
		
		this.getTarget().setCurrentHp(this.getTarget().getCurrentHp()-this.getAttackDmg());
	
	}

}
