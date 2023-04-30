package model.collectibles;
public class Armor extends Supply{
	private int protection;
	private Material material;
	public Armor(int protection, Material material) {
		this.protection=protection;
		this.material=material;
	}
	public int getProtection() {
		return protection;
	}
	public Material getMaterial() {
		return material;
	}

}
