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
		
		if(this.getTarget() == null) {
			throw new InvalidTargetException("No target selected");
		}

		if(this.getTarget() instanceof Zombie) {
			throw new InvalidTargetException("Target must be a Hero");
		}

		if(!(this.adjacent(this.getTarget()))){
			throw new InvalidTargetException("Target is not in range");
		}
try {
	super.useSpecial();
}
	catch(NotEnoughActionsException| NoAvailableResourcesException| InvalidTargetException e){
		System.out.println(e);
	}
		this.getTarget().setCurrentHp(this.getMaxHp());
	}

}
