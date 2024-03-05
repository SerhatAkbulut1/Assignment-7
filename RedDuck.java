import javafx.scene.image.Image;

/**
 * RedDuck class which is child class of Duck abstact class
 */
public class RedDuck extends Duck{

    /**
     * Contructor that loads redducks images
     */
    public RedDuck() {
        image1 = new Image("file:assets/duck_red/1.png");
        image2 = new Image("file:assets/duck_red/2.png");
        image3 = new Image("file:assets/duck_red/3.png");
        image4 = new Image("file:assets/duck_red/4.png");
        image5 = new Image("file:assets/duck_red/5.png");
        image6 = new Image("file:assets/duck_red/6.png");
        image7 = new Image("file:assets/duck_red/7.png");
        image8 = new Image("file:assets/duck_red/8.png");
    }
}
