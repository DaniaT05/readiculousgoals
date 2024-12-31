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
            return books;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Book) {
                        books.add((Book) obj);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return books;
    }
}