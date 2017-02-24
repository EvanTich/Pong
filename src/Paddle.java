import javafx.scene.shape.Rectangle;

/**
 * Created by Evan on 2/22/2017.
 */
public class Paddle {

    protected Rectangle rect;

    protected boolean robot;

    private int score;

    public Paddle(Rectangle rect) {
        this.rect = rect;
        robot = false;
        score = 0;
    }

    public double getCenterY() {
        return rect.getY() + rect.getHeight() / 2;
    }

    public double getCenterX() {
        return rect.getX() + rect.getWidth() / 2;
    }

    public Rectangle getRectangle() {
        return rect;
    }

    public double getWidth() {
        return rect.getWidth();
    }

    public double getHeight() {
        return rect.getHeight();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int s) {
        score += s;
    }

    public void update(double deltaTime) {
        // to be done... (in ai)
    }

    public void moveUp() {
        move(-2);
    }

    public void moveDown() {
        move(2);
    }

    public boolean isRobot() {
        return robot;
    }

    public void move(double delta) {
        rect.setY(rect.getY() + delta);
    }
}
