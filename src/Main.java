import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        stage.setTitle("Pong");

        Rectangle rect1 = new Rectangle(25, 100, Paint.valueOf("WHITE"));
        rect1.setX(25);

        Rectangle rect2 = new Rectangle(25, 100, Paint.valueOf("WHITE"));
        rect2.setX(575 - rect2.getWidth());

        Circle circle = new Circle(300, 300,10, Paint.valueOf("WHITE"));

        Text text1 = new Text(50, 25,"0"),
             text2 = new Text(550, 25,"0");

        Font font = new Font(20);

        text1.setFont( font );
        text2.setFont( font );

        text1.setFill(Paint.valueOf("WHITE"));
        text2.setFill(Paint.valueOf("WHITE"));

        Group group = new Group();
        Scene scene = new Scene(group, 600, 600, Color.BLACK);

        group.getChildren().addAll(rect1, rect2, circle, text1, text2);

        Pong game = new Pong(rect1, rect2, circle, text1, text2);

        scene.setOnKeyPressed(EasyKey.KEY_PRESS);
        scene.setOnKeyReleased(EasyKey.KEY_RELEASE);

        // game loop yo
        AnimationTimer timer = new AnimationTimer() {
            long lastNanoTime;
            public void handle(long currentNanoTime) {
                if(lastNanoTime == 0) {
                    lastNanoTime = currentNanoTime;
                    return;
                }

                if(game.isEnded())
                    stage.close();

                double deltaTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

                game.update(deltaTime);

                lastNanoTime = currentNanoTime;
            }
        };

        timer.start();

        stage.setOnCloseRequest(e -> Platform.exit());

        stage.setScene(scene);
        stage.show();
        stage.requestFocus();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
