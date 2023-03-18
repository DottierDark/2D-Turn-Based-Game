package model.world;
import model.collectibles.Collectible;

public class CollectibleCell extends Cell  {
	
	private Collectible collectible;
	
	public CollectibleCell(boolean isVisible) {
		super(isVisible);
		// TODO Auto-generated constructor stub
	}

	public Collectible getCollectible() {
		return collectible;
	}

}
