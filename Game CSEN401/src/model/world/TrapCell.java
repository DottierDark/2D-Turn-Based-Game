package model.world;
import java.lang.Math;

public class TrapCell extends Cell{
	
	private int trapDamage;

	public TrapCell(boolean isVisible) {
		super(isVisible);
		trapDamage = (int) (Math.floor((Math.random() * 3)+1) * 10);
	}
	public TrapCell() {
		super();
		trapDamage = (int) (Math.floor((Math.random() * 3)+1) * 10);
	}
	public int getTrapDamage() {
		return trapDamage;
	}

}
