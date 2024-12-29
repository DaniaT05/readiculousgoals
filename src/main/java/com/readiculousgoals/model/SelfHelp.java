package com.readiculousgoals.model;
import java.util.ArrayList;
public class SelfHelp extends NonFiction {
    public SelfHelp() {
        super("Self-Help", "Books for personal development.");
    }

    public ArrayList<Book> recommendFor(Book book) {
        return books; // Placeholder logic
    }
    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("SelfHelp")) {
            super.addBook(book);
        } else {
            System.out.println("This book does not belong to the Romance genre!");
        }
    }

    @Override
    public ArrayList<Book> recommendBooks() {
        return books;
    }
}