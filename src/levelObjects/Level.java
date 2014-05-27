package levelObjects;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * @version 2.0
 * @author Liem Malarney
 */
public class Level extends Scene{
    
    /*Constructor Methods*/
    
    
    /**
     * Constructs a new Level object from a background and a player
     * @param background The background Rectangle to be used
     * @param player The player Rectangle to be used
     */
    public Level(Rectangle background, Rectangle player){
        super(new AnchorPane(), 800, 615);//Creates a new Window
        anchor=(AnchorPane)super.getRoot();//Sets AnchorPane to variable for later use
        levelObjects = new ArrayList<>();//Initializes levelObjects using Diamond Notation
        this.background=background;//Initializes background with parameter
        anchor.getChildren().add(background);//Adds background to parent, and to scene
        this.player=player;//Initializes player with parameter
        setTimers();//Sets the Timers using method for organization
        setKeyEvents();//Sets the KeyEvents using method for organization
        setBackgroundMusic();//Sets the background music
    }
    
    
    /*Public Level Builder Methods*/
    
    
    /**
     * Adds a shape that can be added to the Level
     * @param levelObject The shape to be added
     */
    public void addLevelObject(Shape levelObject){
        anchor.getChildren().add(levelObject);//Adds level object to scene
        levelObjects.add(levelObject);//Adds level object to list
    }
    /**
     * Adds the player to the level
     */
    public void addPlayer(){
        anchor.getChildren().add(player);//Adds the player to the scene
    }
    /**
     * Get the level's player object
     * @return The level's player object
     */
    public Rectangle getPlayer(){
        return player;
    }
    /**
     * Gets the level's background
     * @return The level's background
     */
    public Rectangle getBackground(){
        return background;
        }
    
    
    /*Code Reuse Utility Methods*/
    
    
    /**
     * 
     * @param shape The shape to be compared with
     * @param flagged An array of the flagged intersections
     * @return Returns if a flagged intersection has occurred
     */
    private boolean flaggedIntersection(Shape shape, Intersection[] flagged){
        for(Shape levelObject:levelObjects){//Goes through each of the levelObjects
            Intersection i = Intersection.getIntersection(levelObject, shape);//Checks object's intersections
            for(Intersection inter: flagged){//Goes through each flagged intersection
                if(i.equals(inter))return true;//If a flagged one is outputted returns true
            }
        }
        return false;//Otherwise returns false
    } 
    
    /**
     * Moves objects in a direction given by an Intersection Enum Variable
     * @param in Intersection Value, no effect if not Left or Right
     */
    private void moveObjects(Intersection in){
        for(Shape levelObject: levelObjects){//Cycles through each levelObject
            //Moves the object in the appropriate direction
            if(in.equals(Intersection.LEFT))levelObject.setLayoutX(levelObject.getLayoutX()+1);
            if(in.equals(Intersection.RIGHT))levelObject.setLayoutX(levelObject.getLayoutX()-1);
        }
        //Moves the background in the appropriate direction
        if(in.equals(Intersection.LEFT))background.setLayoutX(background.getLayoutX()+1);
        if(in.equals(Intersection.RIGHT))background.setLayoutX(background.getLayoutX()-1);
    }

    
    
  
    
    /*Construction Methods*/
    
    /**
     * Empty method for overriding, sets background music
     */
    public void setBackgroundMusic(){}
    
    
    /**
     * Sets the KeyEvents of the Level
     */
    public void setKeyEvents(){
        //Sets the Key Press events to start movement timers
        this.setOnKeyPressed((KeyEvent event)->{//Lambda Expression for Key Events
                 if(event.getCode()==KeyCode.A||event.getCode()==KeyCode.LEFT)left.play();
                 if(event.getCode()==KeyCode.D||event.getCode()==KeyCode.RIGHT)right.play();
                 if((event.getCode()==KeyCode.W||event.getCode()==KeyCode.UP||event.getCode()==KeyCode.SPACE)&&canJump){jump.play();jumpFalse();}
        });
        //Sets the Key Press events to stop non-jump movement timers
        this.setOnKeyReleased((KeyEvent event)->{//Lambda Expression for Key Events
                 if(event.getCode()==KeyCode.A||event.getCode()==KeyCode.LEFT)left.stop();
                 if(event.getCode()==KeyCode.D||event.getCode()==KeyCode.RIGHT)right.stop();
        });
    }  
    
    /**
     * Sets each of the Timelines (similar to KBasic Timers) to do a specific task
     */
    public void setTimers(){
        //Instantiates each of the Timelines before defining their behavior
        jump = new Timeline(); 
        gravity = new Timeline();
        right = new Timeline();
        left = new Timeline();
        gravCheck = new Timeline();
        
        /*
            Gravity Timeline works by using a parabolic equation from the start to simulate falling
            This equation is (StartPosition)+(time^2)*(2/1875)
            The plus is because addition to the Y is downard movement
            The 2/1875 makes it move 600 pixels in 3/4 of a second, which felt smooth
            It uses the Intersection flag for landing, and on intersection with an object stops itself
            As well as gives the player the ability to jump again
        */
        gravity.getKeyFrames().add(new KeyFrame(Duration.millis(1), (ActionEvent)->{
            player.setLayoutY(start + (counter * counter * (2.0 / 1875.0)));
            nextCounter();
            boolean intersect = flaggedIntersection(player, LAND);
            if(intersect){
                resetCounter(player.getLayoutY());
                jumpTrue();
                gravCheck.play();
                gravity.stop();

            }
        }));
        gravity.setCycleCount(Timeline.INDEFINITE);
        
        /*
            Jump uses the same equation as Gravity, but flipped and shifted
            This gives it the same arch, but makes it slow down to until it reaches +150 its original height
            This happens at 375 seconds, and if it does, or a flagged intersection hits, it stops and lets gravity play
        */
        jump.getKeyFrames().add(new KeyFrame(Duration.millis(1), (ActionEvent)->{
            player.setLayoutY(start - ((counter-375.0)*(counter-375.0)*(-2.0/1875.0)+150));
            nextCounter();
            if(((counter-375.0)*(counter-375.0)*(-2.0/1875.0)+150)==150||flaggedIntersection(player, CEILING)){////Collision Check
                resetCounter(player.getLayoutY());
                jump.stop();
                gravity.play();
                
            }
        }));
        jump.setCycleCount(Timeline.INDEFINITE);
        
        /*
            Left and Right both use the same general idea for movement
            The player himself moves around the screen until reaching the middle (400) mark
            After this the moveObject method is called instead, and shifts all elements across instead
            If intersections are found after the move, the move is reversed
         */
        right.getKeyFrames().add(new KeyFrame(Duration.millis(2),
                 (ActionEvent event)->{
                     if(background.getLayoutX()<=0&&player.getLayoutX()<=400){
                     player.setLayoutX(player.getLayoutX()+1);
                     if(flaggedIntersection(player, HITRIGHT))player.setLayoutX(player.getLayoutX()-1);
                     }else if((-1*background.getLayoutX())+800<background.getWidth()){
                     moveObjects(Intersection.RIGHT);
                     if(flaggedIntersection(player, HITRIGHT))moveObjects(Intersection.LEFT);
                     }else{
                     player.setLayoutX(player.getLayoutX()+1);
                     if(flaggedIntersection(player, HITRIGHT))player.setLayoutX(player.getLayoutX()-1);
                     }
                 }));
        right.setCycleCount(Timeline.INDEFINITE);      
        left.getKeyFrames().add(new KeyFrame(Duration.millis(2),
                 (ActionEvent event)->{
                     if((-1*background.getLayoutX())+800>=background.getWidth()&&player.getLayoutX()>400){
                     player.setLayoutX(player.getLayoutX()-1);
                     if(flaggedIntersection(player, HITLEFT))player.setLayoutX(player.getLayoutX()+1);
                     }else if(background.getLayoutX()==0){
                     player.setLayoutX(player.getLayoutX()-1);
                     if(flaggedIntersection(player, HITLEFT))player.setLayoutX(player.getLayoutX()+1);
                     }else{
                     moveObjects(Intersection.LEFT);
                     if(flaggedIntersection(player, HITLEFT))moveObjects(Intersection.RIGHT);
                     }
                 }));
        left.setCycleCount(Timeline.INDEFINITE);
        
        /*
            Grav Check runs constantly through the program and checks if the player is ever both:
                -Not intersecting objects via LAND (Effectively in the Air)
                -Not currently jumping
            If both these condition are met, it starts gravity, and stops the player from being able to jump
        */
        gravCheck.getKeyFrames().add(new KeyFrame(Duration.millis(2),
                (ActionEvent event) -> {
                    if(!flaggedIntersection(player, LAND)&&jump.currentRateProperty().getValue()==0.0){gravity.play();jumpFalse();resetCounter(player.getLayoutY());gravCheck.stop();}
                    }));
        gravCheck.setCycleCount(Timeline.INDEFINITE);
        
        //Starts gravity temporarily and  gravCheck 
        gravity.play();
        gravCheck.play();
    }   
    
    /*Private Lambda Work Around Methods*/
    
    /**
     * Adds one the x value for the Jump and Gravity
     */
    private void nextCounter(){
        counter+=1;//Method is shared as neither runs at the same time.
    } 
    /**
     * Resets the counter and updates the player's elevation
     * @param start The player's elevation 
     */
    private void resetCounter(double start){
        counter=1;
        this.start=start;
    }
    
    /**
     * Sets the canJump variable to false, exists to get around only final variables
     */
    void jumpFalse(){
        canJump = false;
    }  
    /**
     * Sets the canJump variable to false, exists to get around only final variables
     */
    private void jumpTrue(){
        canJump = true;
    }
    
    /*Variable Declaration*/
    
    /*Private Lambda Work Around Variables*/
    private double start;
    private double counter = 1.0;
    private boolean canJump;

    /*Private Utility Variables*/
    private final Rectangle background;
    private final AnchorPane anchor;
    private final ArrayList<Shape> levelObjects;
    private final Rectangle player;
    private Timeline jump, gravity, left, right, gravCheck;
    
    /*Private Final Intersection Sets*/
    private final Intersection[] LAND ={Intersection.TOP, Intersection.TOP_LEFT, Intersection.TOP_RIGHT};
    private final Intersection[] CEILING ={Intersection.BOTTOM, Intersection.BOTTOM_LEFT, Intersection.BOTTOM_RIGHT};
    private final Intersection[] HITRIGHT ={Intersection.LEFT, Intersection.TOP_LEFT, Intersection.BOTTOM_LEFT};
    private final Intersection[] HITLEFT ={Intersection.RIGHT, Intersection.TOP_RIGHT, Intersection.BOTTOM_RIGHT};
    
}
