package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

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

	public void moveHelper(int x, int y)  throws MovementException {

		if(x < 0 || x > 14 || y < 0 || y > 14) {
			throw new MovementException("Out of bounds");
		}

		if(Game.map[x][y] instanceof CharacterCell && ((CharacterCell) Game.map[x][y]).getCharacter() != null) {
			throw new MovementException("Occupied cell");
		}

		if(Game.map[x][y] instanceof TrapCell) {
			setCurrentHp(getCurrentHp() - ((TrapCell) Game.map[x][y]).getTrapDamage());
			if(getCurrentHp() == 0) {
				Game.updateMap();
				return;
			}
		}

		if(Game.map[x][y] instanceof CollectibleCell) {
			((CollectibleCell) Game.map[x][y]).getCollectible().pickUp(this);;
		}

		Point currentLocation = this.getLocation();
		Game.map[(int) currentLocation.getX()][(int) currentLocation.getY()] = new CharacterCell(null);
		Game.map[x][y] = new CharacterCell(this);
		this.setLocation(new Point(x, y));
		this.setActionsAvailable(actionsAvailable-1);
	
		// Updating visibilty after hero moves
		for(int j=x-1; j<x+2; j++) {
			for(int k=y-1; k<y+2; k++) {
				if(j >= 0 && j <= 14 && k >= 0 && k <= 14) {
					Game.map[j][k].setVisible(true);
				}
			}
		}
	}

	public void move(Direction d) throws MovementException,  NotEnoughActionsException {

		if(this.getActionsAvailable() == 0) {
			throw new NotEnoughActionsException("No actions available");
		}

		String dir = d.toString();
		Point currentLocation = this.getLocation();
		int x = (int) currentLocation.getX();
		int y = (int) currentLocation.getY();
		switch(dir) {
			case "UP": 
				moveHelper(x+1, y);
				break;
			case "DOWN": 
				moveHelper(x-1, y);
				break;
			case "LEFT": 
				moveHelper(x, y-1);
				break;
			case "RIGHT": 
				moveHelper(x, y+1);
				break;
		}
	}

	public void attack() throws InvalidTargetException,  NotEnoughActionsException {

		if(!(this.getTarget() instanceof Zombie)) {
			throw new InvalidTargetException("Target of attack must be a Zombie");
		}

		if(this.actionsAvailable == 0 && !(this instanceof Fighter && ((Fighter) this).isSpecialAction() == true)) {
			throw new NotEnoughActionsException("Not enough actions to attack");
		}

		super.attack();

		if(!(this instanceof Fighter && ((Fighter) this).isSpecialAction() == true)) {
			this.actionsAvailable--;
		}

		
	}

	public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {

		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}

		if(this.getSupplyInventory().size() == 0) {
			throw new NoAvailableResourcesException("No supplies available");
		}

		if(this.actionsAvailable == 0) {
			throw new NotEnoughActionsException("Not enough actions to use special");
		}

		this.getSupplyInventory().get(0).use(this);
		this.setSpecialAction(true);
	}

	public void cure() throws NotEnoughActionsException , NoAvailableResourcesException, InvalidTargetException {
		
		if(this.getTarget() == null) {
			throw new InvalidTargetException("No target selected");
		}

		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}

		if(!(this.getTarget() instanceof Zombie)) {
			throw new InvalidTargetException("Target of attack must be a Zombie");
		}

		if(this.getVaccineInventory().size() == 0) {
			throw new NoAvailableResourcesException("No vaccines available for hero");
		}

		if(this.getActionsAvailable() == 0) {
			throw new NotEnoughActionsException("Not enough actions available");
		}

		

		this.getVaccineInventory().get(0).use(this);
		this.setActionsAvailable(getActionsAvailable()-1);
	}
	
}
