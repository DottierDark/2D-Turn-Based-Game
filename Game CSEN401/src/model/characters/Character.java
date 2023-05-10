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
	
	public Character() {
	}
	

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
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

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0) 
			this.currentHp = 0;
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void  attack() throws InvalidTargetException, NotEnoughActionsException {

		if(this.getTarget() == null) {
			throw new InvalidTargetException("No target selected");
		}

		if(!(this.adjacent(this.getTarget()))) {
			throw new InvalidTargetException("Target is not in range");
				}
		this.getTarget().defend(this);
		
		this.getTarget().setCurrentHp(this.getTarget().getCurrentHp() - this.getAttackDmg());
		
		
		
		if(this.getTarget().getCurrentHp() == 0) {
			this.getTarget().onCharacterDeath();
		}
	
	}

	public void defend(Character attacker) throws InvalidTargetException {
		int currHP = attacker.getCurrentHp();
		int attack = (int)(this.getAttackDmg()/2);
		int newHP = currHP - attack;
		attacker.setCurrentHp(newHP);
		if(attacker.getCurrentHp() == 0) {
			attacker.onCharacterDeath();
		}
	}

	public void onCharacterDeath() {

		int x = this.getLocation().x;
		int y = this.getLocation().y;
		
		((CharacterCell) Game.map[x][y]).setCharacter(null);
		if(this instanceof Fighter||this instanceof Explorer|| this instanceof Medic) {
			Game.heroes.remove(this);
			return;
		}
		if(this instanceof Zombie) {
			Game.spawnZombies(1);
			Game.zombies.remove(this);
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