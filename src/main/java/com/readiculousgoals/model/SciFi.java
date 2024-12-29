package com.readiculousgoals.model;
import java.util.ArrayList;
public class SciFi extends Fiction {
    public SciFi() {
        super("Sci-Fi", "Futuristic and science-based stories.");
    }
    public int worldBuildingRating(Book book) {
        return 90; // Placeholder value
    }
    public int techLevel(Book book) {
        return 95; // Placeholder value
    }
    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("SciFi")) {
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
