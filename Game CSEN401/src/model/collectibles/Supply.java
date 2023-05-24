package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Supply implements Collectible {

	public Supply() {

	}

	@Override
	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
	}

	@Override
	public void use(Hero h) throws NoAvailableResourcesException {
		if (h.getSupplyInventory().isEmpty()) {
			throw new NoAvailableResourcesException();
		}
		h.getSupplyInventory().remove(this);
	}

}
