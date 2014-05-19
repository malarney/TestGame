package LevelObjects;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Jump {

    Entity shape;
    double start;
    Timeline action;
    double counter = 1;

    /**
     * Constructs new Jump object that represents a prebuild Timer for
     * simulating jumping
     *
     * @param shape The shape to jump
     * @param intersectables Arraylist of any potential intersectable objects
     */
    public Jump(Entity shape, ArrayList<Interactable> intersectables) {
        this.shape = shape;
        start = shape.getLayoutY();
        action = new Timeline();
        action.getKeyFrames().add(new KeyFrame(Duration.millis(1), (ActionEvent) -> {
            double current = shape.getLayoutY();
            shape.setLayoutY(start + (counter * counter * (1.0 / 2500.0)));
            nextCounter();
            for (Interactable inter : intersectables) {
                Intersection in = Intersection.getIntersection(inter, shape);
                if (in.equals(Intersection.BOTTOM) || in.equals(Intersection.BOTTOM_LEFT) || in.equals(Intersection.BOTTOM_RIGHT)) {
                    shape.setLayoutY(current);
                    resetCounter();
                    action.stop();
                    //Alert Entity jumping has stopped
                }
            }
        }));
        action.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the Timeline for jump
     */
    public void startJump() {
        start = shape.getLayoutY();
        action.play();
    }
/**
 * Stops the Timeline for the jump
 */
    public void stopJump(){
        action.stop();
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
