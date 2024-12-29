package com.readiculousgoals.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.readiculousgoals.model.User;

public class AdminControlsPage {

    private User adminUser;

    public AdminControlsPage(User adminUser) {
        this.adminUser = adminUser;
    }

    public void displayAdminControls() {
        // Main frame
        JFrame frame = new JFrame("Admin Page - " + adminUser.getFullName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Buttons
        JButton addBooksButton = new JButton("Add Books");
        JButton deleteBooksButton = new JButton("Delete Books");
        JButton updateBooksButton = new JButton("Update Books");
        JButton viewBooksButton = new JButton("View Books");
        JButton addAchievementsButton = new JButton("Add Achievements");

        // Button bounds
        addBooksButton.setBounds(120, 30, 150, 30);
        deleteBooksButton.setBounds(120, 70, 150, 30);
        updateBooksButton.setBounds(120, 110, 150, 30);
        viewBooksButton.setBounds(120, 150, 150, 30);
        addAchievementsButton.setBounds(120, 190, 150, 30);

        // Action listeners for each button
        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddBookDialog(frame);
            }
        });

        deleteBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Delete Books dialog opened.");
            }
        });

        updateBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Update Books dialog opened.");
            }
        });

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "View Books dialog opened.");
            }
        });

        addAchievementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Add Achievements dialog opened.");
            }
        });

        // Adding buttons to frame
        frame.add(addBooksButton);
        frame.add(deleteBooksButton);
        frame.add(updateBooksButton);
        frame.add(viewBooksButton);
        frame.add(addAchievementsButton);

        // Frame visibility
        frame.setVisible(true);
    }

    private void openAddBookDialog(JFrame frame) {
        JDialog addBookDialog = new JDialog(frame, "Add Book", true);
        addBookDialog.setSize(400, 500);
        addBookDialog.setLayout(null);

        // Form fields
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();
        JLabel genreLabel = new JLabel("Genre(s):");
        JTextField genreField = new JTextField();
        JLabel ageRatingLabel = new JLabel("Age Rating:");
        JTextField ageRatingField = new JTextField();
        JLabel pdfLabel = new JLabel("PDF File:");
        JTextField pdfField = new JTextField();
        JButton pdfBrowseButton = new JButton("Browse");
        JLabel coverImageLabel = new JLabel("Cover Image:");
        JTextField coverImageField = new JTextField();
        JButton coverBrowseButton = new JButton("Browse");
        JButton addButton = new JButton("Add to Library");

        // Set bounds for form fields
        titleLabel.setBounds(20, 20, 100, 25);
        titleField.setBounds(150, 20, 200, 25);
        authorLabel.setBounds(20, 60, 100, 25);
        authorField.setBounds(150, 60, 200, 25);
        genreLabel.setBounds(20, 100, 100, 25);
        genreField.setBounds(150, 100, 200, 25);
        ageRatingLabel.setBounds(20, 140, 100, 25);
        ageRatingField.setBounds(150, 140, 200, 25);
        pdfLabel.setBounds(20, 180, 100, 25);
        pdfField.setBounds(150, 180, 150, 25);
        pdfBrowseButton.setBounds(310, 180, 80, 25);
        coverImageLabel.setBounds(20, 220, 100, 25);
        coverImageField.setBounds(150, 220, 150, 25);
        coverBrowseButton.setBounds(310, 220, 80, 25);
        addButton.setBounds(120, 300, 150, 40);

        // Add action listeners for file browsing
        pdfBrowseButton.addActionListener(e -> browseFile(pdfField));
        coverBrowseButton.addActionListener(e -> browseFile(coverImageField));

        // Add action listener for "Add to Library" button
        addButton.addActionListener(e -> {
            // Get the entered details
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            String ageRating = ageRatingField.getText();
            String pdfFilePath = pdfField.getText();
            String coverImagePath = coverImageField.getText();

            // Validate input
            if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || ageRating.isEmpty() || pdfFilePath.isEmpty() || coverImagePath.isEmpty()) {
                JOptionPane.showMessageDialog(addBookDialog, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Handle book addition logic (e.g., using BookManager)
            JOptionPane.showMessageDialog(addBookDialog, "Book added successfully!");
            addBookDialog.dispose();
        });

        // Add components to dialog
        addBookDialog.add(titleLabel);
        addBookDialog.add(titleField);
        addBookDialog.add(authorLabel);
        addBookDialog.add(authorField);
        addBookDialog.add(genreLabel);
        addBookDialog.add(genreField);
        addBookDialog.add(ageRatingLabel);
        addBookDialog.add(ageRatingField);
        addBookDialog.add(pdfLabel);
        addBookDialog.add(pdfField);
        addBookDialog.add(pdfBrowseButton);
        addBookDialog.add(coverImageLabel);
        addBookDialog.add(coverImageField);
        addBookDialog.add(coverBrowseButton);
        addBookDialog.add(addButton);

        // Set dialog visibility
        addBookDialog.setVisible(true);
    }

    private void browseFile(JTextField field) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            field.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
