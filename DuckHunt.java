import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * DuckHunt is a JavaFX application that represents the classic game "Duck Hunt."
 * It allows players to select background and foreground images, choose a crosshair,
 * and start the game. The game consists of shooting ducks as they appear on the screen.
 */
public class DuckHunt extends Application {
    // Constants for file paths and game settings
    private static final String TITLE = "HUBBM Duck Hunt";
    private static final String FAVICON_PATH = "assets/favicon/1.png";
    private static final String WELCOME_BACKGROUND_PATH = "assets/welcome/1.png";
    private static final String TITLE_MUSIC_PATH = "assets/effects/Title.mp3";
    private static final String INTRO_MUSIC_PATH = "assets/effects/Intro.mp3";



    private static final String[] BACKGROUND_PATHS = {
            "assets/background/1.png",
            "assets/background/2.png",
            "assets/background/3.png",
            "assets/background/4.png",
            "assets/background/5.png",
            "assets/background/6.png"
    };
    private static final String[] FOREGROUND_PATHS = {
            "assets/foreground/1.png",
            "assets/foreground/2.png",
            "assets/foreground/3.png",
            "assets/foreground/4.png",
            "assets/foreground/5.png",
            "assets/foreground/6.png"
    };
    private static final String[] CROSSHAIR_PATHS = {
            "assets/crosshair/1.png",
            "assets/crosshair/2.png",
            "assets/crosshair/3.png",
            "assets/crosshair/4.png",
            "assets/crosshair/5.png",
            "assets/crosshair/6.png",
            "assets/crosshair/7.png"
    };
    public static final double Volume = 0.5;
    public static final double Scale = 4.0;
    public static final double Height = 240 * Scale;
    public static final double Width = 256 * Scale;
    private Stage primaryStage;
    private StackPane root;
    private Image[] backgrounds;
    private Image[] foregrounds;
    private int currentBackgroundIndex = 0;
    private int currentCrosshairIndex = 0;
    private ImageView foregroundImageView;
    private FadeTransition fadeTransition;
    private MediaPlayer titleMusicPlayer;
    private MediaPlayer introMusicPlayer;

    private boolean backgroundForegroundSelectable = false;
    private boolean gameStarted = false;
    private ImageView crosshairImageView;
    static boolean level1Complate;
    static boolean level2Complate;
    static boolean level3Complate;
    static boolean level4Complate;
    static boolean level5Complate;
    static boolean level6Complate;


    /**
     * The entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set.
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Set the title and favicon of the primary stage.
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(FAVICON_PATH)));

        // Load the background images.
        backgrounds = new Image[BACKGROUND_PATHS.length];
        for (int i = 0; i < BACKGROUND_PATHS.length; i++) {
            backgrounds[i] = new Image(getClass().getResourceAsStream(BACKGROUND_PATHS[i]));
        }

        // Load the foreground images.
        foregrounds = new Image[FOREGROUND_PATHS.length];
        for (int i = 0; i < FOREGROUND_PATHS.length; i++) {
            foregrounds[i] = new Image(getClass().getResourceAsStream(FOREGROUND_PATHS[i]));
        }

        // Create a BackgroundImage for the welcome background.
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResourceAsStream(WELCOME_BACKGROUND_PATH)),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        // Set the background image.
        root = new StackPane();
        root.setBackground(new Background(backgroundImage));

        // Create an ImageView for the foreground image.
        foregroundImageView = new ImageView();
        foregroundImageView.setPreserveRatio(true);
        foregroundImageView.setFitWidth(Width);
        foregroundImageView.setFitHeight(Height);

        // Create the text.
        Text text = new Text();
        text.setFont(Font.font("Verdana", 20 * Scale));
        text.setFill(Color.ORANGE);

        root.getChildren().addAll(foregroundImageView, text);


        // Create the scene.
        Scene scene = new Scene(root, Width, Height);
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));

        // Set the scene to the primary stage and show it.
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();



        // Show the start text.
        showStartText("PRESS ENTER TO PLAY", "PRESS ESC TO EXIT");

        // Load and play the title music
        Media titleMusic = new Media(getClass().getResource(TITLE_MUSIC_PATH).toString());
        titleMusicPlayer = new MediaPlayer(titleMusic);
        titleMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        titleMusicPlayer.setVolume(Volume);
        // Load the intro music.
        Media introMusic = new Media(getClass().getResource(INTRO_MUSIC_PATH).toString());
        introMusicPlayer = new MediaPlayer(introMusic);
        titleMusicPlayer.play();
        introMusicPlayer.setVolume(Volume);
    }
    /**
     * Handles the key press events.
     *
     * @param keyCode The code of the key that was pressed.
     */
    private void handleKeyPress(KeyCode keyCode) {
        if (!gameStarted) {
            if (keyCode == KeyCode.ENTER) {
                setBackground();
                setForeground();
                if (!backgroundForegroundSelectable) {
                    // First ENTER key press, transition to the background and foreground selection screen.
                    backgroundForegroundSelectable = true;
                    showMenuText("USE ARROW KEYS TO NAVIGATE", "PRESS ENTER TO START", "PRESS ESC TO EXIT");
                    addCrosshair();
                    removeCrosshair();
                } else {
                    titleMusicPlayer.stop();
                    // Second ENTER key press, set the background and start the game.
                    removeCrosshair();
                    introMusicPlayer.play();
                    introMusicPlayer.setOnEndOfMedia(this::startGame);
                    // Remove the start text.
                    removeText();

                }
            } else if (backgroundForegroundSelectable && keyCode == KeyCode.RIGHT) {
                // Pressing RIGHT arrow key to display the next background and foreground
                currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
                setBackground();
                setForeground();
            } else if (backgroundForegroundSelectable && keyCode == KeyCode.LEFT) {
                // Pressing LEFT arrow key to display the previous background and foreground
                currentBackgroundIndex = (currentBackgroundIndex - 1 + backgrounds.length) % backgrounds.length;
                setBackground();
                setForeground();
            }else if (backgroundForegroundSelectable && keyCode == KeyCode.UP) {
                // Pressing UP arrow key to display the next crosshair
                currentCrosshairIndex = (currentCrosshairIndex + 1) % CROSSHAIR_PATHS.length;
                updateCrosshair();
            } else if (backgroundForegroundSelectable && keyCode == KeyCode.DOWN) {
                // Pressing DOWN arrow key to display the previous crosshair
                currentCrosshairIndex = (currentCrosshairIndex - 1 + CROSSHAIR_PATHS.length) % CROSSHAIR_PATHS.length;
                updateCrosshair();
            } else if (keyCode == KeyCode.ESCAPE) {
                // Pressing ESC key to exit the application
                primaryStage.close();
            }
        } else if (keyCode == KeyCode.ESCAPE) {
            // Pressing ESC key to pause the game
            // Perform necessary actions to pause the game here
        }
    }

    /**
     * Sets the background image.
     */
    private void setBackground() {
        // Update the background image
        BackgroundImage backgroundImage = new BackgroundImage(
                backgrounds[currentBackgroundIndex],
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(backgroundImage));
    }
    /**
     * Sets the foreground image.
     */
    private void setForeground() {
        // Update the foreground image
        foregroundImageView.setImage(foregrounds[currentBackgroundIndex]);
    }
    /**
     * Shows the start text and instructions.
     *
     * @param startText       The start text to be displayed.
     * @param instructionText The instruction text to be displayed.
     */
    private void showStartText(String startText, String instructionText) {
        // Show the start text and instructions
        Text text = (Text) root.getChildren().get(1);
        text.setText(startText + "\n" + "  " + instructionText);
        text.setTranslateY(40*Scale);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> text.setVisible(true)),
                new KeyFrame(Duration.seconds(1), event -> text.setVisible(false)),
                new KeyFrame(Duration.seconds(2), event -> text.setVisible(true))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**
     * Removes the start text.
     */
    private void removeText() {
        // Remove the start text
        root.getChildren().remove(1);
    }
    /**
     * Shows the menu text.
     *
     * @param firstLine  The text to be displayed on the first line.
     * @param secondLine The text to be displayed on the second line.
     * @param thirdLine  The text to be displayed on the third line.
     */

    private void showMenuText(String firstLine, String secondLine, String thirdLine) {
        Text text = (Text) root.getChildren().get(1);
        text.setText(firstLine + "\n" + "\t" + secondLine + "\n" + "\t  " + thirdLine);
        text.setTranslateY((-80*Scale));
        text.setFont(Font.font("Verdana", 8*Scale));
        text.setFill(Color.ORANGE);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> text.setVisible(true)),
                new KeyFrame(Duration.seconds(1), event -> text.setVisible(false)),
                new KeyFrame(Duration.seconds(2), event -> text.setVisible(true))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**
     * Adds the crosshair image.
     */
    private void addCrosshair() {
        // Load the crosshair image
        String crosshairPath = CROSSHAIR_PATHS[currentCrosshairIndex];
        Image crosshairImage = new Image(getClass().getResourceAsStream(crosshairPath));
        ImageView crosshairImageView = new ImageView(crosshairImage);
        crosshairImageView.setPreserveRatio(true);
        crosshairImageView.setFitWidth(Width/30);
        crosshairImageView.setFitHeight(Height/30);
        crosshairImageView.setX(Width/2);
        crosshairImageView.setY(Height/2);

        // Position the crosshair image at the center
        crosshairImageView.setTranslateX(-crosshairImageView.getFitWidth() / 2);
        crosshairImageView.setTranslateY(-crosshairImageView.getFitHeight() / 2);

        // Add the crosshair image to the root pane
        root.getChildren().add(crosshairImageView);
    }
    /**
     * Removes the crosshair image.
     */
    private void removeCrosshair() {
        root.getChildren().remove(crosshairImageView);
    }
    /**
     * Updates the crosshair image.
     *
     * @return The updated crosshair image view.
     */
    private ImageView updateCrosshair() {
        // Update the crosshair image
        String crosshairPath = CROSSHAIR_PATHS[currentCrosshairIndex];
        Image crosshairImage = new Image(getClass().getResourceAsStream(crosshairPath));
        ImageView crosshairImageView = (ImageView) root.getChildren().get(root.getChildren().size() - 1);
        crosshairImageView.setImage(crosshairImage);
        return crosshairImageView;
    }
    /**
     * Starts the game by transitioning from the welcome screen to the game screen.
     */

   private void startGame(Level level) {
    gameStarted = true;
    Scene scene = new Scene(level.getLevelPane());
    scene.setOnKeyPressed(e -> handleKeyPress(e.getCode(), level));
    primaryStage.setScene(scene);
    primaryStage.show();
}

private void startLevel1() {
    Level1 level1 = new Level1(backgrounds[currentBackgroundIndex], foregrounds[currentBackgroundIndex], crosshairImage);
    startGame(level1);
}

private void startLevel2() {
    Level2 level2 = new Level2(backgrounds[currentBackgroundIndex], foregrounds[currentBackgroundIndex], crosshairImage);
    startGame(level2);
}

private void startLevel3() {
    Level3 level3 = new Level3(backgrounds[currentBackgroundIndex], foregrounds[currentBackgroundIndex], crosshairImage);
    startGame(level3);
}

private void startLevel4() {
    Level4 level4 = new Level4(backgrounds[currentBackgroundIndex], foregrounds[currentBackgroundIndex], crosshairImage);
    startGame(level4);
}

private void startLevel5() {
    Level5 level5 = new Level5(backgrounds[currentBackgroundIndex], foregrounds[currentBackgroundIndex], crosshairImage);
    startGame(level5);
}

private void startLevel6() {
    Level6 level6 = new Level6(backgrounds[currentBackgroundIndex], foregrounds[currentBackgroundIndex], crosshairImage);
    startGame(level6);
}

