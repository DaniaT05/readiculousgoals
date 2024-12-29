package com.readiculousgoals.model;
import java.util.ArrayList;
public class Religion extends NonFiction {
    public Religion() {
        super("Religion", "Books on faith and spirituality.");
    }

    public ArrayList<Book> suggestIslamic() {
        return books; // Placeholder logic
    }

    public ArrayList<Book> suggestPhilosophical() {
        return books; // Placeholder logic
    }
    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Religion")) {
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