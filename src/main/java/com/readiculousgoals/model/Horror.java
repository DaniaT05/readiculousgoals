package com.readiculousgoals.model;
import java.util.ArrayList;
public class Horror extends Fiction {
    public Horror() {
        super("Horror", "Scary and thrilling stories.");
    }

    public int scareMeter(Book book) {
        return 90; // Placeholder value
    }

    public ArrayList<Book> recommendClassicHorrors() {
        return books; // Placeholder logic
    }
    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Horror")) {
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
