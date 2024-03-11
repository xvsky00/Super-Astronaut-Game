package Game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    public static final int BLOCK_SIZE = 45;
    public static final int PLAYER_SIZE = 64;
    public static RetryWindow retryWindow;
    public static WinWindow winWindow;
    public static World world;
    public static Menu menu;

    @Override
    public void start(Stage window) {
        world = new World();
        world.prepare(window);
        menu = new Menu(window);
        retryWindow = new RetryWindow(window);
        winWindow = new WinWindow(window);
        menu.start();
        window.setTitle("Super Astronaut Game");
        window.setHeight(15 * BLOCK_SIZE - 6);
        window.setWidth(1000);
        window.show();
    }

    public static void main(String[] args) {
        launch(Game.class);
    }
}