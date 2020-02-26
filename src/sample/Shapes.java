package sample;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

final public class Shapes {
    private Shapes(){
    }

    public static Path arrow(double x, double y, double size){
        Path arrow = new Path();
        arrow.getElements().addAll(
            new MoveTo(x ,y),
            new LineTo(x + size, y),
            new MoveTo(x, y),
            new LineTo(x + size/4, y - size/4),
            new MoveTo(x, y),
            new LineTo(x + size/4, y + size/4)
        );
        return arrow;
    }
}
