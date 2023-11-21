package CreatorModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

public class CreatorSignUp {
    Stage stage;
    GameCreator creator;
    String myUsername;
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameCreatorApp", "root", "Thebookthief100%");
    public CreatorSignUp(Stage stage) throws SQLException {
        this.stage = stage;
        runUI();
    }

    public void runUI(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        root.setStyle("-fx-background-color: darkorange;");
        Scene scene = new Scene(root);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setPrefWidth(300);
        signUpButton.setPrefHeight(100);
        signUpButton.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 25px;");
        root.getChildren().add(signUpButton);
        signUpHandler(signUpButton);

        Button logInButton = new Button("Log In");
        logInButton.setPrefWidth(300);
        logInButton.setPrefHeight(100);
        logInButton.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 25px;");
        root.getChildren().add(logInButton);
        logInHandler(logInButton);

        stage.setScene(scene);
        stage.setTitle("SignIn");
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();
    }

    public void signUpHandler(Button button){
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 25px;"));
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 25px;"));
        button.setOnAction(event -> {
            signUpPage();
        });
    }
    public void logInHandler(Button button){
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 25px;"));
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 25px;"));
        button.setOnAction(event -> {
            logInPage();
        });
    }
    public void signUpPage(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        root.setPadding(new Insets(100,100,100,100));
        root.setStyle("-fx-background-color: white;");
        Scene scene = new Scene(root);

        Text text = new Text("Sign Up");
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.BLACK);
        root.getChildren().add(text);

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", 20));
        usernameLabel.setTextFill(Color.BLACK);
        root.getChildren().add(usernameLabel);
        usernameLabel.setWrapText(true);

        TextField usernameTextfeild = new TextField("Type here");
        root.getChildren().add(usernameTextfeild);
        usernameTextfeild.setAccessibleRole(AccessibleRole.TEXT_AREA);
        usernameTextfeild.setFont(new Font("Arial", 20));
        usernameTextfeild.setFocusTraversable(true);
        usernameTextfeild.setPrefHeight(50);
        usernameTextfeild.setStyle("-fx-background-color: lightgreen;");

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", 20));
        passwordLabel.setTextFill(Color.BLACK);
        root.getChildren().add(passwordLabel);

        TextField passwordTextfeild = new TextField("Type here");
        root.getChildren().add(passwordTextfeild);
        passwordTextfeild.setAccessibleRole(AccessibleRole.TEXT_AREA);
        passwordTextfeild.setFont(new Font("Arial", 20));
        passwordTextfeild.setFocusTraversable(true);
        passwordTextfeild.setPrefHeight(50);
        passwordTextfeild.setStyle("-fx-background-color: lightgreen;");

        Button submitButton = new Button("Submit");
        submitButton.setPrefWidth(200);
        submitButton.setPrefHeight(60);
        submitButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 17px;");
        root.getChildren().add(submitButton);
        submitButton.setOnMousePressed(event -> submitButton.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 17px;"));
        submitButton.setOnMouseReleased(event -> submitButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 17px;"));
        submitButton.setOnAction(event -> {
            try{
                if (usernameTextfeild.getText().length() < 4 || passwordTextfeild.getText().length() < 4){
                    usernameLabel.setText("Username and Password must be at least 4 characters long.");}
                else {
                    PreparedStatement ps = connection.prepareStatement("SELECT Username FROM Users;");
                    ResultSet resultSet = ps.executeQuery();
                    boolean unique = true;
                    while (resultSet.next()){
                        String username = resultSet.getString("Username");
                        if (Objects.equals(usernameTextfeild.getText(), username)) {unique = false;}
                    }
                    if (unique) {
                        this.myUsername = usernameTextfeild.getText();
                        PreparedStatement ps2 = connection.prepareStatement(
                                "INSERT INTO Users VALUES ('"+myUsername+"', '"+passwordTextfeild.getText()+"');");
                        ps2.executeUpdate();
                        this.creator = new GameCreator(stage, myUsername);
                    }
                    else {usernameLabel.setText("This Username is taken.");}
                }
            }catch (Exception e){usernameLabel.setText("Error");}
        });


        stage.setScene(scene);
    }

    public void logInPage(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        root.setPadding(new Insets(100,100,100,100));
        root.setStyle("-fx-background-color: white;");
        Scene scene = new Scene(root);

        Text text = new Text("Log In");
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.BLACK);
        root.getChildren().add(text);

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", 20));
        usernameLabel.setTextFill(Color.BLACK);
        root.getChildren().add(usernameLabel);
        usernameLabel.setWrapText(true);

        TextField usernameTextfeild = new TextField("Type here");
        root.getChildren().add(usernameTextfeild);
        usernameTextfeild.setAccessibleRole(AccessibleRole.TEXT_AREA);
        usernameTextfeild.setFont(new Font("Arial", 20));
        usernameTextfeild.setFocusTraversable(true);
        usernameTextfeild.setPrefHeight(50);
        usernameTextfeild.setStyle("-fx-background-color: lightgreen;");

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", 20));
        passwordLabel.setTextFill(Color.BLACK);
        root.getChildren().add(passwordLabel);

        TextField passwordTextfeild = new TextField("Type here");
        root.getChildren().add(passwordTextfeild);
        passwordTextfeild.setAccessibleRole(AccessibleRole.TEXT_AREA);
        passwordTextfeild.setFont(new Font("Arial", 20));
        passwordTextfeild.setFocusTraversable(true);
        passwordTextfeild.setPrefHeight(50);
        passwordTextfeild.setStyle("-fx-background-color: lightgreen;");

        Button submitButton = new Button("Submit");
        submitButton.setPrefWidth(200);
        submitButton.setPrefHeight(60);
        submitButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 17px;");
        root.getChildren().add(submitButton);
        submitButton.setOnMousePressed(event -> submitButton.setStyle("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 17px;"));
        submitButton.setOnMouseReleased(event -> submitButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 17px;"));
        submitButton.setOnAction(event -> {
            try{
                if (usernameTextfeild.getText().isEmpty() || passwordTextfeild.getText().isEmpty()){
                    usernameLabel.setText("Please provide Username and Password.");}
                else {
                    PreparedStatement ps = connection.prepareStatement("SELECT Username, Password FROM Users;");
                    ResultSet resultSet = ps.executeQuery();
                    boolean found = false;
                    while (resultSet.next()){
                        String username = resultSet.getString("Username");
                        String password = resultSet.getString("Password");
                        if (Objects.equals(usernameTextfeild.getText(), username)) {
                            found = true;
                            if (Objects.equals(passwordTextfeild.getText(), password)) {
                                myUsername = usernameTextfeild.getText();
                                this.creator = new GameCreator(stage, myUsername);
                            } else {usernameLabel.setText("Password is incorrect.");}
                        }}
                    if (!found) {usernameLabel.setText("Username does not exist.");}
                }
            }catch (Exception e){usernameLabel.setText("Error");}
        });

        stage.setScene(scene);
    }
}