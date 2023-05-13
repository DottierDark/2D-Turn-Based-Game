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
	
	public Hero(String name,int maxHp, int attackDmg, int maxActions) {
		super(name,maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.vaccineInventory = new ArrayList<Vaccine>();
		this.supplyInventory=new ArrayList<Supply>();
		this.specialAction=false;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
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

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}	

	public void moveHelper(int x, int y)  throws MovementException {

		if(x < 0 || x > 14 || y < 0 || y > 14) {
			throw new MovementException("Out of bounds");
		}

		if(Game.map[x][y] instanceof CharacterCell && ((CharacterCell) Game.map[x][y]).getCharacter() != null) {
			throw new MovementException("Occupied cell");
		}
		
		Point currentLocation = this.getLocation();
		Game.map[currentLocation.getLocation().x][currentLocation.getLocation().y] = new CharacterCell(null);
		
		if(Game.map[x][y] instanceof TrapCell) {
			setCurrentHp(getCurrentHp() - ((TrapCell) Game.map[x][y]).getTrapDamage());
		}

		if(Game.map[x][y] instanceof CollectibleCell) {
			((CollectibleCell) Game.map[x][y]).getCollectible().pickUp(this);
		}
		
		if(this.getCurrentHp() == 0) {
			this.onCharacterDeath();
			return;
		}

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

		Point currentLocation = this.getLocation();
		int x = (int) currentLocation.getX();
		int y = (int) currentLocation.getY();
		switch(d) {
			case UP: 
				this.moveHelper(x+1, y);
				break;
			case DOWN: 
				this.moveHelper(x-1, y);
				break;
			case LEFT: 
				this.moveHelper(x, y-1);
				break;
			case RIGHT: 
				this.moveHelper(x, y+1);
				break;
		}
	}

	public void attack() throws InvalidTargetException,  NotEnoughActionsException {
	
		if(this.getTarget() instanceof Hero){
			throw new InvalidTargetException("Target of attack must be a Zombie");
		}

		if((this instanceof Fighter) && ((Fighter) this).isSpecialAction() == true) {
			super.attack();
			return;
		}

		if(this.actionsAvailable == 0) {
			throw new NotEnoughActionsException("Not enough actions to attack");
		}

		super.attack();
		this.setActionsAvailable(this.getActionsAvailable()-1);
	}


	public void useSpecial() throws  NoAvailableResourcesException, InvalidTargetException {
		
		if(this.getSupplyInventory().isEmpty()) {
			throw new NoAvailableResourcesException("No supplies available");
		}

		this.getSupplyInventory().get(0).use(this);
		this.setSpecialAction(true);
	}

	public void cure() throws NotEnoughActionsException , NoAvailableResourcesException, InvalidTargetException {
		
		if(this.getTarget() == null) {
			throw new InvalidTargetException("No target selected");
		}

		if(!(this.adjacent(this.getTarget()))) {
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
	}
	
}
