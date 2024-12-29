package com.readiculousgoals.ui;
import javax.swing.*;
import java.awt.*;
public class ButtonUI extends JButton {
    public ButtonUI(String text) {
        super(text); // Set the text on the button
        this.setBackground(Color.decode("#7f4829")); // Convert hex to Color
        this.setForeground(Color.WHITE); // Optional: Set text color
        this.setFocusPainted(false); // Optional: Remove focus border
        this.setBorderPainted(false); // Optional: Remove border
        this.setFont(new Font("Arial", Font.PLAIN, 16));
    }
}