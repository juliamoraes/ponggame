package filehandling;

import utils.global;

import java.awt.*;

public class Score extends Rectangle {

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	public int player1;
	public int player2;

	public Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		//---------------------------------Define a dashed stroke------------------------------------------------
		float[] dashPattern = {10, 5}; // 10 pixels drawn, 5 pixels skipped
		BasicStroke dashedStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10, dashPattern, 0);
		g2d.setStroke(dashedStroke);


		// ---------------------------------- Draw the dashed line ----------------------------------
		g2d.setColor(Color.WHITE);
		g2d.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

		// ------------------------------------------------------- Reset the stroke to default after drawing the line -------------------------------------------------------
		g2d.setStroke(new BasicStroke());


		// ------------------------ Draw player scores -----------------------------------------------------

		g2d.setColor(Color.WHITE);

		//------------------------font-size and text--------------------------------------------------------
		g2d.setFont(new Font(global.TextStyle.getValue(), Font.PLAIN, 60));
		g2d.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), (GAME_WIDTH / 2) - 85, 50);
		g2d.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (GAME_WIDTH / 2) + 20, 50);
	}
}

