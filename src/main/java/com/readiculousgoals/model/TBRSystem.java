// package model;
// import ui.GenreSelectionUI;
// import java.io.*;
// import java.util.*;
// import javax.swing.*;
// public class TBRSystem {

//     public static User loadUser(String username) {
//         try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
//             while (true) {
//                 User user = (User) ois.readObject();
//                 if (user.getUsername().equals(username)) {
//                     return user;
//                 }
//             }
//         } catch (EOFException e) {
//             System.out.println("User not found.");
//         } catch (IOException | ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//         return null;
//     }

//     public static void saveUser(User user) {
//         List<User> users = new ArrayList<>();
//         try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
//             while (true) {
//                 users.add((User) ois.readObject());
//             }
//         } catch (EOFException e) {
//             // End of file
//         } catch (IOException | ClassNotFoundException e) {
//             e.printStackTrace();
//         }

//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
//             boolean updated = false;
//             for (User u : users) {
//                 if (u.getUsername().equals(user.getUsername())) {
//                     oos.writeObject(user);
//                     updated = true;
//                 } else {
//                     oos.writeObject(u);
//                 }
//             }
//             if (!updated) {
//                 oos.writeObject(user);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//     public static void main(String[] args) {
//         String username = JOptionPane.showInputDialog("Enter your username:");
//         User user = loadUser(username);
//         if (user == null) {
//             user = new User(username);
//         }
//         GenreSelectionUI.displayGenreSelection(user);
//     }
// }
