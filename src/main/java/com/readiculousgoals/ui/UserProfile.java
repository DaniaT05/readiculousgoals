package com.readiculousgoals.ui;
import java.awt.*;
import javax.swing.*;
import com.readiculousgoals.model.User;
public class UserProfile extends JFrame {
    private User user;
    public UserProfile(User user) {
        this.user = user;
        // Frame settings
        setTitle("User Profile - " + user.getFullName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // User details
        contentPanel.add(new JLabel("Name:"));
        contentPanel.add(new JLabel(user.getFullName()));
        contentPanel.add(new JLabel("Age:"));
        contentPanel.add(new JLabel(Integer.toString((user.getAge()))));
        contentPanel.add(new JLabel("Email:"));
        contentPanel.add(new JLabel(user.getEmail()));
        contentPanel.add(new JLabel("Username:"));
        contentPanel.add(new JLabel(user.getUsername()));
        contentPanel.add(new JLabel("Join Date:"));
        contentPanel.add(new JLabel(user.getJoinDate().toString()));
        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);
        // Make the frame visible
        setVisible(true);
    }
}
