package CreatorModel;

import GameModel.AccessibleGame;
import Visualizer.GameVisualizer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameCreator {
    Stage stage;
    TextField rNumber;
    TextField gamenametext;
    int game_id;
    int room_id;
    int numRooms;
    String gamename;
    Label rNumberLabel;
    ArrayList<String> tempinfo = new ArrayList<>();
    ArrayList<Node> nodeList = new ArrayList<>();
    TextField roomName;
    TextArea roomDescription;
    TextField numPaths;
    HashMap<String, Boolean> obj = new HashMap<>();
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameCreatorApp", "root", "Thebookthief100%");
    public GameCreator(Stage stage) throws SQLException {
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
        VBox root2 = new VBox();
        root2.setAlignment(Pos.CENTER);
        root2.setSpacing(30);
        root2.setPadding(new Insets(0,100,0,100));

        Text text = new Text("Game Name");
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.WHITE);
        root2.getChildren().add(text);

        this.gamenametext = new TextField();
        root2.getChildren().add(gamenametext);
        gamenametext.setAccessibleRole(AccessibleRole.TEXT_AREA);
        gamenametext.setFont(new Font("Arial", 16));
        gamenametext.setFocusTraversable(true);
        gamenametext.setPrefHeight(45);

        Text text2 = new Text("How many rooms?");
        text2.setFont(Font.font("Arial", 30));
        text2.setFill(Color.WHITE);
        root2.getChildren().add(text2);

        this.rNumberLabel = new Label("You can have a maximum of 20 and a minimum of 2 rooms.");
        rNumberLabel.setFont(Font.font("Arial", 15));
        rNumberLabel.setTextFill(Color.WHITE);
        rNumberLabel.setWrapText(true);
        root2.getChildren().add(rNumberLabel);

        this.rNumber = new TextField();
        root2.getChildren().add(rNumber);
        rNumber.setAccessibleRole(AccessibleRole.TEXT_AREA);
        rNumber.setFont(new Font("Arial", 16));
        rNumber.setFocusTraversable(true);

        rNumber.setPrefHeight(45);

        Button submitroom = new Button("Submit");
        submitroom.setPrefWidth(200);
        submitroom.setPrefHeight(60);
        submitroom.setStyle("-fx-background-color: #003300; -fx-text-fill: white; -fx-font-size: 17px;");
        root2.getChildren().add(submitroom);
        subroomButtonHandler(submitroom);

        root2.setStyle("-fx-background-color: darkgreen;");
        Scene scene2 = new Scene(root2);
        stage.setScene(scene2);
    }

    public void subroomButtonHandler(Button button) {
            button.setOnMousePressed(event -> button.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 17px;"));
            button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #003300; -fx-text-fill: white; -fx-font-size: 17px;"));
            button.setOnAction(event -> {
                try {
                    int output = Integer.parseInt(rNumber.getText());
                    if ((2 <= output && output <= 20) && !gamenametext.getText().isEmpty()) {
                        numRooms = output;
                        gamename = gamenametext.getText();
                        rInfoPage(0);
                    }
                    else {rNumberLabel.setText("Error: Choose a number between 2 and 20. Game Name is required.");
                        rNumberLabel.setTextFill(Color.ORANGE);}
                } catch (Exception e) {
                    rNumberLabel.setText("Error: Choose a number between 2 and 20. Game Name is required.");
                    rNumberLabel.setTextFill(Color.ORANGE);}
            });
    }

    public void rInfoPage(int x){
        VBox root3 = new VBox();
        root3.setAlignment(Pos.CENTER);
        root3.setSpacing(20);
        root3.setPadding(new Insets(50,100,0,100));
        ScrollPane scrollPane = new ScrollPane(root3);
        scrollPane.setFitToWidth(true);
        Scene scene3 = new Scene(scrollPane, Color.WHITE);

        Text text = new Text("ROOM " + (x+1));
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.BLACK);
        root3.getChildren().add(text);

        Label pathLabel = new Label("Room Name and Number of Paths categories are required. " +
                "Number of paths must be an integer.");
        pathLabel.setFont(Font.font("Arial", 15));
        pathLabel.setWrapText(true);
        pathLabel.setTextFill(Color.BLACK);
        root3.getChildren().add(pathLabel);

        Text text2 = new Text("Room Name");
        text2.setFont(Font.font("Arial", 20));
        text2.setFill(Color.BLACK);
        root3.getChildren().add(text2);

        this.roomName = new TextField();
        root3.getChildren().add(roomName);
        roomName.setAccessibleRole(AccessibleRole.TEXT_AREA);
        roomName.setFont(new Font("Arial", 15));
        roomName.setFocusTraversable(true);
        roomName.setPrefWidth(300);
        roomName.setPrefHeight(45);

        Text text3 = new Text("Room Description");
        text3.setFont(Font.font("Arial", 20));
        text3.setFill(Color.BLACK);
        root3.getChildren().add(text3);

        this.roomDescription = new TextArea();
        root3.getChildren().add(roomDescription);
        roomDescription.setAccessibleRole(AccessibleRole.TEXT_AREA);
        roomDescription.setFont(new Font("Arial", 15));
        roomDescription.setFocusTraversable(true);
        roomDescription.setPrefWidth(300);
        roomDescription.setPrefHeight(150);


        Text text4 = new Text("Number of Paths");
        text4.setFont(Font.font("Arial", 20));
        text4.setFill(Color.BLACK);
        root3.getChildren().add(text4);

        this.numPaths = new TextField();
        root3.getChildren().add(numPaths);
        numPaths.setAccessibleRole(AccessibleRole.TEXT_AREA);
        numPaths.setFont(new Font("Arial", 15));
        numPaths.setFocusTraversable(true);
        numPaths.setPrefWidth(300);
        numPaths.setPrefHeight(45);

        Text text5 = new Text("Objects");
        text5.setFont(Font.font("Arial", 20));
        text5.setFill(Color.BLACK);
        root3.getChildren().add(text5);

        obj.put("BIRD", false);
        obj.put("BOOK", false);
        obj.put("CHEST", false);
        obj.put("COINS", false);
        obj.put("DIAMOND", false);
        obj.put("EGGS", false);
        obj.put("EMERALD", false);
        obj.put("KEYS", false);
        obj.put("LAMP", false);
        obj.put("NUGGET", false);
        obj.put("PLANT", false);
        obj.put("ROD", false);
        obj.put("WATER", false);

        Button[] objbutton = new Button[13];
        FlowPane fp = new FlowPane();
        fp.setAlignment(Pos.CENTER);
        fp.setHgap(10);
        fp.setVgap(10);
        int i = 0;
        for (String k: obj.keySet()) {
            objbutton[i] = new Button(k);
            objbutton[i].setId(k);
            objbutton[i].setPrefWidth(80);
            objbutton[i].setPrefHeight(30);
            objbutton[i].setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 12px;");
            fp.getChildren().add(objbutton[i]);
            objbutton[i].setOnAction(event -> obj.put(k, true));
            int finalI = i;
            objbutton[i].setOnMousePressed(event -> objbutton[finalI].setStyle("-fx-background-color: orange;" +
                    " -fx-text-fill: black; -fx-font-size: 12px;"));
            i++;
        }
        root3.getChildren().add(fp);

        Button submitInfoButton = new Button("Submit");
        submitInfoButton.setPrefWidth(200);
        submitInfoButton.setPrefHeight(40);
        submitInfoButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;");
        root3.getChildren().add(submitInfoButton);

        submitInfoButton.setOnAction(event -> {
            try{if (x < numRooms - 1) {
                tempinfo.add(roomName.getText());
                tempinfo.add(roomDescription.getText());
                tempinfo.add(numPaths.getText());
                ArrayList<String> temp = new ArrayList<>();
                for (Map.Entry<String, Boolean> k : obj.entrySet()){
                    if (k.getValue()) {temp.add(k.getKey());}
                }
                tempinfo.add(temp.toString());
                rInfoPage(x+1);
            }
            else {
                tempinfo.add(roomName.getText());
                tempinfo.add(roomDescription.getText());
                tempinfo.add(numPaths.getText());
                ArrayList<String> temp = new ArrayList<>();
                for (Map.Entry<String, Boolean> k : obj.entrySet()){
                    if (k.getValue()) {temp.add(k.getKey());}
                }
                tempinfo.add(temp.toString());
                pathInfoPage();}
            } catch (Exception e) {pathLabel.setText("Error: Room Name and Number of Paths " +
                    "categories are required. Number of Paths must be an integer.");
                pathLabel.setTextFill(Color.ORANGE);}
        });

        stage.setScene(scene3);
    }

    public void pathInfoPage() {
        VBox root4 = new VBox();
        root4.setAlignment(Pos.CENTER);
        root4.setSpacing(20);
        root4.setPadding(new Insets(50,50,50,50));

        Label label = new Label( "Indicate a possible direction player cant take (i.e. NORTH). " +
                "Indicate the room number of the destination if the path is taken. Click 'Unblocked' " +
                "if the path is blocked and indicate the object required to unblock it.");
        label.setFont(Font.font("Arial", 15));
        label.setWrapText(true);
        label.setTextFill(Color.BLACK);
        root4.getChildren().add(label);

        for (int i = 1; i <= numRooms; i++) {
            Text text = new Text("Room " + i + " Paths");
            text.setId("RoomNum");
            text.setFont(Font.font("Arial", 20));
            text.setFill(Color.BLACK);

            VBox roomContainer = new VBox();
            roomContainer.setAlignment(Pos.CENTER);
            roomContainer.setSpacing(10);
            roomContainer.getChildren().add(text);
            nodeList.add(text);

            for (int k = 0; k < Integer.parseInt(tempinfo.get(4 * i - 2)); k++) {
                HBox row = new HBox();
                row.setAlignment(Pos.CENTER);
                row.setSpacing(20);

                TextField p = new TextField("Direction");
                p.setId("Direction");
                p.setAccessibleRole(AccessibleRole.TEXT_AREA);
                p.setFont(new Font("Arial", 15));
                p.setFocusTraversable(true);
                p.setPrefWidth(140);
                p.setPrefHeight(45);
                nodeList.add(p);

                Text arrow = new Text("→");
                arrow.setFont(new Font("Arial", 15));
                nodeList.add(arrow);

                TextField q = new TextField("Destination");
                q.setId("Destination");
                q.setAccessibleRole(AccessibleRole.TEXT_AREA);
                q.setFont(new Font("Arial", 15));
                q.setFocusTraversable(true);
                q.setPrefWidth(100);
                q.setPrefHeight(45);
                nodeList.add(q);

                Button blockedbutton = new Button("Unblocked");
                blockedbutton.setId("blockbutton");
                blockedbutton.setPrefWidth(80);
                blockedbutton.setPrefHeight(45);
                blockedbutton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 12px;");
                nodeList.add(blockedbutton);
                TextField blocking = new TextField();
                blocking.setId("Object");
                blocking.setAccessibleRole(AccessibleRole.TEXT_AREA);
                blocking.setFont(new Font("Arial", 15));
                blocking.setFocusTraversable(true);
                blocking.setPrefWidth(80);
                blocking.setPrefHeight(45);
                blockedbutton.setOnAction(event -> row.getChildren().add(blocking)); nodeList.add(blocking);
                blockedbutton.setOnMousePressed(event -> {
                    blockedbutton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 12px;");
                    blockedbutton.setText("Blocked");});

                row.getChildren().addAll(p, arrow, q, blockedbutton);

                roomContainer.getChildren().add(row);
            }

            root4.getChildren().add(roomContainer);
        }

        Button submitInfoButton = new Button("Submit");
        submitInfoButton.setPrefWidth(200);
        submitInfoButton.setPrefHeight(40);
        submitInfoButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;");
        root4.getChildren().add(submitInfoButton);
        submitInfoButton.setOnAction(event -> {
            boolean check = true;
            for (Node n: root4.getChildren()) {
                if (n instanceof TextField) {
                    if (((TextField) n).getText().isEmpty()) {check = false;}
                    if (Objects.equals(n.getId(), "Object")) {
                        if (obj.containsKey(((TextField) n).getText())) {check = false;}}
                    if (Objects.equals(n.getId(), "Destination")) {
                        try {Integer.parseInt(((TextField) n).getText());} catch (Exception e) {check = false;}
                    }
                }
            }
            if (!check) {label.setTextFill(Color.ORANGE);}
            else {
                try {
                    submitInfoButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 20px;");
                    updateDatabase(root4);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ScrollPane scrollPane = new ScrollPane(root4);
        scrollPane.setFitToWidth(true);
        stage.setScene(new Scene(scrollPane, Color.WHITE));
    }

    public void updateDatabase(VBox root4) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Games (Game_name, Number_of_rooms) VALUES ('"+gamenametext.getText()+"',"+numRooms+");");
        ps.executeUpdate();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Games;");
        while (resultSet.next()) {
            game_id = Integer.parseInt(resultSet.getString("Game_id"));
        }
        resultSet.close();

        PreparedStatement ps2 = connection.prepareStatement(
                "CREATE TABLE Game_"+ game_id +" (Room_id INT AUTO_INCREMENT PRIMARY KEY, Room_name VARCHAR(100), " +
                        "Room_description VARCHAR(300), Number_of_paths INT, Objects VARCHAR(300));"
        );
        ps2.executeUpdate();
        for (int i=0; i <= tempinfo.size(); i++) {
            if ((i + 1) % 4 == 0) {
                PreparedStatement ps3 = connection.prepareStatement(
                        "INSERT INTO Game_"+ game_id+" (room_name, room_description, Number_of_paths,Objects) VALUES" +
                                "('"+ tempinfo.get(i-3)+ "', '"+ tempinfo.get(i-2)+ "', "+ tempinfo.get(i-1)+",'" +tempinfo.get(i)+"');");
                ps3.executeUpdate();
            }
        }
        int r = 0;
        String di = null;
        int de = 0;
        String o = null;
        boolean b = false;
        for (Node n: nodeList) {
            if (b) {
                b = false;
                if (n instanceof TextField ){
                    o = ((TextField) n).getText();
                    PreparedStatement ps5 = connection.prepareStatement(
                            "INSERT INTO Game_"+ game_id +"_Room_" +r+" (Path_direction, Path_destination, Blocked) VALUES" +
                                    "('"+ di + "', "+ de + ", '"+o+"');");
                    ps5.executeUpdate();
                }
            }
            else if (Objects.equals(n.getId(), "RoomNum")) {
                r++;
                PreparedStatement ps4 = connection.prepareStatement(
                        "CREATE TABLE Game_"+ game_id +"_Room_" +r+" (Path_id INT AUTO_INCREMENT PRIMARY KEY, Path_direction VARCHAR(100), " +
                                "Path_destination INT, Blocked VARCHAR(300));"
                );
                ps4.executeUpdate();
            }
            else if (Objects.equals(n.getId(), "Direction") && n instanceof TextField) {di = ((TextField) n).getText();}
            else if (Objects.equals(n.getId(), "Destination") && n instanceof TextField) {de = Integer.parseInt(((TextField) n).getText());}
            else if (Objects.equals(n.getId(), "blockbutton") && n instanceof Button) {
                if (Objects.equals(((Button) n).getText(), "Blocked")) {b = true;}
                else {o = null;
                    PreparedStatement ps5 = connection.prepareStatement(
                            "INSERT INTO Game_"+ game_id +"_Room_" +r+" (Path_direction, Path_destination, Blocked) VALUES" +
                                    "('"+ di + "', "+ de + ", '"+o+"');");
                    ps5.executeUpdate();
                }
            }
        }
    }


}