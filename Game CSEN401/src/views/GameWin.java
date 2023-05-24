package views;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class GameWin {

	public String CSS = this.getClass().getResource("win scene.css").toExternalForm();

	private Scene win;

	public Scene getWin() {
		return win;
	}

	public GameWin() {
		StackPane root = new StackPane();
		win = new Scene(root, Main.window.getWidth(), Main.window.getHeight());
		win.getStylesheets().add(CSS);
	}

}
