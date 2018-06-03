package se.kth.sentio.content;

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
        setSource(image);
    }

    public MultiView(Media media) {
        this();
        setSource(media);
    }

    public void setSource(String uri) {
        try {
            setSource(new Media(uri));
        } catch (MediaException e) {
            e.printStackTrace();
            setSource(new Image(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSource(Image image) {
        var mediaPlayer = Optional.ofNullable(mediaView.getMediaPlayer());
        mediaPlayer.ifPresent(MediaPlayer::dispose);
        imageView.setImage(image);
        getChildren().setAll(imageView);
    }

    public void setSource(Media media) {
        var mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        setSource(mediaPlayer);
    }

    public void setSource(MediaPlayer mediaPlayer) {
        mediaView.setMediaPlayer(mediaPlayer);
        getChildren().setAll(mediaView);
    }

}
