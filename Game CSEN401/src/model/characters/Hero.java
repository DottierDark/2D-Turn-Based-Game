package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;

public abstract class Hero extends Character{
	
	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	
	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.vaccineInventory = new ArrayList<Vaccine>();
		this.supplyInventory = new ArrayList<Supply>();
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
	        this.actionsAvailable = actionsAvailable;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public void move(Direction d) throws MovementException {
		String dir = d.toString();
		switch(dir) {
			case "UP": this.getLocation().translate(1,0);
				break;
			case "DOWN": this.getLocation().translate(-1, 0);
				break;
			case "LEFT": this.getLocation().translate(0, 1);
				break;
			case "RIGHT": this.getLocation().translate(0, -1);
				break;
		}
	}

	public void attack() throws InvalidTargetException,  NotEnoughActionsException {

		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}

		if(this instanceof Fighter) {
			((Fighter) this).attack();
		}
		
		if(this.actionsAvailable == 0) {
			throw new NotEnoughActionsException("Not enough actions to attack");
		}

		this.actionsAvailable--;
		this.getTarget().setCurrentHp(this.getTarget().getCurrentHp()-this.getAttackDmg());
		
	}

	public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException {
		this.getSupplyInventory().get(0).use(this);
		this.setSpecialAction(true);
		switch(this.getClass().getSimpleName()) {
		case "Fighter" :
			((Fighter) this).useSpecial();
			break;
		case "Medic":
			((Medic) this).useSpecial();
			break;
		case "Explorer":
			((Explorer) this).useSpecial();
			break;	
		}
		
	}

	public void cure() throws NotEnoughActionsException , NoAvailableResourcesException, InvalidTargetException {
		
		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}

		if(this.getVaccineInventory().size() == 0) {
			throw new NoAvailableResourcesException("No vaccines available for hero");
		}

		Random rand = new Random();
		int heroIndex = rand.nextInt(Game.availableHeroes.size()-1);

		Hero newHero = Game.availableHeroes.get(heroIndex); // Get hero from availableHeros arraylist
		Game.availableHeroes.remove(heroIndex); // Remove hero from availableHeros arraylist

		this.getVaccineInventory().get(0).use(this);

		Point location = this.getTarget().getLocation(); // Get location of zombie cured

		newHero.setLocation(location); // Set location of new hero to cured zombie location

		this.getTarget().onCharacterDeath(); // Kill zombie
		this.setTarget(null);

		Game.availableHeroes.add(newHero); // Spawn hero
	}
	
}
