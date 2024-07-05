package components;

import java.awt.*;
import java.util.Random;

public class Sphere extends Rectangle{
    // generate random directions for the pong ball and ve
    Random random;
    public int xFleetness;
    public int yFleetness;


    //---------------------------------------Initial speed of the ball-------------------------------------------
    int initialSpeed = 3; // Initial speed of the ball.

    //---------------------------------------------------------Colour ball ------------------------------------------------
    Color chooseColor;

    //----------------------------------Constructor class to initialize a new instance of the ball class--------------------------------
    public Sphere(int x, int y, int width, int height) {

        //Calls the rectangle constructor to set position and size.
        super(x, y, width, height);
        random = new Random();


        // ----------------- colour ball -------------------------------
        this.chooseColor = new Color(143, 0, 255);

        //------------------------------------initial horizontal direction of the ball randomly-----------------
        int randomDirectionX = random.nextInt(2);
        if (randomDirectionX == 0) randomDirectionX--;
        setDirectionX(randomDirectionX * initialSpeed);

        //----------------------------------initial vertical direction of the ball randomly-------------------------
        int randomDirectionY = random.nextInt(2);
        if (randomDirectionY == 0) randomDirectionY--;
        setDirectionY(randomDirectionY * initialSpeed);
    }

    //-------------------------------Sets the horizontal direction and speed of the ball-------------------------
    public void setDirectionX(int randomDirectionX) {
        xFleetness = randomDirectionX;
    }

    //-----------------------Sets the vertical direction and speed of the ball---------------------------------------
    public void setDirectionY(int randomDirectionY) {
        yFleetness = randomDirectionY;
    }

    //--------------------------------------- class to update the ball speed velocity.
    public void move() {
        x += xFleetness;
        y += yFleetness;
    }

    //-----------------------------------------------Draws the ball on the screen-------------------------------------
    public void draw(Graphics g) {
        g.setColor(chooseColor);
        g.fillOval(x, y, height, width);
    }

}
