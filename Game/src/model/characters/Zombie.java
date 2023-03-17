package model.characters;

public class Zombie  extends Character{
	static int ZOMBIES_COUNT;
	public Zombie() {
		super("Zombie"+Integer.toString(ZOMBIES_COUNT),40,10);
		
	}

}
