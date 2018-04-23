package se.kth.sentio;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import se.kth.sentio.navigation.NavigationPane;
import se.kth.sentio.zoom.ZoomPane;

public class Application extends javafx.application.Application {

    private static String title = "Sentio";
    private static Image icon = new Image("icon.png");
    private static Image drop = new Image("drop.png");

    private static ImageView imageView = new ImageView(drop);
    private static MediaView mediaView = new MediaView();
    private static Group viewGroup = new Group(imageView);

    private static ZoomPane zoomPane = new ZoomPane(viewGroup);
    private static NavigationPane navigationPane = new NavigationPane(zoomPane);
    private static Scene scene = new Scene(navigationPane);

    static {
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
                try {
                    Media media = new Media(url.replaceAll(" ", "%20"));
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                    mediaPlayer.play();
                    mediaView.setMediaPlayer(mediaPlayer);
                    viewGroup.getChildren().setAll(mediaView);
                } catch (MediaException e) {
                    Image image = new Image(url);
                    imageView.setImage(image);
                    mediaView.getMediaPlayer().pause();
                    viewGroup.getChildren().setAll(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
