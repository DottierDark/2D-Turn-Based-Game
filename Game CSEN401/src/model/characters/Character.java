package model.characters;
import java.awt.Point;

public abstract class Character {
	
	private String name;//read only
	private Point location;//read and write
	private int maxHp;//read only
	private int currentHp;//read and write
	private int attackDmg;//read only
	private Character target;//read and write
	
	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDmg;
		this.currentHp=maxHp;
	}

	public String getName() {
		return name;
	}
	public Point getLocation() {
		return location;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public int getCurrentHp() {
		return currentHp;
	}
	public int getAttackDmg() {
		return attackDmg;
	}
	public Character getTarget() {
		return target;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void setCurrentHp(int current) {
		if(current < maxHp)
		this.currentHp = current;
	}

	public void setTarget(Character target) {
		this.target = target;
	}



	
}
