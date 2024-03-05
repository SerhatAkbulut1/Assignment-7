import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Level 2 class
 */
public class Level2 extends Level {
    private final Duck blueDuck;

    public Level2(Stage stage, int bgIndex, int crosshairIndex) {
        super(stage, bgIndex, crosshairIndex);

        blueDuck = new BlueDuck();

        blueDuck.flappingAnimationImages = new Image[]{blueDuck.image1, blueDuck.image2, blueDuck.image3};

        blueDuck.xVelocity = 15;
        blueDuck.yVelocity = -15;
        blueDuck.xPosition = 200;
        blueDuck.yPosition = 200;
        blueDuck.xScale = 1;
        blueDuck.yScale = 1;

        configureDuck(blueDuck);

        ducks.add(blueDuck);

        this.ammo = ducks.size() * 3;
        updateAmmoText();

        levelText.setText("Level 2/6");

        addNodesToPane(blueDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText, enterToPlayAgainText, youWinText, escToExitText, gameOverText, crosshairIW);

        setupScene();
    }

    private void configureDuck(Duck duck) {
        duck.duckIW.setX(duck.xPosition * DuckHunt.SCALE);
        duck.duckIW.setY(duck.yPosition * DuckHunt.SCALE);
        duck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(200), event -> duck.flyingType2()));
        duck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void nextLevel() {
        super.nextLevel();
    }
}
