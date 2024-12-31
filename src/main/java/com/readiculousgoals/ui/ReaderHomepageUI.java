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
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Box;
import com.readiculousgoals.model.Book;
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
    private int genreIndex = 0;

    public ReaderHomepageUI(User newUser) {
        RegularUser user = (RegularUser) newUser;
        this.user = user;
        
        setTitle("Reader Homepage - Welcome, " + user.getFullName());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeLeftPanel();
        initializeTopPanel();

        // Calculate the available height for content
        // Assuming top panel is about 50 pixels high
        int availableHeight = 700 - 50; // Total height minus top panel height
        int genrePanelHeight = availableHeight / MAX_VISIBLE_GENRES;

        // Content Panel setup
        contentPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                int preferredHeight = genrePanelHeight * Math.min(MAX_VISIBLE_GENRES, 
                    Math.max(user.getPreferences().size(), MAX_VISIBLE_GENRES));
                return new Dimension(getWidth(), preferredHeight);
            }
        };
        contentPanel.setLayout(new GridLayout(MAX_VISIBLE_GENRES, 1, 0, 0));

        // Main scroll pane
        mainScrollPane = new JScrollPane(contentPanel);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainScrollPane.setBorder(null); // Remove border

        add(mainScrollPane, BorderLayout.CENTER);
        loadGenresAndBooks(genrePanelHeight);
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
    private void loadRemainingGenres(List<Genre> remainingGenres) {
        for (Genre genre : remainingGenres) {
            JPanel genrePanel = createGenrePanel(genre.getName(),650/5);
            contentPanel.add(genrePanel);
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createBookContainer(Book book) {
        JPanel bookContainer = new JPanel();
        bookContainer.setLayout(new BorderLayout());
        bookContainer.setPreferredSize(new Dimension(150, 200));
        
        // Convert byte array to ImageIcon
        ImageIcon coverIcon = new ImageIcon(book.getCoverImage());
        Image scaledImage = coverIcon.getImage().getScaledInstance(150, 180, Image.SCALE_SMOOTH);
        JLabel bookImage = new JLabel(new ImageIcon(scaledImage));
        
        JLabel bookTitle = new JLabel(book.getTitle(), SwingConstants.CENTER);
        bookTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        
        bookContainer.add(bookImage, BorderLayout.CENTER);
        bookContainer.add(bookTitle, BorderLayout.SOUTH);
        
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
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 500);
        
        // Create book cover panel
        ImageIcon coverIcon = new ImageIcon(book.getCoverImage());
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
        JLabel pageCountLabel = new JLabel("Page Count: " + book.getPageCount());
        
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
            user.getTbr().add(book);
            JOptionPane.showMessageDialog(dialog, "Added to TBR successfully!");
        });
        
        JButton readNowButton = new JButton("Read Now");
        readNowButton.addActionListener(e -> {
            ReaderBook readerBook = new ReaderBook(
                book.getTitle(), book.getAuthor(), book.getGenre(),
                book.getAgeRating(), "In Progress", book.getPageCount(),
                0, book.getPdfContent(), book.getCoverImage()
            );
            user.getProgressTracker().add(readerBook);
            JOptionPane.showMessageDialog(dialog, "Added to Progress Tracker!");
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
        List<Book> booksForGenre = new ArrayList<>();
        File file = new File("src/main/java/com/readiculousgoals/data/books.dat");
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Book> allBooks = (List<Book>) ois.readObject();
                return allBooks.stream()
                    .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                    .collect(java.util.stream.Collectors.toList());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        return booksForGenre;
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

        JButton readingGoalsButton = createButton("Reading Goals");
        readingGoalsButton.addActionListener(e -> {
            // Add reading goals functionality here
        });
        leftPanel.add(readingGoalsButton);

        JButton tbrButton = createButton("My TBR");
        tbrButton.addActionListener(e -> {
            // Add TBR list functionality here
        });
        leftPanel.add(tbrButton);

        JButton settingsButton = createButton("Settings");
        settingsButton.addActionListener(e -> {
            // Add settings functionality here
        });
        leftPanel.add(settingsButton);

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
    

    

    private void loadGenresAndBooks(int panelHeight) {
        contentPanel.removeAll();
        List<Genre> preferences = user.getPreferences();
        
        // Always show exactly MAX_VISIBLE_GENRES panels (filled or empty)
        for (int i = 0; i < MAX_VISIBLE_GENRES; i++) {
            if (i < preferences.size()) {
                // Add genre panel with books
                Genre genre = preferences.get(i);
                JPanel genrePanel = createGenrePanel(genre.getName(), panelHeight);
                contentPanel.add(genrePanel);
            } else {
                // Add empty panel
                JPanel emptyPanel = createEmptyGenrePanel(panelHeight);
                contentPanel.add(emptyPanel);
            }
        }

        // Only show scrollbar if we have more than MAX_VISIBLE_GENRES genres
        mainScrollPane.setVerticalScrollBarPolicy(
            preferences.size() > MAX_VISIBLE_GENRES ? 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS : 
            JScrollPane.VERTICAL_SCROLLBAR_NEVER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createGenrePanel(String genre, int height) {
        JPanel genrePanel = new JPanel(new BorderLayout());
        genrePanel.setBorder(BorderFactory.createTitledBorder(genre));
        genrePanel.setPreferredSize(new Dimension(getWidth(), height));
        
        // Books panel with horizontal scrolling
        JPanel booksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        List<Book> books = loadBooksForGenre(genre);
        
        for (Book book : books) {
            JPanel bookContainer = createBookContainer(book);
            booksPanel.add(bookContainer);
        }
        
        JScrollPane scrollPane = new JScrollPane(booksPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
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
            int availableHeight = getHeight() - 50;
            int genrePanelHeight = availableHeight / MAX_VISIBLE_GENRES;
            contentPanel.removeAll();
            loadGenresAndBooks(genrePanelHeight);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }

}