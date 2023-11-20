package CreatorModel;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.FileWriter;
import java.util.ArrayList;

public class CreatedGamePlayer{
    Stage stage;
    int game_id;
    public CreatedGamePlayer(Stage stage){
        this.stage = stage;
        runUI();
    }
    public void runUI(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root);

        Text text = new Text("Do you want to share your game?");
        text.setFont(Font.font("Arial", 25));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        HBox yesnoButtons = new HBox();
        yesnoButtons.setAlignment(Pos.CENTER);
        yesnoButtons.setSpacing(20);
        root.getChildren().add(yesnoButtons);

        Button yesButton = new Button("Yes");
        yesButton.setPrefWidth(80);
        yesButton.setPrefHeight(50);
        yesButton.setTextFill(Color.WHITE);
        yesButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 15px;");
        yesnoButtons.getChildren().add(yesButton);

        Button noButton = new Button("No");
        noButton.setPrefWidth(80);
        noButton.setPrefHeight(50);
        noButton.setTextFill(Color.WHITE);
        noButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 15px;");
        yesnoButtons.getChildren().add(noButton);

        Text text2 = new Text("Click to play");
        text2.setFont(Font.font("Arial", 25));
        text2.setFill(Color.WHITE);
        root.getChildren().add(text2);

        Button playButton = new Button("Play Game");
        playButton.setPrefWidth(300);
        playButton.setPrefHeight(70);
        playButton.setTextFill(Color.WHITE);
        playButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 25px;");
        root.getChildren().add(playButton);
        playgameHandler(playButton);

        stage.setScene(scene);
        stage.setTitle("PlayGame");
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();
    }

    public void playgameHandler(Button playButton){
        playButton.setOnMousePressed(event -> playButton.setStyle("-fx-background-color: orange; -fx-text-fill: white;-fx-font-size: 25px;"));
        playButton.setOnMouseReleased(event -> playButton.setStyle("-fx-background-color: green; -fx-text-fill: white;-fx-font-size: 25px;"));
        playButton.setOnAction(event -> {
            writeGame(1, 4);
        });
    }

    public ArrayList<ArrayList<?>> objectList(){
        ArrayList<ArrayList<?>> output = new ArrayList<>();

        return output;
    }
     public void writeGame(int game_id, int number_of_rooms) {

        String roomsPath = "Games/rooms.txt";
         try {
             FileWriter fileWriter = new FileWriter(roomsPath);
             int r = 1;
             while (r <= number_of_rooms) {
                 fileWriter.write(r+ "\n");
                 fileWriter.write("roomname\n");
                 fileWriter.write("roomdescription\n");
                 fileWriter.write("-----\n");
                 fileWriter.write("paths\n\n");
                 r++;
             } fileWriter.close();
         } catch (Exception e) {
             //TODO: handle exception
         }

         String objPath = "Games/objects.txt";
         try {
             FileWriter fileWriter = new FileWriter(objPath);
             int r = 1;
             while (r <= number_of_rooms) {
                 fileWriter.write(r+ "\n");
                 fileWriter.write("roomname\n");
                 fileWriter.write("roomdescription\n");
                 fileWriter.write("-----\n");
                 fileWriter.write("paths\n\n");
                 r++;
             } fileWriter.close();
         } catch (Exception e) {
             //TODO: handle exception
         }
     }
}