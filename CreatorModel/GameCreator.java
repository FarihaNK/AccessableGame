package CreatorModel;

import GameModel.AccessibleGame;
import Visualizer.GameVisualizer;
import javafx.scene.AccessibleRole;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameCreator {
    Stage stage;
    TextField rNumber;
    int numRooms;
    Label rNumberLabel;

    public GameCreator(Stage stage) {
        this.stage = stage;
        runUI();
    }

    public void runUI() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);

        Text text = new Text("CHOOSE YOUR GAME");
        text.setX(140);
        text.setY(100);
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        Button tinyGame = new Button("TinyGame");
        tinyGame.setLayoutX(150);
        tinyGame.setLayoutY(170);
        tinyGame.setPrefWidth(300);
        tinyGame.setPrefHeight(70);
        tinyGame.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;");
        root.getChildren().add(tinyGame);
        gameButtonHandler(tinyGame);

        Button mediumGame = new Button("MediumGame");
        mediumGame.setLayoutX(150);
        mediumGame.setLayoutY(270);
        mediumGame.setPrefWidth(300);
        mediumGame.setPrefHeight(70);
        mediumGame.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;");
        root.getChildren().add(mediumGame);
        gameButtonHandler(mediumGame);

        Button createGame = new Button("Create Your Own Game");
        createGame.setLayoutX(150);
        createGame.setLayoutY(370);
        createGame.setPrefWidth(300);
        createGame.setPrefHeight(70);
        createGame.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 17px;");
        root.getChildren().add(createGame);
        createButtonHandler(createGame);

        stage.setScene(scene);
        stage.setTitle("Choose your Accessible Game");
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();
    }

    public void gameButtonHandler(Button gameButton) {
        gameButton.setOnMousePressed(event -> gameButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 17px;"));
        gameButton.setOnMouseReleased(event -> gameButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 17px;"));
        gameButton.setOnAction(event -> {
            AccessibleGame model = new AccessibleGame(gameButton.getText());
            GameVisualizer visualizer = new GameVisualizer(model, stage);
        });
    }

    public void createButtonHandler(Button gameButton) {
        gameButton.setOnMousePressed(event -> gameButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 17px;"));
        gameButton.setOnMouseReleased(event -> gameButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 17px;"));
        gameButton.setOnAction(event -> {
            creatingrNumberPage();
        });
    }

    public void creatingrNumberPage() {
        Group root2 = new Group();
        Scene scene2 = new Scene(root2, Color.DARKGREEN);

        Text text = new Text("How many rooms?");
        text.setX(175);
        text.setY(170);
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.WHITE);
        root2.getChildren().add(text);

        this.rNumberLabel = new Label("You can have a maximum of 20 and a minimum of 2 rooms.");
        rNumberLabel.setLayoutX(110);
        rNumberLabel.setLayoutY(220);
        rNumberLabel.setFont(Font.font("Arial", 15));
        rNumberLabel.setTextFill(Color.WHITE);
        root2.getChildren().add(rNumberLabel);

        this.rNumber = new TextField();
        root2.getChildren().add(rNumber);
        rNumber.setAccessibleRole(AccessibleRole.TEXT_AREA);
        rNumber.setFont(new Font("Arial", 16));
        rNumber.setFocusTraversable(true);
        rNumber.setLayoutX(250);
        rNumber.setLayoutY(300);
        rNumber.setPrefWidth(100);
        rNumber.setPrefHeight(45);
        rNumberTextHandling();

        stage.setScene(scene2);
    }

    public void rNumberTextHandling() {
        rNumber.setOnAction(event -> {
            try {
                int output = Integer.parseInt(rNumber.getText());
                if (2 <= output & output <= 20) {numRooms = output;}
                else {rNumberLabel.setText("Error: Choose a number between 2 and 20.");}
            } catch (Exception e) {rNumberLabel.setText("Error: Choose a number between 2 and 20.");}
        });
    }

}