
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/**
 * Represents a BLackDuck object that extends the Duck class.
 */

public class BlackDuck extends Duck {
    private static final String DUCK_FALLS_SOUND_PATH = "assets/effects/DuckFalls.mp3";
    String imagePath = "assets/duck_black/";
    String[] imageFiles = {"1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png"};
    Image[] images;
    ImageView imageDuck;
    Timeline timeline;
    double xVelocity;
    double yVelocity;
    double fallingVelocity = 30*DuckHunt.Scale;
    double x;
    double y;
    int currentImageIndex = 0;
    Timeline fallingTimeline;
    boolean isShot = false;
    MediaPlayer MusicPlayer;
    /**
     * Constructs a BLackDuck object with the specified initial position and velocities.
     *
     * @param x The initial x position of the BLackDuck.
     * @param y The initial y position of the BLackDuck.
     * @param xVelocity The initial velocity in the x direction.
     * @param yVelocity The initial velocity in the y direction.
     */
    public BlackDuck(double x,double y, double xVelocity,double yVelocity){
        super(x,y,xVelocity,yVelocity);
        this.x = x*DuckHunt.Scale;
        this.y = y*DuckHunt.Scale;
        this.xVelocity = xVelocity*DuckHunt.Scale;
        this.yVelocity = yVelocity*DuckHunt.Scale;
    }

    /**
     * Moves the BLackDuck using the first type of movement.
     *
     * @param root The root pane where the BLackDuck is displayed.
     */
    public void moveType1(Pane root) {
        images = new Image[3];
        for (int i = 3; i < 6; i++) {
            images[i - 3] = new Image(imagePath + imageFiles[i]);
        }
        imageDuck = new ImageView(images[currentImageIndex]);
        imageDuck.setFitHeight(30*DuckHunt.Scale);
        imageDuck.setFitWidth(20*DuckHunt.Scale);
        imageDuck.setTranslateX(x);
        imageDuck.setTranslateY(y);
        root.getChildren().add(imageDuck);

        imageDuck.setOnMouseClicked(event -> handleMouseClick(event, root));
        timeline = new Timeline();

        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200),
                event -> {
                    imageDuck.setImage(images[0]);
                    update(root);
                });
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(300),
                event -> {
                    imageDuck.setImage(images[1]);
                    update(root);
                });
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(400),
                event -> {
                    imageDuck.setImage(images[2]);
                    update(root);
                });

        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**
     * Moves the BLackDuck using the second type of movement.
     *
     * @param root The root pane where the BLackDuck is displayed.
     */
    public void moveType2(Pane root) {
        images = new Image[3];
        for (int i = 0; i < 3; i++) {
            images[i] = new Image(imagePath + imageFiles[i]);
        }
        imageDuck = new ImageView(images[currentImageIndex]);
        imageDuck.setFitHeight(30*DuckHunt.Scale);
        imageDuck.setFitWidth(20*DuckHunt.Scale);
        imageDuck.setTranslateX(x);
        imageDuck.setTranslateY(y);
        root.getChildren().add(imageDuck);

        imageDuck.setOnMouseClicked(event -> handleMouseClick(event, root));
        timeline = new Timeline();

        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200),
                event -> {
                    imageDuck.setImage(images[0]);
                    update2(root);
                });
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(300),
                event -> {
                    imageDuck.setImage(images[1]);
                    update2(root);
                });
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(400),
                event -> {
                    imageDuck.setImage(images[2]);
                    update2(root);
                });

        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**
     * Updates the position of the BLackDuck using the second type of movement.
     *
     * @param root The root pane where the BLackDuck is displayed.
     */
    public void update(Pane root) {
        if (x >= ((int) root.getWidth()) - imageDuck.getFitWidth()-8*DuckHunt.Scale || x < 0) {
            xVelocity = xVelocity * -1;
            if (x >= ((int) root.getWidth())-8*DuckHunt.Scale - imageDuck.getFitWidth()|| x < 0) {
                imageDuck.setScaleX(imageDuck.getScaleX() * -1);
            }
        }
        x = x + xVelocity;
        imageDuck.setTranslateX(x);
    }
    /**
     * Updates the position of the BLackDuck using the second type of movement.
     *
     * @param root The root pane where the BLackDuck is displayed.
     */
    public void update2(Pane root) {
        if (x >= ((int) root.getWidth()) - imageDuck.getFitWidth() || x < 0) {
            xVelocity = xVelocity * -1;
            if (x >= ((int) root.getWidth()) - imageDuck.getFitWidth() || x < 0) {
                imageDuck.setScaleX(imageDuck.getScaleX() * -1);
            }
        }
        if (y >= ((int) root.getHeight() ) - imageDuck.getFitHeight() || y < 0) {
            yVelocity = yVelocity * -1;
            if (y >= ((int) root.getHeight()) - imageDuck.getFitHeight() || y < 0) {
                imageDuck.setScaleY(imageDuck.getScaleY() * -1);
            }
        }
        x += xVelocity;
        y += yVelocity;
        imageDuck.setTranslateX(x);
        imageDuck.setTranslateY(y);
    }
    /**
     * Handles the mouse click event on the BLackDuck.
     *
     * @param event The MouseEvent object representing the click event.
     * @param root The root pane where the BLackDuck is displayed.
     */
    public void handleMouseClick(MouseEvent event, Pane root) {
        if (!isShot) {
            double mouseX = event.getX();
            double mouseY = event.getY();

            double duckX = imageDuck.getTranslateX();
            double duckY = imageDuck.getTranslateY();

            double duckWidth = imageDuck.getFitWidth();
            double duckHeight = imageDuck.getFitHeight();

            if (true) {
                setShot(true);
                timeline.stop();

                imageDuck.setImage(new Image(imagePath + "7.png"));

                KeyFrame keyFrame4 = new KeyFrame(Duration.millis(500),
                        event2 -> {
                            imageDuck.setImage(new Image(imagePath + "8.png"));
                            startFallingAnimation(root);
                        });

                fallingTimeline = new Timeline(keyFrame4); // Initialize fallingTimeline with keyFrame4
                fallingTimeline.play();
                Media Falling = new Media(getClass().getResource(DUCK_FALLS_SOUND_PATH).toString());
                MusicPlayer = new MediaPlayer(Falling);
                MusicPlayer.setCycleCount(1);
                MusicPlayer.setVolume(DuckHunt.Volume*2);
                MusicPlayer.play();
                setShot(true);

            }
        }
    }
    /**
     * Starts the falling animation of the BLackDuck.
     *
     * @param root The root pane where the BLackDuck is displayed.
     */
    public void startFallingAnimation(Pane root) {
        fallingTimeline.stop();

        KeyFrame fallingFrame = new KeyFrame(Duration.millis(50), event -> {
            y += fallingVelocity;
            imageDuck.setTranslateY(y);

            if (y >= root.getHeight()) {
                root.getChildren().remove(imageDuck);
                fallingTimeline.stop();
            }
        });

        fallingTimeline.getKeyFrames().add(fallingFrame);
        fallingTimeline.setCycleCount(Timeline.INDEFINITE);
        fallingTimeline.play();
    }
    /**
     * Sets the shot status of the BLackDuck.
     *
     * @param shot The shot status to set.
     */
    public void setShot(boolean shot) {
        isShot = shot;
    }
    /**
     * Checks if the BLackDuck is shot.
     *
     * @return true if the BLackDuck is shot, false otherwise.
     */

    public boolean isShot() {
        return isShot;
    }
}
