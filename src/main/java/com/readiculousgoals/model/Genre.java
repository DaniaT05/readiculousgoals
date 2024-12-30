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
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    public abstract ArrayList<Book> recommendBooks();
    public String getName() {
        return name;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Genre genre = (Genre) obj;
        return name.equals(genre.name);
    }
    
}
