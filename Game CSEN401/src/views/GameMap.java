package views;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class GameMap {

	private Scene GameMapScene;
	public String CSS = this.getClass().getResource("game map.css").toExternalForm();
	public Hero selectedHero = Game.heroes.get(0);
	Character selectedZombie;
	Hero currentHero;
	private static final int CELL_SIZE = 50;
	Image grass = new Image("Grass.jpg");
	Image flowers = new Image("Flowers.jpg");
	Image zombie = new Image("Zombie.jpg");
	Image hero = new Image("HeroWithABackGround.jpg");
	Image vaccine = new Image("vaccine.jpg");
	Image supply = new Image("supply.jpg");
	Image fog = new Image("fog.jpg");
	private Background grassback = new Background(new BackgroundImage(grass, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background zombieback = new Background(new BackgroundImage(zombie, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background heroback = new Background(new BackgroundImage(hero, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background vaccineback = new Background(new BackgroundImage(vaccine, BackgroundRepeat.REPEAT,
			BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background supplyback = new Background(new BackgroundImage(supply, BackgroundRepeat.REPEAT,
			BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	private Background fogback = new Background(new BackgroundImage(fog, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

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

		Main.window.widthProperty().addListener((observable, oldWidth, newWidth) -> {
			main.setPrefWidth(newWidth.doubleValue());

		});
		Main.window.heightProperty().addListener((observable, oldHeight, newHeight) -> {
			main.setPrefHeight(newHeight.doubleValue());
		});

		mapbox.setGridLinesVisible(true);

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				cell[i][j] = new Cell(i, j);
				cell[i][j].setPrefSize(CELL_SIZE, CELL_SIZE);
				cell[i][j].setMinSize(CELL_SIZE, CELL_SIZE);
				cell[i][j].setMaxSize(CELL_SIZE, CELL_SIZE);
				cell[i][j].setOnMousePressed(e -> {
					Cell clickedCell = (Cell) e.getSource();
					Character chara =clickedCell.c;
					if(chara instanceof Zombie) {
						selectedZombie = clickedCell.c;
						text.setText("\n Type: " + chara.getClass().getSimpleName()
								+"\n CurrentHP: " + chara.getCurrentHp()+"\n"
								+ "Attack Damage: "+ chara.getAttackDmg()
								);
					}
					if(chara instanceof Hero) {
						if((selectedHero != currentHero)) {
						selectedHero = (Hero) clickedCell.c;
						text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
								+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
								+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
										+ "Actions Available: " + selectedHero.getActionsAvailable()
								);
						}
						currentHero = (Hero) clickedCell.c;
						text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
								+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
								+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
										+ "Actions Available: " + selectedHero.getActionsAvailable()
								);
					}
					
					
				});
				mapbox.add(cell[i][j], cell[i][j].y, cell[i][j].x);
			}
		}

		this.updateShownMap();

		up.setOnAction(e -> {

			try {

				selectedHero.move(Direction.UP);
				text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
						+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
						+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
								+ "Actions Available: " + selectedHero.getActionsAvailable()
						);
				this.updateShownMap();

			} catch (MovementException | NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());
				alert.showAndWait();
			}

		});
		down.setOnAction(e -> {

			try {

				selectedHero.move(Direction.DOWN);
				text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
						+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
						+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
								+ "Actions Available: " + selectedHero.getActionsAvailable()
						);
				this.updateShownMap();
			} catch (MovementException | NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());

				alert.showAndWait();
			}

		});
		left.setOnAction(e -> {

			try {

				selectedHero.move(Direction.LEFT);
				text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
						+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
						+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
								+ "Actions Available: " + selectedHero.getActionsAvailable()
						);
				this.updateShownMap();
			} catch (MovementException | NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());

				alert.showAndWait();
			}

		});
		right.setOnAction(e -> {

			try {

				selectedHero.move(Direction.RIGHT);
				text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
						+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
						+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
								+ "Actions Available: " + selectedHero.getActionsAvailable()
						);
				this.updateShownMap();
			} catch (MovementException | NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());

				alert.showAndWait();
			}

		});

		endTurn.setOnAction(e -> {
			Game.endTurn();
			this.updateShownMap();
			text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
					+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
					+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
							+ "Actions Available: " + selectedHero.getActionsAvailable()
					);
		});
		
		attack.setOnAction(e ->{

            try {
            	selectedHero.setTarget(selectedZombie);
                selectedHero.attack();
            	text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
						+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
						+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
								+ "Actions Available: " + selectedHero.getActionsAvailable()
						);
                this.updateShownMap();
            } catch (InvalidTargetException |NotEnoughActionsException e1) {
            	Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());

				alert.showAndWait();
			
            }

    });
		cure.setOnAction(e ->{

            try {
            	selectedHero.setTarget(selectedZombie);
                selectedHero.cure();
            	text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
						+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
						+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
								+ "Actions Available: " + selectedHero.getActionsAvailable()
						);
                this.updateShownMap();
            } catch (InvalidTargetException |NotEnoughActionsException | NoAvailableResourcesException e1) {
            	Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Move");
				alert.setContentText(e1.getMessage());

				alert.showAndWait();
			
            }

    });
		useSpecial.setOnAction(e ->{

            try {
            	selectedHero.setTarget(currentHero);
                selectedHero.useSpecial();
            	text.setText("\n Type: " + selectedHero.getClass().getSimpleName()
						+"\n CurrentHP: " + selectedHero.getCurrentHp()+"\n"
						+ "Attack Damage: "+ selectedHero.getAttackDmg() +"\n"
								+ "Actions Available: " + selectedHero.getActionsAvailable()
						);
                this.updateShownMap();
            } catch (InvalidTargetException |NoAvailableResourcesException e1) {
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

		leftside.getChildren().addAll(movement, textbox);

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

	public void updateShownMap() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (Game.map[i][j].isVisible()) {
					if (Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() == null) {
						// Render as grass cell
						cell[i][j].setBackground(grassback);
					} else if (Game.map[i][j] instanceof CollectibleCell
							&& ((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine) {
						// Render as vaccine cell
						cell[i][j].setBackground(vaccineback);
						cell[i][j].col = ((CollectibleCell) Game.map[i][j]).getCollectible();
					} else if (Game.map[i][j] instanceof CollectibleCell
							&& ((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Supply) {
						// Render as supply cell
						cell[i][j].setBackground(supplyback);
						cell[i][j].col = ((CollectibleCell) Game.map[i][j]).getCollectible();
					} else if (Game.map[i][j] instanceof CharacterCell
							&& ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie) {
						// Render the zombie
						cell[i][j].setBackground(zombieback);
						cell[i][j].c = ((CharacterCell) Game.map[i][j]).getCharacter();
					} else if (Game.map[i][j] instanceof CharacterCell
							&& ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero) {
						// Render the hero by switching over them or whatever
						cell[i][j].setBackground(heroback);
						cell[i][j].c = ((CharacterCell) Game.map[i][j]).getCharacter();
					} else if (Game.map[i][j] instanceof TrapCell) {
						cell[i][j].setBackground(grassback);
					}
				} else {
					cell[i][j].setBackground(fogback);
					// Render as fog
				}
			}
		}
	}

}