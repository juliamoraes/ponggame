package views;

import utils.global;
import views.GamePanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	GamePanel panel;


	//-------------------------------- Pass the JFrame instance to views.GamePanel constructor --------------------
	GameFrame() {

		panel = new GamePanel(this);
		this.add(panel);
		this.setTitle(global.Title.getValue());
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//------------------------------------Create an exit button-----------------------------------
		JButton exitBtn = getjButton();

		//------------------------------padding around the button----------------------------------------
		exitBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		//-------------------------------- exit button to the frame-------------------------------------
		this.add(exitBtn, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private JButton getjButton() {
		JButton exitBtn = new JButton(global.ExitBtnTitle.getValue());
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//---------------------------------Create and show a new views.HomeFrame-----------------------
				panel.pauseGame();
				panel.saveScoresToFile();
				HomeFrame homeFrame = new HomeFrame();
				homeFrame.setVisible(true);


				//---------------------------Hide the current game frame----------------------------------
				setVisible(false);
			}
		});
		return exitBtn;
	}
}
