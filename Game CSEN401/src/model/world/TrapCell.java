package model.world;
import java.lang.Math;

public class TrapCell extends Cell{
	
	private int trapDamage;

	public TrapCell() {
		super();
		trapDamage = ((int) ((Math.random() * 3)+1)* 10);
	}
	public int getTrapDamage() {
		return trapDamage;
	}

}
