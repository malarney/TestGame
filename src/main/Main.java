
package main;

import javafx.application.Application;
import javafx.stage.Stage;
import testLevel.TestLevel;

/**
 *
 * @author liem
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        TestLevel level1 = new TestLevel();
        primaryStage.setTitle("TestLevel");
        primaryStage.setScene(level1);
        primaryStage.show();
    }
    
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        launch(args);
    }

    
}
