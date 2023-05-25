package views;

import java.util.Random;

import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.characters.Hero;
import model.characters.Zombie;

public class GameMap {

	private Scene GameMapScene;
	public String CSS = this.getClass().getResource("game map.css").toExternalForm();
	public Hero selectedHero;
	

	public Scene getGameMapScene() {
		return GameMapScene;
	}

	public void setGameMapScene(Scene gameMapScene) {
		GameMapScene = gameMapScene;
	}

	public GameMap() {
		
		GridPane mapbox = new GridPane();
		BorderPane main = new BorderPane();
		GridPane buttons = new GridPane();
		GridPane movement = new GridPane();
		Button up = new Button("Up");
		Button down = new Button("Down");
		Button left = new Button("Left");
		Button right = new Button("Right");
		Button attack = new Button("Attack");
		Button endTurn = new Button("End Turn");
		Button cure = new Button("Cure");
		Button useSpecial = new Button("Use Special");
		Button exitButton = new Button("Exit");
		
		Main.window.widthProperty().addListener((observable, oldWidth, newWidth)->{
			main.setPrefWidth(newWidth.doubleValue());
			
		});
		Main.window.heightProperty().addListener((observable, oldHeight, newHeight)->{
			main.setPrefHeight(newHeight.doubleValue());
		});

		mapbox.setGridLinesVisible(true);
		Pane pane;
		Button SelectButton = new Button();
		for (int y = 0; y < 15; y++) {
			for (int x = 0; x < 15; x++) {
				pane = new Pane();
				pane.setPrefSize(50, 50);
				SelectButton = new Button("");
				SelectButton.setOnAction(e -> {
					//selectedHero = Hero ;
				});
				
				/*Image image = new Image("Grass.jpg");
				BackgroundImage imageView = new BackgroundImage(image);
				pane.setBackground(imageView);
				mapbox.add(imageView, x, y);*/

			}
		}
	
		Random rand = new Random();

		for (int i = 0; i < 30; i++) {
			int X1 = rand.nextInt(13);
			int Y1 = rand.nextInt(13);
			Image image = new Image("Flowers.jpg");
			ImageView imageView = new ImageView(image);
			mapbox.add(imageView, X1, Y1);
		}

		Hero FirstHero = Game.heroes.get(0);
		int x = FirstHero.getLocation().x;
		int y = FirstHero.getLocation().y + 14;
		Image image2 = new Image("HeroWithABackGround.jpg");
		ImageView imageView2 = new ImageView(image2);
		mapbox.add(imageView2, x, y);

		for (Zombie z : Game.zombies) {
			int X1 = z.getLocation().x;
			int Y1 = z.getLocation().y;
			Image image = new Image("Zombie.jpg");
			ImageView imageView = new ImageView(image);
			mapbox.add(imageView, X1, Y1);
		}

		mapbox.setAlignment(Pos.CENTER);

		main.setCenter(mapbox);

		buttons.setHgap(10);
		buttons.setVgap(10);

		exitButton.setOnAction(e -> System.exit(0));

		movement.add(up, 1, 0);
		movement.add(down, 1, 2);
		movement.add(left, 0, 1);
		movement.add(right, 2, 1);

		buttons.add(attack, 0, 1);
		buttons.add(endTurn, 0, 2);
		buttons.add(cure, 0, 3);
		buttons.add(useSpecial, 0, 4);
		buttons.add(exitButton, 0, 5);

		main.setRight(buttons);
		main.setLeft(movement);
		// root1.setBackground(new Background(new BackgroundFill(Color.BLACK,
		// CornerRadii.EMPTY, Insets.EMPTY)));

		mapbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		main.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		try {
		SelectButton.setOnAction(e ->{
			
		});
		}
		catch(Exception e) {
			System.out.println("ay habal");
		}

		GameMapScene = new Scene(main);

		GameMapScene.getStylesheets().add(CSS);
	}

}