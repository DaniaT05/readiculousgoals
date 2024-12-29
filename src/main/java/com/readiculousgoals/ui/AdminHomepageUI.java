package com.readiculousgoals.ui;

import com.readiculousgoals.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.*;
import java.io.FileInputStream;
import java.io.IOException;

public class AdminHomepageUI {
    private AdminUser adminUser;

    public AdminHomepageUI(AdminUser adminUser) {
        this.adminUser = adminUser;
        JFrame frame = new JFrame("Admin Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Buttons
        JButton addBooksButton = new JButton("Add Books");

        // Button bounds
        addBooksButton.setBounds(120, 30, 150, 30);

        // Action listener for "Add Books"
        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New dialog for adding books
                JDialog addBookDialog = new JDialog(frame, "Add Book", true);
                addBookDialog.setSize(400, 500);
                addBookDialog.setLayout(null);

                // Form fields
                JLabel titleLabel = new JLabel("Title:");
                JTextField titleField = new JTextField();
                JLabel authorLabel = new JLabel("Author:");
                JTextField authorField = new JTextField();
                JLabel genreLabel = new JLabel("Genre:");
                JTextField genreField = new JTextField();
                JLabel ageRatingLabel = new JLabel("Age Rating:");
                JTextField ageRatingField = new JTextField();
                JLabel pageCountLabel = new JLabel("Page Count:");
                JTextField pageCountField = new JTextField();
                JLabel pdfLabel = new JLabel("PDF File:");
                JTextField pdfField = new JTextField();
                JButton pdfBrowseButton = new JButton("Browse");
                JLabel coverImageLabel = new JLabel("Cover Image:");
                JTextField coverImageField = new JTextField();
                JButton coverBrowseButton = new JButton("Set Cover From PDF");

                // Set bounds for components
                titleLabel.setBounds(20, 20, 100, 25);
                titleField.setBounds(150, 20, 200, 25);
                authorLabel.setBounds(20, 60, 100, 25);
                authorField.setBounds(150, 60, 200, 25);
                genreLabel.setBounds(20, 100, 100, 25);
                genreField.setBounds(150, 100, 200, 25);
                ageRatingLabel.setBounds(20, 140, 100, 25);
                ageRatingField.setBounds(150, 140, 200, 25);
                pageCountLabel.setBounds(20, 180, 100, 25);
                pageCountField.setBounds(150, 180, 200, 25);
                pdfLabel.setBounds(20, 220, 100, 25);
                pdfField.setBounds(150, 220, 150, 25);
                pdfBrowseButton.setBounds(310, 220, 80, 25);
                coverImageLabel.setBounds(20, 260, 100, 25);
                coverImageField.setBounds(150, 260, 150, 25);
                coverBrowseButton.setBounds(310, 260, 150, 25);

                // Add action listeners
                pdfBrowseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        int option = fileChooser.showOpenDialog(addBookDialog);
                        if (option == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            pdfField.setText(selectedFile.getAbsolutePath());

                            // Get PDF page count dynamically
                            try {
                                int pageCount = getPdfPageCount(selectedFile);
                                pageCountField.setText(String.valueOf(pageCount));
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(addBookDialog, "Error reading PDF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                coverBrowseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String pdfFilePath = pdfField.getText();
                        if (pdfFilePath.isEmpty()) {
                            JOptionPane.showMessageDialog(addBookDialog, "Please select a PDF file first.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            Image coverImage = extractCoverFromPDF(pdfFilePath, 1);
                            ImageIcon coverIcon = new ImageIcon(coverImage);
                            coverImageField.setText("Cover Set from PDF");
                            JOptionPane.showMessageDialog(addBookDialog, coverIcon, "Cover Preview", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(addBookDialog, "Error extracting cover image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // Submit button to create the book object
                JButton submitButton = new JButton("Submit");
                submitButton.setBounds(150, 320, 100, 30);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get values from text fields
                        String title = titleField.getText();
                        String author = authorField.getText();
                        String genre = genreField.getText();
                        String ageRating = ageRatingField.getText();
                        int pageCount = Integer.parseInt(pageCountField.getText());
                        byte[] pdfContent = getFileBytes(pdfField.getText());
                        byte[] coverImageContent = getFileBytes(coverImageField.getText());

                        // Create AdminBook object
                        AdminBook adminBook = new AdminBook(title, author, genre, ageRating, pageCount, pdfContent, coverImageContent);
                        JOptionPane.showMessageDialog(addBookDialog, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Close the dialog
                        addBookDialog.dispose();
                    }
                });

                // Add components to the dialog
                addBookDialog.add(titleLabel);
                addBookDialog.add(titleField);
                addBookDialog.add(authorLabel);
                addBookDialog.add(authorField);
                addBookDialog.add(genreLabel);
                addBookDialog.add(genreField);
                addBookDialog.add(ageRatingLabel);
                addBookDialog.add(ageRatingField);
                addBookDialog.add(pageCountLabel);
                addBookDialog.add(pageCountField);
                addBookDialog.add(pdfLabel);
                addBookDialog.add(pdfField);
                addBookDialog.add(pdfBrowseButton);
                addBookDialog.add(coverImageLabel);
                addBookDialog.add(coverImageField);
                addBookDialog.add(coverBrowseButton);
                addBookDialog.add(submitButton);

                // Set dialog visibility
                addBookDialog.setVisible(true);
            }
        });

        // Add button to the frame
        frame.add(addBooksButton);

        // Frame visibility
        frame.setVisible(true);
    }

    // Method to get the page count of a PDF file
    private int getPdfPageCount(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            return document.getNumberOfPages();
        }
    }

    // Method to get file bytes (for PDF and cover image)
    private byte[] getFileBytes(String filePath) {
        try {
            File file = new File(filePath);
            byte[] fileBytes = new byte[(int) file.length()];
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                fileInputStream.read(fileBytes);
            }
            return fileBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    // Extract cover image from a PDF file
    private static Image extractCoverFromPDF(String pdfFilePath, int pageNumber) throws Exception {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            return pdfRenderer.renderImageWithDPI(pageNumber - 1, 300); // Render at 300 DPI
        }
    }
}
