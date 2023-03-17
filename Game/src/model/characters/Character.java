package model.characters;

import java.awt.Point;

public abstract class Character {
	String name;
	Point location;
	int maxHp;
	int currentHp;
	int attackDmg;
	Character target;
	Character(String name, int maxHp, int attackDmg){
		
	}
	
}
