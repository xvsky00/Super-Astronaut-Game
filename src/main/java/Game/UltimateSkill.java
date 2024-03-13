package Game;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;

public class UltimateSkill {
    private final ImageView ultimateSkill;
    private static boolean isActive;
    private static int speedOfSkill;

    public UltimateSkill() {
        this.ultimateSkill = new ImageView("file:ultimate_skill.png");
        this.ultimateSkill.setScaleX(2.0);
        this.ultimateSkill.setScaleY(2.0);
        isActive = false;
        speedOfSkill = 5;
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
        this.ultimateSkill.setTranslateX(-Game.BLOCK_SIZE);
        this.ultimateSkill.setTranslateY(-Game.BLOCK_SIZE);
        isActive = false;
    }

    protected void move(Scene scene) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                ultimateSkill.setTranslateX(ultimateSkill.getTranslateX() + 2 * speedOfSkill);
                ultimateSkill.setTranslateY(ultimateSkill.getTranslateY() + speedOfSkill);

                if(isActive) {
                    start();
                } else {
                    stop();
                }
            }
        }.start();

    }
}
