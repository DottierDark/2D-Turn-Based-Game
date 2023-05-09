package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
public class Zombie extends Character {
	
	private static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		
		for(Hero h : Game.heroes) {
			if(this.adjacent(h)) {
				this.setTarget(h);
				break;
			}
		}
		if(this.getTarget() == null) {
			return;
		}
		super.attack();
	}

}
