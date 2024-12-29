package com.readiculousgoals.model;
import java.io.Serializable;
import java.util.ArrayList;
public abstract class Genre implements Serializable {
    protected String name;
    protected String description;
    protected ArrayList<Book> books;
    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
        this.books = new ArrayList<>();
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public ArrayList<Book> listBooks() {
        return books;
    }
    public abstract ArrayList<Book> recommendBooks();
    public String getName() {
        return name;
    }
}
