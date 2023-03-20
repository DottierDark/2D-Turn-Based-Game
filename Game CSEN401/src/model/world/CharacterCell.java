package model.world;
import model.characters.Character;

public class CharacterCell extends Cell {
	
	private Character character;
	private boolean isSafe;
	
	public CharacterCell(boolean isVisible) {
		super(isVisible);
		// TODO Auto-generated constructor stub
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public boolean isSafe() {
		return isSafe;
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
	
	

}