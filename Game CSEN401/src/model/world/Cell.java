package model.world;

public abstract class Cell {
	
	private boolean isVisible;
	
	public Cell() {
		isVisible = false;
	}
	
	public Cell(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	
	
}
