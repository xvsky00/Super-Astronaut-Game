package Game;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Menu extends Window {
    private final Stage window;

    public Menu(Stage window) {
        this.window = window;
    }

    public void start() {
        GridPane layout = new GridPane();
        layout.setPrefSize(1000, 900);

        VBox buttons = new VBox();
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(titleOfTheGame());
        buttons.getChildren().add(startButton());
        buttons.getChildren().add(quitButton());
        buttons.getChildren().add(new Label("Designed by stockgiu / Freepik"));

        layout.getChildren().add(buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(Window.background());

        Scene scene = new Scene(layout);

        window.setScene(scene);
    }

    private Button startButton() {

        Button button = new Button();
        button.setText("Start");
        button.setPrefWidth(400);
        button.setPrefHeight(80);
        button.setFont(new Font("Book Antiqua", 30));
        button.setOnAction((event) -> {
                    Game.world.start(window);
                }

        );
        return button;
    }

    private Button quitButton() {
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
}
