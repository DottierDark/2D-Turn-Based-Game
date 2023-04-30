package model.characters;
import java.awt.Point;
import engine.Game;

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
	public void attack() {
		// TODO Auto-generated method stub
	}
	public void defend(Character c) {
		// TODO Auto-generated method stub
	}
	public void onCharacterDeath() {
		Character ch = this.getTarget();
		if (ch instanceof Explorer || ch instanceof Medic ||ch instanceof Fighter ){
			boolean t = heroes.remove(ch);
			
		}
		if(ch instanceof Zombie) {
			Zombie z = new Zombie();
			
			
		}
	}
}
