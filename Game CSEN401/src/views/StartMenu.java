package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class StartMenu {
	private Scene start;
	public String CSS = this.getClass().getResource("start menue scene.css").toExternalForm();

	public StartMenu() {
		// TODO Auto-generated method stub
		VBox root = new VBox();
		Button single = new Button("Single Player");
		Button button2 = new Button("Player Vs AI");
		Button button3 = new Button("Player Vs Player");
		Button quit = new Button("Quit");
		single.setAlignment(Pos.CENTER);
		button2.setAlignment(Pos.CENTER);
		button3.setAlignment(Pos.CENTER);
		quit.setAlignment(Pos.CENTER);
		root.getChildren().addAll(single, button2, button3, quit);
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		root.setMinSize(Main.window.getWidth(), Main.window.getHeight());

		single.setOnAction(e -> {
			Main.HeroPick = new HeroPick();
			Main.window.setScene(Main.HeroPick.getHeroPick());
			Main.window.show();
		});
		quit.setOnAction(e -> {
			System.exit(0);
		});
		start = new Scene(root, 500, 500);
		start.getStylesheets().add(CSS);

	}

	public Scene getScene() {
		return start;
	}

	public void setStart(Scene start) {
		this.start = start;
	}

}
