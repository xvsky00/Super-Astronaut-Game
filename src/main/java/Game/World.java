package Game;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class World {
    public static Pane layout = new Pane();
    public static PlayerCharacter playerCharacter;
    public static EnemyCharacter enemyCharacter;
    public void start(Stage window) {

        Scene scene = new Scene(layout);
        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);

        window.setScene(scene);
        initContent();
        playerCharacter = new PlayerCharacter(scene);
        enemyCharacter = new EnemyCharacter(scene);
        layout.getChildren().add(playerCharacter.getCharacter());
        layout.getChildren().add(enemyCharacter.getCharacter());
    }

    private void initContent(){
        Image backgroundImg = new Image("file:background_game.png");
        ImageView backgroundIV = new ImageView(backgroundImg);
        backgroundIV.setFitHeight(14*Game.BLOCK_SIZE);
        backgroundIV.setFitWidth(212*Game.BLOCK_SIZE);
        layout.getChildren().add(backgroundIV);

        int levelNumber = 0;
        int levelWidth = MapData.levels[levelNumber][0].length() * Game.BLOCK_SIZE;

        for(int i = 0; i < MapData.levels[levelNumber].length; i++){
            String line = MapData.levels[levelNumber][i];
            for(int j = 0; j < line.length();j++){
                switch (line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Block platformFloor = new Block(Block.BlockType.PLATFORM, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '2':
                        Block brick = new Block(Block.BlockType.BRICK,j*Game.BLOCK_SIZE,i*Game.BLOCK_SIZE);
                        break;
                    case '3':
                        Block bonus = new Block(Block.BlockType.BONUS,j*Game.BLOCK_SIZE,i*Game.BLOCK_SIZE);
                        break;
                    case '4':
                        Block stone = new Block(Block.BlockType.STONE,j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '5':
                        Block PipeTopBlock = new Block(Block.BlockType.PIPE_TOP,j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '6':
                        Block PipeBottomBlock = new Block(Block.BlockType.PIPE_BOTTOM,j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '*':
                        Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK,j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                }
            }
        }
    }
}
