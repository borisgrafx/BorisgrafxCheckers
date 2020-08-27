package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("checkas.png")));
        primaryStage.setTitle("Englishmen's Draughts");
        primaryStage.setScene(new Scene(root, 400, 480));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
