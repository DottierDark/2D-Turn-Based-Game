package model.collectibles;

import java.awt.Point;
import java.util.Random;

import engine.Game;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;
import model.world.CharacterCell;

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
		
		if(h.getVaccineInventory().isEmpty()) {
			throw new NoAvailableResourcesException();
		}
		
		Random rand = new Random();
		int heroIndex = rand.nextInt(Game.availableHeroes.size());

		Hero newHero = Game.availableHeroes.get(heroIndex); // Get hero from availableHeros arraylist
		Game.availableHeroes.remove(newHero); // Remove hero from availableHeros arraylist
		Game.zombies.remove(h.getTarget());
		Point location = h.getTarget().getLocation(); // Get location of zombie cured

		newHero.setLocation(location); // Set location of new hero to cured zombie location
		
		int x = newHero.getLocation().x;
		int y = newHero.getLocation().y;
		Game.heroes.add(newHero); // Spawn hero
		Game.map[x][y] = new CharacterCell(newHero);
		h.getVaccineInventory().remove(this);
		h.setActionsAvailable(h.getActionsAvailable()-1);
	}
}
