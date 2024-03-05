import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Level6 extends Level {
    private final Duck blackDuck1Type2;
    private final Duck blackDuck2Type2;
    private final Duck redDuck1Type2;
    private final Duck redDuck2Type2;
    private final Duck blueDuck1Type2;
    private final Duck blueDuck2Type2;

    public Level6(Stage stage, int bgIndex, int crosshairIndex) {
        super(stage, bgIndex, crosshairIndex);

        blackDuck1Type2 = createBlackDuckType2(214, 70, 20, -10);
        blackDuck2Type2 = createBlackDuckType2(200, 210, -25, -13);
        redDuck1Type2 = createRedDuckType2(119, 186, -15, -14);
        redDuck2Type2 = createRedDuckType2(123, 212, 20, -11);
        blueDuck1Type2 = createBlueDuckType2(15, 214, 20, -12);
        blueDuck2Type2 = createBlueDuckType2(177, 176, -20, -10);

        addDucksToList(blackDuck1Type2, blackDuck2Type2, redDuck1Type2, redDuck2Type2, blueDuck1Type2, blueDuck2Type2);

        setupScene();
    }

    private Duck createBlackDuckType2(double xPosition, double yPosition, double xVelocity, double yVelocity) {
        Duck blackDuckType2 = new BlackDuck();
        configureDuck(blackDuckType2, blackDuckType2.image1, blackDuckType2.image2, blackDuckType2.image3, xVelocity, yVelocity, xPosition, yPosition);
        return blackDuckType2;
    }

    private Duck createRedDuckType2(double xPosition, double yPosition, double xVelocity, double yVelocity) {
        Duck redDuckType2 = new RedDuck();
        configureDuck(redDuckType2, redDuckType2.image1, redDuckType2.image2, redDuckType2.image3, xVelocity, yVelocity, xPosition, yPosition);
        return redDuckType2;
    }

    private Duck createBlueDuckType2(double xPosition, double yPosition, double xVelocity, double yVelocity) {
        Duck blueDuckType2 = new BlueDuck();
        configureDuck(blueDuckType2, blueDuckType2.image1, blueDuckType2.image2, blueDuckType2.image3, xVelocity, yVelocity, xPosition, yPosition);
        return blueDuckType2;
    }

    private void configureDuck(Duck duck, Image image1, Image image2, Image image3, double xVelocity, double yVelocity, double xPosition, double yPosition) {
        duck.flappingAnimationImages = new Image[]{image1, image2, image3};
        duck.xVelocity = xVelocity;
        duck.yVelocity = yVelocity;
        duck.xPosition = xPosition;
        duck.yPosition = yPosition;
        duck.duckIW.setX(duck.xPosition * DuckHunt.SCALE);
        duck.duckIW.setY(duck.yPosition * DuckHunt.SCALE);
        duck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> duck.fly()));
        duck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    private void addDucksToList(Duck... ducksToAdd) {
        for (Duck duck : ducksToAdd) {
            ducks.add(duck);
        }
    }

    @Override
    public void gameFinished() {
        isFinished = true;

        youWinText.setText("You have completed the game!");
        youWinText.setTranslateX(14 * DuckHunt.SCALE);
        youWinText.setTranslateY(110 * DuckHunt.SCALE);

        enterToNextLevelText.setText("Press ENTER to play again");
        enterToNextLevelText.setTranslateX(29 * DuckHunt.SCALE);
        enterToNextLevelText.setTranslateY(126 * DuckHunt.SCALE);

        winFlashTextAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.5), evt -> enterToNextLevelText.setVisible(false)),
                new KeyFrame(Duration.seconds(0.2), evt -> enterToNextLevelText.setVisible(true)),
                new KeyFrame(Duration.seconds(0.5), evt -> escToExitText.setVisible(false)),
                new KeyFrame(Duration.seconds(0.2), evt -> escToExitText.setVisible(true)));
        winFlashTextAnimation.setCycleCount(Animation.INDEFINITE);

        Media levelCompletedAudioEffect = new Media(new File("assets/effects/GameCompleted.mp3").toURI().toString());
        MediaPlayer levelCompletedAudioEffectPlayer = new MediaPlayer(levelCompletedAudioEffect);
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
            } else if (key.equals("ESCAPE")) {
                TitleScreen ts = new TitleScreen(stage);
                ts.setStage();
            }
        });
    }

    @Override
    public void nextLevel() {
        Level1 level1 = new Level1(stage, bgIndex, crosshairIndex);
        level1.startGame();
    }
}
