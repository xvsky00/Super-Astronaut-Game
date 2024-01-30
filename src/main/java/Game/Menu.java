package Game;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class Menu {
    public void start(Stage window, World world) {
        GridPane layout = new GridPane();
        layout.setPrefSize(1000, 900);

        VBox buttons = new VBox();
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(titleOfTheGame());
        buttons.getChildren().add(startButton(window, world));
        buttons.getChildren().add(settingsButton());
        buttons.getChildren().add(quitButton(window));
        buttons.getChildren().add(new Label("Designed by stockgiu / Freepik"));

        layout.getChildren().add(buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background());

        Scene scene = new Scene(layout);

        window.setScene(scene);
    }

    private Button startButton(Stage window, World world) {

        Button button = new Button();
        button.setText("Start");
        button.setPrefWidth(400);
        button.setPrefHeight(80);
        button.setFont(new Font("Book Antiqua", 30));
        button.setOnAction((event) ->
                world.start(window)
        );
        return button;
    }

    private Button settingsButton() {
        Button button = new Button();
        button.setText("Settings");
        button.setPrefWidth(400);
        button.setPrefHeight(80);
        button.setFont(new Font("Book Antiqua", 30));
        return button;
    }

    private Button quitButton(Stage window) {
        Button button = new Button();
        button.setText("Quit");
        button.setPrefWidth(400);
        button.setPrefHeight(80);
        button.setFont(new Font("Book Antiqua", 30));
        button.setOnAction((event) ->
                window.close()
        );
        return button;
    }

    private Label titleOfTheGame() {
        Label title = new Label("Super Astronaut Game");
        title.setPrefSize(500, 100);
        title.setFont(new Font("Book Antiqua", 40));
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.web("#00008B"));
        return title;
    }

    private Background background() {
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
