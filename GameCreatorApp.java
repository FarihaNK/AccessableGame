import CreatorModel.GameCreator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameCreatorApp extends Application{

        GameCreator creator;
public static void main(String[]args){
        launch(args);
        }

@Override
public void start(Stage stage){
        this.creator = new GameCreator(stage);
        }
}
