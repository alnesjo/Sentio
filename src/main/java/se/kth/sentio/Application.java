package se.kth.sentio;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    private static String title = "Sentio";
    private static Image icon = new Image("icon.png");
    private static Image drop = new Image("drop.png");
    private static ImageView imageView = new ImageView(drop);
    private static StackPane stackPane = new StackPane(imageView);
    private static Group group = new Group(stackPane);
    private static ScrollPane scrollPane = new ScrollPane(group);
    private static Scene scene = new Scene(scrollPane);

    private static DoubleProperty zoomFactor = new SimpleDoubleProperty(1);

    static {
        imageView.scaleXProperty().bind(zoomFactor);
        imageView.scaleYProperty().bind(zoomFactor);

        stackPane.minWidthProperty().bind(Bindings.createDoubleBinding(
            () -> scrollPane.getViewportBounds().getWidth(),
            scrollPane.viewportBoundsProperty()
        ));

        stackPane.minHeightProperty().bind(Bindings.createDoubleBinding(
            () -> scrollPane.getViewportBounds().getHeight(),
            scrollPane.viewportBoundsProperty()
        ));

        stackPane.setOnScroll(event -> {
            zoomFactor.set(zoomFactor.get() * (0 < event.getDeltaY() ? 1.1 : 1 / 1.1));
            event.consume();
        });

        scrollPane.setPannable(true);

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
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(title);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
