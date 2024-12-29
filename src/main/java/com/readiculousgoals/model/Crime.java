package com.readiculousgoals.model;

import java.util.ArrayList;

public class Crime extends Fiction {
    
    public Crime() {
        super("Crime", "Stories involving criminal acts and mysteries.");
    }

    // Replaced List.of() with ArrayList for mutable list
    public ArrayList<String> detectiveProfiles() {
        ArrayList<String> profiles = new ArrayList<>();
        profiles.add("Sherlock Holmes");
        profiles.add("Hercule Poirot");
        return profiles;
    }

    public ArrayList<Book> unsolvedCases() {
        return books; // Placeholder logic
    }

    public int crimeMeter(Book book) {
        return 70; // Placeholder value
    }

    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Crime")) {
            super.addBook(book);
        } else {
            System.out.println("This book does not belong to the Crime genre!");
        }
    }

    @Override
    public ArrayList<Book> recommendBooks() {
        return books; // Placeholder logic
    }
}
