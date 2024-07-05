package views;

import utils.global;
import views.Home;
import views.HomeFrame;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


//----------------------------------------------class leaderboard panel--------------------------------------
public class LeaderboardPanel extends JPanel {
    static final int LEADERBOARD_WIDTH = 800;
    static final int LEADERBOARD_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(LEADERBOARD_WIDTH, LEADERBOARD_HEIGHT);

    JTextArea leaderboardTextArea;

    //class leaderboard panel where the size, colour, label and scrollable text to display the leaderboard panel
    public LeaderboardPanel() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        //--------------------------------------Create and customize the title label-----------------------------------
        JLabel titleLabel = getjLabel();


        //------------------------------------------------title label to the top of the panel---------------------------
        add(titleLabel, BorderLayout.NORTH);

        leaderboardTextArea = new JTextArea();
        leaderboardTextArea.setEditable(false);
        leaderboardTextArea.setBackground(Color.BLACK); // Set background color of text area to black
        leaderboardTextArea.setForeground(Color.WHITE); // Set text color to white
        leaderboardTextArea.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.PLAIN, 20)); // Set font and size

        JScrollPane scrollPane = new JScrollPane(leaderboardTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border around scroll pane

        add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = getjButton();
        // --------------------back button to the bottom of the panel----------------------------------------------
        add(backBtn, BorderLayout.SOUTH);

        displayLeaderboard();
    }

    // -------------------------- Get J Label Values ----------------------------------------
    private static JLabel getjLabel() {
        JLabel titleLabel = new JLabel(global.LeaderBoard.getValue(), SwingConstants.CENTER);


        //------------------------------------- font and size----------------------------------------
        titleLabel.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 24));


        //colour text for leaderboard label
        titleLabel.setForeground(Color.WHITE);


        // Allow background color to be set
        titleLabel.setOpaque(true);

        // Set background color
        titleLabel.setBackground(Color.BLACK);
        return titleLabel;
    }

    // ---------------------- Set the Values here for Get J Buttons -----------------------------------
    private JButton getjButton() {
        JButton backBtn = new JButton(global.HomeBtnTitle.getValue());
        backBtn.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 18));
        backBtn.setForeground(Color.WHITE); // Set text color
        backBtn.setBackground(new Color(247, 39, 152));
        backBtn.setPreferredSize(new Dimension(200, 50));
        backBtn.addActionListener(e -> {


            //-----------------------Navigate back to the home page------------------------------------------------
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getWindowAncestor(this);
            homeFrame.setContentPane(new Home(homeFrame));
            homeFrame.revalidate();
            homeFrame.repaint();
        });
        return backBtn;
    }

    // ----------------------- private class within the class leaderboard panel to file handle the text information ------
    // ------------------ and display in the leaderboard area ------------------------------------------------------------
    private void displayLeaderboard() {

        try {
            File file = new File(global.FileName.getValue());
            Scanner scanner = new Scanner(file);

            StringBuilder leaderboardData = new StringBuilder();
            while (scanner.hasNextLine()) {
                leaderboardData.append(scanner.nextLine()).append("\n");
            }

            leaderboardTextArea.setText(leaderboardData.toString());

            scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, global.FileError.getValue(), global.ErrorTitle.getValue(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
