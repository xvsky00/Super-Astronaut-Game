package Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Block extends Pane {
    Image blocksImg;
    ImageView block;

    public enum BlockType {
        PLATFORM, BRICK, BONUS, PIPE_TOP, PIPE_BOTTOM, INVISIBLE_BLOCK, STONE
    }
    public Block(BlockType blockType, int x, int y) {
        this.blocksImg = new Image(getClass().getResourceAsStream("/1.png"));
        this.block = new ImageView(blocksImg);
        this.block.setFitWidth(Game.BLOCK_SIZE);
        this.block.setFitHeight(Game.BLOCK_SIZE);
        this.setTranslateX(x);
        this.setTranslateY(y);

        switch (blockType) {
            case PLATFORM:
                this.block.setViewport(new Rectangle2D(0, 0, 16, 16));
                break;
            case BRICK:
                this.block.setViewport(new Rectangle2D(16, 0, 16, 16));
                break;
            case BONUS:
                this.block.setViewport(new Rectangle2D(384, 0, 16, 16));
                break;
            case PIPE_TOP:
                this.block.setViewport(new Rectangle2D(0, 128, 32, 16));
                this.block.setFitWidth(Game.BLOCK_SIZE * 2);
                break;
            case PIPE_BOTTOM:
                this.block.setViewport(new Rectangle2D(0, 145, 32, 14));
                this.block.setFitWidth(Game.BLOCK_SIZE * 2);
                break;
            case INVISIBLE_BLOCK:
                this.block.setViewport(new Rectangle2D(0, 0, 16, 16));
                this.block.setOpacity(0);
                break;
            case STONE:
                this.block.setViewport(new Rectangle2D(0, 16, 16, 16));
                break;
        }

        this.getChildren().add(this.block);
        World.layout.getChildren().add(this);
    }
}
