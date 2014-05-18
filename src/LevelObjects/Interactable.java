package LevelObjects;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Interactable extends Rectangle {

    /**
     * Creates a new Interactable object with a given height, width, and skin
     *
     * @param height The height of the object
     * @param width The width of the object
     * @param skin The skin of the object
     */
    public Interactable(int height, int width, ImagePattern skin) {
        this.setHeight(height);
        this.setWidth(width);
        this.setFill(skin);
    }

    /**
     * Plays a runnable action given to it when interacted with
     *
     * @param run The runnable action
     */
    public void commitAction(Runnable run) {
        run.run();
    }

}
