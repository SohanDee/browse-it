package browse.it;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/scene/MainScene.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Browse-it");
        primaryStage.setFullScreen(true);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
