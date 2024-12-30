package com.readiculousgoals.model;
import java.util.ArrayList;
public class Drama extends Fiction {
    public Drama() {
        super("Drama", "Emotional and impactful storytelling.");
    }

    public ArrayList<Book> recommendHeartWarmingStories() {
        return books; // Placeholder logic
    }

    public ArrayList<Book> recommendTragicStories() {
        return books; // Placeholder logic
    }

    @Override
    public ArrayList<Book> recommendBooks() {
        return books; // Placeholder logic
    }
}