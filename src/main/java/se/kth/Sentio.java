package se.kth;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Sentio extends Application {

    private static Image icon = new Image("icon.png");
    private static Image drop = new Image("drop.png");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        ImageView imageView = new ImageView(drop);
        BorderPane borderPane = new BorderPane(imageView);
        Scene scene = new Scene(borderPane);

        scene.setOnDragOver(event -> {
            event.consume();
            if (event.getDragboard().hasUrl()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });

        scene.setOnDragDropped(event -> {
            event.consume();
            if (event.getDragboard().hasUrl()) {
                String url = event.getDragboard().getUrl();
                imageView.setImage(new Image(url));
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
        });

        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
        imageView.setPreserveRatio(true);

        stage.setScene(scene);
        stage.setTitle("Sentio");
        stage.getIcons().add(icon);
        stage.sizeToScene();
        stage.show();
    }

}
