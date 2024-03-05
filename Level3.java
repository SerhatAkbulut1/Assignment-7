import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Level 3 class
 */
public class Level3 extends Level {
    private final Duck blackDuck;
    private final Duck blueDuck;
    private final Duck redDuck;

    public Level3(Stage stage, int bgIndex, int crosshairIndex) {
        super(stage, bgIndex, crosshairIndex);

        blackDuck = new BlackDuck();
        configureDuck(blackDuck, blackDuck.image4, blackDuck.image5, blackDuck.image6, 20, 0, 20, 35, 1);

        blueDuck = new BlueDuck();
        configureDuck(blueDuck, blueDuck.image4, blueDuck.image5, blueDuck.image6, -20, 0, 225, 85, -1);

        redDuck = new RedDuck();
        configureDuck(redDuck, redDuck.image1, redDuck.image2, redDuck.image3, -15, -15, 119, 186, -1);

        ducks.add(blackDuck);
        ducks.add(blueDuck);
        ducks.add(redDuck);

        this.ammo = ducks.size() * 3;
        updateAmmoText();

        levelText.setText("Level 3/6");

        addNodesToPane(blackDuck.duckIW, blueDuck.duckIW, redDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText, enterToPlayAgainText, youWinText, escToExitText, gameOverText, crosshairIW);

        setupScene();
    }

    private void configureDuck(Duck duck, Image image1, Image image2, Image image3, double xVelocity, double yVelocity, double xPosition, double yPosition, double xScale) {
        duck.flappingAnimationImages = new Image[]{image1, image2, image3};
        duck.xVelocity = xVelocity;
        duck.yVelocity = yVelocity;
        duck.xPosition = xPosition;
        duck.yPosition = yPosition;
        duck.xScale = xScale;
        duck.duckIW.setX(duck.xPosition * DuckHunt.SCALE);
        duck.duckIW.setY(duck.yPosition * DuckHunt.SCALE);
        duck.duckIW.setScaleX(duck.xScale);
        duck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(duck instanceof RedDuck ? 150 : 200), event -> duck.fly()));
        duck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void nextLevel() {
        super.nextLevel();
    }
}
