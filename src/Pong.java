import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by Evan on 2/22/2017.
 */
public class Pong {

    public static Pong pong;

    public static final double TOP_BOUNDS = 0, BOTTOM_BOUNDS = 600;
    public static final double LEFT_BOUNDS = 0, RIGHT_BOUNDS = 600;

    private Paddle player1, player2;
    private Ball ball;

    private Text player1Text, player2Text;

    private boolean ended;

    public Pong(Paddle player1, Paddle player2, Ball ball, Text text1, Text text2) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;
        player1Text = text1;
        player2Text = text2;

        pong = this;
        ended = false;
    }

    public Pong(Rectangle rect1, Rectangle rect2, Circle circle, Text text1, Text text2) {
        this.player1 = new Paddle(rect1);
        this.player2 = new PaddleAI(rect2);
        this.ball = new Ball(circle, 5);
        player1Text = text1;
        player2Text = text2;

        pong = this;
        ended = false;
    }

    public void update(double deltaTime) {
        keys();

        ball.update(deltaTime);
        ball.bounce(player1);
        ball.bounce(player2);

        if(player1.isRobot())
            player1.update(deltaTime);
        if(player2.isRobot())
            player2.update(deltaTime);

        player1Text.setText(player1.getScore() + "");
        player2Text.setText(player2.getScore() + "");
    }

    public void keys() {
        if(!player1.isRobot()) {
            if (EasyKey.keyPressed(KeyCode.UP) && player1.getCenterY() - player1.getHeight() / 2 > TOP_BOUNDS)
                player1.moveUp();
            else if (EasyKey.keyPressed(KeyCode.DOWN) && player1.getCenterY() + player1.getHeight() / 2 < BOTTOM_BOUNDS)
                player1.moveDown();
        }

        if(!player2.isRobot()) {
            if (EasyKey.keyPressed(KeyCode.W) && player2.getCenterY() - player2.getHeight() / 2 > TOP_BOUNDS)
                player2.moveUp();
            else if (EasyKey.keyPressed(KeyCode.S) && player2.getCenterY() + player2.getHeight() / 2 < BOTTOM_BOUNDS)
                player2.moveDown();
        }

        // TODO: make pause screen, able to exit from there
        if(EasyKey.keyPressed(KeyCode.ESCAPE))
            endGame();
    }

    public void addScore(int player) {
        if(player == 1)
            player1.addScore(1);
        else if(player == 2)
            player2.addScore(1);

        ball.reset(player);
    }

    public Paddle getPlayer1() {
        return player1;
    }

    public Paddle getPlayer2() {
        return player2;
    }

    public Ball getBall() {
        return ball;
    }

    public void endGame() {
        ended = true;
    }

    public boolean isEnded() {
        return ended;
    }
}
