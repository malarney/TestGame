/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Ascension extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();

        TestLevel level = new TestLevel();
        Scene scene = new Scene(level, 800, 616);

        Rectangle floor = new Rectangle();
        floor.setHeight(10);
        floor.setWidth(3310);
        floor.setOpacity(0);
        floor.setLayoutX(0);
        floor.setLayoutY(580);

        primaryStage.setTitle("Ascension Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    double startY = 0.0;
    double x = 0;

    public double getNext() {
        x += 1.0;
        return x;
    }

    public void reset() {
        x = 0.0;
    }

    public static boolean isIntersecting(Shape p1, Shape p2) {
        Shape inter = Shape.intersect(p1, p2);
        return !(inter.getLayoutBounds().getHeight() <= 0 || inter.getLayoutBounds().getWidth() <= 0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
