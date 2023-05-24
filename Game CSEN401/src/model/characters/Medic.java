package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Medic extends Hero {

	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {

		if (this.getTarget() == null) {
			throw new InvalidTargetException("No target selected");
		}

		if (this.getTarget() instanceof Zombie) {
			throw new InvalidTargetException("Target must be a Hero");
		}
		if (!(this.adjacent(this.getTarget()))) {
			throw new InvalidTargetException("Target is not in range");
		}
		try {
			super.useSpecial();
		}

		catch (NoAvailableResourcesException e) {
			throw new NoAvailableResourcesException();
		} catch (InvalidTargetException e) {
			throw new InvalidTargetException();
		}
		this.getTarget().setCurrentHp(this.getTarget().getMaxHp());
	}

}
