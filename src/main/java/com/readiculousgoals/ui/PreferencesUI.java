package com.readiculousgoals.ui;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.readiculousgoals.model.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class PreferencesUI {
    public PreferencesUI(RegularUser user) {
        JFrame frame = new JFrame("Set Your Preferences");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        JLabel welcomeLabel = new JLabel("Select your favorite genres:");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // Create an array of genres (using Genre objects directly)
        Genre[] genres = {
            new Romance(), new Crime(), new Mystery(), new SciFi(), new Drama(),
            new Horror(), new Fantasy(), new Classics(), new Thriller(),
            new Science(), new Religion(), new Biography(), new Educational(), new History()
        };
        // Panel for checkboxes
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(genres.length, 1));
        // Create checkboxes for genres
        JCheckBox[] genreCheckBoxes = new JCheckBox[genres.length];
        for (int i = 0; i < genres.length; i++) {
            genreCheckBoxes[i] = new JCheckBox(genres[i].getName());
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
                // Update user's preferences based on selected checkboxes
                for (int i = 0; i < genres.length; i++) {
                    if (genreCheckBoxes[i].isSelected()) {
                        user.addPreference(genres[i]); // Add genre object (Set prevents duplicates)
                    } else {
                        user.removePreference(genres[i]); // Remove genre object
                    }
                }

                // Show confirmation message
                JOptionPane.showMessageDialog(frame, "Your preferences have been saved!");

                // Display the updated preferences in the console (optional)
                System.out.println("Updated Preferences for " + user.getFullName() + ":");
                for (Genre preference : user.getPreferences()) {
                    System.out.println("- " + preference.getName());
                }

                // Serialize the updated user object to users.dat
                saveUserToFile(user);

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
    private void saveUserToFile(RegularUser user) {
        File file = new File("src/main/java/com/readiculousgoals/data/users.dat");

        try {
            // Read existing users from the file
            List<RegularUser> users = new ArrayList<>(15);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    users = (List<RegularUser>) ois.readObject();
                } catch (EOFException e) {
                    // Handle case where file exists but is empty
                }
            }

            // Update or add the user
            boolean userUpdated = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(user.getUsername())) { // Assuming RegularUser has a `getUsername` method
                    users.set(i, user); // Update user
                    userUpdated = true;
                    break;
                }
            }
            if (!userUpdated) {
                users.add(user); // Add new user
            }

            // Write updated list back to the file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(users);
                System.out.println("User preferences saved to users.dat.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving preferences to file.");
        }
    }
}
