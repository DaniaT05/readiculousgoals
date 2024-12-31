package com.readiculousgoals.model;
import java.util.Date;
import javax.swing.JOptionPane;

import com.readiculousgoals.ui.AdminControlsPage;

public class AdminUser extends User {

    // Default constructor
    public AdminUser() {
        super();
    }

    // Parameterized constructor 
    public AdminUser(String fullName, int age, String username, String email, String password, Date joinDate) {
        super(fullName, age, username, email, password, joinDate);
    }

    @Override
    public void performUserSpecificTask() {
        JOptionPane.showMessageDialog(null, "Admin: Managing Users and System Settings.");
        // Display the admin controls page
        showAdminControls();
    }

    // Method to display the admin controls page
    public void showAdminControls() {
        AdminControlsPage adminControlsPage = new AdminControlsPage(this);
        adminControlsPage.displayAdminControls();
    }
}
