package com.readiculousgoals.ui;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.readiculousgoals.model.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class PreferencesUI {
    // Add a ReaderHomepageUI reference to update it after preferences change
    private ReaderHomepageUI readerHomepage;

    public PreferencesUI(RegularUser user, ReaderHomepageUI readerHomepageUI) {
        JFrame frame = new JFrame("Set Your Preferences");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 600);
        JLabel welcomeLabel = new JLabel("Select your favorite genres:");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
        Genre[] genres = {
            new Romance(), new Crime(), new Mystery(), new SciFi(), new Drama(),
            new Horror(), new Fantasy(), new Classics(), new Thriller(),
            new Science(), new Religion(), new Biography(), new Educational(), new History()
        };
    
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(genres.length, 1));
    
        JCheckBox[] genreCheckBoxes = new JCheckBox[genres.length];
        for (int i = 0; i < genres.length; i++) {
            genreCheckBoxes[i] = new JCheckBox(genres[i].getName());
            genreCheckBoxes[i].setSelected(user.hasPreference(genres[i]));
            checkBoxPanel.add(genreCheckBoxes[i]);
        }
    
        JButton saveButton = new JButton("Save Preferences");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 14));
        saveButton.setBackground(Color.decode("#7a4f29"));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBorder(new CompoundBorder(
            new EmptyBorder(0, 0, 0, 0), 
            BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#7a4f29"))
        ));
    
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < genres.length; i++) {
                    if (genreCheckBoxes[i].isSelected()) {
                        user.addPreference(genres[i]);
                    } else {
                        user.removePreference(genres[i]);
                    }
                }
    
                JOptionPane.showMessageDialog(frame, "Your preferences have been saved!");
    
                // Print the updated preferences
                System.out.println("Updated Preferences for " + user.getFullName() + ":");
                for (Genre preference : user.getPreferences()) {
                    System.out.println("- " + preference.getName());
                }
    
                saveUserToFile(user);
    
                // Notify ReaderHomepageUI to refresh itself
                readerHomepageUI.refreshHomepage();
    
                frame.dispose();
            }
        });
    
        frame.setLayout(new BorderLayout());
        frame.add(welcomeLabel, BorderLayout.NORTH);
        frame.add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);
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