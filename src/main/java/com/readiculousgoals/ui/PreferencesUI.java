package com.readiculousgoals.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.readiculousgoals.model.*;

public class PreferencesUI {

    public PreferencesUI(RegularUser user) {
        JFrame frame = new JFrame("Set Your Preferences");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        JLabel welcomeLabel = new JLabel("Select your favorite genres:");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Create an array of genres
        String[] genres = {
            "Romance", "Crime", "Mystery", "Sci-Fi", "Drama", "Horror", 
            "Fantasy", "Classics", "Thriller", "Science", "Religion", 
            "Biography", "Educational", "History"
        };

        // Panel for checkboxes
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(genres.length, 1));

        // Create checkboxes for genres
        JCheckBox[] genreCheckBoxes = new JCheckBox[genres.length];
        for (int i = 0; i < genres.length; i++) {
            genreCheckBoxes[i] = new JCheckBox(genres[i]);
            genreCheckBoxes[i].setSelected(user.hasPreference(genres[i])); // Pre-select if already in preferences
            checkBoxPanel.add(genreCheckBoxes[i]);
        }

        // Save Button
        JButton saveButton = new JButton("Save Preferences");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.WHITE);

        // Action Listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update user's preferences
                for (int i = 0; i < genres.length; i++) {
                    if (genreCheckBoxes[i].isSelected()) {
                        user.addPreference(genres[i]);
                    } else {
                        user.removePreference(genres[i]);
                    }
                }

                // Show confirmation message
                JOptionPane.showMessageDialog(frame, "Your preferences have been saved!");

                // Display the updated preferences in the console (optional)
                System.out.println("Updated Preferences for " + user.getFullName() + ":");
                for (String preference : user.getPreferences()) {
                    System.out.println("- " + preference);
                }

                // Close the frame
                frame.dispose();
            }
        });

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(welcomeLabel, BorderLayout.NORTH);
        frame.add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }
}
