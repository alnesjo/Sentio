package se.kth.sentio.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class ViewSwitch extends Region {

    private MediaView mediaView;
    private ImageView imageView;

    public ViewSwitch(Image image) {
        super();
        imageView = new ImageView(image);
        mediaView = new MediaView();
        getChildren().setAll(imageView);
    }

    public void setUrl(String url) {
        try {
            Media media = new Media(url);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            mediaView.setMediaPlayer(mediaPlayer);
            System.out.println(mediaPlayer.getVolume() + " " + mediaPlayer.isMute());
            getChildren().setAll(mediaView);
        } catch (MediaException e) {
            Image image = new Image(url);
            imageView.setImage(image);
            mediaView.getMediaPlayer().pause();
            getChildren().setAll(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
