package Game;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Loader extends Preloader {
    ProgressBar progressBar;
    Stage stage;

    private Scene createLoaderScene() {
        progressBar = new ProgressBar();
        BorderPane p = new BorderPane();
        p.setCenter(progressBar);
        return new Scene(p, 15 * Game.BLOCK_SIZE - 6, 1000);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createLoaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification progressNotification) {
        progressBar.setProgress(progressNotification.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
}
