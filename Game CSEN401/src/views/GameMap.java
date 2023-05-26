package views;

import engine.Game;
import model.characters.Character;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class GameMap {

	private Scene GameMapScene;
	public String CSS = this.getClass().getResource("game map.css").toExternalForm();
	public Hero selectedHero = Game.heroes.get(0);
	private static final int GRID_SIZE = 15;
	private static final int CELL_SIZE = 50;
	Image grass = new Image("Grass.jpg");
	Image flowers = new Image("Flowers.jpg");
	Image zombie = new Image("Zombie.jpg");
	Image hero = new Image("david-back-1.png");
	Image vaccine = new Image("vaccine.jpg");
	Image supply = new Image("supply.jpg");
	private Background grassback = new Background(new BackgroundImage(grass, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background zombieback = new Background(new BackgroundImage(zombie, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background heroback = new Background(new BackgroundImage(hero, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	
	private Background vaccineback = new Background(new BackgroundImage(vaccine, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background supplyback = new Background(new BackgroundImage(supply, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

	Cell zombieCell;
	Cell cellHero;
	Cell[][] cell = new Cell[15][15];
	
	
	
	public GameMap() {
		
		GridPane mapbox = new GridPane();
		BorderPane main = new BorderPane();
		GridPane buttons = new GridPane();
		GridPane movement = new GridPane();
		Button up = new Button("^");
		Button down = new Button("v");
		Button left = new Button("<");
		Button right = new Button(">");
		Button attack = new Button("Attack");
		Button endTurn = new Button("End Turn");
		Button cure = new Button("Cure");
		Button useSpecial = new Button("Use Special");
		Button exitButton = new Button("Exit");
		Text text = new Text("");
		TextFlow textbox = new TextFlow();
		VBox leftside = new VBox();
		
		
		text.setFill(Color.WHITE);
		up.setPrefSize(40, 40);
		down.setPrefSize(40, 40);
		left.setPrefSize(40, 40);
		right.setPrefSize(40, 40);
		textbox.getChildren().add(text);
		
	
		
		Main.window.widthProperty().addListener((observable, oldWidth, newWidth)->{
			main.setPrefWidth(newWidth.doubleValue());
			
		});
		Main.window.heightProperty().addListener((observable, oldHeight, newHeight)->{
			main.setPrefHeight(newHeight.doubleValue());
		});

		mapbox.setGridLinesVisible(true);
		
		for(int i = 0 ; i<15;i++) {
			for(int j = 0; j<15;j++) {
			if(Game.map[14-i][j] instanceof CharacterCell) {
				cell[i][j] = new Cell(((CharacterCell) Game.map[14-i][j]).getCharacter(),i,j);
				cell[i][j].setPrefSize(CELL_SIZE , CELL_SIZE );
				cell[i][j].setMinSize(CELL_SIZE , CELL_SIZE );
				cell[i][j].setMaxSize(CELL_SIZE , CELL_SIZE );
				cell[i][j].setBackground(grassback);
				mapbox.add(cell[i][j],i,j);
				}
			if(Game.map[14-i][j] instanceof CollectibleCell) {
					cell[i][j] = new Cell(((CollectibleCell) Game.map[14-i][j]).getCollectible(),i,j);
					cell[i][j].setPrefSize(CELL_SIZE , CELL_SIZE );
					cell[i][j].setMinSize(CELL_SIZE , CELL_SIZE );
					cell[i][j].setMaxSize(CELL_SIZE , CELL_SIZE );
			switch(((CollectibleCell) Game.map[14-i][j]).getCollectible().getClass().getSimpleName()) {
					case("Vaccine"):cell[i][j].setBackground(vaccineback);break;
					case("Supply"):cell[i][j].setBackground(supplyback);break;
					}
					mapbox.add(cell[i][j],i,j);
				}
			if(Game.map[14-i][j] instanceof TrapCell) {
				cell[i][j] = new Cell(i,j);
				cell[i][j].setPrefSize(CELL_SIZE , CELL_SIZE );
				cell[i][j].setMinSize(CELL_SIZE , CELL_SIZE );
				cell[i][j].setMaxSize(CELL_SIZE , CELL_SIZE );
				cell[i][j].setBackground(grassback);
				mapbox.add(cell[i][j],i,j);
				}
				
				
				}
		}
			
		
	
		
		for (Zombie z : Game.zombies) {
			int X1 = 14-z.getLocation().x;
			int Y1 = z.getLocation().y;
			zombieCell = new Cell(z,X1,Y1);
			zombieCell.setPrefSize(CELL_SIZE , CELL_SIZE );
			zombieCell.setMinSize(CELL_SIZE , CELL_SIZE );
			zombieCell.setMaxSize(CELL_SIZE , CELL_SIZE );
			zombieCell.setBackground(zombieback);
			zombieCell.setOnMouseClicked(e ->{text.setText( "\nType: " + z.getClass().getSimpleName().toString()  +
					"\nAttack Damage: " + z.getAttackDmg() + "\nMax HP: "
					+ z.getMaxHp());
					text.setFill(Color.WHITE);
				}
			);
			
			mapbox.add(zombieCell, Y1, X1);
		}
		int x = 14-selectedHero.getLocation().x;
		int y = selectedHero.getLocation().y ;
		cellHero = new Cell(selectedHero,x,y);
		cellHero.setBackground(heroback);
		cellHero.setPrefSize(CELL_SIZE , CELL_SIZE );
		cellHero.setMinSize(CELL_SIZE , CELL_SIZE );
		cellHero.setMaxSize(CELL_SIZE , CELL_SIZE );
		cellHero.setOnMousePressed(e ->{text.setText("\nHero Type: " + selectedHero.getClass().getSimpleName().toString() + "\n Available Actions: "
				+ selectedHero.getActionsAvailable() + "\nAttack Damage: " + selectedHero.getAttackDmg() + "\nCurrent HP: "
				+ selectedHero.getCurrentHp());
				
			}
		);
		
		mapbox.add(cellHero, y, x);
		
		
		
		up.setOnAction(e ->{
			
			try {
				selectedHero.move(Direction.UP);
				GridPane.setRowIndex(cellHero,(14-selectedHero.getLocation().x));
			} catch (MovementException | NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());

				alert.showAndWait();
			}
			
		
	});
down.setOnAction(e ->{
			
			try {
				selectedHero.move(Direction.DOWN);
				cellHero.fireEvent(e);
				GridPane.setRowIndex(cellHero,(14-selectedHero.getLocation().x));
			} catch (MovementException | NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());

				alert.showAndWait();
			}
			
		
	});
left.setOnAction(e ->{
	
	try {
		selectedHero.move(Direction.LEFT);
		GridPane.setColumnIndex(cellHero,selectedHero.getLocation().y);
	} catch (MovementException | NotEnoughActionsException e1) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Move");
		alert.setContentText(e1.getMessage());

		alert.showAndWait();
	}
	

});
right.setOnAction(e ->{
	
	try {
		selectedHero.move(Direction.RIGHT);
		GridPane.setColumnIndex(cellHero,selectedHero.getLocation().y);
	} catch (MovementException | NotEnoughActionsException e1) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Move");
		alert.setContentText(e1.getMessage());

		alert.showAndWait();
	}
	

});
		

		mapbox.setAlignment(Pos.CENTER);

		main.setCenter(mapbox);

		buttons.setHgap(10);
		buttons.setVgap(10);

		exitButton.setOnAction(e -> System.exit(0));

		movement.add(up, 1, 0);
		movement.add(down, 1, 2);
		movement.add(left, 0, 1);
		movement.add(right, 2, 1);
		
		leftside.getChildren().addAll(movement,textbox);

		buttons.add(attack, 0, 1);
		buttons.add(endTurn, 0, 2);
		buttons.add(cure, 0, 3);
		buttons.add(useSpecial, 0, 4);
		buttons.add(exitButton, 0, 5);
		

		main.setRight(buttons);
		main.setLeft(leftside);
		// root1.setBackground(new Background(new BackgroundFill(Color.BLACK,
		// CornerRadii.EMPTY, Insets.EMPTY)));

		mapbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		main.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	

		GameMapScene = new Scene(main);

		GameMapScene.getStylesheets().add(CSS);
	}
	
	
	public Scene getGameMapScene() {
		return GameMapScene;
	}

	public void setGameMapScene(Scene gameMapScene) {
		GameMapScene = gameMapScene;
	}

}