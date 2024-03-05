import javafx.scene.image.Image;

/**
 * BlackDuck class which is child class of Duck abstact class
 */
public class BlackDuck extends Duck{

    /**
     * Contructor that loads blackducks images
     */
    public BlackDuck() {
         image1 = new Image("file:assets/duck_black/1.png");
         image2 = new Image("file:assets/duck_black/2.png");
         image3 = new Image("file:assets/duck_black/3.png");
         image4 = new Image("file:assets/duck_black/4.png");
         image5 = new Image("file:assets/duck_black/5.png");
         image6 = new Image("file:assets/duck_black/6.png");
         image7 = new Image("file:assets/duck_black/7.png");
         image8 = new Image("file:assets/duck_black/8.png");

    }
}
