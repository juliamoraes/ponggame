package views;

import components.Paddle;
import components.Sphere;
import filehandling.Score;
import utils.global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


class GamePanel extends JPanel implements Runnable {

	// --------------------- Create the Constant Static Variables --------------------------
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;

	boolean gameOver = false;
	static int computerPaddleSpeed = 10;
	int previousPaddle2Y = (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2);

	int savedPlayer1Score = 0;
	int savedPlayer2Score = 0;

	private boolean paused = false;


	// ------------------------------------------- Create the Objects of the Classes ----------------------------------------------
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Sphere orb;
	Score score;




    public GamePanel(JFrame frame) {
        // Add a JFrame instance variable
        setLayout(new GridBagLayout());

		// Create the exit button
        JButton exitBtn = new JButton(global.ExitBtnTitle.getValue());
		exitBtn.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 16));
		exitBtn.setForeground(Color.WHITE);
		exitBtn.setBackground(Color.WHITE); // Pink color from the home page
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Quit the system
				System.exit(0);
			}
		});

		// Add the exit button to the panel using GridBagConstraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1; // Adjust the grid position for the exit button
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST; // Align the button to the right
		gbc.insets = new Insets(10, 10, 10, 10); // Add padding
		add(exitBtn, gbc);

		// Other initialization code...

		// Set preferred size of the panel
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));


		newPaddle();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);


		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Save scores when the window is closing
				saveScoresToFile();
			}
		});

		gameThread = new Thread(this);
		gameThread.start();
	}



	public void newBall() {
		random = new Random();
		orb = new Sphere((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
	}

	public void newPaddle() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);

	}

	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		orb.draw(g);
		score.draw(g);
	}

	public void moveThePaddles() {
		paddle1.movePaddle();
		paddle2.movePaddle();
		orb.move();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void checkCollision() {

		//Stop the Ball at the window edges!
		if (orb.y <= 0) {
			orb.setDirectionY(-orb.yFleetness);
		}
		if (orb.y >= GAME_HEIGHT - BALL_DIAMETER) {
			orb.setDirectionY(-orb.yFleetness);
		}
		// Check if either player's score reaches 11
		if (score.player1 >= 11 || score.player2 >= 11) {
			// Set gameOver to true
			gameOver = true;
			saveScoresToFile();

			// Determine the winner
			String winner;
			if (score.player1 >= 11) {
				winner = global.PlayerOneTitle.getValue();;
			} else{
				winner = global.PlayerTwoTitle.getValue();
			}

			// Display the message box with restart and quit options
			int choice = JOptionPane.showOptionDialog(
					null,
					winner + global.WinnerText.getValue(),
					global.GameOverText.getValue(),
					JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					new String[]{global.RestartGameTitle.getValue(), global.QuitGameTitle.getValue()},
					null);

			// Handle the user's choice
			if (choice == JOptionPane.YES_OPTION) {
				restartGame();
			} else {
				quitGame();
				saveScoresToFile();
			}
		}


		// Bounce the Ball after collision with Paddles
		if (orb.intersects(paddle1)) {
			orb.xFleetness = Math.abs(orb.xFleetness);
			orb.xFleetness++; //IT is Optional for Difficulty
			if (orb.yFleetness > 0)
				orb.yFleetness++; //Optional for Difficulty
			else
				orb.yFleetness--;
			orb.setDirectionX(orb.xFleetness);
			orb.setDirectionY(orb.yFleetness);
		}
		if (orb.intersects(paddle2)) {
			orb.xFleetness = Math.abs(orb.xFleetness);
			orb.xFleetness++; //IT is Optional for Difficulty
			if (orb.yFleetness > 0)
				orb.yFleetness++; //Optional for Difficulty
			else
				orb.yFleetness--;
			orb.setDirectionX(-orb.xFleetness);
			orb.setDirectionY(orb.yFleetness);
		}


		// IF ball cross the screen give players a score and then create a new BALL and PADDLES.
		if (orb.x <= 0) {
			score.player2++;
			newPaddle();
			newBall();

		}
		if (orb.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddle();
			newBall();
		}

		//stop the Paddles at the Window Edges!
		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

	}
	public void saveScoresToFile() {
		try (FileWriter writer = new FileWriter(global.FileName.getValue(), true)) {
			writer.write(global.Player1Score.getValue() + ": " + score.player1 + "\n");
			writer.write(global.Player2Score.getValue() + ": " + score.player2 + "\n");
			writer.write("\n"); // Add a newline for better readability
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void restartGame() {
		// Add code to reset the game (e.g., reset scores, positions, etc.)
		score.player1 = 0;
		score.player2 = 0;
		// Reset paddle and ball positions
		newBall();
		newPaddle();



		//----------------------------Continue the game by setting gameOver to false-----------------------
		gameOver = false;
        /*--------------did not work//		switchToGamePanel(); */

	}

	public void quitGame() {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.dispose();
		System.exit(0);
	}



	public void run() {
		while (!gameOver) {
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;

			while (!paused && !gameOver) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				if (delta >= 1) {
					moveThePaddles();
					checkCollision();
					updateComputerPaddle();
					repaint();
					delta--;
				}
			}
		}
	}


	// Method to update computer-controlled paddle's position with interpolation
	public void updateComputerPaddle() {
		if (orb.xFleetness > 0) { // Ball is moving towards the computer-controlled paddle
			if (paddle2.y + (PADDLE_HEIGHT / 2) < orb.y + (BALL_DIAMETER / 2)) {
				paddle2.setDirectionY(computerPaddleSpeed);
			} else if (paddle2.y + (PADDLE_HEIGHT / 2) > orb.y + (BALL_DIAMETER / 2)) {
				paddle2.setDirectionY(-computerPaddleSpeed);
			} else {
				paddle2.setDirectionY(0);
			}
		} else { // Ball is moving away from the computer-controlled paddle
			paddle2.setDirectionY(0);
		}

		// Ensure the paddle stays within the game area
		if (paddle2.y < 0) {
			paddle2.y = 0;
		} else if (paddle2.y + PADDLE_HEIGHT > GAME_HEIGHT) {
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}

		// Track previous position of paddle2
		previousPaddle2Y = paddle2.y;
	}
	//----------------------------------------- Set the paused flag to true-------------------------------------------
	public void pauseGame() {
		paused = true;                     }

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_SPACE) {
				if (!paused) {
					// Save scores and pause the game
					savedPlayer1Score = score.player1;
					savedPlayer2Score = score.player2;
				}
			} else {
				paddle1.pressedKey(e);
			}
		}

		public void keyReleased(KeyEvent e) {
			paddle1.releasedKey(e);
		}
	}
}