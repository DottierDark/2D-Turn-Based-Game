package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {

	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		// TODO Auto-generated constructor stub
	}

	public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
		
		super.useSpecial();
		this.getTarget().setCurrentHp(this.getMaxHp());
		
	}

}
