package com.readiculousgoals.model;
import java.util.*;
import javax.swing.JOptionPane;

public class RegularUser extends User {
    private static final int MAX_PREFERENCES = 15;
    private List<Genre> preferences = new ArrayList<>();
    // Default constructor
    public RegularUser() {
        super();
    }
    // Constructor with parameters
    public RegularUser(String fullName, int age, String username, String email, String password, Date joinDate, List<Genre> preferences) {
        super(fullName, age, username, email, password, joinDate);
        this.preferences = preferences;
    }
    // Getter for preferences
    public List<Genre> getPreferences() {
        return preferences;
    }

    // Add a genre to preferences
    public void addPreference(Genre genre) {
        if (preferences.size() < MAX_PREFERENCES && !preferences.contains(genre)) {
            preferences.add(genre);
        }
    }

    // Remove a genre from preferences
    public void removePreference(Genre genre) {
        preferences.remove(genre);
    }

    // Check if the user has a preference for a specific genre
    public boolean hasPreference(Genre genre) {
        return preferences.contains(genre);
    }
    
    // Perform user-specific task (overridden method)
    @Override
    public void performUserSpecificTask() {
        JOptionPane.showMessageDialog(null, "Regular User: Viewing your reading dashboard!");
    }
}
