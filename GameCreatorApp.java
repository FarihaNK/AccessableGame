import CreatorModel.GameCreator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class GameCreatorApp extends Application{

        GameCreator creator;
public static void main(String[]args){
        launch(args);
        }

@Override
public void start(Stage stage) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameCreatorApp", "user", "password");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rooms (idRoom, RoomName) VALUES (4, 'room5');");
        preparedStatement.executeUpdate();
        Statement statement2 = connection.createStatement();
        ResultSet resultSet = statement2.executeQuery("SELECT * FROM rooms;");
        while (resultSet.next()) {
                System.out.println(resultSet.getString("RoomName"));
        }

        resultSet.close();

        this.creator = new GameCreator(stage);
        }
}
