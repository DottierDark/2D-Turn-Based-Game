package model.world;

import model.collectibles.Collectible;

public abstract class Building extends Cell implements Collectible {
	private Collectible loot;
	public Building(Collectible loot) {
		super();
		this.loot = loot;
	}
	public  Collectible getLoot() {
		return loot;
	}

}
