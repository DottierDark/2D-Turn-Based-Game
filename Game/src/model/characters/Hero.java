package model.characters;

import java.util.ArrayList;

import model.collectibles.Vaccine;

public class Hero extends Character{
	int actionsAvailable;
	int maxActions;
	boolean specialAction;
	ArrayList<Vaccine> vaccineInventory;
	ArrayList<Supply> supplyInventory;
	

	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name,maxHp,attackDmg);
	}

}
