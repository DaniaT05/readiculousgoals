package com.readiculousgoals.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class RegularUser extends User {
    private static final int MAX_PREFERENCES = 15;
    private List<Genre> preferences = new ArrayList<>(15);
    private List<Book> tbr = new ArrayList<>(100);
    private List<ReaderBook> progressTracker = new ArrayList<>(100);
    private ArrayList<Achievement> achievements = new ArrayList<>(50);

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

    // Setter for preferences
    public void setPreferences(List<Genre> preferences) {
        if (preferences != null && preferences.size() <= MAX_PREFERENCES) {
            this.preferences = preferences;
        }
    }

    // Getter for tbr
    public List<Book> getTbr() {
        return tbr;
    }

    // Setter for tbr
    public void setTbr(List<Book> tbr) {
        if (tbr != null) {
            this.tbr = tbr;
        }
    }

    // Getter for progressTracker
    public List<ReaderBook> getProgressTracker() {
        return progressTracker;
    }

    // Setter for progressTracker
    public void setProgressTracker(List<ReaderBook> progressTracker) {
        if (progressTracker != null) {
            this.progressTracker = progressTracker;
        }
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
    
    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }
    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }
}
