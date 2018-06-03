package se.kth.sentio.navigation;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

public class NavigationPane extends ScrollPane {

    private DoubleBinding heightBinding = Bindings.createDoubleBinding(
        () -> getViewportBounds().getHeight(),
        viewportBoundsProperty()
    );

    private DoubleBinding widthBinding = Bindings.createDoubleBinding(
        () -> getViewportBounds().getWidth(),
        viewportBoundsProperty()
    );

    private Group viewportGroup = new Group();

    private NavigationPane() {
        setPannable(true);
        setContent(viewportGroup);
    }

    public NavigationPane(Region region) {
        this();
        setViewport(region);
    }

    private void setViewport(Region region) {
        viewportGroup.getChildren().setAll(region);
        region.minWidthProperty().bind(widthBinding);
        region.maxWidthProperty().bind(widthBinding);
        region.minHeightProperty().bind(heightBinding);
        region.maxHeightProperty().bind(heightBinding);
    }

}
