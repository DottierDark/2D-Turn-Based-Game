package model.characters;
import java.awt.Point;

import engine.Game;
import exceptions.*;

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
		if(currentHp < 0) {
			this.currentHp = 0;
			onCharacterDeath();
		}
		else if(currentHp > maxHp) {
			this.currentHp = maxHp;
		}	
		else {
			this.currentHp = currentHp;
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

	public void attack() throws InvalidTargetException, NotEnoughActionsException {

		if(this instanceof Hero) {
			((Hero) this).attack();
		}

		if(this instanceof Zombie) {
			((Zombie) this).attack();
		}

		this.getTarget().defend(this);

	}

	public void defend(Character attacker) {
		
		setTarget(attacker);
		attacker.setCurrentHp(attacker.getCurrentHp() - this.attackDmg/2);
		
	}
	public void onCharacterDeath() {

		if (this instanceof Explorer || this instanceof Medic ||this instanceof Fighter){
			Game.heroes.remove(this);
			Game.availableHeroes.remove(this);
			int x = this.getLocation().x;
			int y = this.getLocation().y;
			Game.map[x][y] = null;
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

		if (dx <= 1 && dy <= 1) {
			return true;
		} else {
			return false;
		}
	}
}