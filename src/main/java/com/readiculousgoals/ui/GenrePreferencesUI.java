// package ui;
// import javax.swing.*;
// import java.awt.*;
// import java.util.ArrayList;
// import java.util.List;
// import model.*;
// public class GenrePreferencesUI extends JFrame {

//     private static final String[] FICTION_GENRES = {"Thriller", "Romance", "Fantasy", "Science Fiction"};
//     private static final String[] NON_FICTION_GENRES = {"Biography", "Self-Help", "History"};
//     package ui;

//     import javax.swing.*;
//     import java.awt.*;
//     import java.util.ArrayList;
//     import java.util.List;
//     import model.*;
    
//     public class GenrePreferencesUI extends JFrame {
//         private static final String[] FICTION_GENRES = {"Thriller", "Romance", "Fantasy", "Science Fiction"};
//         private static final String[] NON_FICTION_GENRES = {"Biography", "Self-Help", "History"};
    
//         public static void displayGenreSelection(Reader reader) {
//             JFrame frame = new JFrame("Select Your Preferred Genres");
//             frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//             frame.setSize(400, 400);
    
//             JPanel mainPanel = new JPanel();
//             mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
//             List<JCheckBox> selectedCheckboxes = new ArrayList<>();
    
//             // Fiction Section
//             JLabel fictionLabel = new JLabel("Fiction:");
//             fictionLabel.setFont(new Font("Arial", Font.BOLD, 14));
//             mainPanel.add(fictionLabel);
    
//             JPanel fictionPanel = new JPanel();
//             fictionPanel.setLayout(new BoxLayout(fictionPanel, BoxLayout.Y_AXIS));
    
//             for (String genre : FICTION_GENRES) {
//                 JCheckBox checkbox = new JCheckBox(genre);
//                 fictionPanel.add(checkbox);
//                 selectedCheckboxes.add(checkbox);
//             }
//             mainPanel.add(fictionPanel);
    
//             // Non-Fiction Section
//             JLabel nonFictionLabel = new JLabel("Non-Fiction:");
//             nonFictionLabel.setFont(new Font("Arial", Font.BOLD, 14));
//             mainPanel.add(nonFictionLabel);
    
//             JPanel nonFictionPanel = new JPanel();
//             nonFictionPanel.setLayout(new BoxLayout(nonFictionPanel, BoxLayout.Y_AXIS));
    
//             for (String genre : NON_FICTION_GENRES) {
//                 JCheckBox checkbox = new JCheckBox(genre);
//                 nonFictionPanel.add(checkbox);
//                 selectedCheckboxes.add(checkbox);
//             }
//             mainPanel.add(nonFictionPanel);
    
//             // Add Save Button
//             JButton saveButton = new JButton("Save Preferences");
//             saveButton.addActionListener(e -> {
//                 int addedCount = 0;
//                 for (JCheckBox checkbox : selectedCheckboxes) {
//                     if (checkbox.isSelected()) {
//                         if (reader.addPreference(checkbox.getText())) {
//                             addedCount++;
//                         } else {
//                             JOptionPane.showMessageDialog(frame, "Preference list is full (maximum 15).");
//                             break;
//                         }
//                     }
//                 }
//                 if (addedCount > 0) {
//                     TBRSystem.save(reader);
//                     JOptionPane.showMessageDialog(frame, "Preferences saved successfully!");
//                 }
//                 frame.dispose();
//             });
    
//             mainPanel.add(saveButton);
    
//             // Display the UI
//             frame.add(new JScrollPane(mainPanel));
//             frame.setVisible(true);
//         }
//     }
    
//     public static void displayGenreSelection(Reader reader) {
//         JFrame frame = new JFrame("Select Your Preferred Genres");
//         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         frame.setSize(400, 400);

//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

//         List<JCheckBox> selectedCheckboxes = new ArrayList<>();

//         // Fiction Section
//         JLabel fictionLabel = new JLabel("Fiction:");
//         fictionLabel.setFont(new Font("Arial", Font.BOLD, 14));
//         mainPanel.add(fictionLabel);

//         JPanel fictionPanel = new JPanel();
//         fictionPanel.setLayout(new BoxLayout(fictionPanel, BoxLayout.Y_AXIS));

//         for (String genre : FICTION_GENRES) {
//             JCheckBox checkbox = new JCheckBox(genre);
//             fictionPanel.add(checkbox);
//             selectedCheckboxes.add(checkbox);
//         }
//         mainPanel.add(fictionPanel);

//         // Non-Fiction Section
//         JLabel nonFictionLabel = new JLabel("Non-Fiction:");
//         nonFictionLabel.setFont(new Font("Arial", Font.BOLD, 14));
//         mainPanel.add(nonFictionLabel);

//         JPanel nonFictionPanel = new JPanel();
//         nonFictionPanel.setLayout(new BoxLayout(nonFictionPanel, BoxLayout.Y_AXIS));

//         for (String genre : NON_FICTION_GENRES) {
//             JCheckBox checkbox = new JCheckBox(genre);
//             nonFictionPanel.add(checkbox);
//             selectedCheckboxes.add(checkbox);
//         }
//         mainPanel.add(nonFictionPanel);

//         // Add Save Button
//         JButton saveButton = new JButton("Save Preferences");
//         saveButton.addActionListener(e -> {
//             int addedCount = 0;
//             for (JCheckBox checkbox : selectedCheckboxes) {
//                 if (checkbox.isSelected()) {
//                     if (reader.addPreference(checkbox.getText())) {
//                         addedCount++;
//                     } else {
//                         JOptionPane.showMessageDialog(frame, "Preference list is full (maximum 15).");
//                         break;
//                     }
//                 }
//             }
//             if (addedCount > 0) {
//                 TBRSystem.save(reader);
//                 JOptionPane.showMessageDialog(frame, "Preferences saved successfully!");
//             }
//             frame.dispose();
//         });

//         mainPanel.add(saveButton);

//         // Display the UI
//         frame.add(new JScrollPane(mainPanel));
//         frame.setVisible(true);
//     }
// }
