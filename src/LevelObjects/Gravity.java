package LevelObjects;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Gravity {

    Entity shape;
    double start;
    Timeline action;
    double counter = 1;

    /**
     * Constructs new Gravity object that represents a prebuild Timer for
     * simulating gravity
     *
     * @param shape The shape to fall
     * @param intersectables Arraylist of any potential intersectable objects
     */
    public Gravity(Entity shape, ArrayList<Interactable> intersectables) {
        this.shape = shape;
        start = shape.getLayoutY();
        action = new Timeline();
        action.getKeyFrames().add(new KeyFrame(Duration.millis(1), (ActionEvent) -> {
            double current = shape.getLayoutY();
            shape.setLayoutY(start + (counter * counter * (1.0 / 2500.0)));
            nextCounter();
            for (Interactable inter : intersectables) {
                Intersection in = Intersection.getIntersection(inter, shape);
                if (in.equals(Intersection.TOP) || in.equals(Intersection.TOP_LEFT) || in.equals(Intersection.TOP_RIGHT)) {
                    shape.setLayoutY(current);
                    resetCounter();
                    action.stop();
                    //Alert Entity gravity has stopped
                }
            }
        }));
        action.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the Timeline for gravity
     */
    public void startGravity() {
        start = shape.getLayoutY();
        action.play();
    }

    /**
     * For adding to variable whilst keeping it effectively final
     */
    private void nextCounter() {
        counter += 1;
    }

    /**
     * For reseting variable whilst keeping it effectively final
     */
    private void resetCounter() {
        counter = 0;
    }

}
