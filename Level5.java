import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class Level5 extends Level {
    private final Duck blackDuckType1;
    private final Duck blackDuckType2;
    private final Duck redDuckType2;
    private final Duck blueDuckType1;

    public Level5(Stage stage, int bgIndex, int crosshairIndex) {
        super(stage, bgIndex, crosshairIndex);

        blackDuckType1 = new BlackDuck();
        configureDuck(blackDuckType1, blackDuckType1.image4, blackDuckType1.image5, blackDuckType1.image6, 20, 0, 20, 35);

        blackDuckType2 = new BlackDuck();
        configureDuck(blackDuckType2, blackDuckType2.image1, blackDuckType2.image2, blackDuckType2.image3, -15, -15, 40, 150);

        blueDuckType1 = new BlueDuck();
        configureDuck(blueDuckType1, blueDuckType1.image4, blueDuckType1.image5, blueDuckType1.image6, -20, 0, 225, 85);

        redDuckType2 = new RedDuck();
        configureDuck(redDuckType2, redDuckType2.image1, redDuckType2.image2, redDuckType2.image3, 15, -15, 200, 200);

        ducks.add(blackDuckType1);
        ducks.add(blackDuckType2);
        ducks.add(redDuckType2);
        ducks.add(blueDuckType1);

        this.ammo = ducks.size() * 3;
        updateAmmoText();

        levelText.setText("Level 5/6");

        addNodesToPane(blackDuckType1.duckIW, blueDuckType1.duckIW, redDuckType2.duckIW, blackDuckType2.duckIW, fg, ammoText, levelText, enterToNextLevelText, enterToPlayAgainText, youWinText, escToExitText, gameOverText, crosshairIW);

        setupScene();
    }

    private void configureDuck(Duck duck, Image image1, Image image2, Image image3, double xVelocity, double yVelocity, double xPosition, double yPosition) {
        duck.flappingAnimationImages = new Image[]{image1, image2, image3};
        duck.xVelocity = xVelocity;
        duck.yVelocity = yVelocity;
        duck.xPosition = xPosition;
        duck.yPosition = yPosition;
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
