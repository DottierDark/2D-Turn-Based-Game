package model.characters;

public class Medic extends Hero {

	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		// TODO Auto-generated constructor stub
	}

	public void useSpecial() {
		this.getTarget().setCurrentHp(this.getMaxHp());
	}

}
