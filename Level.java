import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;


/**
 * Abstract Level class which is ancestor of all levels in the game
 */
public abstract class Level {
    Stage stage;
    Scene scene;
    BorderPane root;
    AnchorPane objects;
    TitleScreen ts;
    ImageView crosshairIW;
    ImageView fg;
    ImageView bg;
    MediaPlayer shotAudioEffectPlayer;
    MediaPlayer gameOverAudioEffectPlayer;
    MediaPlayer levelCompletedAudioEffectPlayer;
    Timeline winFlashTextAnimation;
    Timeline loseFlashTextAnimation;
    Text ammoText;
    Text youWinText;
    Text gameOverText;
    Text enterToNextLevelText;
    Text enterToPlayAgainText;
    Text escToExitText;
    Text levelText;
    ArrayList<Duck> ducks = new ArrayList<>();
    int bgIndex;
    int crosshairIndex;
    int shotBirdCount = 0;
    boolean isFinished = false;
    int ammo;
    

    public Level(Stage stage, int bgIndex, int crosshairIndex) {
        this.stage = stage;
        this.bgIndex = bgIndex;
        this.crosshairIndex = crosshairIndex;
        root = new BorderPane();
        objects = new AnchorPane();

        bg = new ImageView(new Image(String.format("file:assets/background/%d.png", bgIndex)));
        bg.setFitHeight(DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        bg.setFitWidth(DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE);

        fg = new ImageView(new Image(String.format("file:assets/foreground/%d.png", bgIndex)));
        fg.setFitHeight(DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        fg.setFitWidth(DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE);

        ammoText = createText("Ammo Left: %d", 195, 10);
        youWinText = createText("YOU WIN!", 93, 110);
        enterToNextLevelText = createText("Press ENTER to play next level", 20, 124);
        gameOverText = createText("GAME OVER!", 80, 115);
        enterToPlayAgainText = createText("Press ENTER to play again", 30, 129);
        escToExitText = createText("Press ESC to exit", 65, 142);
        levelText = createText("Level 1/6", 112, 10);

        loseFlashTextAnimation = createFlashTextAnimation(enterToPlayAgainText, escToExitText);
        winFlashTextAnimation = createFlashTextAnimation(enterToNextLevelText);

        crosshairIW = createCrosshairImage(crosshairIndex);

        root.setOnMouseMoved(e -> {
            crosshairIW.setX(e.getX());
            crosshairIW.setY(e.getY());
        });

        root.setOnMouseClicked(e -> {
            if (!isFinished) {
                shoot(e.getSceneX(), e.getSceneY());
            }
        });
    }

    private Text createText(String text, double translateX, double translateY) {
        Text newText = new Text(text);
        newText.setTranslateX(translateX * DuckHunt.SCALE);
        newText.setTranslateY(translateY * DuckHunt.SCALE);
        newText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15));
        newText.setFill(Color.ORANGE);
        newText.setVisible(false);
        root.getChildren().add(newText);
        return newText;
    }

    private Timeline createFlashTextAnimation(Text... texts) {
        KeyFrame[] keyFrames = new KeyFrame[texts.length * 2];
        int index = 0;
        for (Text text : texts) {
            keyFrames[index++] = new KeyFrame(Duration.seconds(0.5), evt -> text.setVisible(false));
            keyFrames[index++] = new KeyFrame(Duration.seconds(0.2), evt -> text.setVisible(true));
        }
        Timeline timeline = new Timeline(keyFrames);
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }

    private ImageView createCrosshairImage(int crosshairIndex) {
        Image newCrosshair = new Image(String.format("file:assets/crosshair/%d.png", crosshairIndex));
        ImageView crosshair = new ImageView(newCrosshair);
        crosshair.setFitHeight(newCrosshair.getHeight() * DuckHunt.SCALE);
        crosshair.setFitWidth(newCrosshair.getWidth() * DuckHunt.SCALE);
        return crosshair;
    }

    public void shoot(double shotX, double shotY) {
    Media shotAudioEffect = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
    shotAudioEffectPlayer = new MediaPlayer(shotAudioEffect);
    shotAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
    shotAudioEffectPlayer.play();
    ammoText.setText(String.format("Ammo Left: %d", --ammo));

    for (Duck duck : ducks) {
        Bounds duckBounds = duck.duckIW.localToScene(duck.duckIW.getBoundsInLocal());
        if ((duckBounds.getMinX() <= shotX && shotX <= duckBounds.getMaxX()) && (duckBounds.getMinY() <= shotY && shotY <= duckBounds.getMaxY()) && duck.isAlive) {
            duck.dyingAnimation(shotX, shotY);
            shotBirdCount++;
            duck.isAlive = false;
        }
    }

    if (shotBirdCount == ducks.size()) {
        gameFinished();
    } else if (ammo <= 0) {
        isFinished = true;
        Media gameoverAudioEffect = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
        gameOverAudioEffectPlayer = new MediaPlayer(gameoverAudioEffect);
        gameOverAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
        gameOverAudioEffectPlayer.play();

        enterToPlayAgainText.setVisible(true);
        escToExitText.setVisible(true);
        gameOverText.setVisible(true);

        loseFlashTextAnimation.play();
        scene.setOnKeyPressed(keyEvent -> {
            String key = keyEvent.getCode().toString();
            if (key.equals("ENTER")) {
                Level1 level1 = new Level1(stage, bgIndex, crosshairIndex);
                level1.startGame();
            } else if (key.equals("ESCAPE")) {
                TitleScreen ts = new TitleScreen(stage);
                ts.setStage();
            }
        });
    }
}

public void nextLevel() {
    if (this instanceof Level1) {
        Level2 level2 = new Level2(stage, bgIndex, crosshairIndex);
        level2.startGame();
    } else if (this instanceof Level2) {
        // Proceed to next level
    }
}

public void gameFinished() {
    isFinished = true;

    Media levelCompletedAudioEffect = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
    levelCompletedAudioEffectPlayer = new MediaPlayer(levelCompletedAudioEffect);
    levelCompletedAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
    levelCompletedAudioEffectPlayer.play();

    enterToNextLevelText.setVisible(true);
    youWinText.setVisible(true);
    winFlashTextAnimation.play();
    scene.setOnKeyPressed(keyEvent -> {
        String key = keyEvent.getCode().toString();
        if (key.equals("ENTER")) {
            levelCompletedAudioEffectPlayer.stop();
            nextLevel();
        }
    });
}
    public void startGame() {
        stage.setScene(scene);
        for (Duck duck : ducks) {
            duck.flyingAnimation.play();
        }
    }
}
