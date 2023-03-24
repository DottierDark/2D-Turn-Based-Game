package model.world;

public class TrapCell extends Cell{
	
	private int trapDamage;

	public TrapCell() {
		super();
		trapDamage = ((int) (Math.random() * 3) + 1)*10;
	}
	
	public TrapCell(boolean isVisible) {
		super(isVisible);
		trapDamage = ((int) (Math.random() * 3) + 1)*10;
	}	

	public int getTrapDamage() {
		return trapDamage;
	}

}
