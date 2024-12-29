package com.readiculousgoals.model;
import java.util.*;
import javax.swing.JOptionPane;
public class RegularUser extends User {
    private static final int MAX_PREFERENCES = 15;
    private ArrayList<String> preferences;

    public RegularUser() {
        super();
        this.preferences = new ArrayList<>(MAX_PREFERENCES);
    }

    public RegularUser(String fullName, int age, String username, String email, String password, Date joinDate) {
        super(fullName, age, username, email, password, joinDate);
        this.preferences = new ArrayList<>(MAX_PREFERENCES);
    }

    public boolean addPreference(String genre) {
        if (preferences.size() < MAX_PREFERENCES && !preferences.contains(genre)) {
            preferences.add(genre);
            return true;
        }
        return false;
    }

    public boolean removePreference(String genre) {
        return preferences.remove(genre);
    }

    public ArrayList<String> getPreferences() {
        return new ArrayList<>(preferences);
    }

    public boolean hasPreference(String genre) {
        return preferences.contains(genre);
    }

    @Override
    public void performUserSpecificTask() {
        JOptionPane.showMessageDialog(null, "Regular User: Viewing your reading dashboard!");
    }
}
