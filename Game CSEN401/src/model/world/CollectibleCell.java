package model.world;
import model.collectibles.Collectible;

public class CollectibleCell extends Cell implements Collectible {
	
	private Collectible collectible;
	
 public CollectibleCell(Collectible collectible) {
	 super();
	 this.collectible=collectible;
 }
	public Collectible getCollectible() {
		return collectible;
	}

}
