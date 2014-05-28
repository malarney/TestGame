package testLevel;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import levelObjects.Level;

/**
 *
 * @author liem
 */
public class TestLevel extends Level{
    Rectangle protagonist;
    Rectangle background;
    
    
    public TestLevel(){
        super(new Rectangle(6417, 616, new ImagePattern(new Image(TestLevel.class.getResourceAsStream("Level1cropped.png")))),
              new Rectangle(45, 84, new ImagePattern(new Image(TestLevel.class.getResourceAsStream("pro.png")))));
        protagonist = super.getPlayer();
        background = super.getBackground();
        
        protagonist.setLayoutX(60);
        protagonist.setLayoutY(0);
        
        Rectangle floor = new Rectangle();
        floor.setWidth(6417);
        floor.setHeight(40);
        floor.setLayoutY(616-40);
        floor.setLayoutX(0);
        floor.setOpacity(0);
        floor.setFill(Color.BLACK);
        super.addLevelObject(floor);
        
        Rectangle p1 = new Rectangle();
        p1.setWidth(417);
        p1.setHeight(10);
        p1.setLayoutY(616-135);
        p1.setLayoutX(4400+13);
        p1.setOpacity(0);
        p1.setFill(Color.BLACK);
        super.addLevelObject(p1);
        
        Rectangle p2 = new Rectangle();
        p2.setWidth(417);
        p2.setHeight(10);
        p2.setLayoutY(616-254);
        p2.setLayoutX(4400+309);
        p2.setOpacity(0);
        p2.setFill(Color.BLACK);
        super.addLevelObject(p2);
        
        Rectangle p3 = new Rectangle();
        p3.setWidth(417);
        p3.setHeight(10);
        p3.setLayoutY(616-387);
        p3.setLayoutX(4400+627);
        p3.setOpacity(0);
        p3.setFill(Color.BLACK);
        super.addLevelObject(p3);
        
        Rectangle p4 = new Rectangle();
        p4.setWidth(417);
        p4.setHeight(10);
        p4.setLayoutY(616-142);
        p4.setLayoutX(4400+1017);
        p4.setOpacity(0);
        p4.setFill(Color.BLACK);
        super.addLevelObject(p4);
        
        Rectangle p5 = new Rectangle();
        p5.setWidth(417);
        p5.setHeight(10);
        p5.setLayoutY(616-357);
        p5.setLayoutX(4400+1367);
        p5.setOpacity(0);
        p5.setFill(Color.BLACK);
        super.addLevelObject(p5);
        
        super.addPlayer();
    }
    
    @Override
    public void setBackgroundMusic(){
        AudioClip BACKGROUND = new AudioClip(TestLevel.class.getResource("tilt.wav").toString());
        BACKGROUND.play();
    }
    
}
