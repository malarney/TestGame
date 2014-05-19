package LevelObjects;

import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Entity extends Rectangle {

    private Timeline left, right;
    private Gravity gravity;
    private Jump jump;
    BooleanProperty isJumping;
    BooleanProperty canDrop;
    
    /**
     * Constructs a new Entity object (for example a player of bad guy)
     *
     * @param height The height of the Entity
     * @param width The width of the Entity
     * @param skin The skin the Entity will wear
     */
    public Entity(int height, int width, ImagePattern skin) {
        this.setHeight(height);
        this.setWidth(width);
        this.setFill(skin);
//        canDrop.addListener((ChangeListener)->{if(canDrop.getValue()==true&&isJumping.getValue()==false)gravity.startGravity();});
        
    }   
    
    /**
     * Replaces the current Timeline for going left with the given one
     *
     * @param left The new Timeline
     */
    public void setLeft(Timeline left) {
        this.left = left;
    }

    /**
     * Gets the current Timeline for going left
     *
     * @return The current Timeline for going left
     */
    public Timeline getLeft() {
        return this.left;
    }

    /**
     * Starts the Timeline for going left
     */
    public void startLeft() {
        left.play();
    }

    /**
     * Stops the Timeline for going left
     */
    public void stopLeft() {
        left.stop();
    }

    /**
     * Replaces the current Timeline for going right with the given one
     *
     * @param right The new Timeline
     */
    public void setRight(Timeline right) {
        this.right = right;
    }

    /**
     * Gets the current Timeline for going right
     *
     * @return The current Timeline for going right
     */
    public Timeline getRight() {
        return this.right;
    }

    /**
     * Starts the Timeline for going right
     */
    public void startRight() {
        right.play();
    }

    /**
     * Stops the Timeline for going right
     */
    public void stopRight() {
        right.stop();
    }

    /**
     * Replaces the current Timeline for jumping with the given one
     *
     * @param jump The new Timeline
     */
    public void setJump(Jump jump) {
        this.jump = jump;
    }

    /**
     * Get the current Timeline for jumping
     *
     * @return The current Timeline for jumping
     */
    public Jump getJump() {
        return this.jump;
    }

    /**
     * Starts the Timeline for jumping
     */
    public void startJump() {
        jump.startJump();
    }

    /**
     * Stops the Timeline for jumping
     */
    public void stopJump() {
        jump.stopJump();
    }

    /**
     * Replaces the current Timeline for going down with the given one
     *
     * @param down The new Timeline
     */
    public void setDown(Gravity down) {
        this.gravity = down;
    }

    /**
     * Gets the current Timeline for going down
     *
     * @return The current Timeline for going down
     */
    public Gravity getGravity() {
        return this.gravity;
    }

    /**
     * Starts the Timeline for going down
     */
    public void startGravity() {
        gravity.startGravity();
    }

    /**
     * Stops the Timeline for going down
     */
    public void stopDown() {
        gravity.stopGravity();
    }
}
