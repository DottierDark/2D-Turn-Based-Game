package model.world;

public abstract class  Cell {
	private boolean isVisible;
	
	public Cell(boolean isVisible) {
		this.setVisible(isVisible); 
	}
	public Cell() {
		this.setVisible(false);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	
	
}
