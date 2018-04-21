package se.kth;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;

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
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        scene.setOnDragDropped(event -> {
            if (event.getDragboard().hasFiles()) {
                event.getDragboard().getFiles().stream()
                    .map(File::toURI)
                    .map(URI::toString)
                    .map(Image::new)
                    .findFirst()
                    .ifPresent(imageView::setImage);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
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
