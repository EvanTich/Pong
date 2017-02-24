import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Created by Evan on 2/22/2017.
 */
public class PaddleAI extends Paddle {

    public PaddleAI(Rectangle rect) {
        super(rect); // super rekt
        robot = true;
    }

    @Override
    public void update(double deltaTime) {
        Circle ball = Pong.pong.getBall().getBall();

        if(ball.getCenterY() > getCenterY() && getCenterY() + getHeight() / 2 < Pong.BOTTOM_BOUNDS)
            moveDown();
        else if(ball.getCenterY() < getCenterY() && getCenterY() - getHeight() / 2 > Pong.TOP_BOUNDS)
            moveUp();

    }
}
