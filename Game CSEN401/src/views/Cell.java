package views;

import javafx.scene.layout.Pane;
import model.characters.Character;
import model.collectibles.Collectible;

public class Cell extends Pane {
	public int x;
	public int y;
	public Character c;
	public Collectible col;
	
	public Cell(Character c,int x,int y) {
		this.c=c;
		this.x =14-x;
		this.y=y;
	}
	public Cell(Collectible c,int x,int y) {
		this.col=c;
		this.x =14-x;
		this.y=y;
	}
	public Cell(int x,int y) {
		this.x =14-x;
		this.y=y;
	}
	

}
