package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Rectangle rectangle = new Rectangle(100, 100, 100, 100);
        root.getChildren().add(rectangle);
        rectangle.setFill(Color.valueOf("#FF00AA"));
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(4);

        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);

        rectangle.setRotate(45);

        Circle circle = new Circle(200, 200, 50);
        root.getChildren().add(circle);
        circle.setFill(Color.VIOLET);

        Line line = new Line(0,0, 100,100);
        line.setStroke(Color.LIGHTGREEN);
        line.setStrokeWidth(4);
        root.getChildren().add(line);

        CubicCurve curve = new CubicCurve(0,0,120,20, 240,140, 200, 200);
        curve.setStrokeWidth(5);
        curve.setFill(Color.TRANSPARENT);
        curve.setStroke(Color.BLACK);
        root.getChildren().add(curve);

        Path path = new Path();
        path.getElements().addAll(
            new MoveTo(50,100),
            new LineTo(100,150),
            new CubicCurveTo(0,200, 150, 300, 200, 200),
            new LineTo(50,100)
        );
        path.setFill(Color.CRIMSON);
        root.getChildren().add(path);

        Path arrow = Shapes.arrow(50,50,150);
        root.getChildren().add(arrow);
        arrow.setRotate(90);

        Text napis = new Text(10, 40, "Hello");
        root.getChildren().add(napis);
        napis.setFont(Font.font("Calibri", 48));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
