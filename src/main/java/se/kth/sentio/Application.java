package se.kth.sentio;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import se.kth.sentio.navigation.NavigationPane;
import se.kth.sentio.view.ViewSwitch;
import se.kth.sentio.zoom.ZoomPane;

public class Application extends javafx.application.Application {

    private static String title = "Sentio";
    private static Image icon = new Image("icon.png");
    private static Image drop = new Image("drop.png");

    private static ViewSwitch viewSwitch = new ViewSwitch(drop);
    private static ZoomPane zoomPane = new ZoomPane(viewSwitch);
    private static NavigationPane navigationPane = new NavigationPane(zoomPane);
    private static Scene scene = new Scene(navigationPane);

    static {
        zoomPane.setOnDragOver(event -> {
            event.consume();
            if (event.getDragboard().hasUrl()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });
        zoomPane.setOnDragDropped(event -> {
            event.consume();
            if (event.getDragboard().hasUrl()) {
                String url = event.getDragboard().getUrl().replaceAll(" ", "%20");
                viewSwitch.setUrl(url);
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
