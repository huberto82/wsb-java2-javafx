package animation;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Transitions extends Application {
    Group root = new Group();
    Scene scene = new Scene(root, 600,400, Color.AQUA);
    Rectangle rectangle = new Rectangle(200,200, 100,100);

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.show();
        root.getChildren().add(rectangle);
        rectangle.setFill(Color.STEELBLUE);

        FadeTransition fade = new FadeTransition(Duration.millis(1000), rectangle);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setCycleCount(Animation.INDEFINITE);
        fade.setAutoReverse(true);

        ScaleTransition scale = new ScaleTransition(Duration.millis(2000), rectangle);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(0.5);
        scale.setToY(0.5);
        scale.setCycleCount(1);


        RotateTransition rotate = new RotateTransition(Duration.millis(3000), rectangle);
        rotate.setToAngle(270);
        rotate.setCycleCount(Animation.INDEFINITE);

        //złożenie trzech animacji, których wykonaie rozpoczyna się w tym samym momencie
        ParallelTransition parallel = new ParallelTransition(rectangle);
        parallel.getChildren().addAll(fade, rotate, scale);

        Timeline move = new Timeline();
        KeyValue value1 = new KeyValue(rectangle.xProperty(), 400);
        KeyFrame key1 = new KeyFrame(Duration.millis(1000), value1);
        move.getKeyFrames().add(key1);

        SequentialTransition sequence = new SequentialTransition();
        sequence.getChildren().addAll(move, parallel);
        sequence.play();
    }
}
