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
        // JFrame frame = new JFrame("Admin Page - " + adminUser.getFullName());
        JFrame frame = new JFrame("Admin Page - " + adminUser.getFullName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Buttons
        JButton addBooksButton = new JButton("Add Books");
        JButton deleteBooksButton = new JButton("Delete Books");
        JButton updateBooksButton = new JButton("Update Books");
        JButton viewBooksButton = new JButton("View Books");
        JButton addChallengesButton = new JButton("Add Challenges");
        JButton signOutButton = new JButton("Sign Out");

        // Button bounds
        addBooksButton.setBounds(120, 30, 150, 30);
        deleteBooksButton.setBounds(120, 70, 150, 30);
        updateBooksButton.setBounds(120, 110, 150, 30);
        viewBooksButton.setBounds(120, 150, 150, 30);
        addChallengesButton.setBounds(120, 190, 150, 30);
        signOutButton.setBounds(120, 230, 150, 30);

        // Action listeners for each button
        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddBooksDialog(frame);
            }
        });

        deleteBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDeleteBooksDialog(frame);
            }
        });

        updateBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUpdateBooksDialog(frame);
            }
        });

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewBooksDialog(frame);
            }
        });

        addAchievementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Add Achievements dialog opened.");
            }
        });

        signOutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                openSignOutDialogBox(frame);
            }
        });

        // Adding buttons to frame
        frame.add(addBooksButton);
        frame.add(deleteBooksButton);
        frame.add(updateBooksButton);
        frame.add(viewBooksButton);
        frame.add(addAchievementsButton);
        frame.add(signOutButton);

        // Frame visibility
        frame.setVisible(true);
    }

    private void openAddBooksDialog(JFrame frame) {
    //     JDialog addBookDialog = new JDialog(frame, "Add Book", true);
    //     addBookDialog.setSize(400, 500);
    //     addBookDialog.setLayout(null);

    //     // Form fields
    //     JLabel titleLabel = new JLabel("Title:");
    //     JTextField titleField = new JTextField();
    //     JLabel authorLabel = new JLabel("Author:");
    //     JTextField authorField = new JTextField();
    //     JLabel genreLabel = new JLabel("Genre(s):");
    //     JTextField genreField = new JTextField();
    //     JLabel ageRatingLabel = new JLabel("Age Rating:");
    //     JTextField ageRatingField = new JTextField();
    //     JLabel pdfLabel = new JLabel("PDF File:");
    //     JTextField pdfField = new JTextField();
    //     JButton pdfBrowseButton = new JButton("Browse");
    //     JLabel coverImageLabel = new JLabel("Cover Image:");
    //     JTextField coverImageField = new JTextField();
    //     JButton coverBrowseButton = new JButton("Browse");
    //     JButton addButton = new JButton("Add to Library");

    //     // Set bounds for form fields
    //     titleLabel.setBounds(20, 20, 100, 25);
    //     titleField.setBounds(150, 20, 200, 25);
    //     authorLabel.setBounds(20, 60, 100, 25);
    //     authorField.setBounds(150, 60, 200, 25);
    //     genreLabel.setBounds(20, 100, 100, 25);
    //     genreField.setBounds(150, 100, 200, 25);
    //     ageRatingLabel.setBounds(20, 140, 100, 25);
    //     ageRatingField.setBounds(150, 140, 200, 25);
    //     pdfLabel.setBounds(20, 180, 100, 25);
    //     pdfField.setBounds(150, 180, 150, 25);
    //     pdfBrowseButton.setBounds(310, 180, 80, 25);
    //     coverImageLabel.setBounds(20, 220, 100, 25);
    //     coverImageField.setBounds(150, 220, 150, 25);
    //     coverBrowseButton.setBounds(310, 220, 80, 25);
    //     addButton.setBounds(120, 300, 150, 40);

    //     // Add action listeners for file browsing
    //     pdfBrowseButton.addActionListener(e -> browseFile(pdfField));
    //     coverBrowseButton.addActionListener(e -> browseFile(coverImageField));

    //     // Add action listener for "Add to Library" button
    //     addButton.addActionListener(e -> {
    //         // Get the entered details
    //         String title = titleField.getText();
    //         String author = authorField.getText();
    //         String genre = genreField.getText();
    //         String ageRating = ageRatingField.getText();
    //         String pdfFilePath = pdfField.getText();
    //         String coverImagePath = coverImageField.getText();

    //         // Validate input
    //         if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || ageRating.isEmpty() || pdfFilePath.isEmpty() || coverImagePath.isEmpty()) {
    //             JOptionPane.showMessageDialog(addBookDialog, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         // Handle book addition logic (e.g., using BookManager)
    //         JOptionPane.showMessageDialog(addBookDialog, "Book added successfully!");
    //         addBookDialog.dispose();
    //     });

    //     // Add components to dialog
    //     addBookDialog.add(titleLabel);
    //     addBookDialog.add(titleField);
    //     addBookDialog.add(authorLabel);
    //     addBookDialog.add(authorField);
    //     addBookDialog.add(genreLabel);
    //     addBookDialog.add(genreField);
    //     addBookDialog.add(ageRatingLabel);
    //     addBookDialog.add(ageRatingField);
    //     addBookDialog.add(pdfLabel);
    //     addBookDialog.add(pdfField);
    //     addBookDialog.add(pdfBrowseButton);
    //     addBookDialog.add(coverImageLabel);
    //     addBookDialog.add(coverImageField);
    //     addBookDialog.add(coverBrowseButton);
    //     addBookDialog.add(addButton);

    //     // Set dialog visibility
    //     addBookDialog.setVisible(true);
    // }

    // private void browseFile(JTextField field) {
    //     JFileChooser fileChooser = new JFileChooser();
    //     int option = fileChooser.showOpenDialog(null);
    //     if (option == JFileChooser.APPROVE_OPTION) {
    //         field.setText(fileChooser.getSelectedFile().getAbsolutePath());
    //     }
    // }

    JDialog addBookDialog = new JDialog(frame, "Add Book", true);
                addBookDialog.setSize(400, 500);
                addBookDialog.setLayout(null);

                // Form fields
                JLabel titleLabel = new JLabel("Title:");
                JTextField titleField = new JTextField();
                JLabel authorLabel = new JLabel("Author:");
                JTextField authorField = new JTextField();
                JLabel genreLabel = new JLabel("Genre(s) (comma-separated):");
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
                genreLabel.setBounds(20, 100, 200, 25);
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
                pdfBrowseButton.addActionListener(e1 -> {
                    JFileChooser fileChooser = new JFileChooser();
                    int option = fileChooser.showOpenDialog(addBookDialog);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        pdfField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                });

                coverBrowseButton.addActionListener(e1 -> {
                    JFileChooser fileChooser = new JFileChooser();
                    int option = fileChooser.showOpenDialog(addBookDialog);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        coverImageField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                });

                // Add action listener for "Add to Library" button
                addButton.addActionListener(e1 -> {
                    try {
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

                        // Convert PDF and image to byte arrays
                        byte[] pdfContent = FileUtilities.getFileContentAsBytes(pdfFilePath);
                        byte[] coverImageContent = FileUtilities.getFileContentAsBytes(coverImagePath);

                        // Parse genres into a list
                        ArrayList<String> genres = new ArrayList<>();
                        for (String g : genre.split(",")) {
                            genres.add(g.trim());
                        }

                        ArrayList<ReaderBook> books = FileUtilities.readAllObjects("books.dat", ReaderBook.class);

                        // Check for duplicate book
                        boolean duplicateFound = false;
                        for (ReaderBook book : books) { // <-- Ensure 'books' is declared before this line
                            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                                duplicateFound = true;
                                break;
                            }
                        }

                        if (duplicateFound) {
                            JOptionPane.showMessageDialog(addBookDialog, "A book with the same title and author already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Create a new ReaderBook object
                        ReaderBook newBook = new ReaderBook(1, title, author, genres, 0, ageRating, pdfContent, coverImageContent);

                        // ArrayList<ReaderBook> books = FileUtilities.readAllObjects("books.dat", ReaderBook.class);
                        if (books.isEmpty()) {
                            System.out.println("No books found. Starting fresh.");
                        }

                        // Append the new book to the list
                        books.add(newBook);

                        // Save the new book to the file
                        FileUtilities.writeObjectToFile("books.dat", newBook);
                        JOptionPane.showMessageDialog(addBookDialog, "Book added successfully!");
                        addBookDialog.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(addBookDialog, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
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

            private void openDeleteBooksDialog(JFrame frame) {
                JDialog deleteDialog = new JDialog(frame, "Delete Book", true);
                deleteDialog.setSize(300, 200);
                deleteDialog.setLayout(new FlowLayout());

                JLabel bookNameLabel = new JLabel("Book Title:");
                JTextField bookNameField = new JTextField(20);
                JLabel authorNameLabel = new JLabel("Author Name:");
                JTextField authorNameField = new JTextField(20);

                JButton deleteButton = new JButton("Delete");
                JButton cancelButton = new JButton("Cancel");

                deleteDialog.add(bookNameLabel);
                deleteDialog.add(bookNameField);
                deleteDialog.add(authorNameLabel);
                deleteDialog.add(authorNameField);
                deleteDialog.add(deleteButton);
                deleteDialog.add(cancelButton);

                cancelButton.addActionListener(e1 -> deleteDialog.dispose());

                deleteButton.addActionListener(e1 -> {
                    String bookName = bookNameField.getText();
                    String authorName = authorNameField.getText();

                    // Read books from file
                    ArrayList<ReaderBook> books = FileUtilities.readAllObjects("books.dat", ReaderBook.class);
                    ReaderBook bookToDelete = null;

                    // Find the book to delete
                    for (ReaderBook book : books) {
                        if (book.getTitle().equalsIgnoreCase(bookName)
                                && book.getAuthor().equalsIgnoreCase(authorName)) {
                            bookToDelete = book;
                            break;
                        }
                    }

                    if (bookToDelete != null) {
                        int response = JOptionPane.showConfirmDialog(
                                deleteDialog,
                                """
                                Are you sure you want to delete this book?
                                Title: """ + bookToDelete.getTitle() + "\n"
                                + "Author: " + bookToDelete.getAuthor() + "\n"
                                + "Genres: " + String.join(", ", bookToDelete.getGenres()),
                                "Confirm Deletion",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (response == JOptionPane.YES_OPTION) {
                            // Remove the book from the list
                            books.remove(bookToDelete);

                            // Write the updated list back to the file
                            try (FileOutputStream fos = new FileOutputStream("books.dat"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                                oos.writeObject(books);  // Make sure this writes the entire list
                                JOptionPane.showMessageDialog(deleteDialog,
                                        "Book deleted successfully.",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(deleteDialog,
                                        "Error deleting book: " + ex.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(deleteDialog,
                                "Book not found.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    deleteDialog.dispose();
                });

                deleteDialog.setVisible(true);
            }

    private void openAddBooksDialog(JFrame frame) {
                ArrayList<ReaderBook> books = FileUtilities.readAllObjects("books.dat", ReaderBook.class);

                // New dialog for viewing books
                JDialog viewBooksDialog = new JDialog(frame, "View Books", true);
                viewBooksDialog.setSize(500, 400);
                viewBooksDialog.setLayout(null);

                // Radio buttons for choosing between search and view by genre
                JRadioButton searchRadioButton = new JRadioButton("Search by:");
                JRadioButton genreRadioButton = new JRadioButton("View by Genre");
                ButtonGroup group = new ButtonGroup();
                group.add(searchRadioButton);
                group.add(genreRadioButton);

                // Components for search functionality
                JLabel searchLabel = new JLabel("Search:");
                JTextField searchField = new JTextField();
                JComboBox<String> searchCriteriaDropdown = new JComboBox<>(new String[]{"Title", "Author", "Age Rating"});
                JButton searchButton = new JButton("Search");

                // Components for view by genre functionality
                JLabel viewByLabel = new JLabel("View by:");
                String[] genres = {"All", "Fiction", "Non-Fiction", "Romance", "Thriller", "Fantasy", "Crime",
                    "Classics", "Educational", "History", "Horror", "Mystery", "Religion",
                    "Science", "Sci-Fi", "Self-Help", "Biography"};
                JComboBox<String> genreDropdown = new JComboBox<>(genres);
                JButton viewButton = new JButton("View");

                // Set bounds
                searchRadioButton.setBounds(20, 20, 120, 25);
                genreRadioButton.setBounds(150, 20, 150, 25);

                searchLabel.setBounds(20, 60, 50, 25);
                searchField.setBounds(80, 60, 200, 25);
                searchCriteriaDropdown.setBounds(290, 60, 100, 25);
                searchButton.setBounds(400, 60, 80, 25);

                viewByLabel.setBounds(20, 100, 50, 25);
                genreDropdown.setBounds(80, 100, 200, 25);
                viewButton.setBounds(290, 100, 80, 25);

                // Add action listeners for radio buttons
                searchRadioButton.addActionListener(event -> {
                    searchField.setEnabled(true);
                    searchCriteriaDropdown.setEnabled(true);
                    searchButton.setEnabled(true);
                    genreDropdown.setEnabled(false);
                    viewButton.setEnabled(false);
                });

                genreRadioButton.addActionListener(event -> {
                    searchField.setEnabled(false);
                    searchCriteriaDropdown.setEnabled(false);
                    searchButton.setEnabled(false);
                    genreDropdown.setEnabled(true);
                    viewButton.setEnabled(true);
                });

                // Add action listener for the "Search" button
                searchButton.addActionListener(event -> {
                    String criteria = (String) searchCriteriaDropdown.getSelectedItem();
                    String query = searchField.getText().trim().toLowerCase();

                    if (query.isEmpty()) {
                        JOptionPane.showMessageDialog(viewBooksDialog, "Please enter a search term.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    StringBuilder results = new StringBuilder();
                    for (ReaderBook book : books) {
                        if (criteria.equals("Title") && book.getTitle().toLowerCase().contains(query)
                                || criteria.equals("Author") && book.getAuthor().toLowerCase().contains(query)
                                || criteria.equals("Age Rating") && book.getAgeRating().toLowerCase().contains(query)) {
                            results.append("Title: ").append(book.getTitle())
                                    .append("\nAuthor: ").append(book.getAuthor())
                                    .append("\nGenres: ").append(String.join(", ", book.getGenres()))
                                    .append("\nAge Rating: ").append(book.getAgeRating())
                                    .append("\n------------------------\n");
                        }
                    }

                    if (results.length() == 0) {
                        JOptionPane.showMessageDialog(viewBooksDialog, "No books found.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(viewBooksDialog, results.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                // Add action listener for the "View" button
                viewButton.addActionListener(event -> {
                    String selectedGenre = (String) genreDropdown.getSelectedItem();

                    StringBuilder bookList = new StringBuilder();
                    for (ReaderBook book : books) {
                        if ("All".equals(selectedGenre) || book.getGenres().contains(selectedGenre)) {
                            bookList.append("Title: ").append(book.getTitle())
                                    .append("\nAuthor: ").append(book.getAuthor())
                                    .append("\nGenres: ").append(String.join(", ", book.getGenres()))
                                    .append("\nAge Rating: ").append(book.getAgeRating())
                                    .append("\n------------------------\n");
                        }
                    }

                    if (bookList.length() == 0) {
                        JOptionPane.showMessageDialog(viewBooksDialog, "No books found of this genre.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(viewBooksDialog, bookList.toString(), "Books", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                // Disable search and enable view by default
                genreRadioButton.setSelected(true);
                searchField.setEnabled(false);
                searchCriteriaDropdown.setEnabled(false);
                searchButton.setEnabled(false);

                // Add components to the dialog
                viewBooksDialog.add(searchRadioButton);
                viewBooksDialog.add(genreRadioButton);
                viewBooksDialog.add(searchLabel);
                viewBooksDialog.add(searchField);
                viewBooksDialog.add(searchCriteriaDropdown);
                viewBooksDialog.add(searchButton);
                viewBooksDialog.add(viewByLabel);
                viewBooksDialog.add(genreDropdown);
                viewBooksDialog.add(viewButton);

                // Set dialog visibility
                viewBooksDialog.setVisible(true);
            }

            private void openUpdateBooksDialog(JFrame frame){
                ArrayList<ReaderBook> books = FileUtilities.readAllObjects("books.dat", ReaderBook.class);
                // Create dialog for updating books
                JDialog updateBooksDialog = new JDialog(frame, "Update Books", true);
                updateBooksDialog.setSize(600, 400);
                updateBooksDialog.setLayout(new BorderLayout());
                // Panel for displaying books
                JPanel booksPanel = new JPanel();
                booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS));
                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No books found.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                for (final ReaderBook book : books) {  // Made 'book' final for use in inner class
                    // Panel for each book
                    JPanel bookPanel = new JPanel(new BorderLayout());
                    bookPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
                    // Book details
                    JTextArea bookDetails = new JTextArea();
                    bookDetails.setEditable(false);
                    bookDetails.setText("Title: " + book.getTitle() + "\n"
                            + "Author: " + book.getAuthor() + "\n"
                            + "Genres: " + String.join(", ", book.getGenres()) + "\n"
                            + "Age Rating: " + book.getAgeRating());
                    // Button for updating
                    JButton updateButton = new JButton("Update");
                    updateButton.addActionListener(updateEvent -> {
                        // Create a dialog for updating the book
                        JDialog updateBookDialog = new JDialog(updateBooksDialog, "Update Book", true);
                        updateBookDialog.setSize(500, 400);
                        updateBookDialog.setLayout(null);
                        // Labels and fields for book details
                        JLabel titleLabel = new JLabel("Title:");
                        JTextField titleField = new JTextField(book.getTitle());
                        JLabel authorLabel = new JLabel("Author:");
                        JTextField authorField = new JTextField(book.getAuthor());
                        JLabel genreLabel = new JLabel("Genres (comma-separated):");
                        JTextField genreField = new JTextField(String.join(", ", book.getGenres()));
                        JLabel ageRatingLabel = new JLabel("Age Rating:");
                        JTextField ageRatingField = new JTextField(book.getAgeRating());
                        JButton confirmButton = new JButton("Confirm");
                        JButton cancelButton = new JButton("Cancel");
                        // Set bounds for components
                        titleLabel.setBounds(20, 20, 150, 25);
                        titleField.setBounds(180, 20, 280, 25);
                        authorLabel.setBounds(20, 60, 150, 25);
                        authorField.setBounds(180, 60, 280, 25);
                        genreLabel.setBounds(20, 100, 150, 25);
                        genreField.setBounds(180, 100, 280, 25);
                        ageRatingLabel.setBounds(20, 140, 150, 25);
                        ageRatingField.setBounds(180, 140, 280, 25);
                        confirmButton.setBounds(100, 300, 120, 30);
                        cancelButton.setBounds(280, 300, 120, 30);
                        // Add components to the dialog
                        updateBookDialog.add(titleLabel);
                        updateBookDialog.add(titleField);
                        updateBookDialog.add(authorLabel);
                        updateBookDialog.add(authorField);
                        updateBookDialog.add(genreLabel);
                        updateBookDialog.add(genreField);
                        updateBookDialog.add(ageRatingLabel);
                        updateBookDialog.add(ageRatingField);
                        updateBookDialog.add(confirmButton);
                        updateBookDialog.add(cancelButton);

                        // Add functionality for Confirm button
                        confirmButton.addActionListener(confirmEvent -> {
                            try {
                                String updatedTitle = titleField.getText();
                                String updatedAuthor = authorField.getText();
                                String updatedGenre = genreField.getText();
                                String updatedAgeRating = ageRatingField.getText();

                                if (updatedTitle.isEmpty() || updatedAuthor.isEmpty() || updatedGenre.isEmpty() || updatedAgeRating.isEmpty()) {
                                    JOptionPane.showMessageDialog(updateBookDialog, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                // Check for duplicate books, excluding the current book being updated
                                for (ReaderBook existingBook : books) {
                                    if (existingBook != book
                                            && // Compare references instead of equals()
                                            existingBook.getTitle().equalsIgnoreCase(updatedTitle)
                                            && existingBook.getAuthor().equalsIgnoreCase(updatedAuthor)) {
                                        JOptionPane.showMessageDialog(updateBookDialog, "A book with the same title and author already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                }

                                // Find the exact book in the list and update it
                                int bookIndex = -1;
                                for (int i = 0; i < books.size(); i++) {
                                    if (books.get(i) == book) { // Compare references instead of equals()
                                        bookIndex = i;
                                        break;
                                    }
                                }

                                if (bookIndex != -1) {
                                    // Create a new book object with updated values
                                    ReaderBook updatedBook = new ReaderBook(
                                            book.getBookId(),
                                            updatedTitle,
                                            updatedAuthor,
                                            new ArrayList<>(Arrays.asList(updatedGenre.split(","))),
                                            book.getTotalReaderPages(),
                                            updatedAgeRating,
                                            book.getPdfContent(),
                                            book.getCoverImage()
                                    );

                                    // Replace the old book with the updated one
                                    books.set(bookIndex, updatedBook);

                                    // Write all books back to file
                                    FileUtilities.writeAllObjects("books.dat", books);

                                    JOptionPane.showMessageDialog(updateBookDialog, "Book updated successfully!");
                                    updateBookDialog.dispose();
                                    updateBooksDialog.dispose(); // Close the main dialog to refresh the view
                                } else {
                                    JOptionPane.showMessageDialog(updateBookDialog, "Error: Book not found in list", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(updateBookDialog, "Error updating book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });

                        // Add functionality for Cancel button
                        cancelButton.addActionListener(cancelEvent -> updateBookDialog.dispose());
                        // Set dialog visibility
                        updateBookDialog.setVisible(true);
                    });
                    // Add components to the book panel
                    bookPanel.add(bookDetails, BorderLayout.CENTER);
                    bookPanel.add(updateButton, BorderLayout.EAST);
                    // Add book panel to the main books panel
                    booksPanel.add(bookPanel);
                }
                // Add the books panel to a scroll pane
                JScrollPane scrollPane = new JScrollPane(booksPanel);
                updateBooksDialog.add(scrollPane, BorderLayout.CENTER);
                // Set dialog visibility
                updateBooksDialog.setVisible(true);
            }

            private void openSignOutDialogBox(JFrame frame){
                signOutButton.addActionListener(e -> {
                    // Create a confirmation dialog
                    JDialog signOutDialog = new JDialog(frame, "Confirm Sign Out", true);
                    signOutDialog.setSize(300, 150);
                    signOutDialog.setLayout(null);
        
                    // Label for confirmation
                    JLabel confirmLabel = new JLabel("Are you sure you want to sign out?");
                    confirmLabel.setBounds(40, 20, 220, 25);
                    signOutDialog.add(confirmLabel);
        
                    // "Yes" and "No" buttons
                    JButton yesButton = new JButton("Yes");
                    JButton noButton = new JButton("No");
        
                    yesButton.setBounds(60, 70, 80, 30);
                    noButton.setBounds(160, 70, 80, 30);
        
                    signOutDialog.add(yesButton);
                    signOutDialog.add(noButton);
        
                    // Action for "Yes" button
                    yesButton.addActionListener(yesEvent -> {
                        signOutDialog.dispose(); // Close the dialog
                        frame.dispose(); // Close the admin's control page
                        LoginPage(); // Display the login page (assuming the method exists in another class)
                    });
        
                    // Action for "No" button
                    noButton.addActionListener(noEvent -> signOutDialog.dispose());
        
                    // Show the dialog
                    signOutDialog.setVisible(true);
                });
            }
}
