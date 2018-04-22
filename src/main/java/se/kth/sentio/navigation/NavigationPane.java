package se.kth.sentio.navigation;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import se.kth.sentio.navigation.zoom.ZoomPane;

public class NavigationPane extends ScrollPane {

    public NavigationPane(Node node) {
        super();

        ZoomPane zoomPane = new ZoomPane(node);
        Group zoomGroup = new Group(zoomPane);
        setContent(zoomGroup);

        DoubleBinding heightBinding = Bindings.createDoubleBinding(
            () -> getViewportBounds().getHeight(),
            viewportBoundsProperty()
        );

        DoubleBinding widthBinding = Bindings.createDoubleBinding(
            () -> getViewportBounds().getWidth(),
            viewportBoundsProperty()
        );

        zoomPane.minWidthProperty().bind(widthBinding);
        zoomPane.maxWidthProperty().bind(widthBinding);
        zoomPane.minHeightProperty().bind(heightBinding);
        zoomPane.maxHeightProperty().bind(heightBinding);

        setPannable(true);
    }

}
