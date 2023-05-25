package views;

import engine.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import model.characters.Hero;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HeroPick {
	private Scene Heropick;
	private final String condition1 = "Your mission if you choose to accept it \n"
			+ "To survive, Collect all the Vaccines and cure the Zombies \n"
			+ "Your poplulation must exceed 5 Heroes to survive this apoclypse";

	private final IntegerProperty i = new SimpleIntegerProperty(0);
	private final Timeline timeline = new Timeline();
	public String CSS = this.getClass().getResource("hero pick scene.css").toExternalForm();
	public Hero startingHero;

	private final String csvFilepath = "C:\\Users\\youus\\eclipse-workspace\\git repository\\CSEN401-Game\\Game CSEN401\\src\\Heros.csv";

	public HeroPick() {
		
	
		BorderPane borderPane = new BorderPane();
		BorderPane controls = new BorderPane();
		BorderPane vBox = new BorderPane();
		GridPane gridPane = new GridPane();
		StackPane root = new StackPane();
		TextFlow textFlow = new TextFlow();
		Text condition = new Text(condition1);
		Button startButton = new Button("Start");
		Button backButton = new Button("Back");
		Button exitButton = new Button("Exit");
		
		Main.window.widthProperty().addListener((observable, oldWidth, newWidth)->{
			root.setPrefWidth(newWidth.doubleValue());
			
		});
		Main.window.heightProperty().addListener((observable, oldHeight, newHeight)->{
			root.setPrefHeight(newHeight.doubleValue());
		});

		try {
			Game.loadHeroes(csvFilepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Text heroData = new Text();
		Text special = new Text("\n *please select a hero*");
		heroData.setTextAlignment(TextAlignment.CENTER);
		condition.setTextAlignment(TextAlignment.CENTER);
		special.setTextAlignment(TextAlignment.CENTER);
		textFlow.setTextAlignment(TextAlignment.CENTER);
		textFlow.setPrefSize(100, 200);
		condition.setStyle("-fx-font-weight: bold");

		textFlow.getChildren().add(condition);
		textFlow.getChildren().add(heroData);
		textFlow.getChildren().add(special);

		exitButton.setOnAction(event -> System.exit(0));

		controls.setLeft(startButton);
		controls.setRight(backButton);
		controls.setCenter(exitButton);

		vBox.setMaxSize(1000, 1000);
		vBox.setCenter(textFlow);

		vBox.setBottom(controls);

		vBox.setOnMouseEntered(event -> {
			vBox.setStyle("-fx-border-color: red;");
		});

		vBox.setOnMouseExited(event -> {
			vBox.setStyle(null);
		});

		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridPane.setHgap(10);
		gridPane.setVgap(5);
		gridPane.setPrefSize(Main.window.getWidth(), 100);
		gridPane.autosize();

		int column = 0;
		int row = 0;
		for (Hero hero : Game.availableHeroes) {
			Button button = new Button();

			switch (hero.getName()) {
			case ("Joel Miller"):
				button.setGraphic(new ImageView(new Image("Joel Icon.jpg")));
				break;
			case ("Ellie Williams"):
				button.setGraphic(new ImageView(new Image("Ellie Icon.jpg")));
				break;
			case ("Tess"):
				button.setGraphic(new ImageView(new Image("Tess Icon.jpg")));
				break;
			case ("Riley Abel"):
				button.setGraphic(new ImageView(new Image("Riley Icon.jpg")));
				break;
			case ("Tommy Miller"):
				button.setGraphic(new ImageView(new Image("Tommy Icon.jpg")));
				break;
			case ("Bill"):
				button.setGraphic(new ImageView(new Image("Bill Icon.jpg")));
				break;
			case ("David"):
				button.setGraphic(new ImageView(new Image("David Icon.jpg")));
				break;
			case ("Henry Burell"):
				button.setGraphic(new ImageView(new Image("Henry Icon.jpg")));
				break;
			}

			button.setText(hero.getName());
			button.setAlignment(Pos.CENTER);
			gridPane.add(button, column, row);
			column++;

			button.setOnAction(e -> {
				startingHero = hero;
				switch (hero.getClass().getSimpleName().toString()) {
				case ("Fighter"):
					special.setText("\nFor 1 Turn and 1 Supply can attack infinitly ");
					break;
				case ("Medic"):
					special.setText("\nFor 1 Turn can heal, each heal consumes 1 supply ");
					break;
				case ("Explorer"):
					special.setText("\nFor 1 Turn and 1 Supply the Whole map is Visible ");
					break;
				default:
					special.setText("\nplease select a hero");
				}

				String Data = "\nHero Type: " + hero.getClass().getSimpleName().toString() + "\n Max Actions: "
						+ hero.getMaxActions() + "\nAttack Damage: " + hero.getAttackDmg() + "\nMax HP: "
						+ hero.getMaxHp();
				heroData.setText(Data);

			});

			button.setContentDisplay(ContentDisplay.TOP);
			button.setPrefSize(150, 100);
			if (column == 4) {
				column = 0;
				row++;
			}
		}

		KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), event -> {
			if (i.get() > condition1.length()) {
				timeline.stop();
			} else {
				condition.setText(condition1.substring(0, i.get()));
				i.set(i.get() + 1);
			}
		});
		timeline.getKeyFrames().add(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		gridPane.setOnMouseEntered(event -> {
			gridPane.setStyle("-fx-border-color: red;");
		});

		gridPane.setOnMouseExited(event -> {
			gridPane.setStyle(null);
		});

		gridPane.setAlignment(Pos.CENTER);

		vBox.setPrefSize(700, 250);
		borderPane.setCenter(gridPane);

		borderPane.setBottom(vBox);

		borderPane.setMaxHeight(1000);
		borderPane.setMaxWidth(1500);

		borderPane.setAlignment(vBox, Pos.CENTER);

		root.getChildren().add(borderPane);

		// root.setAlignment(Pos.CENTER);
		// root.setLayoutX(0);
		// root.setLayoutY(0);
		// root.setMinSize(Main.window.getWidth(), Main.window.getHeight());

		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		// border.setStyle("fx-background-color: black;");
		// border.setBackground(new Background(new BackgroundFill(Color.BLACK,
		// CornerRadii.EMPTY, Insets.EMPTY)));

		startButton.setOnAction(e -> {

			try {
				Game(startingHero);
			} catch (NullPointerException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Hero Select");
				alert.setHeaderText("Look, an Error Dialog");
				alert.setContentText("Please select a hero");

				alert.showAndWait();
				
			}
			Main.gameMap = new GameMap();
			Main.window.setScene(Main.gameMap.getGameMapScene());
			Main.window.setFullScreen(false);
			Main.window.setFullScreen(true);
			
		});
		backButton.setOnAction(e -> {
			Main.window.setScene(Main.startMenu.getScene());
			Main.window.setFullScreen(false);
			Main.window.setFullScreen(true);
		});
		root.setMinHeight(Main.window.getHeight());
		root.setMinWidth(Main.window.getWidth());
		Heropick = new Scene(root, Main.window.getHeight(), Main.window.getWidth());

		Main.window.setFullScreen(true);
		Heropick.getStylesheets().add(CSS);

	}

	public Scene getHeroPick() {
		return Heropick;
	}

	public void setHeroPick(Scene heroPick) {
		Heropick = heroPick;
	}

	public static void Game(Hero h) throws NullPointerException {
		if (h == null) {
			throw new NullPointerException("no hero selected");
		}
		Game.startGame(h);
	}

}