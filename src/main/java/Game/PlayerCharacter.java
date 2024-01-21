package Game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class PlayerCharacter {

    public enum IntersectionLine {
        UP, DOWN, LEFT, RIGHT
    }

    private final ImageView playerCharacter;
    private final Map<Double, Image> images;
    private double numberOfAnimation;
    private final double speedOfCharacter;

    public PlayerCharacter() {
        this.images = new HashMap<>();
        createImagesForRunning();
        this.playerCharacter = new ImageView(this.images.get(0.0));
        this.playerCharacter.setScaleX(2.0);
        this.playerCharacter.setScaleY(2.0);
        this.numberOfAnimation = 0.0;
        this.speedOfCharacter = 10;

    }

    public ImageView create(Scene scene) {
        this.playerCharacter.setTranslateX(0);
        this.playerCharacter.setTranslateY(8 * Game.BLOCK_SIZE);
        this.playerCharacter.setRotate(0);
        move(scene);
        return this.playerCharacter;
    }

    private void move(Scene scene) {
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        scene.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        scene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (pressedKeys.getOrDefault(KeyCode.LEFT, false) && noCollide(IntersectionLine.LEFT)) {
                    runAnimation();
                    playerCharacter.setScaleX(-2.0);

                    if (playerCharacter.getTranslateX() - scene.getCamera().getTranslateX() <= 2 * speedOfCharacter) {
                        scene.getCamera().translateXProperty().set(scene.getCamera().translateXProperty().get() - speedOfCharacter);
                    }

                    if (playerCharacter.getTranslateX() >= 3 * speedOfCharacter) {
                        playerCharacter.setTranslateX(playerCharacter.getTranslateX() - speedOfCharacter);
                    }
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false) && noCollide(IntersectionLine.RIGHT)) {
                    runAnimation();
                    playerCharacter.setScaleX(2.0);

                    if (playerCharacter.getTranslateX() - scene.getCamera().getTranslateX() >= scene.getWidth() - 2 * speedOfCharacter - Game.PLAYER_SIZE) {
                        scene.getCamera().translateXProperty().set(scene.getCamera().translateXProperty().get() + speedOfCharacter);
                    }

                    if (playerCharacter.getTranslateX() <= 212 * Game.BLOCK_SIZE - 3 * speedOfCharacter - Game.PLAYER_SIZE) {
                        playerCharacter.setTranslateX(playerCharacter.getTranslateX() + speedOfCharacter);
                    }
                }

                if (pressedKeys.getOrDefault(KeyCode.UP, false) && noCollide(IntersectionLine.UP)) {
                    playerCharacter.setImage(images.get((double) 12));
                    playerCharacter.setTranslateY(playerCharacter.getTranslateY() - speedOfCharacter);
                } else {
                    if (noCollide(IntersectionLine.DOWN)) {
                        playerCharacter.setImage(images.get((double) 13));
                        playerCharacter.setTranslateY(playerCharacter.getTranslateY() + speedOfCharacter);
                    }
                }
            }
        }.start();
    }

    private void runAnimation() {
        this.numberOfAnimation = this.numberOfAnimation + 0.5;

        if (this.numberOfAnimation > 12) {
            this.numberOfAnimation = 0;
        } else if (this.numberOfAnimation % 1 == 0) {
            this.playerCharacter.setImage(images.get(this.numberOfAnimation));
        }
    }

    private void createImagesForRunning() {

        for (int i = 0; i < 12; i++) {
            Image run = new Image("file:Run.png");
            PixelReader reader = run.getPixelReader();
            WritableImage frame = new WritableImage(reader, i * Game.PLAYER_SIZE / 2, 0, Game.PLAYER_SIZE / 2, Game.PLAYER_SIZE / 2);
            this.images.put((double) i, frame);
        }

        this.images.put((double) 12, new Image("file:Jump.png"));
        this.images.put((double) 13, new Image("file:Fall.png"));
    }

    private boolean noCollide(IntersectionLine intersectionLine) {
        Rectangle2D intersectLine = null;

        switch (intersectionLine) {
            case UP:
                intersectLine = new Rectangle2D(this.playerCharacter.getTranslateX(), this.playerCharacter.getTranslateY(), Game.PLAYER_SIZE, 1);
                break;
            case DOWN:
                intersectLine = new Rectangle2D(this.playerCharacter.getTranslateX(), this.playerCharacter.getTranslateY() + Game.PLAYER_SIZE - 1 - 10, Game.PLAYER_SIZE, 1);
                break;
            case LEFT:
                intersectLine = new Rectangle2D(this.playerCharacter.getTranslateX(), this.playerCharacter.getTranslateY() + 20, 1, Game.PLAYER_SIZE - 40);
                break;
            case RIGHT:
                intersectLine = new Rectangle2D(this.playerCharacter.getTranslateX() + Game.PLAYER_SIZE - 1, this.playerCharacter.getTranslateY() + 20, 1, Game.PLAYER_SIZE - 40);
                break;
        }

        for (Node node : World.layout.getChildren()) {
            Rectangle2D rect = new Rectangle2D(node.getTranslateX(), node.getTranslateY(), Game.BLOCK_SIZE, Game.BLOCK_SIZE);
            if (node.getClass() == Block.class && rect.intersects(intersectLine)) {
                return false;
            }
        }

        return true;
    }
}
