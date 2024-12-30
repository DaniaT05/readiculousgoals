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
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.readiculousgoals.model.Book;
import com.readiculousgoals.model.Genre;
import com.readiculousgoals.model.ReaderBook;
import com.readiculousgoals.model.RegularUser;
import com.readiculousgoals.model.User;

public class ReaderHomepageUI extends JFrame {
    private RegularUser user;
    private JPanel leftPanel;
    private JPanel contentPanel;
    private JTextField searchField;
    private int genreIndex = 0; // To track which genres are loaded
    private static final int GENRES_PER_PAGE = 5; // Limit to 5 genres at a time


    public ReaderHomepageUI(User newUser) {
        RegularUser user = (RegularUser) newUser;
        this.user = user;

        // Frame settings
        setTitle("Reader Homepage - Welcome, " + user.getFullName());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left Panel (hidden by default)
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setBackground(Color.decode("#986842")); 
        leftPanel.setVisible(false);
        leftPanel.setLayout(new GridLayout(13, 1, 0, 0));

        // Add buttons to the left panel
        JButton profileButton = createButton("Profile");
        profileButton.addActionListener(e -> openUserProfile());
        leftPanel.add(profileButton);

        JButton preferencesButton = createButton("Preferences");
        preferencesButton.addActionListener(e -> openPreferencesUI());
        leftPanel.add(preferencesButton);

        JButton readingGoalsButton = createButton("Reading Goals");
        leftPanel.add(readingGoalsButton);

        JButton tbrButton = createButton("My TBR");
        leftPanel.add(tbrButton);

        JButton settingsButton = createButton("Settings");
        leftPanel.add(settingsButton);

        JButton logoutButton = createButton("Logout");
        leftPanel.add(logoutButton);

        add(leftPanel, BorderLayout.WEST);

        // Top Panel with toggle button and search bar
        ImageIcon icon = new ImageIcon("src/ui/icons/menu-bar-icon.png");
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton toggleButton = new JButton(scaledIcon);
        toggleButton.setFont(new Font("Arial", Font.BOLD, 16));
        toggleButton.setBackground(Color.DARK_GRAY);
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setFocusPainted(false);
        toggleButton.addActionListener(e -> leftPanel.setVisible(!leftPanel.isVisible()));

        searchField = new JTextField(30);
        searchField.setToolTipText("Search books by title or author");
        searchField.addActionListener(e -> filterBooks());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(toggleButton);
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);

        add(topPanel, BorderLayout.NORTH);

        // Content Panel for genres and books
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        loadGenresAndBooks();

        // Make the frame visible
        setVisible(true);
    }


    private JPanel createGenrePanel(String genre) {
        JPanel genrePanel = new JPanel(new BorderLayout());
        genrePanel.setBorder(BorderFactory.createTitledBorder(genre));
        JPanel booksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Load books for the genre
        List<Book> books = loadBooksForGenre(genre);
        for (Book book : books) {
            JPanel bookContainer = createBookContainer(book);
            booksPanel.add(bookContainer);
        }
        JButton scrollButton = new JButton(">>");
        scrollButton.addActionListener(e -> {
            // Add scroll functionality
        });
        genrePanel.add(booksPanel, BorderLayout.CENTER);
        genrePanel.add(scrollButton, BorderLayout.EAST);
        return genrePanel;
    }
    private JPanel createBookContainer(Book book) {
        JPanel bookContainer = new JPanel();
        bookContainer.setLayout(new BorderLayout());
        bookContainer.setPreferredSize(new Dimension(120, 180));
        JLabel bookImage = new JLabel(new ImageIcon(new ImageIcon(book.getCoverImage()).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
        JLabel bookTitle = new JLabel(book.getTitle(), SwingConstants.CENTER);
        bookContainer.add(bookImage, BorderLayout.CENTER);
        bookContainer.add(bookTitle, BorderLayout.SOUTH);
        bookContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bookContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openBookDialog(book);
            }
        });
        return bookContainer;
    }
    private void openBookDialog(Book book) {
        JDialog dialog = new JDialog(this, book.getTitle(), true);
        dialog.setLayout(new BorderLayout());
        JLabel bookImage = new JLabel(new ImageIcon(new ImageIcon(book.getCoverImage()).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH)));
        JLabel bookDetails = new JLabel("<html>Title: " + book.getTitle() + "<br>Author: " + book.getAuthor() + "<br>Genre: " + book.getGenre() + "<br>Age Rating: " + book.getAgeRating() + "<br>Page Count: " + book.getPageCount() + "</html>");
        JButton addToTBRButton = new JButton("Add to TBR");
        addToTBRButton.addActionListener(e -> {
            user.getTbr().add(book);
            JOptionPane.showMessageDialog(dialog, "Added to TBR");
        });
        JButton readNowButton = new JButton("Read Now");
        readNowButton.addActionListener(e -> {
            ReaderBook readerBook = new ReaderBook(book.getTitle(), book.getAuthor(), book.getGenre(), book.getAgeRating(), "In Progress", book.getPageCount(), 0, book.getPdfContent(), book.getCoverImage());
            user.getProgressTracker().add(readerBook);
            JOptionPane.showMessageDialog(dialog, "Added to Progress Tracker");
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addToTBRButton);
        buttonPanel.add(readNowButton);
        dialog.add(bookImage, BorderLayout.NORTH);
        dialog.add(bookDetails, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setSize(300, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    private void loadGenresAndBooks() {
        // This sets the layout of the content panel to be a BoxLayout for vertical stacking
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); 
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the frame
        // Load and display the first 5 genres
        displayGenres(0, GENRES_PER_PAGE);
        // Add a listener to detect when the user reaches the end of the list and load more genres
        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            int maxScroll = e.getAdjustable().getMaximum();
            int currentScroll = e.getAdjustable().getValue();
            if (currentScroll >= maxScroll - 100) {  // When near the bottom, load more genres
                if (genreIndex < user.getPreferences().size()) {
                    displayGenres(genreIndex, GENRES_PER_PAGE);
                }
            }
        });
    }
    
    
    private void displayGenres(int start, int count) {
        List<Genre> preferences = user.getPreferences();
        int end = Math.min(start + count, preferences.size());
    
        // Add the genres to the content panel
        for (int i = start; i < end; i++) {
            Genre genre = preferences.get(i);
            JPanel genrePanel = createGenrePanel(genre.getName());
            contentPanel.add(genrePanel);
            genreIndex++;
        }
    
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
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
    private List<Book> loadBooksForGenre(String genre) {
        // Initialize an empty list to hold books of the specified genre
        List<Book> booksForGenre = new ArrayList<>();
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            // Read all books from the file
            List<Book> allBooks = (List<Book>) ois.readObject();
    
            // Filter the books by the specified genre
            for (Book book : allBooks) {
                if (book.getGenre().equalsIgnoreCase(genre)) {
                    booksForGenre.add(book);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    
        return booksForGenre;
    }
    
    private void loadRemainingGenres(List<Genre> remainingGenres) {
        for (Genre genre : remainingGenres) {
            JPanel genrePanel = createGenrePanel(genre.getName());
            contentPanel.add(genrePanel);
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    public void refreshHomepage() {
        contentPanel.removeAll(); // Clear existing content
        loadGenresAndBooks();     // Reload genres and books
        contentPanel.revalidate(); // Revalidate the content panel
        contentPanel.repaint();   // Repaint the frame
    }
    
}