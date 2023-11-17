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
        this.creator = new GameCreator(stage);
        }
}
