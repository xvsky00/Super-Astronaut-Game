package Game;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class World {
    public static Pane layout;
    public static PlayerCharacter playerCharacter;
    public static EnemyCharacter enemyCharacter_1;
    public static EnemyCharacter enemyCharacter_2;
    public static EnemyCharacter enemyCharacter_3;
    public static EnemyCharacter enemyCharacter_4;
    public static Scene scene;


    public void prepare(Stage window) {
        layout = new Pane();
        scene = new Scene(layout);
        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        initContent();
    }

    public void start(Stage window) {
        window.setScene(scene);
        enemyCharacter_1 = new EnemyCharacter(scene, 49 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        enemyCharacter_2 = new EnemyCharacter(scene, 31 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        enemyCharacter_3 = new EnemyCharacter(scene, 41 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        enemyCharacter_4 = new EnemyCharacter(scene, 25 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        playerCharacter = new PlayerCharacter(scene);
    }

    public Scene getScene() {
        return scene;
    }

    public void reset(Scene scene) {
        scene.getCamera().setTranslateX(0);
        enemyCharacter_1.reset(scene, 49 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        enemyCharacter_2.reset(scene, 31 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        enemyCharacter_3.reset(scene, 41 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        enemyCharacter_4.reset(scene, 25 * Game.BLOCK_SIZE, 12 * Game.BLOCK_SIZE);
        playerCharacter.reset(scene);
    }

    private void initContent() {
        Image backgroundImg = new Image("file:background_game.png");
        ImageView backgroundIV = new ImageView(backgroundImg);
        backgroundIV.setFitHeight(14 * Game.BLOCK_SIZE);
        backgroundIV.setFitWidth(212 * Game.BLOCK_SIZE);
        layout.getChildren().add(backgroundIV);

        int levelNumber = 0;

        for (int i = 0; i < MapData.levels[levelNumber].length; i++) {
            String line = MapData.levels[levelNumber][i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        Block platformFloor = new Block(Block.BlockType.PLATFORM, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '2':
                        Block brick = new Block(Block.BlockType.BRICK, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '3':
                        Block bonus = new Block(Block.BlockType.BONUS, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '4':
                        Block stone = new Block(Block.BlockType.STONE, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '5':
                        Block PipeTopBlock = new Block(Block.BlockType.PIPE_TOP, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '6':
                        Block PipeBottomBlock = new Block(Block.BlockType.PIPE_BOTTOM, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                    case '*':
                        Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK, j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE);
                        break;
                }
            }
        }
    }
}
