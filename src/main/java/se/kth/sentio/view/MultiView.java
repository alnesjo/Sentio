package se.kth.sentio.view;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.Optional;

public class MultiView extends Parent {

    private final ImageView imageView = new ImageView();
    private final MediaView mediaView = new MediaView();

    public MultiView() {
        imageView.setCache(true);
        imageView.setSmooth(true);
        mediaView.setCache(true);
        mediaView.setSmooth(true);
    }

    public MultiView(Image image) {
        this();
        setView(image);
    }

    public MultiView(Media media) {
        this();
        setView(media);
    }

    public void setView(String uri) {
        try {
            setView(new Media(uri));
        } catch (MediaException e) {
            setView(new Image(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setView(Image image) {
        var mediaPlayer = Optional.ofNullable(mediaView.getMediaPlayer());
        mediaPlayer.ifPresent(MediaPlayer::dispose);
        imageView.setImage(image);
        getChildren().setAll(imageView);
    }

    public void setView(Media media) {
        var mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        setView(mediaPlayer);
    }

    public void setView(MediaPlayer mediaPlayer) {
        mediaView.setMediaPlayer(mediaPlayer);
        getChildren().setAll(mediaView);
    }

}
