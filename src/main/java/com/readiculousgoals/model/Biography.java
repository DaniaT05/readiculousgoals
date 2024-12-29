package com.readiculousgoals.model;
import java.util.ArrayList;
public class Biography extends NonFiction {
    public Biography() {
        super("Biography", "Books about influential figures.");
    }

    public String influentialFigures(Book book) {
        return "Albert Einstein"; // Placeholder value
    }

    public ArrayList<Book> recommendByInfluentialFigures() {
        return books; // Placeholder logic
    }

    @Override
    public ArrayList<Book> recommendBooks() {
        return books; // Placeholder logic
    }
}
