package Game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class EnemyCharacter extends Character {
    private final ImageView enemyCharacter;
    private final Map<Double, Image> images;
    private double speedOfCharacter;

    public EnemyCharacter(Scene scene, int xCord, int yCord) {
        this.images = new HashMap<>();
        Image run = new Image("file:enemy_run.png");
        createImagesForRunning(images, run);
        this.enemyCharacter = new ImageView(this.images.get(0.0));
        this.enemyCharacter.setScaleX(2.0);
        this.enemyCharacter.setScaleY(2.0);
        this.enemyCharacter.setTranslateX(xCord);
        this.enemyCharacter.setTranslateY(yCord);
        this.enemyCharacter.setRotate(0);
        move(scene);
        this.speedOfCharacter = 5;
        World.layout.getChildren().add(getCharacter());
    }

    @Override
    public ImageView getCharacter() {
        return this.enemyCharacter;
    }

    @Override
    protected void move(Scene scene) {
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                enemyCharacter.setTranslateX(enemyCharacter.getTranslateX() + speedOfCharacter);
                runAnimation(enemyCharacter, images);

                if (!noCollide(IntersectionLine.RIGHT, enemyCharacter)) {
                    speedOfCharacter = -1 * speedOfCharacter;
                    enemyCharacter.setScaleX(-2.0);
                } else if (!noCollide(IntersectionLine.LEFT, enemyCharacter)) {
                    speedOfCharacter = -1 * speedOfCharacter;
                    enemyCharacter.setScaleX(2.0);
                } else if (noCollide(IntersectionLine.DOWN, enemyCharacter)) {
                    enemyCharacter.setImage(images.get((double) 13));
                    enemyCharacter.setTranslateY(enemyCharacter.getTranslateY() + speedOfCharacter);
                } else if (CollideWithPlayerCharacter(IntersectionLine.UP, World.playerCharacter.getCharacter()) && !CollideWithPlayerCharacter(IntersectionLine.RIGHT, World.playerCharacter.getCharacter()) && !CollideWithPlayerCharacter(IntersectionLine.LEFT, World.playerCharacter.getCharacter())) {
                    stop();
                    isAlive(false);
                    enemyCharacter.setScaleY(1.0);
                    enemyCharacter.setTranslateY(enemyCharacter.getTranslateY() + 16);
                }

                if (CollideWithPlayerCharacter(IntersectionLine.RIGHT, World.playerCharacter.getCharacter()) | CollideWithPlayerCharacter(IntersectionLine.LEFT, World.playerCharacter.getCharacter())) {
                    stop();
                    World.playerCharacter.isAlive(false);
                    isAlive(false);
                    Game.retryWindow.start();
                }

                if (enemyCharacter.getTranslateY() > 16 * Game.BLOCK_SIZE) {
                    stop();
                    isAlive(false);
                    Game.retryWindow.start();
                }
            }
        }.start();
    }

    protected boolean CollideWithPlayerCharacter(IntersectionLine intersectionLine, ImageView playerCharacter) {
        Rectangle2D intersectLine = null;
        Rectangle2D playerIntersectLine = new Rectangle2D(playerCharacter.getTranslateX(), playerCharacter.getTranslateY(), Game.PLAYER_SIZE, Game.PLAYER_SIZE);

        switch (intersectionLine) {
            case UP:
                intersectLine = new Rectangle2D(enemyCharacter.getTranslateX() + 20, enemyCharacter.getTranslateY(), Game.PLAYER_SIZE - 40, 5);
                break;
            case RIGHT:
                intersectLine = new Rectangle2D(enemyCharacter.getTranslateX() + Game.PLAYER_SIZE - 25, enemyCharacter.getTranslateY() + 20, 5, Game.PLAYER_SIZE - 20);
                break;
            case LEFT:
                intersectLine = new Rectangle2D(enemyCharacter.getTranslateX() + 20, enemyCharacter.getTranslateY() + 20, 5, Game.PLAYER_SIZE - 20);
                break;
        }

        return playerIntersectLine.intersects(intersectLine);
    }

    public void reset(Scene scene, int xCord, int yCord) {
        this.enemyCharacter.setImage(this.images.get(0.0));
        this.enemyCharacter.setTranslateX(xCord);
        this.enemyCharacter.setTranslateY(yCord);
        this.enemyCharacter.setRotate(0);
        this.enemyCharacter.setScaleX(-2.0);
        this.enemyCharacter.setScaleY(2.0);
        if (!this.alive) {
            this.alive = true;
            this.move(scene);
        }
    }
}
