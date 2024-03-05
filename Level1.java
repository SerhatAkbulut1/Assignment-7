import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Level1 class
 */
public class Level1 extends Level {
    private final Duck blackDuck;

    public Level1(Stage stage, int bgIndex, int crosshairIndex) {
        super(stage, bgIndex, crosshairIndex);

        blackDuck = new BlackDuck();

        blackDuck.flappingAnimationImages = new Image[]{blackDuck.image4, blackDuck.image5, blackDuck.image6};

        blackDuck.xVelocity = 20;
        blackDuck.xPosition = 20;
        blackDuck.yPosition = 30;

        configureDuck(blackDuck);

        ducks.add(blackDuck);

        this.ammo = ducks.size() * 3;
        updateAmmoText();

        levelText.setText("Level 1/6");

        addNodesToPane(blackDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText, enterToPlayAgainText, youWinText, escToExitText, gameOverText, crosshairIW);

        setupScene();
    }

    private void configureDuck(Duck duck) {
        duck.duckIW.setScaleX(duck.xScale);
        duck.duckIW.setScaleY(duck.yScale);
        duck.duckIW.setX(duck.xPosition * DuckHunt.SCALE);
        duck.duckIW.setY(duck.yPosition * DuckHunt.SCALE);

        duck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(333), event -> duck.flyingType1()));
        duck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void nextLevel() {
        super.nextLevel();
    }
}
