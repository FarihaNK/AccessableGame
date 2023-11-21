import CreatorModel.CreatorSignUp;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class GameCreatorApp extends Application{
        CreatorSignUp signUp;
public static void main(String[]args){
        launch(args);
        }

@Override
public void start(Stage stage) throws SQLException {
        this.signUp = new CreatorSignUp(stage);
        }
}
