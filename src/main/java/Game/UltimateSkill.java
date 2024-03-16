package Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;

public class UltimateSkill {
    private final ImageView ultimateSkill;
    private static boolean isActive;
    private static int xSpeedOfSkill;
    private static int ySpeedOfSkill;

    public UltimateSkill() {
        this.ultimateSkill = new ImageView("file:ultimate_skill.png");
        this.ultimateSkill.setScaleX(2.0);
        this.ultimateSkill.setScaleY(2.0);
        isActive = false;
        xSpeedOfSkill = 7;
        ySpeedOfSkill = 5;
        World.layout.getChildren().add(this.ultimateSkill);
    }

    public void start(Scene scene) {
        if (!isActive) {
            isActive = true;
            this.ultimateSkill.setTranslateX(World.playerCharacter.getCharacter().getTranslateX());
            this.ultimateSkill.setTranslateY(World.playerCharacter.getCharacter().getTranslateY());
            move(scene);
        }
    }

    public void reset() {
        this.ultimateSkill.setTranslateX(- 3 * Game.BLOCK_SIZE);
        this.ultimateSkill.setTranslateY(- 3 * Game.BLOCK_SIZE);
        isActive = false;
    }

    protected void move(Scene scene) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                ultimateSkill.setTranslateX(ultimateSkill.getTranslateX() + xSpeedOfSkill);
                ultimateSkill.setTranslateY(ultimateSkill.getTranslateY() + ySpeedOfSkill);

                if(isActive) {
                    start();
                } else {
                    stop();
                }

                if(collideWithBlocks(Character.IntersectionLine.UP) || (collideWithBlocks(Character.IntersectionLine.DOWN))) {
                    ySpeedOfSkill = -1 * ySpeedOfSkill;
                } else if(collideWithBlocks(Character.IntersectionLine.LEFT) || (collideWithBlocks(Character.IntersectionLine.RIGHT))) {
                    xSpeedOfSkill = -1 * xSpeedOfSkill;
                };

                if(ultimateSkill.getTranslateX() < -32 || ultimateSkill.getTranslateX() > 213 * Game.BLOCK_SIZE || ultimateSkill.getTranslateY() < -32 || ultimateSkill.getTranslateY() > 15 * Game.BLOCK_SIZE) {
                    reset();
                }
            }
        }.start();

    }

    public Rectangle2D getShape() {
        return new Rectangle2D(this.ultimateSkill.getTranslateX(), this.ultimateSkill.getTranslateY(), 32, 32);
    }
    protected boolean collideWithBlocks(Character.IntersectionLine intersectionLine) {
        Rectangle2D intersectLine = null;

        switch (intersectionLine) {
            case UP:
                intersectLine = new Rectangle2D(this.ultimateSkill.getTranslateX(), this.ultimateSkill.getTranslateY(), 32, 1);
                break;
            case DOWN:
                intersectLine = new Rectangle2D(this.ultimateSkill.getTranslateX(), this.ultimateSkill.getTranslateY() + 32 - 1, 32, 1);
                break;
            case LEFT:
                intersectLine = new Rectangle2D(this.ultimateSkill.getTranslateX(), this.ultimateSkill.getTranslateY(), 1, 32);
                break;
            case RIGHT:
                intersectLine = new Rectangle2D(this.ultimateSkill.getTranslateX() + 32 - 1, this.ultimateSkill.getTranslateY(), 1, 32);
                break;
        }

        for (Node node : World.layout.getChildren()) {
            Rectangle2D rect = new Rectangle2D(node.getTranslateX(), node.getTranslateY(), Game.BLOCK_SIZE, Game.BLOCK_SIZE);
            if (node.getClass() == Block.class && rect.intersects(intersectLine) && node.getOpacity() != 0) {
                return true;
            }
        }

        return false;
    }
}
