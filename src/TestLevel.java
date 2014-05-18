
import LevelObjects.Level;
import LevelObjects.Player;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class TestLevel extends Level {

    public TestLevel() {
        super(new Rectangle(3310, 616, new ImagePattern(new Image(TestLevel.class.getResourceAsStream("Assets/Bg1.png")))), new Player(84, 45, new ImagePattern(new Image(TestLevel.class.getResourceAsStream("Assets/pro.png")))));
    }
}
