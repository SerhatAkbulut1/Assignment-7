import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Level 4 class
 */
public class Level4 extends Level {
    private final Duck blackDuck;
    private final Duck blueDuck;
    private final Duck redDuck;

    public Level4(Stage stage, int bgIndex, int crosshairIndex) {
        super(stage, bgIndex, crosshairIndex);

        blueDuck = new BlueDuck();
        configureDuck(blueDuck, blueDuck.image1, blueDuck.image2, blueDuck.image3, -12, -12, 160, 172, -1);

        blackDuck = new BlackDuck();
        configureDuck(blackDuck, blackDuck.image1, blackDuck.image2, blackDuck.image3, 12, -12, 94, 172, 1);

        redDuck = new RedDuck();
        configureDuck(redDuck, redDuck.image1, redDuck.image2, redDuck.image3, -20, -10, 50, 186, -1);

        ducks.add(blueDuck);
        ducks.add(blackDuck);
        ducks.add(redDuck);

        this.ammo = ducks.size() * 3;
        updateAmmoText();

        levelText.setText("Level 4/6");

        addNodesToPane(blueDuck.duckIW, blackDuck.duckIW, redDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText, enterToPlayAgainText, youWinText, escToExitText, gameOverText, crosshairIW);

        setupScene();
    }

    private void configureDuck(Duck duck, Image image1, Image image2, Image image3, double xVelocity, double yVelocity, double xPosition, double yPosition, double xScale) {
        duck.flappingAnimationImages = new Image[]{image1, image2, image3};
        duck.xVelocity = xVelocity;
        duck.yVelocity = yVelocity;
        duck.xPosition = xPosition;
        duck.yPosition = yPosition;
        duck.xScale = xScale;
        duck.duckIW.setScaleX(duck.xScale);
        duck.duckIW.setScaleY(duck.yScale);
        duck.duckIW.setX(duck.xPosition * DuckHunt.SCALE);
        duck.duckIW.setY(duck.yPosition * DuckHunt.SCALE);
        duck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event -> duck.fly()));
        duck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void nextLevel() {
        super.nextLevel();
    }
}
