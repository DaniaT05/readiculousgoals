package com.readiculousgoals.model;
import java.util.ArrayList;
public class History extends NonFiction {
    public History() {
        super("History", "Exploration of historical events and timelines.");
    }
    public int historicalAccuracy(Book book) {
        return 90; // Placeholder value
    }
    public String timelineEra(Book book) {
        return "Medieval"; // Placeholder value
    }
    public ArrayList<Book> suggestByTimeline() {
        return books; // Placeholder logic
    }
    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("History")) {
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