package Game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    public static final int BLOCK_SIZE = 45;
    public static final int PLAYER_SIZE = 64;

    @Override
    public void start(Stage window) {
        World world = new World();
        Menu menu = new Menu();
        menu.start(window, world);
        window.setTitle("Super Astronaut Game");
        window.setHeight(900);
        window.setWidth(1000);
        window.show();
    }

    public static void main(String[] args) {
        launch(Game.class);
    }
}