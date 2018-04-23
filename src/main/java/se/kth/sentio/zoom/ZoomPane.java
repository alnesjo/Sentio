package se.kth.sentio.zoom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ZoomPane extends StackPane {

    private double magnitude = 1.1;

    private DoubleProperty scaleProperty = new SimpleDoubleProperty(1);

    public ZoomPane(Node... children) {
        super();

        getChildren().addListener((ListChangeListener<Node>) change -> {
            for (Node child : getChildren()) {
                child.scaleXProperty().bind(scaleProperty);
                child.scaleYProperty().bind(scaleProperty);
            }
        });

        setOnScroll(event -> {
            double k = 0 < event.getDeltaY() ? magnitude : 1 / magnitude;
            scaleProperty.set(scaleProperty.get() * k);
            event.consume();
        });

        getChildren().add(new Group(children));
    }

}
