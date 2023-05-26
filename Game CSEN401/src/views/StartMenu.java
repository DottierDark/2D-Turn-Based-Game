package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StartMenu {
	private Scene start;
	public String CSS = this.getClass().getResource("start menue scene.css").toExternalForm();
	Image image = new Image("apocalypse.jpg");
	BackgroundSize rakam =new BackgroundSize(100, 100, true, true, true, true);
	private Background back = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,rakam));
	
	
	public StartMenu() {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		VBox buttons = new VBox();
		Button single = new Button("Single Player");
		Button button2 = new Button("Player Vs AI");
		Button button3 = new Button("Player Vs Player");
		Button quit = new Button("Quit");
		single.setAlignment(Pos.CENTER);
		button2.setAlignment(Pos.CENTER);
		button3.setAlignment(Pos.CENTER);
		quit.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(single, button2, button3, quit);
		buttons.setSpacing(20);
		buttons.setAlignment(Pos.CENTER);
		buttons.setMinSize(Main.window.getWidth(), Main.window.getHeight());
		

		Main.window.widthProperty().addListener((observable, oldWidth, newWidth)->{
			root.setPrefWidth(newWidth.doubleValue());
			
		});
		Main.window.heightProperty().addListener((observable, oldHeight, newHeight)->{
			root.setPrefHeight(newHeight.doubleValue());
		});

		single.setOnAction(e -> {
			Main.HeroPick = new HeroPick();
			Main.window.setScene(Main.HeroPick.getHeroPick());
			Main.window.setFullScreen(false);
			Main.window.setFullScreen(true);
			
		});
		quit.setOnAction(e -> {
			System.exit(0);
		});
		root.getChildren().add(buttons);
		root.setBackground(back);
		start = new Scene(root, 500, 500);
		//start.getStylesheets().add(CSS);

	}

	public Scene getScene() {
		return start;
	}

	public void setStart(Scene start) {
		this.start = start;
	}

}
