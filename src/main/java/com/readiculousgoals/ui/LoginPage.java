package com.readiculousgoals.ui;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import com.readiculousgoals.model.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Component;
//login, signup, reseting password
// username for admin: admin 
//password: admin123
public class LoginPage {

    private JFrame frame;
    private JPanel loginPanel, signupPanel, resetPasswordPanel;
    private JTextField loginUsernameField, signupFullNameField, signupAgeField, signupUsernameField, signupEmailField, resetUsernameField, resetEmailField;
    private JPasswordField loginPasswordField, signupPasswordField, resetPasswordField;
    private JButton loginButton, signupButton, switchToSignupButton, switchToLoginButton, resetPasswordButton, backToLoginButton;
    private List<User> users;

    public LoginPage() {
        users = loadUsers(); // Load users from file

        // Frame Setup
        frame = new JFrame("Readiculous Goals - Welcome");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new CardLayout());

        initializeLoginPanel();
        initializeSignupPanel();
        initializeResetPasswordPanel();

        frame.add(loginPanel, "LOGIN");
        frame.add(signupPanel, "SIGNUP");
        frame.add(resetPasswordPanel, "RESET_PASSWORD");

        frame.setVisible(true);
    }

    private void initializeLoginPanel() {
        loginPanel = createGradientPanel(); // Set gradient background
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title and Slogan
        JLabel titleLabel = new JLabel("Welcome to Readiculous Goals");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        JLabel sloganLabel = new JLabel("Your Journey Through Books Starts Here.");
        sloganLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
        sloganLabel.setForeground(Color.LIGHT_GRAY);

        // Add title and slogan
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);
        gbc.gridy = 1;
        loginPanel.add(sloganLabel, gbc);

        // Username Field
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(createLabel("Username:"), gbc);
        loginUsernameField = createTextField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(loginUsernameField, gbc);

        // Password Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(createLabel("Password:"), gbc);
        loginPasswordField = createPasswordField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(loginPasswordField, gbc);

        // Login Button
        loginButton = createRoundedButton("Login", new Color(96, 65, 44));
        loginButton.addActionListener(e -> handleLogin());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // Switch to Signup
        switchToSignupButton = createRoundedButton("New User? Sign Up", new Color(96, 65, 44));
        switchToSignupButton.addActionListener(e -> showSignupPanel());
        gbc.gridy = 5;
        loginPanel.add(switchToSignupButton, gbc);

        // Switch to Reset Password
        JButton forgotPasswordButton = createRoundedButton("Forgot Password?", new Color(96, 65, 44));
        forgotPasswordButton.addActionListener(e -> showResetPasswordPanel());
        gbc.gridy = 6;
        loginPanel.add(forgotPasswordButton, gbc);
    }

    private void initializeSignupPanel() {
        signupPanel = createGradientPanel(); // Set gradient background
        signupPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        signupPanel.add(titleLabel, gbc);

        // Full Name
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        signupPanel.add(createLabel("Full Name:"), gbc);
        signupFullNameField = createTextField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        signupPanel.add(signupFullNameField, gbc);

        // Age
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        signupPanel.add(createLabel("Age:"), gbc);
        signupAgeField = createTextField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        signupPanel.add(signupAgeField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        signupPanel.add(createLabel("Username:"), gbc);
        signupUsernameField = createTextField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        signupPanel.add(signupUsernameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        signupPanel.add(createLabel("Email:"), gbc);
        signupEmailField = createTextField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        signupPanel.add(signupEmailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        signupPanel.add(createLabel("Password:"), gbc);
        signupPasswordField = createPasswordField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        signupPanel.add(signupPasswordField, gbc);

        // Signup Button
        signupButton = createRoundedButton("Sign Up", new Color(96, 65, 44));
        signupButton.addActionListener(e -> handleSignup());
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        signupPanel.add(signupButton, gbc);

        // Switch to Login
        switchToLoginButton = createRoundedButton("Back to Login", new Color(96, 65, 44));
        switchToLoginButton.addActionListener(e -> showLoginPanel());
        gbc.gridy = 7;
        signupPanel.add(switchToLoginButton, gbc);
    }

    private void initializeResetPasswordPanel() {
        resetPasswordPanel = createGradientPanel(); // Set gradient background
        resetPasswordPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Reset Password");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        resetPasswordPanel.add(titleLabel, gbc);

        // Reset Username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        resetPasswordPanel.add(createLabel("Username:"), gbc);
        resetUsernameField = createTextField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        resetPasswordPanel.add(resetUsernameField, gbc);

        // Reset Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        resetPasswordPanel.add(createLabel("Email:"), gbc);
        resetEmailField = createTextField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        resetPasswordPanel.add(resetEmailField, gbc);

        // New Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        resetPasswordPanel.add(createLabel("New Password:"), gbc);
        resetPasswordField = createPasswordField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        resetPasswordPanel.add(resetPasswordField, gbc);

        // Reset Password Button
        resetPasswordButton = createRoundedButton("Reset Password", new Color(96, 65, 44));
        resetPasswordButton.addActionListener(e -> handleResetPassword());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        resetPasswordPanel.add(resetPasswordButton, gbc);

        // Switch to Login
        backToLoginButton = createRoundedButton("Back to Login", new Color(0, 150, 255));
        backToLoginButton.addActionListener(e -> showLoginPanel());
        gbc.gridy = 5;
        resetPasswordPanel.add(backToLoginButton, gbc);
    }

    private void handleLogin() {
        String username = loginUsernameField.getText();
        String password = new String(loginPasswordField.getPassword());
        boolean isAuthenticated = false;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + username + "!");
                // Transition to ReaderHomepageUI
                if (user instanceof RegularUser) {
                    new ReaderHomepageUI((RegularUser) user);
                } else if (user instanceof AdminUser) {
                    new AdminHomepageUI((AdminUser) user);
                }
                frame.dispose(); // Close the login frame
                isAuthenticated = true;
                break;
            }
        }
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void handleSignup() {
        String fullName = signupFullNameField.getText();
        String ageStr = signupAgeField.getText();
        String username = signupUsernameField.getText();
        String email = signupEmailField.getText();
        String password = new String(signupPasswordField.getPassword());

        if (fullName.isEmpty() || ageStr.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.", "Signup Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Age must be a number.", "Signup Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(frame, "Username is already taken. Please choose a different one.", "Signup Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Capture the current date as the join date
        Date joinDate = new Date();
        User newUser;
        // Create a new user and save to the list and file
        if (email.contains("@readiculousgoals.com")) {
            newUser = new AdminUser(fullName, age, username, email, password, joinDate); 
            users.add(newUser);
        } else {
            newUser = new RegularUser(fullName, age, username, email, password, joinDate, new ArrayList<>());
            users.add(newUser);
        }
        saveUsers();
        JOptionPane.showMessageDialog(frame, "Signup successful! Redirecting to your homepage...", "Signup Successful", JOptionPane.INFORMATION_MESSAGE);
        if (newUser instanceof AdminUser) {
            new AdminHomepageUI((AdminUser)newUser);
        } else {
            new PreferencesUI((RegularUser)newUser);
            new ReaderHomepageUI(newUser);
        }
        frame.dispose(); // Close the signup frame
    }
    private void handleResetPassword() {
        String username = resetUsernameField.getText();
        String email = resetEmailField.getText();
        String newPassword = new String(resetPasswordField.getPassword());

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                saveUsers();
                JOptionPane.showMessageDialog(frame, "Password reset successful!");
                showLoginPanel();
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Username and email not found.", "Reset Failed", JOptionPane.ERROR_MESSAGE);
    }

    private void saveUsers() {
        File file = new File("src/main/java/com/readiculousgoals/data/users.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to save user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private List<User> loadUsers() {
        File file = new File("src/main/java/com/readiculousgoals/data/users.dat");
        if (!file.exists()) {
            System.out.println("No user data found, starting with an empty list.");
            return new ArrayList<>();
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object object = ois.readObject();
            if (object instanceof List<?>) {
                List<User> users = (List<User>) object;
                
                // Debugging: Print users to console
                if (users.isEmpty()) {
                    System.out.println("No users found.");
                } else {
                    System.out.println("Loaded users:");
                    for (User user : users) {
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Full Name: " + user.getFullName());
                        System.out.println("Email: " + user.getEmail());
                        System.out.println("Age: " + user.getAge());
                        System.out.println("--------------------");
                    }
                }
    
                return users;
            } else {
                throw new ClassNotFoundException("Deserialized object is not a List<User>");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to load user data. Starting with an empty list.", "Error", JOptionPane.WARNING_MESSAGE);
            return new ArrayList<>();
        }
    }
    
    

    private void showLoginPanel() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "LOGIN");
    }

    private void showSignupPanel() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "SIGNUP");
    }

    private void showResetPasswordPanel() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "RESET_PASSWORD");
    }

    private JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Create a gradient from a light blue at the top to a deeper blue at the bottom
                GradientPaint gp = new GradientPaint(0, 0, new Color(205,149,100), 0, height, new Color(233,203,169));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Verdana", Font.PLAIN, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Verdana", Font.PLAIN, 14));
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 14));
        return passwordField;
    }

    private JButton createRoundedButton(String text, Color color) {
        JButton button = new ButtonUI(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setBorder(new RoundedBorder(7, color));  // Pass the button color to the border
        return button;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
    static class RoundedBorder implements javax.swing.border.Border {
        private int radius;
        private Color borderColor;  // Store the border color
    
        public RoundedBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;  // Initialize the border color
        }
    
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }
    
        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(borderColor);  // Set the border color to the button's background color
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}