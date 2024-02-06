package Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import java.util.Map;

public abstract class Character {
    public enum IntersectionLine {
        UP, DOWN, LEFT, RIGHT
    }

    private double numberOfAnimation;

    public Character() {
        this.numberOfAnimation = 0.0;
    }

    protected abstract void move(Scene scene);
    public abstract ImageView getCharacter();

    protected void runAnimation(ImageView character, Map<Double, Image> images) {
        this.numberOfAnimation = this.numberOfAnimation + 0.5;

        if (this.numberOfAnimation > 12) {
            this.numberOfAnimation = 0.0;
            character.setImage(images.get(this.numberOfAnimation));
        } else if (this.numberOfAnimation % 1 == 0) {
            character.setImage(images.get(this.numberOfAnimation));
        }
    }

    protected void createImagesForRunning(Map<Double, Image> images, Image run) {

        for (int i = 0; i < 12; i++) {
            PixelReader reader = run.getPixelReader();
            WritableImage frame = new WritableImage(reader, i * Game.PLAYER_SIZE / 2, 0, Game.PLAYER_SIZE / 2, Game.PLAYER_SIZE / 2);
            images.put((double) i, frame);
        }
    }

    protected boolean noCollide(IntersectionLine intersectionLine, ImageView character) {
        Rectangle2D intersectLine = null;

        switch (intersectionLine) {
            case UP:
                intersectLine = new Rectangle2D(character.getTranslateX(), character.getTranslateY() - 10, Game.PLAYER_SIZE - 25, 1);
                break;
            case DOWN:
                intersectLine = new Rectangle2D(character.getTranslateX(), character.getTranslateY() + Game.PLAYER_SIZE - 1 - 12, Game.PLAYER_SIZE - 30, 1);
                break;
            case LEFT:
                intersectLine = new Rectangle2D(character.getTranslateX() - 10, character.getTranslateY() + 20, 1, Game.PLAYER_SIZE - 40);
                break;
            case RIGHT:
                intersectLine = new Rectangle2D(character.getTranslateX() + Game.PLAYER_SIZE - 15, character.getTranslateY() + 20, 1, Game.PLAYER_SIZE - 40);
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
