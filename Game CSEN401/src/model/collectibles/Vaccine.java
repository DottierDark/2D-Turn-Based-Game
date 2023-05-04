package model.collectibles;

import exceptions.NotEnoughActionsException;
import model.characters.Hero;

public class Vaccine implements Collectible {	
	
	public Vaccine() {
		
	}

	@Override
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
	}

	@Override
	public void use(Hero h) throws NotEnoughActionsException {
		h.getVaccineInventory().remove(this);
	}
}
