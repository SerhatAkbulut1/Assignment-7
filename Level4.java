import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the first level of the Duck Hunt game.
 */
public class Level4 {
    private static final String GUNSHOT_SOUND_PATH = "assets/effects/Gunshot.mp3";
    private static final String LEVEL_COMPLETED_SOUND_PATH = "assets/effects/LevelCompleted.mp3";
    private static final String GAME_OVER_SOUND_PATH = "assets/effects/GameOver.mp3";
    private Image background;
    private Image foreground;
    private Image crosshairImage;
    MediaPlayer GunShotvoice;
    static MediaPlayer MusicPlayer;
    private ImageView backgroundImageView;
    private ImageView foregroundImageView;
    private ImageView crosshairImageView;
    private Text bulletCountText;
    private Text statusText;
    private Text statusText2;
    private Text levelText;
    private boolean levelComplate = false;

    private List<Duck> ducks;
    private int bullets = 6;
    RedDuck redDuck = new RedDuck(40,25 , 5, -5);
    BlackDuck blackDuck = new BlackDuck(15, 10, 4, -4);


    private Pane levelPane;

    /**
     * Constructs a Level1 object with the specified background, foreground, and crosshair image.
     *
     * @param background      The image used as the background of the level.
     * @param foreground      The image used as the foreground of the level.
     * @param crosshairImage  The image used as the crosshair.
     */
    public Level4( Image background, Image foreground, Image crosshairImage) {
        this.background = background;
        this.foreground = foreground;
        this.crosshairImage = crosshairImage;

        ducks = new ArrayList<>();
        initializeLevelPane();
        initializeBackground();
        initializelevelText("Level 4/6");
        spawnDucks();
        startDuckMovements();
        initializeForeground();
        initializeCrosshair();
        initializeBullets();
        initializeStatusText();
        moveAbleStatusText();


    }
    /**
     * Initializes the level pane.
     */
    private void initializeLevelPane() {
        levelPane = new Pane();
        levelPane.setPrefSize(background.getWidth()*DuckHunt.Scale, background.getHeight()*DuckHunt.Scale);

    }

    /**
     * Initializes the background image view.
     */
    private void initializeBackground() {
        backgroundImageView = new ImageView(background);
        backgroundImageView.setFitHeight(background.getHeight()*DuckHunt.Scale);
        backgroundImageView.setFitWidth(background.getWidth()*DuckHunt.Scale);
        levelPane.getChildren().add(backgroundImageView);
    }
    /**
     * Initializes the foreground image view.
     */
    private void initializeForeground() {
        foregroundImageView = new ImageView(foreground);
        foregroundImageView.setFitWidth(background.getWidth()*DuckHunt.Scale);
        foregroundImageView.setFitHeight(background.getHeight()*DuckHunt.Scale);
        foregroundImageView.setDisable(true);
        levelPane.getChildren().add(foregroundImageView);
    }
    /**
     * Initializes the crosshair image view and handles mouse and keyboard events.
     */
    private void initializeCrosshair() {
        crosshairImageView = new ImageView(crosshairImage);
        crosshairImageView.setFitHeight(5*DuckHunt.Scale);
        crosshairImageView.setFitWidth(5*DuckHunt.Scale);
        crosshairImageView.setMouseTransparent(true);
        crosshairImageView.setTranslateX(levelPane.getPrefWidth() / 2 - crosshairImageView.getImage().getWidth() / 2);
        crosshairImageView.setTranslateY(levelPane.getPrefHeight() / 2 - crosshairImageView.getImage().getHeight() / 2);
        levelPane.getChildren().add(crosshairImageView);

        levelPane.setOnMouseMoved(event -> {
            crosshairImageView.setTranslateX(event.getX() - crosshairImageView.getImage().getWidth() / 2);
            crosshairImageView.setTranslateY(event.getY() - crosshairImageView.getImage().getHeight() / 2);
        });
        levelPane.setOnMouseClicked(event -> {
            List<Duck> deadDucks = new ArrayList<>();
            if (bullets > 0) {
                if(!isLevelComplate()){
                    Media GunShot = new Media(getClass().getResource(GUNSHOT_SOUND_PATH).toString());
                    GunShotvoice = new MediaPlayer(GunShot);
                    GunShotvoice.setCycleCount(1);
                    GunShotvoice.setVolume(DuckHunt.Volume);
                    GunShotvoice.play();
                    bullets--;
                    updateBulletCountText();
                    for (Duck duck : ducks) {
                        if (duck instanceof RedDuck && ((RedDuck) duck).isShot()) {
                            deadDucks.add(duck);
                        }
                        if(duck instanceof BlackDuck && ((BlackDuck) duck).isShot()){
                            deadDucks.add(duck);
                        }
                    }
                }
            }
            if (bullets == 0 && deadDucks.size() < 2) {
                statusText.setText("GAME OVER!");
                statusText2.setText("Press ENTER to play again\n\t Press ESC to exit");
                statusText.toFront();
                statusText2.toFront();
                DuckHunt.level4Complate = false;
                Media gameOver = new Media(getClass().getResource(GAME_OVER_SOUND_PATH).toString());
                MediaPlayer MusicPlayer = new MediaPlayer(gameOver);
                MusicPlayer.setCycleCount(1);
                MusicPlayer.setVolume(DuckHunt.Volume);
                GunShotvoice.stop();
                MusicPlayer.play();

            } else if (bullets >=0 && deadDucks.size()==2) {
                statusText.setText("    YOU WIN!");
                statusText2.setText("Press ENTER to play next level");
                statusText.toFront();
                statusText2.toFront();
                levelComplate = true;
                DuckHunt.level4Complate = true;
                Media levelComplate = new Media(getClass().getResource(LEVEL_COMPLETED_SOUND_PATH).toString());
                MusicPlayer = new MediaPlayer(levelComplate);
                MusicPlayer.setCycleCount(1);
                MusicPlayer.setVolume(DuckHunt.Volume);
                redDuck.MusicPlayer.stop();
                blackDuck.MusicPlayer.stop();
                GunShotvoice.stop();
                MusicPlayer.play();
                return;
            }
        });

        ImageCursor cursor = new ImageCursor(crosshairImage);
        levelPane.setCursor(cursor);
    }
    /**
     * Handles the mouse moved event to track the crosshair position.
     *
     * @param event The MouseEvent containing information about the event.
     */
    private void handleMouseMoved(MouseEvent event) {
        crosshairImageView.setTranslateX(event.getX() - crosshairImageView.getImage().getWidth() / 2);
        crosshairImageView.setTranslateY(event.getY() - crosshairImageView.getImage().getHeight() / 2);
    }
    /**
     * Initializes the bullet count text view.
     */
    private void initializeBullets() {
        bulletCountText = new Text();
        bulletCountText.setFont(Font.font("Verdana",5*DuckHunt.Scale));
        bulletCountText.setFill(Color.ORANGE);
        bulletCountText.setX(210*DuckHunt.Scale);
        bulletCountText.setY(10*DuckHunt.Scale);
        updateBulletCountText();
        levelPane.getChildren().add(bulletCountText);
    }
    /**
     * Updates the bullet count text with the current number of bullets.
     */
    private void updateBulletCountText() {
        bulletCountText.setText("Ammo Left: " + bullets);
    }
    /**
     * Initializes the status text view.
     */
    private void initializeStatusText() {
        statusText = new Text();
        statusText.setFont(Font.font(20*DuckHunt.Scale));
        statusText.setFill(Color.ORANGE);
        statusText.setX(70*DuckHunt.Scale);
        statusText.setY(100*DuckHunt.Scale);
        levelPane.getChildren().add(statusText);
    }

    /**
     * Moves the status text by fading it in and out repeatedly.
     */
    public void moveAbleStatusText(){
        statusText2 = new Text();
        statusText2.setFont(Font.font(15*DuckHunt.Scale));
        statusText2.setFill(Color.ORANGE);
        statusText2.setX(40*DuckHunt.Scale);
        statusText2.setY(120*DuckHunt.Scale);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> statusText2.setVisible(true)),
                new KeyFrame(Duration.seconds(1), event -> statusText2.setVisible(false)),
                new KeyFrame(Duration.seconds(2), event -> statusText2.setVisible(true))
        );
        levelPane.getChildren().add(statusText2);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**
     * Spawns the ducks in the level.
     */
    private void spawnDucks() {
        levelPane.getChildren().add(redDuck);
        ducks.add(redDuck);
        levelPane.getChildren().add(blackDuck);
        ducks.add(blackDuck);

    }
    /**
     * Starts the movements of the ducks.
     */
    private void startDuckMovements() {
        blackDuck.moveType2(levelPane);
        redDuck.moveType2(levelPane);

    }
    /**
     * Returns the level pane containing the level elements.
     *
     * @return The Pane representing the level.
     */
    public Pane getLevelPane() {
        return levelPane;
    }

    /**
     * Checks if the level is completed.
     *
     * @return true if the level is completed, false otherwise.
     */
    public boolean isLevelComplate() {
        return levelComplate;
    }

    private void initializelevelText(String text) {
        levelText = new Text(text);
        levelText.setFont(Font.font("Verdana",5*DuckHunt.Scale));
        levelText.setFill(Color.ORANGE);
        levelText.setX(100*DuckHunt.Scale);
        levelText.setY(10*DuckHunt.Scale);
        levelPane.getChildren().add(levelText);
    }
}
