import javafx.scene.control.Skin;

/**
 * Progress indicator showing a circle that fills
 * 
 * @author Andrea Vacondio
 *
 */
public class FillProgressIndicator extends ProgressCircleIndicator {

    public FillProgressIndicator() {
        this.getStylesheets().add(RingProgressIndicator.class.getResource("IndicatorStyle/fillprogress.css").toExternalForm());
        this.getStyleClass().add("fillindicator");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new FillProgressIndicatorSkin(this);
    }
}
