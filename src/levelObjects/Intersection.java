package levelObjects;



import javafx.scene.shape.Shape;

public enum Intersection {

    TOP_LEFT, TOP, TOP_RIGHT, LEFT, INSIDE, RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT, NO_INTERSECTION;

    /**
     * Checks through two objects to decide if two objects are intersecting, and
     * if so their orientation
     *
     * @param base The base object to be compared
     * @param entity The entity that is intersecting it
     * @return The orientation of intersection
     */
    public static Intersection getIntersection(Shape base, Shape entity) {
        Shape inter = Shape.intersect(base, entity);
        if (inter.getLayoutBounds().getHeight() <= 0 || inter.getLayoutBounds().getWidth() <= 0) {
            return NO_INTERSECTION;
        } else {
            if (entity.getLayoutX() < base.getLayoutX()) {
                if (entity.getLayoutY() < base.getLayoutY()) {
                    return TOP_LEFT;
                } else if (entity.getLayoutY() + entity.getBoundsInParent().getHeight() > base.getLayoutY() + base.getBoundsInParent().getHeight()) {
                    return BOTTOM_LEFT;
                } else {
                    return LEFT;
                }
            } else if (entity.getLayoutX() + entity.getBoundsInParent().getWidth() > base.getLayoutX() + base.getBoundsInParent().getWidth()) {
                if (entity.getLayoutY() < base.getLayoutY()) {
                    return TOP_RIGHT;
                } else if (entity.getLayoutY() + entity.getBoundsInParent().getHeight() > base.getLayoutY() + base.getBoundsInParent().getHeight()) {
                    return BOTTOM_RIGHT;
                } else {
                    return RIGHT;
                }
            } else {
                if (entity.getLayoutY() < base.getLayoutY()) {
                    return TOP;
                } else if (entity.getLayoutY() + entity.getBoundsInParent().getHeight() > base.getLayoutY() + base.getBoundsInParent().getHeight()) {
                    return BOTTOM;
                } else {
                    return INSIDE;
                }
            }
        }
    }

}
