package views;

import utils.global;
import views.HomeFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JPanel implements ActionListener, KeyListener {

	static final int HOME_WIDTH = 1000;
	static final int HOME_HEIGHT = (int) (HOME_WIDTH * (0.5555));
	static final Dimension SCREEN = new Dimension(HOME_WIDTH, HOME_HEIGHT);
	Color chooseColor;

	HomeFrame homeFrame;
	LeaderboardPanel leaderboardPanel;

	Home(HomeFrame homeFrame) {
		this.homeFrame = homeFrame;

		// Create and customize the label for "PONG!"
		JLabel pongLabel = new JLabel(global.ShowTitlePong.getValue());
		pongLabel.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 40));
		pongLabel.setForeground(Color.WHITE);

		// Create and customize the button
		JButton startBtn = new JButton(global.StartBtnTitle.getValue());
		startBtn.addActionListener(this);
		this.chooseColor = new Color(247, 39, 152);
		startBtn.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 24)); // Change font
		startBtn.setForeground(Color.WHITE); // Text color
		startBtn.setBackground(chooseColor); // Button background color
		startBtn.setPreferredSize(new Dimension(200, 50)); // Button size

		// Create a panel to hold the label and buttons
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setPreferredSize(SCREEN);
		contentPanel.setBackground(Color.BLACK);

		// Set constraints for the "PONG!" label
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		labelConstraints.insets = new Insets(0, 0, 50, 0); // Add spacing below the label
		contentPanel.add(pongLabel, labelConstraints);

		// Set constraints for the start button with additional bottom inset for spacing
		GridBagConstraints startBtnConstraints = new GridBagConstraints();
		startBtnConstraints.gridx = 0;
		startBtnConstraints.gridy = 1;
		startBtnConstraints.insets = new Insets(0, 0, 20, 0); // Add bottom inset for spacing
		contentPanel.add(startBtn, startBtnConstraints);

		JButton leaderboardBtn = new JButton(global.LeaderBoard.getValue());
		leaderboardBtn.addActionListener(this);
		leaderboardBtn.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 24));
		leaderboardBtn.setForeground(Color.WHITE);
		leaderboardBtn.setBackground(chooseColor);
		leaderboardBtn.setPreferredSize(new Dimension(200, 50));

		// Add the leaderboard button to the content panel
		GridBagConstraints leaderboardBtnConstraints = new GridBagConstraints();
		leaderboardBtnConstraints.gridx = 0;
		leaderboardBtnConstraints.gridy = 2;
		contentPanel.add(leaderboardBtn, leaderboardBtnConstraints);

		// Add the content panel to the views.Home panel
		add(contentPanel, BorderLayout.CENTER);

		this.setFocusable(true);
		this.setPreferredSize(SCREEN);
		this.setBackground(Color.BLACK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(global.StartBtnTitle.getValue())) {
			// Assuming homeFrame is accessible here, either as a field or somehow in scope
			InfoPanel infoPanel = new InfoPanel(homeFrame); // Create an views.InfoPanel, pass the current frame if needed

			// Update the homeFrame's content pane to show the views.InfoPanel
			homeFrame.setContentPane(infoPanel);
			homeFrame.revalidate(); // Ensure the UI is updated
			homeFrame.repaint(); // Repaint to reflect the changes visually

			// No need to dispose of homeFrame since we're reusing it
		} else if (e.getActionCommand().equals(global.LeaderBoard.getValue())) {
			// Switch to the views.LeaderboardPanel
			switchToLeaderboardPanel();
		}
	}

	// ------------------------ Open the Leaderboard window -------------------------
	private void switchToLeaderboardPanel() {
		leaderboardPanel = new LeaderboardPanel();
		homeFrame.setContentPane(leaderboardPanel);
		homeFrame.revalidate();
		homeFrame.repaint();
	}

	// ----------------------------------------------------------------------------------------------------------
	// ------------------------- The void Function Required for pass the event buttons Regarding keys -----------
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
