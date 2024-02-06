package Game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class PlayerCharacter extends Character {
    private final ImageView playerCharacter;
    private final Map<Double, Image> images;
    private final double speedOfCharacter;

    private static double jumpLimit;

    public PlayerCharacter(Scene scene) {
        this.images = new HashMap<>();
        Image run = new Image("file:Run.png");
        createImagesForRunning(images, run);
        this.images.put((double) 12, new Image("file:Jump.png"));
        this.images.put((double) 13, new Image("file:Fall.png"));
        this.playerCharacter = new ImageView(this.images.get(0.0));
        this.playerCharacter.setScaleX(2.0);
        this.playerCharacter.setScaleY(2.0);
        this.playerCharacter.setTranslateX(2 * Game.BLOCK_SIZE);
        this.playerCharacter.setTranslateY(8 * Game.BLOCK_SIZE);
        this.playerCharacter.setRotate(0);
        move(scene);
        this.speedOfCharacter = 8;
        World.layout.getChildren().add(getCharacter());
        jumpLimit = 0;
    }

    public ImageView getCharacter() {
        return this.playerCharacter;
    }

    @Override
    protected void move(Scene scene) {
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        scene.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        scene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    runAnimation(playerCharacter, images);
                    playerCharacter.setScaleX(-2.0);

                    if (noCollide(IntersectionLine.LEFT, playerCharacter)) {
                        playerCharacter.setTranslateX(playerCharacter.getTranslateX() - speedOfCharacter);

                        if (playerCharacter.getTranslateX() - scene.getCamera().getTranslateX() <= 8 * Game.BLOCK_SIZE && playerCharacter.getTranslateX() > 8 * Game.BLOCK_SIZE) {
                            scene.getCamera().translateXProperty().set(scene.getCamera().translateXProperty().get() - speedOfCharacter);
                        }
                    }
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    runAnimation(playerCharacter, images);
                    playerCharacter.setScaleX(2.0);

                    if (noCollide(IntersectionLine.RIGHT, playerCharacter)) {
                        playerCharacter.setTranslateX(playerCharacter.getTranslateX() + speedOfCharacter);

                        if (playerCharacter.getTranslateX() - scene.getCamera().getTranslateX() >= scene.getWidth() - 7 * Game.BLOCK_SIZE - Game.PLAYER_SIZE && playerCharacter.getTranslateX() < 205 * Game.BLOCK_SIZE) {
                            scene.getCamera().translateXProperty().set(scene.getCamera().translateXProperty().get() + speedOfCharacter);
                        }
                    }
                }

                if(!noCollide(IntersectionLine.DOWN, playerCharacter)) {
                    jumpLimit = 0;
                } else if (!noCollide(IntersectionLine.UP, playerCharacter)) {
                    jumpLimit = 4.5 * Game.BLOCK_SIZE;
                }

                System.out.println(jumpLimit);

                if (pressedKeys.getOrDefault(KeyCode.UP, false) && noCollide(IntersectionLine.UP, playerCharacter) && jumpLimit < 4.5 * Game.BLOCK_SIZE) {
                    playerCharacter.setImage(images.get((double) 12));
                    playerCharacter.setTranslateY(playerCharacter.getTranslateY() - speedOfCharacter);
                    jumpLimit = jumpLimit + speedOfCharacter;
                } else {
                    if (noCollide(IntersectionLine.DOWN, playerCharacter)) {
                        playerCharacter.setImage(images.get((double) 13));
                        playerCharacter.setTranslateY(playerCharacter.getTranslateY() + speedOfCharacter);
                    }
                }

                if(playerCharacter.getTranslateY() > 14 * Game.BLOCK_SIZE) {
                    stop();
                    Game.retryWindow.start();
                } else if(playerCharacter.getTranslateX() > 198 * Game.BLOCK_SIZE) {
                    stop();
                    Game.winWindow.start();
                }
            }
        }.start();
    }
}
