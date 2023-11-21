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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class CreatedGamePlayer{
    Stage stage;
    Scene scene;
    int game_id;
    int number_of_rooms;
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameCreatorApp", "root", "Thebookthief100%");
    String gamename;
    HashMap<String, String> objectInfo = new HashMap<>();

    public CreatedGamePlayer(Stage stage, String gamename) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT Game_id FROM Games WHERE Game_name = '"+gamename+"';");
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        int x = resultSet.getInt("Game_id");

        this.gamename = gamename;
        this.game_id = x;
        this.stage = stage;
        setObjectInfo();
        runUI();
    }

    private void setObjectInfo(){
        objectInfo.put("KEYS", "a set of keys");
        objectInfo.put("LAMP", "a brightly shining brass lamp");
        objectInfo.put("ROD", "a black rod with a rusty star");
        objectInfo.put("BIRD", "a water bird");
        objectInfo.put("NUGGET", "a nugget of gold");
        objectInfo.put("DIAMOND", "a sparkling diamond");
        objectInfo.put("COINS", "a bag of coins");
        objectInfo.put("EMERALD", "an emerald the size of a plover's egg");
        objectInfo.put("EGGS", "a nest of golden eggs");
        objectInfo.put("WATER", "a bottle of water");
        objectInfo.put("PLANT", "a small plant murmuring 'Water, water'");
        objectInfo.put("CHEST", "a pirate chest");
        objectInfo.put("BOOK", "a copy of an illuminated manuscript");
    }

    public void runUI(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root);
        this.scene = scene;

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
        noButton.setOnMouseClicked(event -> {
            noButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 15px;");
            yesButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 15px;");});
        yesButton.setOnMouseClicked(event -> {
            yesButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 15px;");
            noButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 15px;");});

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
            try {
                writeRooms();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

     public void writeRooms() throws SQLException, IOException {
         PreparedStatement ps1 = connection.prepareStatement("SELECT Number_of_rooms FROM Games WHERE Game_id = "+game_id+";");
         ResultSet resultSet1 = ps1.executeQuery();
         resultSet1.next();

         int x = resultSet1.getInt("Number_of_rooms");
        this.number_of_rooms = x;

         File gamenameFile = new File("Games/"+gamename+"/");
         gamenameFile.mkdirs();

         String roomsPath = "Games/"+gamename+"/rooms.txt";
         try {
             FileWriter fileWriter = new FileWriter(roomsPath);
             int r = 1;
             while (r <= number_of_rooms) {
                 fileWriter.write(r+ "\n");

                 PreparedStatement ps2 = connection.prepareStatement("SELECT Room_name FROM Game_"+game_id+" WHERE Room_id = "+r+";");
                 ResultSet resultSet2 = ps2.executeQuery();
                 resultSet2.next();
                 String a = resultSet2.getString("Room_name");

                 fileWriter.write(a+"\n");

                 PreparedStatement ps3 = connection.prepareStatement("SELECT Room_description FROM Game_"+game_id+" WHERE Room_id = "+r+";");
                 ResultSet resultSet3 = ps3.executeQuery();
                 resultSet3.next();
                 String b = resultSet3.getString("Room_description");

                 fileWriter.write(b+"\n");
                 fileWriter.write("-----\n");

                 PreparedStatement ps4 = connection.prepareStatement("SELECT Number_of_paths FROM Game_"+game_id+" WHERE Room_id = "+r+";");
                 ResultSet resultSet4 = ps4.executeQuery();
                 resultSet4.next();
                 int c = resultSet4.getInt("Number_of_paths");
                 int p = 1;

                 while (p <= c){
                     PreparedStatement ps5 = connection.prepareStatement("SELECT Path_direction, Path_destination, Blocked FROM Game_"+game_id+"_Room_"+r+" WHERE Path_id = "+p+";");
                     ResultSet resultSet5 = ps5.executeQuery();
                     resultSet5.next();
                     String d = resultSet5.getString("Path_direction");
                     int e = resultSet5.getInt("Path_destination");
                     String f = resultSet5.getString("Blocked");
                     if (!Objects.equals(f, "null")){fileWriter.write(d+"     "+e+"/"+f+"\n");}
                     else {fileWriter.write(d+"     "+e+"\n");}
                     p++;
                 }

                 fileWriter.write("\n");
                 r++;
             }
             fileWriter.write("21\n");
             fileWriter.write("Victory\n");
             fileWriter.write("You have collected all the treasures and are admitted to the Adventurer's Hall of Fame. Congratulations!\n");
             fileWriter.write("-----\n");
             fileWriter.write("FORCED     0\n");
             fileWriter.write("\n");

             fileWriter.close();
         } catch (Exception e) {
             System.out.println(this.scene.getRoot().getChildrenUnmodifiable().add(
                     new Text("There was an error with your game, restart the application.")));
         }
         writeObjects();
     }

     public void writeObjects() throws SQLException, IOException {
         String objPath = "Games/"+gamename+"/objects.txt";
         try {
             int r = 1;
             FileWriter fileWriter = new FileWriter(objPath);
             while (r <= number_of_rooms) {
                 PreparedStatement ps1 = connection.prepareStatement("SELECT Objects FROM Game_"+game_id+" WHERE Room_id = "+r+";");
                 ResultSet resultSet1 = ps1.executeQuery();
                 resultSet1.next();
                 String a = resultSet1.getString("Objects");
                 String[] elements = a.replaceAll("[\\[\\]]", "").split(", ");
                 ArrayList<String> b = new ArrayList<>(Arrays.asList(elements));

                 for (String obj: b) {
                     fileWriter.write(obj+"\n");
                     fileWriter.write(objectInfo.get(obj) + "\n");
                     fileWriter.write(r+"\n\n");
                 }

                 r++;

             } fileWriter.close();
         } catch (Exception e) {
             System.out.println(this.scene.getRoot().getChildrenUnmodifiable().add(
                     new Text("There was an error with your game, restart the application.")));
         }
         writeHelp();
    }
    public void writeHelp() throws IOException {
        String helpPath = "Games/"+gamename+"/help.txt";
        FileWriter fileWriter = new FileWriter(helpPath);
        fileWriter.write(
                """
                        To play this game you must move between locations and interact with objects by typing one or two word commands.

                        Some commands are motion commands.  These will move you from room to room. Motion commands include:

                        UP, DOWN, EAST, WEST, NORTH, SOUTH

                        Not all motions are possible in every room. In addition, some rooms may have "special" or "secret" motion commands.

                        There are other action commands in the game. These include:

                        COMMANDS: this will print the moves that are legal in a given room.
                        HELP: this will display instructions
                        INVENTORY: this will print your current inventory.
                        LOOK: this will print the description for the current room.
                        TAKE <object>: this will take an object from a room and place it in your inventory. Replace <object> with the name of the object to take.  The object must be present in the room in order to take it.
                        DROP <object>: this will drop an object in your inventory. Replace <object> with the name of the object to drop. The object must be in your inventory to drop it.

                        Some paths may be blocked.  To unblock a path you may need a specific object to be in your inventory.

                        The game is over when your player reaches the VICTORY room, or when your player DIES.

                        """
        );
        fileWriter.close();
    }
}