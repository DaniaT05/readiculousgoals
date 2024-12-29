// package ui;

// import java.io.*;
// import java.util.*;
// import javax.swing.*;

// import model.RegularUser;
// public class Preferences extends JFrame {
//         public static void saveRegularUser(RegularUser reader) {
//         List<RegularUser> readers = new ArrayList<>();
//         try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/users.dat"))) {
//             while (true) {
//                 readers.add((RegularUser) ois.readObject());
//             }
//         } catch (EOFException e) {
//             // End of file
//         } catch (IOException | ClassNotFoundException e) {
//             e.printStackTrace();
//         }

//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/users.dat"))) {
//             boolean updated = false;
//             for (RegularUser r : readers) {
//                 if (r.getUsername().equals(reader.getUsername())) {
//                     oos.writeObject(reader); // Write updated reader
//                     updated = true;
//                 } else {
//                     oos.writeObject(r); // Write existing readers
//                 }
//             }
//             if (!updated) {
//                 oos.writeObject(reader); // If new reader, add to file
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//     public static void displayGenreSelection(RegularUser reader) {
//         String[] genres = {"Fiction", "Non-Fiction", "Mystery", "Fantasy", "Romance", "Science Fiction", "Horror"};
//         JCheckBox[] checkboxes = new JCheckBox[genres.length];
//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//         for (int i = 0; i < genres.length; i++) {
//             checkboxes[i] = new JCheckBox(genres[i]);
//             panel.add(checkboxes[i]);
//         }

//         int result = JOptionPane.showConfirmDialog(null, panel, "Select Your Preferred Genres",
//                 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

//         if (result == JOptionPane.OK_OPTION) {
//             for (JCheckBox checkbox : checkboxes) {
//                 if (checkbox.isSelected()) {
//                     if (!reader.addPreference(checkbox.getText())) {
//                         JOptionPane.showMessageDialog(null, "Preference list is full (maximum 15).");
//                         break;
//                     }
//                 }
//             }
//             saveRegularUser(reader);
//             JOptionPane.showMessageDialog(null, "Preferences saved successfully!");
//         }
//     }
// }
