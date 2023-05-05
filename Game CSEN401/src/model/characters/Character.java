package model.characters;
import java.awt.Point;

import engine.Game;
import exceptions.*;
import model.world.CharacterCell;

public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;
	
	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
	    this.maxHp = maxHp;
	    this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}

	
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp <= 0) {
			this.currentHp = 0;
		    onCharacterDeath();
		} else {
			if(currentHp > maxHp) {
				this.currentHp = maxHp;
			} else {
				this.currentHp = currentHp;
			}
		}
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException{

		if(this.getTarget() == null) {
			throw new InvalidTargetException("No target selected");
		}

		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}

		this.getTarget().setCurrentHp(this.getTarget().getCurrentHp()-this.getAttackDmg());
		this.getTarget().defend(this);
	}

	public void defend(Character attacker) {
		this.setTarget(attacker);
		attacker.setCurrentHp(attacker.getCurrentHp() - (int)(this.attackDmg/2));
		
	}

	public void onCharacterDeath() {
		int x = this.getLocation().x;
		int y = this.getLocation().y;
		 ((CharacterCell) Game.map[x][y]).setCharacter(null);
		if (this instanceof Explorer || this instanceof Medic ||this instanceof Fighter){
			Game.heroes.remove(this);
			Game.availableHeroes.remove(this);
		}

		if(this instanceof Zombie) {
			Game.zombies.remove(this);
			Game.spawnZombies(1);
		}

	}
	

	public boolean adjacent(Character character) {

		Point location = this.getLocation();
		Point target = character.getLocation();
	
		int dx = Math.abs(location.x - target.x);
		int dy = Math.abs(location.y - target.y);
	
		return dx <= 1 && dy <= 1;
	}
}