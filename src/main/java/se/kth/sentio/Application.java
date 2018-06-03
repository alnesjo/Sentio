package se.kth.sentio;

import se.kth.sentio.navigation.PannablePane;
import se.kth.sentio.content.MultiView;
import se.kth.sentio.navigation.ZoomPane;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Application extends javafx.application.Application {

    private static final String title = "Sentio";
    private static final Image icon = new Image("icon.png");
    private static final Image drop = new Image("drop.png");

    private final MultiView multiView = new MultiView(drop);
    private final ZoomPane zoomPane = new ZoomPane(multiView);
    private final PannablePane pannablePane = new PannablePane(zoomPane);
    private final Scene scene = new Scene(pannablePane);

    public Application() {
        zoomPane.setOnDragOver(event -> {
            event.consume();
            if (event.getDragboard().hasUrl()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });
        zoomPane.setOnDragDropped(event -> {
            event.consume();
            if (event.getDragboard().hasUrl()) {
                try {
                    var url = new URL(event.getDragboard().getUrl());
                    var file = new File(url.getFile());
                    var uri = file.toURI().toString();
                    multiView.setSource(uri);
                    event.setDropCompleted(true);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    event.setDropCompleted(false);
                }
            } else {
                event.setDropCompleted(false);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(title);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

}
