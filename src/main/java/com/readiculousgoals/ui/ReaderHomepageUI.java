package com.readiculousgoals.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import com.readiculousgoals.model.RegularUser;
import com.readiculousgoals.model.User;

public class ReaderHomepageUI extends JFrame {
    private RegularUser user;
    private JPanel leftPanel;

    public ReaderHomepageUI(User newUser) {
        RegularUser user = (RegularUser) newUser;
        this.user = user;

        // Frame settings
        setTitle("Reader Homepage - Welcome, " + user.getFullName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left Panel (hidden by default)
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setBackground(Color.decode("#986842")); // Hex value for light gray
        leftPanel.setVisible(false);

        // Left Panel content using GridLayout
        leftPanel.setLayout(new GridLayout(13, 1, 0, 0));

        // Add buttons with hover and click effects
        JButton profileButton = createButton("Profile");
        profileButton.addActionListener(e -> openUserProfile());
        leftPanel.add(profileButton);

        JButton preferencesButton = createButton("Preferences");
        preferencesButton.addActionListener(e -> openPreferencesUI()); // Action listener for Preferences button
        leftPanel.add(preferencesButton);

        JButton readingGoalsButton = createButton("Reading Goals");
        leftPanel.add(readingGoalsButton);

        JButton tbrButton = createButton("My TBR");
        leftPanel.add(tbrButton);

        JButton settingsButton = createButton("Settings");
        leftPanel.add(settingsButton);

        JButton logoutButton = createButton("Logout");
        leftPanel.add(logoutButton);

        // Add the left panel to the frame
        add(leftPanel, BorderLayout.WEST);

        // Toggle button to show/hide the left panel
        ImageIcon icon = new ImageIcon("src/ui/icons/menu-bar-icon.png"); // Provide the path to your image
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton toggleButton = new JButton(scaledIcon);
        toggleButton.setFont(new Font("Arial", Font.BOLD, 16));
        toggleButton.setBackground(Color.DARK_GRAY);
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setFocusPainted(false);

        // Add hover behavior to toggleButton
        toggleButton.addActionListener(e -> {
            leftPanel.setVisible(!leftPanel.isVisible());
        });

        // Add the toggle button to the top-left corner
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(toggleButton);

        // Add top panel to frame
        add(topPanel, BorderLayout.NORTH);

        // Make the frame visible
        setVisible(true);
    }

    // Updated method to create buttons with hover and click effects
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(null);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50)); // width 150, height 50

        // Set only the bottom border
        button.setBorder(new CompoundBorder(
            new EmptyBorder(0, 0, 0, 0), 
            BorderFactory.createLineBorder(Color.decode("#986842"))
        ));

        // Add hover effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.decode("#7a4f29")); // Darker color on hover
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Only reset color if not clicked
                if (!button.getModel().isPressed()) {
                    button.setBackground(null); // Reset to no color
                }
            }
        });

        // Change button color on click and keep it dark
        button.addActionListener(e -> button.setBackground(Color.decode("#7a4f29"))); // Darker color on click

        // Reset to original color when the button is released (if needed)
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Keep the button in the darker color state after being clicked
                button.setBackground(Color.decode("#7a4f29"));
            }
        });

        return button;
    }

    private void openUserProfile() {
        new UserProfile(user); // Pass user details to the profile page
    }

    // Method to open Preferences UI
    private void openPreferencesUI() {
        new PreferencesUI(user); // Pass the RegularUser to PreferencesUI
    }
}
