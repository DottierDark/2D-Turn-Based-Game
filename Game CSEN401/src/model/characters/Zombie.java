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

		for(int i = 0 ; i< Game.heroes.size(); i++) {
			Hero h = Game.heroes.get(i);
			if(adjacent(h)) {
				setTarget(h);
				super.attack();;
				break;
			}
		}		
		
		if(this.getTarget() == null) {
			return;
		}
		
	}
	
}
