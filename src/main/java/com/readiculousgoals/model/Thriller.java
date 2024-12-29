package com.readiculousgoals.model;
import java.util.ArrayList;
public class Thriller extends Fiction {
    public Thriller() {
        super("Thriller", "Edge-of-the-seat suspense stories.");
    }

    public int thrillMeter(Book book) {
        return 95; // Placeholder value
    }

    public int plotTwistRating(Book book) {
        return 90; // Placeholder value
    }
    @Override
public void addBook(Book book) {
    if (book.getGenre().equalsIgnoreCase("Thriller")) {
        super.addBook(book);
    } else {
        System.out.println("This book does not belong to the Romance genre!");
    }
}
    @Override
    public ArrayList<Book> recommendBooks() {
        ArrayList<Book> recommendations = new ArrayList<>();
        for (Book book : books) {
            if (thrillMeter(book) > 80) {
                recommendations.add(book);
            }
        }
        return recommendations;
    }
}
