package CreatorModel;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameCreator {
    Stage stage;

    public GameCreator(Stage stage) {
        this.stage = stage;
        runUI();
    }

    public void runUI() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);

        Text text = new Text("CHOOSE YOUR GAME");
        text.setX(135);
        text.setY(100);
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        stage.setScene(scene);
        stage.setTitle("Choose your Accessible Game");
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();
    }

}