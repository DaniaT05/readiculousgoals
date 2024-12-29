package com.readiculousgoals.model;
import javax.swing.JOptionPane;
import java.util.Date;
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
    }
}