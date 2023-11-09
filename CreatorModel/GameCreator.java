package CreatorModel;

import GameModel.AccessibleGame;
import Visualizer.GameVisualizer;
import javafx.scene.AccessibleRole;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameCreator {
    Stage stage;
    TextField rNumber;
    int numRooms;
    Label rNumberLabel;
    ArrayList<String> tempinfo = new ArrayList<>();
    TextField roomName;
    TextArea roomDescription;
    TextField numPaths;

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
                if (2 <= output & output <= 20) {numRooms = output; rInfoPage(0);}
                else {rNumberLabel.setText("Error: Choose a number between 2 and 20.");}
            } catch (Exception e) {rNumberLabel.setText("Error: Choose a number between 2 and 20.");}
        });
    }

    public void rInfoPage(int x){
        Group root3 = new Group();
        Scene scene3 = new Scene(root3, Color.WHITE);

        Text text = new Text("Room " + numRooms);
        text.setX(220);
        text.setY(50);
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.BLACK);
        root3.getChildren().add(text);

        Text text2 = new Text("Room Name");
        text2.setX(220);
        text2.setY(100);
        text2.setFont(Font.font("Arial", 20));
        text2.setFill(Color.BLACK);
        root3.getChildren().add(text2);

        this.roomName = new TextField();
        root3.getChildren().add(roomName);
        roomName.setAccessibleRole(AccessibleRole.TEXT_AREA);
        roomName.setFont(new Font("Arial", 15));
        roomName.setFocusTraversable(true);
        roomName.setLayoutX(150);
        roomName.setLayoutY(120);
        roomName.setPrefWidth(300);
        roomName.setPrefHeight(45);

        Text text3 = new Text("Room Description");
        text3.setX(220);
        text3.setY(210);
        text3.setFont(Font.font("Arial", 20));
        text3.setFill(Color.BLACK);
        root3.getChildren().add(text3);

        this.roomDescription = new TextArea();
        root3.getChildren().add(roomDescription);
        roomDescription.setAccessibleRole(AccessibleRole.TEXT_AREA);
        roomDescription.setFont(new Font("Arial", 15));
        roomDescription.setFocusTraversable(true);
        roomDescription.setLayoutX(150);
        roomDescription.setLayoutY(240);
        roomDescription.setPrefWidth(300);
        roomDescription.setPrefHeight(150);


        Text text4 = new Text("Number of Paths");
        text4.setX(220);
        text4.setY(430);
        text4.setFont(Font.font("Arial", 20));
        text4.setFill(Color.BLACK);
        root3.getChildren().add(text4);

        this.numPaths = new TextField();
        root3.getChildren().add(numPaths);
        numPaths.setAccessibleRole(AccessibleRole.TEXT_AREA);
        numPaths.setFont(new Font("Arial", 15));
        numPaths.setFocusTraversable(true);
        numPaths.setLayoutX(150);
        numPaths.setLayoutY(460);
        numPaths.setPrefWidth(300);
        numPaths.setPrefHeight(45);

        Button submitInfoButton = new Button("Submit");
        submitInfoButton.setLayoutX(200);
        submitInfoButton.setLayoutY(520);
        submitInfoButton.setPrefWidth(200);
        submitInfoButton.setPrefHeight(40);
        submitInfoButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;");
        root3.getChildren().add(submitInfoButton);

        submitInfoButton.setOnAction(event -> {
            if (x < numRooms) {
                tempinfo.add(roomName.getText());
                tempinfo.add(roomDescription.getText());
                tempinfo.add(numPaths.getText());
                rInfoPage(x+1);
            }
            else {System.out.println("DONE");} //continue here!
        });

        stage.setScene(scene3);
    }


}