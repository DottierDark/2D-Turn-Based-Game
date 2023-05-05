package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		// TODO Auto-generated constructor stub
	}
	
	public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
		if(!(this.adjacent(this.getTarget()))) {
			throw new InvalidTargetException("Target is not in range");
		}
		try {
		super.useSpecial();}
		catch(NotEnoughActionsException| NoAvailableResourcesException| InvalidTargetException e) {
			System.out.println(e);
		}
	}

	public void attack() throws InvalidTargetException,  NotEnoughActionsException {
		super.attack();
	}
}
