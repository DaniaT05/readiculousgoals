// package com.readiculousgoals.ui;

// import com.readiculousgoals.model.*;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.*;
// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.rendering.PDFRenderer;
// import java.awt.image.*;

// public class AdminHomepageUI {
//     private AdminUser adminUser;

//     public AdminHomepageUI(AdminUser adminUser) {
//         this.adminUser = adminUser;
//         JFrame frame = new JFrame("Admin Page");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(400, 400);
//         frame.setLayout(null);

//         // Buttons
//         JButton addBooksButton = new JButton("Add Books");
//         JButton viewBooksButton = new JButton("View Books");

//         // Button bounds
//         addBooksButton.setBounds(120, 30, 150, 30);
//         viewBooksButton.setBounds(120, 70, 150, 30);

//         // Action listener for "Add Books"
//         addBooksButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // New dialog for adding books
//                 JDialog addBookDialog = new JDialog(frame, "Add Book", true);
//                 addBookDialog.setSize(400, 500);
//                 addBookDialog.setLayout(null);

//                 // Form fields
//                 JLabel titleLabel = new JLabel("Title:");
//                 JTextField titleField = new JTextField();
//                 JLabel authorLabel = new JLabel("Author:");
//                 JTextField authorField = new JTextField();
//                 JLabel genreLabel = new JLabel("Genre:");
//                 JTextField genreField = new JTextField();
//                 JLabel ageRatingLabel = new JLabel("Age Rating:");
//                 JTextField ageRatingField = new JTextField();
//                 JLabel pageCountLabel = new JLabel("Page Count:");
//                 JTextField pageCountField = new JTextField();
//                 JLabel pdfLabel = new JLabel("PDF File:");
//                 JTextField pdfField = new JTextField();
//                 JButton pdfBrowseButton = new JButton("Browse");
//                 JLabel coverImageLabel = new JLabel("Cover Image:");
//                 JTextField coverImageField = new JTextField();
//                 JButton coverBrowseButton = new JButton("Set Cover From PDF");

//                 // Set bounds for components
//                 titleLabel.setBounds(20, 20, 100, 25);
//                 titleField.setBounds(150, 20, 200, 25);
//                 authorLabel.setBounds(20, 60, 100, 25);
//                 authorField.setBounds(150, 60, 200, 25);
//                 genreLabel.setBounds(20, 100, 100, 25);
//                 genreField.setBounds(150, 100, 200, 25);
//                 ageRatingLabel.setBounds(20, 140, 100, 25);
//                 ageRatingField.setBounds(150, 140, 200, 25);
//                 pageCountLabel.setBounds(20, 180, 100, 25);
//                 pageCountField.setBounds(150, 180, 200, 25);
//                 pdfLabel.setBounds(20, 220, 100, 25);
//                 pdfField.setBounds(150, 220, 150, 25);
//                 pdfBrowseButton.setBounds(310, 220, 80, 25);
//                 coverImageLabel.setBounds(20, 260, 100, 25);
//                 coverImageField.setBounds(150, 260, 150, 25);
//                 coverBrowseButton.setBounds(310, 260, 150, 25);

//                 // Add action listeners
//                 pdfBrowseButton.addActionListener(new ActionListener() {
//                     @Override
//                     public void actionPerformed(ActionEvent e) {
//                         JFileChooser fileChooser = new JFileChooser();
//                         int option = fileChooser.showOpenDialog(addBookDialog);
//                         if (option == JFileChooser.APPROVE_OPTION) {
//                             File selectedFile = fileChooser.getSelectedFile();
//                             pdfField.setText(selectedFile.getAbsolutePath());

//                             // Get PDF page count dynamically
//                             try {
//                                 int pageCount = getPdfPageCount(selectedFile);
//                                 pageCountField.setText(String.valueOf(pageCount));
//                             } catch (IOException ex) {
//                                 JOptionPane.showMessageDialog(addBookDialog, "Error reading PDF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                             }
//                         }
//                     }
//                 });

//                 coverBrowseButton.addActionListener(new ActionListener() {
//                     @Override
//                     public void actionPerformed(ActionEvent e) {
//                         String pdfFilePath = pdfField.getText();
//                         if (pdfFilePath.isEmpty()) {
//                             JOptionPane.showMessageDialog(addBookDialog, "Please select a PDF file first.", "Error", JOptionPane.ERROR_MESSAGE);
//                             return;
//                         }

//                         try {
//                             Image coverImage = extractCoverFromPDF(pdfFilePath, 1);
//                             ImageIcon coverIcon = new ImageIcon(coverImage);
//                             coverImageField.setText("Cover Set from PDF");
//                             JOptionPane.showMessageDialog(addBookDialog, coverIcon, "Cover Preview", JOptionPane.INFORMATION_MESSAGE);
//                         } catch (Exception ex) {
//                             JOptionPane.showMessageDialog(addBookDialog, "Error extracting cover image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                         }
//                     }
//                 });

//                 // Submit button to create the book object
//                 JButton submitButton = new JButton("Submit");
//                 submitButton.setBounds(150, 320, 100, 30);
//                 submitButton.addActionListener(new ActionListener() {
//                     @Override
//                     public void actionPerformed(ActionEvent e) {
//                         // Get values from text fields
//                         String title = titleField.getText();
//                         String author = authorField.getText();
//                         String genre = genreField.getText();
//                         String ageRating = ageRatingField.getText();
//                         int pageCount = Integer.parseInt(pageCountField.getText());
//                         byte[] pdfContent = getFileBytes(pdfField.getText());
//                         byte[] coverImageContent = getFileBytes(coverImageField.getText());

//                         // Create AdminBook object
//                         AdminBook adminBook = new AdminBook(title, author, genre, ageRating, pageCount, pdfContent, coverImageContent);
//                         JOptionPane.showMessageDialog(addBookDialog, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

//                         // Save to books.dat
//                         saveBookToFile(adminBook);

//                         // Close the dialog
//                         addBookDialog.dispose();
//                     }
//                 });

//                 // Add components to the dialog
//                 addBookDialog.add(titleLabel);
//                 addBookDialog.add(titleField);
//                 addBookDialog.add(authorLabel);
//                 addBookDialog.add(authorField);
//                 addBookDialog.add(genreLabel);
//                 addBookDialog.add(genreField);
//                 addBookDialog.add(ageRatingLabel);
//                 addBookDialog.add(ageRatingField);
//                 addBookDialog.add(pageCountLabel);
//                 addBookDialog.add(pageCountField);
//                 addBookDialog.add(pdfLabel);
//                 addBookDialog.add(pdfField);
//                 addBookDialog.add(pdfBrowseButton);
//                 addBookDialog.add(coverImageLabel);
//                 addBookDialog.add(coverImageField);
//                 addBookDialog.add(coverBrowseButton);
//                 addBookDialog.add(submitButton);

//                 // Set dialog visibility
//                 addBookDialog.setVisible(true);
//             }
//         });

//         // Action listener for "View Books"
//         viewBooksButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // View books in a dialog
//                 JDialog viewBooksDialog = new JDialog(frame, "View Books", true);
//                 viewBooksDialog.setSize(400, 500);
//                 viewBooksDialog.setLayout(new BorderLayout());

//                 JTextArea booksTextArea = new JTextArea();
//                 booksTextArea.setEditable(false);

//                 // Load books from books.dat and display in the text area
//                 File booksFile = new File("src/main/java/com/readiculousgoals/data/books.dat");
//                 if (booksFile.exists()) {
//                     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(booksFile))) {
//                         while (true) {
//                             AdminBook book = (AdminBook) ois.readObject();
//                             booksTextArea.append("Title: " + book.getTitle() + "\n");
//                             booksTextArea.append("Author: " + book.getAuthor() + "\n");
//                             booksTextArea.append("Genre: " + book.getGenre() + "\n");
//                             booksTextArea.append("Age Rating: " + book.getAgeRating() + "\n");
//                             booksTextArea.append("Page Count: " + book.getPageCount() + "\n");
//                             booksTextArea.append("--------------------------\n");
//                         }
//                     } catch (EOFException eofe) {
//                         // End of file reached, good
//                     } catch (IOException | ClassNotFoundException ex) {
//                         ex.printStackTrace();
//                     }
//                 }

//                 JScrollPane scrollPane = new JScrollPane(booksTextArea);
//                 viewBooksDialog.add(scrollPane, BorderLayout.CENTER);
//                 viewBooksDialog.setVisible(true);
//             }
//         });

//         // Add buttons to the frame
//         frame.add(addBooksButton);
//         frame.add(viewBooksButton);

//         // Set frame visibility
//         frame.setVisible(true);
//     }

//     // Save the book to the file
//     private void saveBookToFile(AdminBook book) {
//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/com/readiculousgoals/data/books.dat", true))) {
//             oos.writeObject(book);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Utility method to extract PDF page count
//     private int getPdfPageCount(File file) throws IOException {
//         try (PDDocument document = PDDocument.load(file)) {
//             return document.getNumberOfPages();
//         }
//     }

//     // Utility method to extract cover image from PDF
//     private Image extractCoverFromPDF(String pdfFilePath, int pageNum) throws IOException {
//         try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
//             PDFRenderer pdfRenderer = new PDFRenderer(document);
//             BufferedImage bufferedImage = pdfRenderer.renderImage(pageNum);
//             return bufferedImage;
//         }
//     }

//     // Utility method to convert file to bytes
//     private byte[] getFileBytes(String filePath) {
//         try {
//             File file = new File(filePath);
//             byte[] bytes = new byte[(int) file.length()];
//             try (FileInputStream fis = new FileInputStream(file)) {
//                 fis.read(bytes);
//             }
//             return bytes;
//         } catch (IOException e) {
//             e.printStackTrace();
//             return null;
//         }
//     }
// }
