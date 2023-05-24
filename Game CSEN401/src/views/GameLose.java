package views;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

public class GameLose {

	public String CSS = this.getClass().getResource("Lose scene.css").toExternalForm();
	public Scene lose;

	public Scene getLose() {
		return lose;
	}

	public GameLose() {
		Button startMenu = new Button("Start Menu");
		StackPane root = new StackPane();
		lose = new Scene(root, Main.window.getWidth(), Main.window.getHeight());
		lose.getStylesheets().add(CSS);
	}

}
