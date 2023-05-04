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
		if(currentHp < 0)
			this.currentHp = 0;
		else if(currentHp > maxHp)
			this.currentHp = maxHp;
		else
			this.currentHp = currentHp;
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

		if(!this.adjacent(this.getTarget())) {
			throw new InvalidTargetException("Target is not in range");
		}

		if(this instanceof Hero) {
			Hero h = (Hero) this;
			if(h.getActionsAvailable() == 0) {
				throw new NotEnoughActionsException("Not enough actions to attack");
			}
		}

		if(this.getClass() == getTarget().getClass()) {
			throw new InvalidTargetException("Cant attack same type");
		}

		this.getTarget().setCurrentHp(this.getTarget().getCurrentHp()-this.getAttackDmg());
		if(this.getTarget().getCurrentHp()==0) {
			this.getTarget().onCharacterDeath();
		}
		else {
			this.getTarget().defend(this);
		}
	}

	public void defend(Character c) {
		
		setTarget(c);
		int x =	this.getAttackDmg();
		this.attackDmg = x / 2;
		try {
			this.attack();
		} catch (InvalidTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughActionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.attackDmg = x;
		}
		
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
	

	    public boolean adjacent(Character tar) {
	    	  Point character = this.getLocation();
	          Point target = tar.getLocation();

	          int dx = Math.abs(character.x - target.x);
	          int dy = Math.abs(character.y - target.y);

	          if (dx <= 1 && dy <= 1) {
	              return true;
	          } else {
	              return false;
	          }
	      }
	    }