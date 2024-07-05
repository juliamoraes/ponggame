package components;
import java.awt.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    //---------------------------------------- variable to create the Paddles --------------------------------------
    int paddleId;
    int yFleetness;
    Color chooseColor;

    //-----------------------------------------------constant variable paddle speed-----------------------
    static int speed = 10;
    private int PredecessorY;

    //-----------------------Constructor: Initializes the paddle with its position, size, and ID.----------------------
    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int paddleId) {

        // Calls the Rectangle constructor to set position and size.
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.paddleId = paddleId;
        this.PredecessorY = y;
        this.chooseColor = new Color(143, 0, 255);
    }

    // Handles key press events for moving the paddle.
    public void pressedKey(KeyEvent e) {
        switch (paddleId) { // Checks which paddle (player) is being controlled.
            case 1: // For player 1.
                if (e.getKeyCode() == KeyEvent.VK_UP) { // Move up.
                    setDirectionY(-speed);
                    movePaddle();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) { // Move down.
                    setDirectionY(speed);
                    movePaddle();
                }
                break;
            // Add a case for other players if multiplayer and using different keys. ***IT DID NOT WORK****
        }
    }

    //--------------------class to handles key release events to stop the paddles movement
    public void releasedKey(KeyEvent e) {
        switch (paddleId) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setDirectionY(0);
                }
                break;

        }
    }

    // -------------------- Velocity of the Paddle ----------------------------
    // -------------- Updates the paddle's position based on its Speed. -------
    // ------------------------------------------------------------------------
    public void movePaddle() {
        PredecessorY = y; // Store the current position before moving.
        y += yFleetness; // Move the paddle.
    }

    //------------------------------- Class paddle to draw the paddle on the game panel ----------------------------------
    public void draw(Graphics g) {

        //paddle colour
        g.setColor(chooseColor);

        g.fillRect(x, y, width, height);
    }

    // ------------------ Class Utils Function Common function -----------------------------
    // -------------- Sets the vertical direction and speed of the paddle. -----------------
    // -------------------------------------------------------------------------------------
    public void setDirectionY(int directionY) {
        yFleetness = directionY;
    }
}

