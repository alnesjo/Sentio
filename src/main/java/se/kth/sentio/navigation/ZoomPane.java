package se.kth.sentio.navigation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;


public class ZoomPane extends StackPane {

    private static final double magnitude = Math.pow(2, 1.0/8);

    private final DoubleProperty scaleProperty = new SimpleDoubleProperty(1);

    private Group zoomGroup = new Group();

    private ZoomPane() {
        setOnScroll(scroll -> {
            var k = 0 < scroll.getDeltaY() ? magnitude : 1.0 / magnitude;
            scaleProperty.set(scaleProperty.get() * k);
            scroll.consume();
        });
        getChildren().addAll(zoomGroup);
        zoomGroup.scaleXProperty().bind(scaleProperty);
        zoomGroup.scaleYProperty().bind(scaleProperty);
        // TODO Forward children to group by change listener
    }

    public ZoomPane(Node... children) {
        this();
        zoomGroup.getChildren().addAll(children);
    }
}
