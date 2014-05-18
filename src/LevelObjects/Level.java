package LevelObjects;

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Level extends AnchorPane {

    private ArrayList<Entity> entities;
    private ArrayList<Interactable> interactables;
    private Rectangle background;
    private Player player;

    /**
     * Creates a new level with a specified background and player
     *
     * @param background The background to be added
     * @param player The player to be added
     */
    public Level(Rectangle background, Player player) {
        this.background = background;
        this.player = player;
        this.getChildren().add(background);
        this.entities = new ArrayList<>();
        this.interactables = new ArrayList<>();
    }

    /**
     * Adds a new Entity to the level
     *
     * @param entity The entity to be added
     */
    public void addEntity(Entity entity) {
        this.getChildren().add(entity);
        entities.add(entity);
    }

    /**
     * Adds a new Interactable to the level
     *
     * @param interactable The interactable to be added
     */
    public void addInteractable(Interactable interactable) {
        this.getChildren().add(0, interactable);
        interactables.add(interactable);
    }

    /**
     * Sets the background of the level
     *
     * @param background
     */
    public void setBack(Rectangle background) {
        this.background = background;
    }

    /**
     * Gets the background of the level
     *
     * @return The background of the level
     */
    public Rectangle getBack() {
        return background;
    }

    /**
     * Sets the player of the level
     *
     * @param player The new player of the level
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the player of the level
     *
     * @return The player of the level
     */
    public Player getPlayer() {
        return player;
    }

}
