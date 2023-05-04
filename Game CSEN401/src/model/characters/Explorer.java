package model.characters;

import engine.Game;

public class Explorer extends Hero{

	public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		// TODO Auto-generated constructor stub
	}

	public void useSpecial() {
		
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				Game.map[i][j].setVisible(true);
			}
		}
	}
	
}
