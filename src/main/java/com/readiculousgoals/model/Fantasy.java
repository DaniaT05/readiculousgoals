package com.readiculousgoals.model;

import java.util.ArrayList;

public class Fantasy extends Fiction {

    public Fantasy() {
        super("Fantasy", "Magical and mythical storytelling.");
    }

    public int magicSystemScore(Book book) {
        return 80; // Placeholder value
    }

    public String fantasyType(Book book) {
        return "High Fantasy"; // Placeholder value
    }

    // Replaced List.of() with ArrayList for mutable list
    public ArrayList<String> recommendTropes() {
        ArrayList<String> tropes = new ArrayList<>();
        tropes.add("Chosen One");
        tropes.add("Epic Quest");
        return tropes;
    }

    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Fantasy")) {
            super.addBook(book);
        } else {
            System.out.println("This book does not belong to the Fantasy genre!");
        }
    }

    @Override
    public ArrayList<Book> recommendBooks() {
        return books; // Placeholder logic
    }
}
