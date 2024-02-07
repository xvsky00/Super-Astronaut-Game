package Game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class Window {

    public abstract void start();

    protected static Background background() {
        Image imageFile = new Image("file:background.jpg");

        BackgroundImage backgroundImage = new BackgroundImage(
                imageFile,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        100,
                        100,
                        true,
                        true,
                        true,
                        true
                )
        );

        return new Background(backgroundImage);
    }
}


