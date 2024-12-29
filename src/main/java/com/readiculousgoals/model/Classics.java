package com.readiculousgoals.model;
import java.util.ArrayList;
public class Classics extends Fiction {
    public Classics() {
        super("Classics", "Timeless literary masterpieces.");
    }

    public ArrayList<Book> suggestByEra(String era) {
        return books; // Placeholder logic
    }

    public String highlightThemes(Book book) {
        return "Courage and morality"; // Placeholder value
    }
    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Classics")) {
            super.addBook(book);
        } else {
            System.out.println("This book does not belong to the Romance genre!");
        }
    }    
    @Override
    public ArrayList<Book> recommendBooks() {
        return books; // Placeholder logic
    }
}
