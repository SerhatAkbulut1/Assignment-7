import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.io.File;

/**
 * Abstract class representing a Duck.
 */
public abstract class Duck {

    Image[] flappingAnimationImages;
    Timeline flyingAnimation;
    int currentImageIndex = 0;
    double xPosition;
    double yPosition;
    double xVelocity;
    double yVelocity;
    double xScale = 1;
    double yScale = 1;
    boolean scaled = false;
    int counter = 0;
    boolean isAlive = true;
    ImageView duckImageView;

    public Duck() {
        duckImageView = new ImageView();
    }

    public void dyingAnimation(double shotX, double shotY) {
        flyingAnimation.stop();
        changeBirdImage(image7);

        Timeline shockedAnimation = new Timeline(new KeyFrame(Duration.seconds(0.13), event -> changeBirdImage(image8)));

        Line line = new Line(shotX, shotY, shotX, DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(line);
        pathTransition.setNode(duckImageView);
        pathTransition.setDuration(Duration.seconds(4));
        pathTransition.setInterpolator(Interpolator.LINEAR);

        Media duckFallsAudioEffect = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
        MediaPlayer duckFallsAudioEffectPlayer = new MediaPlayer(duckFallsAudioEffect);
        duckFallsAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
        duckFallsAudioEffectPlayer.play();

        shockedAnimation.play();
        shockedAnimation.setOnFinished(e -> pathTransition.play());
    }

    public void changeBirdImage() {
        currentImageIndex = (currentImageIndex + 1) % flappingAnimationImages.length;
        Image imageToChange = flappingAnimationImages[currentImageIndex];
        duckImageView.setFitHeight(imageToChange.getHeight() * DuckHunt.SCALE);
        duckImageView.setFitWidth(imageToChange.getWidth() * DuckHunt.SCALE);
        duckImageView.setImage(imageToChange);
    }

    public void changeBirdImage(Image imageToChange) {
        duckImageView.setFitHeight(imageToChange.getHeight() * DuckHunt.SCALE);
        duckImageView.setFitWidth(imageToChange.getWidth() * DuckHunt.SCALE);
        duckImageView.setImage(imageToChange);
    }

    public void flyingType1() {
        if (xPosition >= 220 && !scaled) {
            scaled = true;
            xVelocity *= -1;
            xScale *= -1;
            duckImageView.setScaleX(xScale);
        } else if (xPosition <= 6 && !scaled) {
            scaled = true;
            xVelocity *= -1;
            xScale *= -1;
            duckImageView.setScaleX(xScale);
        } else {
            scaled = false;
            changeBirdImage();
            xPosition += xVelocity;
            duckImageView.setX(xPosition * DuckHunt.SCALE);
            duckImageView.setY(yPosition * DuckHunt.SCALE);
        }
    }

    public void flyingType2() {
        changeBirdImage();

        if (xPosition > 220 || xPosition < 8) {
            xVelocity *= -1;
            xScale *= -1;
            duckImageView.setScaleX(xScale);
        }
        if (yPosition > 220 || yPosition < 8) {
            yVelocity *= -1;
            yScale *= -1;
            duckImageView.setScaleY(yScale);
        }
        xPosition += xVelocity;
        yPosition += yVelocity;
        duckImageView.setX(xPosition * DuckHunt.SCALE);
        duckImageView.setY(yPosition * DuckHunt.SCALE);
    }

    public void flyingType3(boolean toRight) {
        changeBirdImage();

        if (xPosition >= 240 || xPosition <= 0) {
            xVelocity *= -1;
            xScale *= -1;
            duckImageView.setScaleX(xScale);
            if (counter == 0 && ((toRight && xPosition >= 240) || (!toRight && xPosition <= 0))) {
                counter++;
                xPosition += toRight ? -30 : 30;
                duckImageView.setX(xPosition * DuckHunt.SCALE);
            }
        }
        if (yPosition >= 240 || yPosition <= 0) {
            yVelocity *= -1;
            yScale *= -1;
            duckImageView.setScaleY(yScale);
        }
        xPosition += xVelocity;
        yPosition += yVelocity;
        duckImageView.setX(xPosition * DuckHunt.SCALE);
        duckImageView.setY(yPosition * DuckHunt.SCALE);
    }
}
