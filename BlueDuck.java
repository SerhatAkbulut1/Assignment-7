import javafx.scene.image.Image;

/**
 * BlueDuck class which is child class of Duck abstact class
 */
public class BlueDuck extends Duck{

    /**
     * Contructor that loads blueducks images
     */
    public BlueDuck() {
        image1 = new Image("file:assets/duck_blue/1.png");
        image2 = new Image("file:assets/duck_blue/2.png");
        image3 = new Image("file:assets/duck_blue/3.png");
        image4 = new Image("file:assets/duck_blue/4.png");
        image5 = new Image("file:assets/duck_blue/5.png");
        image6 = new Image("file:assets/duck_blue/6.png");
        image7 = new Image("file:assets/duck_blue/7.png");
        image8 = new Image("file:assets/duck_blue/8.png");
    }
}
