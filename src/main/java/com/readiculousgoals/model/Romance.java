package com.readiculousgoals.model;
import java.util.ArrayList;

public class Romance extends Fiction {

    public Romance() {
        super("Romance", "Love stories and emotional connections.");
    }

    // Suggesting romance tropes using ArrayList instead of List.of()
    public ArrayList<String> suggestTropes() {
        ArrayList<String> tropes = new ArrayList<>();
        tropes.add("Enemies to Lovers");
        tropes.add("Love Triangle");
        tropes.add("Second Chances");
        return tropes;
    }

    // Returning a list of top quotes using ArrayList
    public ArrayList<String> topQuotes() {
        ArrayList<String> quotes = new ArrayList<>();
        quotes.add("Love conquers all.");
        quotes.add("To love and be loved.");
        return quotes;
    }

    // Placeholder loveMeter function
    public int loveMeter(Book book) {
        return 85; // Placeholder value for love meter
    }

    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Romance")) {
            super.addBook(book);
        } else {
            System.out.println("This book does not belong to the Romance genre!");
        }
    }

    @Override
    public ArrayList<Book> recommendBooks() {
        ArrayList<Book> recommendations = new ArrayList<>();
        for (Book book : books) {
            if (loveMeter(book) > 80) {
                recommendations.add(book);
            }
        }
        return recommendations;
    }
}
