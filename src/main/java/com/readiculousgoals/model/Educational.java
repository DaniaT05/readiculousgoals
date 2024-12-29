package com.readiculousgoals.model;
import java.util.ArrayList;
public class Educational extends NonFiction {
    public Educational() {
        super("Educational", "Books for academic and personal growth.");
    }

    public ArrayList<Book> recommendBySubject() {
        return books; // Placeholder logic
    }

    public String gradeLevel(Book book) {
        return "Undergraduate"; // Placeholder value
    }
    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Educational")) {
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