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

public class RetryWindow extends Window {
    private final Stage window;

    public RetryWindow(Stage window) {
        this.window = window;
    }

    public void start() {
        GridPane layout = new GridPane();
        layout.setPrefSize(1000, 900);

        VBox buttons = new VBox();
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(failedLabel());
        buttons.getChildren().add(retryButton());
        buttons.getChildren().add(quitButton());
        buttons.getChildren().add(new Label("Designed by stockgiu / Freepik"));

        layout.getChildren().add(buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(Window.background());

        Scene scene = new Scene(layout);

        this.window.setScene(scene);
    }

    private Button retryButton() {

        Button button = new Button();
        button.setText("Retry");
        button.setPrefWidth(400);
        button.setPrefHeight(80);
        button.setFont(new Font("Book Antiqua", 30));
        button.setOnAction((event) -> {
                    Game.world.start(this.window);
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
                this.window.close()
        );
        return button;
    }

    private Label failedLabel() {
        Label title = new Label("You failed!");
        title.setPrefSize(500, 100);
        title.setFont(new Font("Book Antiqua", 40));
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.web("#00008B"));
        return title;
    }
}
