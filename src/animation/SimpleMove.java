package animation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleMove extends Application {
    Rectangle rectangle = new Rectangle(0, 200, 100, 100);

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600,400, Color.AQUA);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animacja");
        primaryStage.show();
        root.getChildren().add(rectangle);

        ExecutorService service = Executors.newCachedThreadPool();
        Runnable moveAnimation = () -> {
            int steps = 20;
            while (steps-- > 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    rectangle.setX(rectangle.getX() + 5);
                });
            }
        };
        service.execute(moveAnimation);
    }
}
