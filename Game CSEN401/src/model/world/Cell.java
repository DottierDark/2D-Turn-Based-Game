package model.world;

public class Cell {
	private boolean isVisible;
	
	public Cell(boolean isVisible) {
		this.setVisible(isVisible); 
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	
	
}
