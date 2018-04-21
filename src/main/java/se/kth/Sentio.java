package se.kth;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

public class Sentio extends Application {

    private static Image drop = new Image("drop.png");
    private static Image icon = new Image("icon.png");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        ImageView imageView = new ImageView(drop);
        BorderPane borderPane = new BorderPane(imageView);
        Scene scene = new Scene(borderPane);

        stage.setScene(scene);
        stage.setTitle("Sentio");
        stage.getIcons().add(icon);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

}
