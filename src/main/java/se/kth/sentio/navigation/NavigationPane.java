package se.kth.sentio.navigation;

import javafx.beans.binding.Bindings;
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

        zoomPane.minWidthProperty().bind(Bindings.createDoubleBinding(
            () -> getViewportBounds().getWidth(),
            viewportBoundsProperty()
        ));

        zoomPane.minHeightProperty().bind(Bindings.createDoubleBinding(
            () -> getViewportBounds().getHeight(),
            viewportBoundsProperty()
        ));

        setPannable(true);
    }

}
