package com.readiculousgoals.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Box;
import com.readiculousgoals.model.Book;
import com.readiculousgoals.model.BookStorage;
import com.readiculousgoals.model.Genre;
import com.readiculousgoals.model.ReaderBook;
import com.readiculousgoals.model.RegularUser;
import com.readiculousgoals.model.User;

public class ReaderHomepageUI extends JFrame {
    private RegularUser user;
    private JPanel contentPanel;
    private JTextField searchField;
    private JPanel leftPanel;
    private static final int MAX_VISIBLE_GENRES = 5;
    private JScrollPane mainScrollPane;
    private static final int GENRE_PANEL_HEIGHT = 130; 
    public ReaderHomepageUI(User newUser) {
        RegularUser user = (RegularUser) newUser;
        this.user = user;
        
        setTitle("Reader Homepage - Welcome, " + user.getFullName());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeLeftPanel();
        initializeTopPanel();

        // Content Panel setup with fixed height rows
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1)); // Use 0 for unlimited rows, 1 column
        
        // Set a preferred size that will show exactly 5 panels
        contentPanel.setPreferredSize(new Dimension(getWidth() - 50, GENRE_PANEL_HEIGHT * MAX_VISIBLE_GENRES));
        
        // Main scroll pane setup
        mainScrollPane = new JScrollPane(contentPanel);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainScrollPane.setBorder(null);
debugLoadBooks();

        // Calculate the viewport height to show exactly 5 panels
        mainScrollPane.setPreferredSize(new Dimension(getWidth() - 50, GENRE_PANEL_HEIGHT * MAX_VISIBLE_GENRES));

        add(mainScrollPane, BorderLayout.CENTER);
        loadGenresAndBooks();
        setVisible(true);
    }

    private void openUserProfile() {
        new UserProfile(user); // Pass user details to the profile page
    }
    private void filterBooks() {
        String query = searchField.getText().toLowerCase();
        contentPanel.removeAll();
        List<Genre> preferences = user.getPreferences();
        for (Genre genre : preferences) {
            List<Book> books = loadBooksForGenre(genre.getName());
            // Use Collectors.toList() to collect the filtered books into a list
            List<Book> filteredBooks = books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(query) || book.getAuthor().toLowerCase().contains(query))
                    .collect(Collectors.toList());
            if (!filteredBooks.isEmpty()) {
                JPanel genrePanel = new JPanel(new BorderLayout());
                genrePanel.setBorder(BorderFactory.createTitledBorder(genre.getName()));
                JPanel booksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                for (Book book : filteredBooks) {
                    booksPanel.add(createBookContainer(book));
                }
                genrePanel.add(booksPanel, BorderLayout.CENTER);
                contentPanel.add(genrePanel);
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private void openPreferencesUI() {
        new PreferencesUI(user,ReaderHomepageUI.this);
        refreshHomepage(); // Pass the RegularUser to PreferencesUI
    }
    // private void loadRemainingGenres(List<Genre> remainingGenres) {
    //     for (Genre genre : remainingGenres) {
    //         JPanel genrePanel = createGenrePanel(genre.getName());
    //         contentPanel.add(genrePanel);
    //     }
    //     contentPanel.revalidate();
    //     contentPanel.repaint();
    // }

    private JPanel createBookContainer(Book book) {
        // Create main container with BorderLayout
        JPanel bookContainer = new JPanel(new BorderLayout(5, 5));
        bookContainer.setPreferredSize(new Dimension(100, 150)); // Adjust size as needed
        bookContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        try {
            // Create and scale the cover image
            ImageIcon coverIcon;
            if (book.getCoverImage() != null) {
                coverIcon = new ImageIcon(book.getCoverImage());
            } else {
                // Provide a default image or handle the null case appropriately
                coverIcon = new ImageIcon("src/main/java/com/readiculousgoals/resources/dummy.jpeg");
            }
            Image scaledImage = coverIcon.getImage().getScaledInstance(90, 120, Image.SCALE_SMOOTH);
            JLabel coverLabel = new JLabel(new ImageIcon(scaledImage));
            coverLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            // Create and style the title label
            JLabel titleLabel = new JLabel(book.getTitle(), SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            // If title is too long, add ellipsis
            if (book.getTitle().length() > 15) {
                titleLabel.setText(book.getTitle().substring(0, 12) + "...");
            }
            titleLabel.setToolTipText(book.getTitle()); // Show full title on hover
            
            // Add components to container
            bookContainer.add(coverLabel, BorderLayout.CENTER);
            bookContainer.add(titleLabel, BorderLayout.SOUTH);
            
            // Add click listener for book details
            bookContainer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openBookDialog(book);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    bookContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    bookContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    bookContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    bookContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                }
            });
            

        } catch (Exception e) {
            e.printStackTrace();
            // If image loading fails, show placeholder
            JLabel errorLabel = new JLabel("Cover Not Available");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            bookContainer.add(errorLabel, BorderLayout.CENTER);
        }
        
        return bookContainer;
    }
    private void openBookDialog(Book book) {
        System.out.println("Opening book dialog for: " + book.getTitle());
        JDialog dialog = new JDialog(this, book.getTitle(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 500);
        
        // Create book cover panel
        ImageIcon coverIcon;
        if (book.getCoverImage() != null) {
            coverIcon = new ImageIcon(book.getCoverImage());
        } else {
            // Provide a default image or handle the null case appropriately
            coverIcon = new ImageIcon("src/main/java/com/readiculousgoals/resources/dummy.jpeg");
        }
            
        Image scaledImage = coverIcon.getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH);
        JLabel bookImage = new JLabel(new ImageIcon(scaledImage));
        
        // Create book details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Title: " + book.getTitle());
        JLabel authorLabel = new JLabel("Author: " + book.getAuthor());
        JLabel genreLabel = new JLabel("Genre: " + book.getGenre());
        JLabel ageRatingLabel = new JLabel("Age Rating: " + book.getAgeRating());
        JLabel pageCountLabel = new JLabel("Page Count: " + book.getTotalPages());
        
        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(authorLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(genreLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(ageRatingLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(pageCountLabel);
        
        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton addToTBRButton = new JButton("Add to TBR");
        addToTBRButton.addActionListener(e -> {
            try {
                // Print the TBR list before adding the book
                System.out.println("TBR List Before Adding: " + user.getTbr());

                // Add the book to the TBR list
                user.getTbr().add(book);

                // Print the TBR list after adding the book
                System.out.println("TBR List After Adding: " + user.getTbr());

                // Write the updated TBR list to tbr.dat
                writeTBRToFile("src/main/java/com/readiculousgoals/data/tbr"+user.getUsername()+".dat", user.getTbr());

                // Show a success message
                JOptionPane.showMessageDialog(dialog, "Added to TBR successfully!");
            } catch (Exception ex) {
                // Log any errors that occur during the process
                System.err.println("Error adding book to TBR: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        
        JButton readNowButton = new JButton("Read Now");
        readNowButton.addActionListener(e -> {
            ReaderBook readerBook = new ReaderBook(book.getBookId(),book.getTitle(), book.getAuthor(), book.getGenre(),book.getTotalPages(),book.getAgeRating(), "In Progress",0, book.getPdfContent(), book.getCoverImage());
            user.getProgressTracker().add(readerBook);
            // JOptionPane.showMessageDialog(dialog, "Added to Progress Tracker!");

            
            // Create a new JFrame to display the book content
            JFrame bookFrame = new JFrame("Reading: " + book.getTitle());
            bookFrame.setSize(800, 600); // Set optimal width and height
            bookFrame.setLayout(new BorderLayout());
            bookFrame.setLocationRelativeTo(null);

                // Create a JPanel to display the PDF content
                JPanel pdfPanel = new JPanel();
                pdfPanel.setLayout(new BoxLayout(pdfPanel, BoxLayout.Y_AXIS));

                // Convert the byte array to an InputStream and render the PDF content
                try (PDDocument document = PDDocument.load(new ByteArrayInputStream(book.getPdfContent()))) {
                    PDFRenderer pdfRenderer = new PDFRenderer(document);
                    for (int page = 0; page < document.getNumberOfPages(); ++page) {
                        BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 80, ImageType.RGB);
                        ImageIcon imageIcon = new ImageIcon(bim);
                        JLabel label = new JLabel(imageIcon);
                        pdfPanel.add(label);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(bookFrame, "Failed to load PDF content.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Make the book content scrollable
                JScrollPane scrollPane = new JScrollPane(pdfPanel);
                bookFrame.add(scrollPane, BorderLayout.CENTER);

                // Show the book frame
                bookFrame.setVisible(true);
        });
        
        buttonsPanel.add(addToTBRButton);
        buttonsPanel.add(readNowButton);
        
        // Add all components to dialog
        dialog.add(bookImage, BorderLayout.WEST);
        dialog.add(detailsPanel, BorderLayout.CENTER);
        dialog.add(buttonsPanel, BorderLayout.SOUTH);
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    private List<Book> loadBooksForGenre(String genre) {
        System.out.println("Input Genre: " + genre);
    
        List<Book> allBooks = BookStorage.loadBooks();
        System.out.println("All Books: " + allBooks);
        
        List<Book> filteredBooks = allBooks.stream()
            .filter(book -> {
                List<String> bookGenres = Arrays.asList(book.getGenre().split(","));
                System.out.println("Book Genres: " + bookGenres);
                boolean matches = bookGenres.stream().anyMatch(g -> g.trim().equalsIgnoreCase(genre));
                System.out.println("Book: " + book + ", Matches: " + matches);
                return matches;
            })
            .collect(Collectors.toList());
        
        System.out.println("Filtered Books: " + filteredBooks);
        return filteredBooks;
    }
    
    private void initializeLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setBackground(Color.decode("#986842")); 
        leftPanel.setVisible(false);
        leftPanel.setLayout(new GridLayout(13, 1, 0, 0));

        // Create and add buttons
        JButton profileButton = createButton("Profile");
        profileButton.addActionListener(e -> openUserProfile());
        leftPanel.add(profileButton);

        JButton preferencesButton = createButton("Preferences");
        preferencesButton.addActionListener(e -> openPreferencesUI());
        leftPanel.add(preferencesButton);
        JButton tbrButton = createButton("My TBR");
        tbrButton.addActionListener(e -> {
            try {
                // Read books from tbr.dat
                List<Book> tbrList = readTBRFromFile("src/main/java/com/readiculousgoals/data/tbr"+user.getUsername()+".dat");
        
                // If TBR list is empty, show a message
                if (tbrList.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Your TBR list is empty!", "My TBR", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
        
                // Create a string representation of books for display
                StringBuilder tbrDisplay = new StringBuilder("Books in your TBR:\n");
                for (int i = 0; i < tbrList.size(); i++) {
                    Book book = tbrList.get(i);
                    tbrDisplay.append((i + 1))
                              .append(". Title: ").append(book.getTitle())
                              .append(", Author: ").append(book.getAuthor())
                              .append("\n");
                }
        
                // Display the TBR list in a dialog
                JOptionPane.showMessageDialog(this, tbrDisplay.toString(), "My TBR", JOptionPane.INFORMATION_MESSAGE);
        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error reading TBR: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        leftPanel.add(tbrButton);
        JButton logoutButton = createButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose(); // Close current window
            new LoginPage(); 
        });
        leftPanel.add(logoutButton);

        add(leftPanel, BorderLayout.WEST);
    }
    private void initializeTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.setBackground(Color.WHITE);

        // Create menu toggle button with icon
        ImageIcon menuIcon = new ImageIcon("src/main/java/com/readiculousgoals/resources/menu-icon.png");
        // Fallback if icon not found
        JButton toggleButton;
        if (menuIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image scaledImage = menuIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            toggleButton = new JButton(new ImageIcon(scaledImage));
        } else {
            toggleButton = new JButton("☰"); // Unicode menu symbol as fallback
        }
        
        toggleButton.setPreferredSize(new Dimension(30, 30));
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(Color.WHITE);
        toggleButton.addActionListener(e -> leftPanel.setVisible(!leftPanel.isVisible()));

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(30);
        searchField.setPreferredSize(new Dimension(300, 25));
        
        // Add search functionality
        searchField.addActionListener(e -> filterBooks());
        
        // Add document listener for real-time search
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filterBooks(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filterBooks(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filterBooks(); }
        });
        // Add components to search panel
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        // Add all components to top panel
        topPanel.add(toggleButton);
        topPanel.add(searchPanel);
        // Add top panel to frame
        add(topPanel, BorderLayout.NORTH);
    }
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(null);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFocusPainted(false);
        // Set only the bottom border
        button.setBorder(new CompoundBorder(
            new EmptyBorder(0, 0, 0, 0), 
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#7a4f29"))
        ));
        // Add hover effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.decode("#7a4f29"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.getModel().isPressed()) {
                    button.setBackground(null);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(Color.decode("#614023")); // Darker shade when pressed
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.contains(e.getPoint())) {
                    button.setBackground(Color.decode("#7a4f29"));
                } else {
                    button.setBackground(null);
                }
            }
        });
        return button;
    }
    /////

    private void loadGenresAndBooks() {
        contentPanel.removeAll();
        List<Genre> preferences = user.getPreferences();
        
        for (Genre genre : preferences) {
            JPanel genrePanel = createGenrePanel(genre.getName());
            contentPanel.add(genrePanel);
        }

        // Add empty panels if we have fewer than MAX_VISIBLE_GENRES
        int emptyPanelsNeeded = Math.max(0, MAX_VISIBLE_GENRES - preferences.size());
        for (int i = 0; i < emptyPanelsNeeded; i++) {
            contentPanel.add(createEmptyGenrePanel(GENRE_PANEL_HEIGHT));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }


    private JPanel createGenrePanel(String genre) {
        // Create main panel with border layout
        JPanel genrePanel = new JPanel(new BorderLayout());
        genrePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            genre,
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        genrePanel.setPreferredSize(new Dimension(getWidth(), GENRE_PANEL_HEIGHT));
        
        // Create books panel with FlowLayout
        JPanel booksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        List<Book> books = loadBooksForGenre(genre);
        
        if (books.isEmpty()) {
            JLabel noBooksLabel = new JLabel("No books available for this genre");
            noBooksLabel.setForeground(Color.GRAY);
            booksPanel.add(noBooksLabel);
        } else {
            for (Book book : books) {
                JPanel bookContainer = createBookContainer(book);
                booksPanel.add(bookContainer);
            }
        }
        
        // Create scroll pane for horizontal scrolling
        JScrollPane scrollPane = new JScrollPane(booksPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Remove scroll pane border
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        
        genrePanel.add(scrollPane, BorderLayout.CENTER);
        return genrePanel;
    }

    private JPanel createEmptyGenrePanel(int height) {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBorder(BorderFactory.createTitledBorder(""));
        emptyPanel.setPreferredSize(new Dimension(getWidth(), height));
        emptyPanel.setBackground(new Color(245, 245, 245));
        return emptyPanel;
    }
    public void refreshHomepage() {
        SwingUtilities.invokeLater(() -> {
            contentPanel.removeAll();
            loadGenresAndBooks();
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }
    private void debugLoadBooks() {
        File file = new File("src/main/java/com/readiculousgoals/data/books.dat");
        System.out.println("test");
        if (!file.exists()) {
            System.out.println("books.dat file not found at: " + file.getAbsolutePath());
            return;
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            List<Book> allBooks = (List<Book>) ois.readObject();
            System.out.println("Books loaded successfully! Total books: " + allBooks.size());
    
            // Print details of each book
            for (int i = 0; i < allBooks.size(); i++) {
                Book book = allBooks.get(i);
                System.out.println("Book " + (i + 1) + ":");
                System.out.println("  Title: " + book.getTitle());
                System.out.println("  Author: " + book.getAuthor());
                System.out.println("  Genre: " + book.getGenre());
                System.out.println("  Age Rating: " + book.getAgeRating());
                System.out.println("  Page Count: " + book.getTotalPages());
                System.out.println("  PDF Content: " + (book.getPdfContent() != null ? "Present" : "Not Present"));
                System.out.println("  Cover Image: " + (book.getCoverImage() != null ? "Present" : "Not Present"));
            }
        } catch (IOException e) {
            System.err.println("Error reading books.dat file: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found while reading books.dat: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Error casting object to List<Book>: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void writeTBRToFile(String filePath, List<Book> tbrList) {
        try {
            // Ensure the parent directory exists
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("Created parent directory: " + parentDir.getAbsolutePath());
                }
            }
    
            // Write the TBR list to the file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(tbrList);
                System.out.println("TBR list successfully written to " + filePath);
            }
        } catch (IOException ex) {
            System.err.println("Error writing TBR list to file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public List<Book> readTBRFromFile(String filePath) {
        List<Book> tbrList = new ArrayList<>();
        File file = new File(filePath);
    
        // Check if the file exists
        if (!file.exists()) {
            System.out.println("TBR file not found: " + filePath);
            return tbrList; // Return empty list
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            tbrList = (List<Book>) ois.readObject();
            System.out.println("TBR list read successfully from file: " + filePath);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error reading TBR file: " + ex.getMessage());
            ex.printStackTrace();
        }
    
        return tbrList;
    }
    
    

}
   

    
    