package model.collectibles;

import java.awt.Point;
import java.util.Random;

import engine.Game;
import exceptions.NoAvailableResourcesException;
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
	public void use(Hero h) throws NotEnoughActionsException,NoAvailableResourcesException {

		if(h.getActionsAvailable() == 0) {
			throw new NotEnoughActionsException("Not enough actions to use vaccine");
		}
		if(h.getVaccineInventory().size() == 0) {
			throw new NotEnoughActionsException("Not enough vaccines to use vaccine");
		}
		int heroIndex = (int)(Math.random()*3);

		Hero newHero = Game.availableHeroes.remove(heroIndex); // Get hero from availableHeros arraylist// Remove hero from availableHeros arraylist
		Game.zombies.remove(h.getTarget());
		newHero.setLocation(h.getTarget().getLocation()); // Set location of new hero to cured zombie location // Get location of zombie cured
		h.setTarget(null);

		Game.availableHeroes.add(newHero); // Spawn hero

		h.getVaccineInventory().remove(this);
	}
	public static void main (String[] args) {
		int heroindex = (int)(Math.random()*3);
		System.out.println(heroindex);
	}
}
