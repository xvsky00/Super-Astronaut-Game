package Game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class EnemyCharacter extends Character {
    private final ImageView enemyCharacter;
    private final Map<Double, Image> images;
    private final double speedOfCharacter;

    public EnemyCharacter(Scene scene) {
        this.images = new HashMap<>();
        Image run = new Image("file:enemy_run.png");
        createImagesForRunning(images, run);
        this.enemyCharacter = new ImageView(this.images.get(0.0));
        this.enemyCharacter.setScaleX(2.0);
        this.enemyCharacter.setScaleY(2.0);
        this.enemyCharacter.setTranslateX(49 * Game.BLOCK_SIZE);
        this.enemyCharacter.setTranslateY(12 * Game.BLOCK_SIZE);
        this.enemyCharacter.setRotate(0);
        move(scene);
        this.speedOfCharacter = 9;
    }

    @Override
    public ImageView getCharacter() {
        return this.enemyCharacter;
    }

    @Override
    protected void move(Scene scene) {

    }
}
