import javafx.scene.shape.Circle;

/**
 * Created by Evan on 2/22/2017.
 */
public class Ball {

    private Circle ball;

    private double angle;

    private double speed;
    private double maxSpeed;

    public Ball(Circle ball, double maxSpeed) {
        this.ball = ball;
        this.maxSpeed = maxSpeed;
        angle = 0;
        speed = 1;
    }

    public void reset(int player) {
        ball.setCenterX(300);
        ball.setCenterY(300);

        angle = 0;
        speed = (player == 1 ? -1 : 1);
    }

    public Circle getBall() {
        return ball;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getSpeed() {
        return speed;
    }

    public void addSpeed(double ds) {
        setSpeed(speed + ds);
    }

    public void setSpeed(double speed) {
        if(speed > maxSpeed)
            this.speed = maxSpeed;
        else if(speed < -maxSpeed)
            this.speed = -maxSpeed;
        else this.speed = speed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void update(double deltaTime) {
        double dist = speed * deltaTime * 250;

        if(ball.getCenterX() - ball.getRadius() < Pong.LEFT_BOUNDS)
            Pong.pong.addScore(2);
        else if(ball.getCenterX() + ball.getRadius() > Pong.RIGHT_BOUNDS)
            Pong.pong.addScore(1);

        if(ball.getCenterY() - ball.getRadius() < Pong.TOP_BOUNDS ||
           ball.getCenterY() + ball.getRadius() > Pong.BOTTOM_BOUNDS)
            bounce();

        double dx = dist * Math.cos(angle),
               dy = dist * Math.sin(angle);

        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }

    public boolean hit(Paddle paddle) {
        return ball.intersects(paddle.getRectangle().getLayoutBounds());
    }

    public void bounce(Paddle paddle) {
        // bounce off of paddle, some calculated angle
        if(hit(paddle)) {
            //angle = Math.PI - angle; // lame
            boolean right = paddle.getCenterX() < ball.getCenterX();
            angle = ((right ? 0 : Math.PI) - Math.atan( (right ? 1 : -1) * (paddle.getCenterY() - ball.getCenterY()) / paddle.getWidth() )) % (2 * Math.PI);

            setSpeed( Math.abs(paddle.getCenterY() - ball.getCenterY()) / 25 + 1);
        }
    }

    /**
     * For Top and Bottom walls
     */
    public void bounce() {
        angle = -angle;
    }
}
