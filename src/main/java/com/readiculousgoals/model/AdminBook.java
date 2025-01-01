package com.readiculousgoals.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminBook extends Book {
    private static final String BOOKS_FILE = "src/main/java/com/readiculousgoals/data/books.dat";
    private static final long serialVersionUID = 2306690647651245740L;  
    
    public AdminBook(int bookId, String title, String author, String genres, String ageRating, int pageCount, byte[] pdfContent, byte[] coverImage) {
        super(bookId,title, author, genres, pageCount, ageRating, pdfContent, coverImage);
    }

    public void addBook(String title, String author, String genres, String ageRating, int pageCount, byte[] pdfContent, byte[] coverImage) {
        List<Book> books = loadBooks();
        Book newBook = new Book(bookId, title, author, genres, pageCount, ageRating, pdfContent, coverImage);
        books.add(newBook);
        saveBooks(books);
        System.out.println("New book added successfully!");
    }

    public static void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
