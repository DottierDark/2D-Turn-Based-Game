package views;

import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage window = new Stage();
	public static HeroPick HeroPick;
	public static GameMap gameMap;
	public static StartMenu startMenu = new StartMenu();
	public static GameLose losescene = new GameLose();
	public static GameWin winscene = new GameWin();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = window;
		window.setScene(startMenu.getScene());
		window.setResizable(true);
		window.setFullScreen(true);
		window.setFullScreenExitHint("YOU CANNOT ESCAPE PRESS ESC");

		window.setFullScreenExitKeyCombination(KeyCombination.valueOf("ESC"));
		window.setTitle("The Last of Us");
		Main.window.centerOnScreen();
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
