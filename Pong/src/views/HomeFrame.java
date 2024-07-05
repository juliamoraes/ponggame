package views;

import utils.global;

import java.awt.*;
import javax.swing.*;

public class HomeFrame extends JFrame {

	// ---------------------------------------
	// Creating the object views.Home Class
	Home home;

	// This is our views.Home Frame
	public HomeFrame() {
		home = new Home(this); // Pass the views.HomeFrame instance
		this.add(home);
		// --- set the title from Enum Files
		this.setTitle(global.HomeScreen.getValue());
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(HomeFrame::new);
	}
}
