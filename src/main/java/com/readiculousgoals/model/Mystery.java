package com.readiculousgoals.model;

import java.util.ArrayList;

public class Mystery extends Fiction {
    
    public Mystery() {
        super("Mystery", "Intriguing and suspenseful stories.");
    }

    // Most complex plots in mystery using ArrayList instead of List.of()
    public ArrayList<String> mostComplexPlots() {
        ArrayList<String> plots = new ArrayList<>();
        plots.add("The Da Vinci Code");
        plots.add("Gone Girl");
        return plots;
    }

    // Placeholder for guessing the culprit
    public void guessTheCulprit() {
        System.out.println("Guess the culprit feature!");
    }

    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Mystery")) {
            super.addBook(book);
        } else {
            System.out.println("This book does not belong to the Mystery genre!");
        }
    }    

    @Override
    public ArrayList<Book> recommendBooks() {
        return books; // Placeholder logic
    }
}
