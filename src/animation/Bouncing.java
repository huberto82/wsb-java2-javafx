package animation;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

public class Bouncing implements Runnable{
    private Circle ball;
    private Scene root;
    private double deltaX = 2;
    private double deltaY = 2;

    public Bouncing(Circle ball, Scene root, double dx, double dy) {
        this.ball = ball;
        this.root = root;
        deltaX = dx;
        deltaY = dy;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                break;
            }
            double x = ball.getCenterX();
            double y = ball.getCenterY();
            double radius = ball.getRadius();
            if (x + deltaX <= radius || x + deltaX >= root.getWidth()-radius){
                deltaX = -deltaX;
            }
            if (y + deltaY <= radius || y + deltaY >= root.getHeight()-radius){
                deltaY = -deltaY;
            }
            Platform.runLater(()-> {
                ball.setCenterX(x + deltaX);
                ball.setCenterY(y + deltaY);
            });
        }
    }
}
