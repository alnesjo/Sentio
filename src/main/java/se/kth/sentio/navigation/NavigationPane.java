package se.kth.sentio.navigation;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

public class NavigationPane extends ScrollPane {

    public NavigationPane(Region region) {
        super();

        Group regionGroup = new Group(region);
        setContent(regionGroup);

        DoubleBinding heightBinding = Bindings.createDoubleBinding(
            () -> getViewportBounds().getHeight(),
            viewportBoundsProperty()
        );

        DoubleBinding widthBinding = Bindings.createDoubleBinding(
            () -> getViewportBounds().getWidth(),
            viewportBoundsProperty()
        );

        region.minWidthProperty().bind(widthBinding);
        region.maxWidthProperty().bind(widthBinding);
        region.minHeightProperty().bind(heightBinding);
        region.maxHeightProperty().bind(heightBinding);

        setPannable(true);
    }

}
