import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class Duck extends ImageView {
   /* private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final double FALL_SPEED = 2.0;

    protected double x;
    protected double y;
    protected double speedX;
    protected double speedY;
    protected boolean movingRight;
    protected boolean alive;
    protected boolean falling;

    public Duck(double x, double y, double speedX,double speedY, boolean movingRight) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.movingRight = movingRight;
        this.alive = true;
        this.falling = false;
    }*/
    protected double x;
    protected double y;
    protected double speedX;
    protected double speedY;
    //boolean isShot = false;
    public Duck(double x,double y, double xVelocity,double yVelocity){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }
    public void moveType1(Pane root){

    }
    public void moveType2(Pane root){

    }
    /*public void setShot(boolean shot) {
        isShot = shot;
    }


    public boolean isShot() {
        return isShot;
    }*/
}



