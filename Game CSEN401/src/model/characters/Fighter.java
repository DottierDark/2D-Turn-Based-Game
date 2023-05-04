package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		// TODO Auto-generated constructor stub
	}
	
	public void useSpecial() {
		
	}

	public void attack() throws InvalidTargetException,  NotEnoughActionsException {

		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}

		if(!this.isSpecialAction() && this.getActionsAvailable() == 0) {
			throw new NotEnoughActionsException("Not enough actions to attack");
		}

		if(!this.isSpecialAction()) {
			this.setActionsAvailable(getActionsAvailable()-1);
		}
		
		this.getTarget().setCurrentHp(this.getTarget().getCurrentHp()-this.getAttackDmg());
		
	}
}
