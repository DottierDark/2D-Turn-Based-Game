package model.characters;

import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public class Zombie extends Character {

	private static int ZOMBIES_COUNT = 1;

	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {

		ArrayList<Character> adjHeroes = new ArrayList<Character>();

		int x = (int) this.getLocation().getX();
		int y = (int) this.getLocation().getY();

		for (int j = x - 1; j < x + 2; j++) {
			for (int k = y - 1; k < y + 2; k++) {
				if (j >= 0 && j < 15 && k >= 0 && k < 15) {
					if (Game.map[j][k] instanceof CharacterCell) {
						if (((CharacterCell) Game.map[j][k]).getCharacter() instanceof Hero) {
							adjHeroes.add(((CharacterCell) Game.map[j][k]).getCharacter());
						}
					}
				}
			}
		}

		if (adjHeroes.isEmpty()) {
			return;
		}

		Random rand = new Random();
		this.setTarget(adjHeroes.get(rand.nextInt(adjHeroes.size())));

		super.attack();
	}

}
