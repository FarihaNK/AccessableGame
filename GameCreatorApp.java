import CreatorModel.CreatorSignUp;
import CreatorModel.GameCreator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class GameCreatorApp extends Application{

        GameCreator creator;
        CreatorSignUp signUp;
public static void main(String[]args){
        launch(args);
        }

@Override
public void start(Stage stage) throws SQLException {
        //this.creator = new GameCreator(stage);
        this.signUp = new CreatorSignUp(stage);
        }
}
