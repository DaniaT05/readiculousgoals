package com.readiculousgoals.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderBook extends Book {
    private static final String BOOKS_FILE = "books.ser";
    private boolean isRead;
    private double myRating;
    private int pagesRead;
    private String status;
    public ReaderBook(String title, String author, String genre, String ageRating, String status, int pageCount, int pagesRead, byte[] pdfContent, byte[] coverImage) {
        super(title, author, genre, ageRating, pageCount, pdfContent, coverImage);
        this.isRead = false;
        this.myRating = 0.0;
        this.pagesRead = 0;
        this.status = status;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public void setMyRating(double myRating) {
        if (!isRead) {
            System.out.println("Cannot rate a book that hasn't been read.");
        } else if (myRating < 0 || myRating > 5) {
            System.out.println("Invalid rating. Please provide a rating between 0 and 5.");
        } else {
            this.myRating = myRating;
        }
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
