package se.kth.sentio.zoom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ZoomPane extends StackPane {

    private double magnitude = 1.1;

    private DoubleProperty scaleProperty = new SimpleDoubleProperty(1);

    public ZoomPane(Node... children) {
        super();

        Group scaleGroup = new Group(children);
        getChildren().add(scaleGroup);

        scaleGroup.scaleXProperty().bind(scaleProperty);
        scaleGroup.scaleYProperty().bind(scaleProperty);

        setOnScroll(event -> {
            double k = 0 < event.getDeltaY() ? magnitude : 1 / magnitude;
            scaleProperty.set(scaleProperty.get() * k);
            event.consume();
        });
    }
}
