package views;

import utils.global;
import views.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InfoPanel extends JPanel {
    private final JTextField nameField;
    private final JSpinner ageSpinner;

    public InfoPanel(JFrame frame) {

        // ----------------- Create the Labels -----------------------------
        JLabel nameLabel = new JLabel(global.SetNameLabel.getValue());
        JLabel ageLabel = new JLabel(global.SetAgeLabel.getValue());

        // ------------------- Font Setting in this portion -------------------------
        Font labelFont = new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 24);
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.WHITE);
        ageLabel.setFont(labelFont);
        ageLabel.setForeground(Color.WHITE);

        // ------------------- Set background color of labels to black ----------
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        ageLabel.setOpaque(true);
        ageLabel.setBackground(Color.BLACK);

        // ------------------------- Create the Entry Box here and It's settings here --------------------
        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(200, 40)); // Increase vertical size
        nameField.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.PLAIN, 20)); // Increase text size

        // ----------------------- Create the Spinner Settings here for entering the numbers --------------
        SpinnerModel ageModel = new SpinnerNumberModel(18, 0, 120, 1);
        ageSpinner = new JSpinner(ageModel);
        ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField().setColumns(4);
        ageSpinner.setPreferredSize(new Dimension(100, 40)); // Increase vertical size

        // ---------------------- Create Start Button for play the game ---------------------
        JButton startBtn = getjButton(frame);

        // ---------------- Set the Layouts -------
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding
        gbc.anchor = GridBagConstraints.CENTER;

        // ------------- Add nameLabel with GridBagConstraints --------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameLabel, gbc);

        gbc.gridy++;
        add(nameField, gbc);

        gbc.gridy++;
        add(ageLabel, gbc);

        gbc.gridy++;
        add(ageSpinner, gbc);

        gbc.gridy++;
        add(startBtn, gbc);

        // Set background color to black
        setBackground(Color.BLACK);
    }

    // -----------------------------------------------------------------------------------------------------
    // Set the Button Theme and functionality checks by in this area regard valid thing putted or not ------
    // -----------------------------------------------------------------------------------------------------
    private JButton getjButton(JFrame frame) {
        JButton startBtn = new JButton(global.StartBtnTitle.getValue());

        // Style the button
        startBtn.setFont(new Font(global.LeaderBoardFontStyle.getValue(), Font.BOLD, 24));
        startBtn.setForeground(Color.WHITE);
        startBtn.setBackground(new Color(247, 39, 152)); // Pink color from the home page

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(global.StartBtnTitle.getValue())) {
                    String name = nameField.getText();
                    int age = (int) ageSpinner.getValue();
                    // Check if name and age are not empty
                    if (!name.trim().isEmpty() && age > 0) {
                        // Save user information to text file
                        saveUserInfo(name, age);
                        // Start the game
                        startGame();
                    } else {
                        // Display error message if name or age is empty
                        JOptionPane.showMessageDialog(frame, global.ShowMessageBoxText.getValue() , global.ErrorTitle.getValue(), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        return startBtn;
    }

    // --------------- File Handling Function Save user data into the Files ------------------
    private void saveUserInfo(String name, int age) {
        // Write user information to a text file
        try (FileWriter writer = new FileWriter(global.FileName.getValue(), true)) {
            writer.write(global.SetNameLabel.getValue() + ": " + name + "\n");
            writer.write( global.SetAgeLabel.getValue() + ": " + age + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // --------------------- This Function Will Start the Game ---------------------------
    private void startGame() {
        // Create a views.GameFrame instance
        new GameFrame();
        // Close the current frame (views.InfoPanel)
        SwingUtilities.getWindowAncestor(this).dispose();
    }
}
