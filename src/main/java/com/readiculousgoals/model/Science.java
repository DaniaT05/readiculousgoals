package com.readiculousgoals.model;
import java.util.ArrayList;
import java.util.List;

public class Science extends NonFiction {

    public Science() {
        super("Science", "Books on scientific discoveries and principles.");
    }

    public ArrayList<String> scientificTopics(Book book) {
        // Fixed: Use ArrayList constructor to create a list of topics
        List<String> topics = new ArrayList<>();
        topics.add("Physics");
        topics.add("Astronomy");
        topics.add("Biology");
        return new ArrayList<>(topics);
    }

    public ArrayList<Book> suggestDiscoveries() {
        return books; // Placeholder logic
    }

    @Override
    public void addBook(Book book) {
        if (book.getGenre().equalsIgnoreCase("Science")) {
            super.addBook(book);
        } else {
            System.out.println("This book does not belong to the Science genre!");
        }
    }

    @Override
    public ArrayList<Book> recommendBooks() {
        return books; // Placeholder logic
    }
}
