package com.readiculousgoals.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookStorage {
    private static final String BOOKS_FILE = "src/main/java/com/readiculousgoals/data/books.dat";
    
    public static void addBook(AdminBook book) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE, true)) {
            protected void writeStreamHeader() throws IOException {
                if (new File(BOOKS_FILE).length() > 0) {
                    reset();
                } else {
                    super.writeStreamHeader();
                }
            }
        }) {
            oos.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(BOOKS_FILE);
        if (!file.exists()) {
            System.out.println("books.dat file not found at: " + file.getAbsolutePath());
            return books;
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Book> allBooks = (List<Book>) ois.readObject();
            System.out.println("Books loaded successfully! Total books: " + allBooks.size());
    
            // Print details of each book
            for (int i = 0; i < allBooks.size(); i++) {
                Book book = allBooks.get(i);
                
                books.add((Book) book);
                System.out.println("Loaded Book in loadBooks: " + book);
        
            }
        } catch (IOException e) {
            System.err.println("Error reading books.dat file: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found while reading books.dat: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Error casting object to List<Book>: " + e.getMessage());
            e.printStackTrace();
        }
        return books;
    }
    
}