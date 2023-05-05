package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Zombie extends Character {
	
	private static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT++ , 40, 10); 
		// TODO Auto-generated constructor stub
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {

		if(this.getTarget() instanceof Zombie) {
			throw new InvalidTargetException("Target of attack must be a Hero");
		}
		
		super.attack();
	}

}
