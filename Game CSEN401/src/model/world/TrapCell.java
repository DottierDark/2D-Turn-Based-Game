package model.world;
import java.util.Random;

public class TrapCell extends Cell{
	
	private int trapDamage;

	public TrapCell(boolean isVisible) {
		super(isVisible);
		// TODO Auto-generated constructor stub
		trapDamage = (int) (((Math.random()*3)+1)*10);
	}

	public int getTrapDamage() {
		return trapDamage;
	}

}
