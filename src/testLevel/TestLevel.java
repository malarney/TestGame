package testLevel;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import levelObjects.Level;

/**
 *
 * @author liem
 */
public class TestLevel extends Level{
    Rectangle protagonist;
    Rectangle background;
    Rectangle obstacle;
    
    public TestLevel(){
        super(new Rectangle(3310, 616, new ImagePattern(new Image(TestLevel.class.getResourceAsStream("Bg1.png")))),
              new Rectangle(45, 84, new ImagePattern(new Image(TestLevel.class.getResourceAsStream("pro.png")))));
        protagonist = super.getPlayer();
        background = super.getBackground();
        
        protagonist.setLayoutX(60);
        protagonist.setLayoutY(0);
        
        Rectangle floor = new Rectangle();
        floor.setWidth(3310);
        floor.setHeight(40);
        floor.setLayoutY(615-40);
        floor.setLayoutX(0);
        floor.setOpacity(0);
        floor.setFill(Color.BLACK);
        super.addLevelObject(floor);
        
        super.addPlayer();
    }
    
    @Override
    public void setBackgroundMusic(){
        AudioClip BACKGROUND = new AudioClip(TestLevel.class.getResource("tilt.wav").toString());
        BACKGROUND.play();
    }
    
}
