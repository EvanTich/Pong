import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evan Tichenor (evan.tichenor@gmail.com)
 * @version 1.0, 12/10/2016
 */
public class EasyKey {

    private static List<KeyCode> keys;

    static {
        keys = new ArrayList<>();
    }

    public static final EventHandler<KeyEvent> KEY_PRESS = event -> {
        if (!keys.contains(event.getCode()))
            keys.add(event.getCode());
    };

    public static final EventHandler<KeyEvent> KEY_RELEASE = event -> {
        keys.remove(event.getCode());
    };

    public static boolean keyPressed(KeyCode... codes) {
        for(KeyCode key : codes)
            if(keys.contains(key))
                return true;
        return false;
    }

    /**
     * Used to fix direction bug for the snake.
     */
    public static void emptyKeys() {
        keys.clear();
    }

    public static List<KeyCode> getKeys() {
        return keys;
    }

    public static boolean noPress() {
        return keys.isEmpty();
    }

}
