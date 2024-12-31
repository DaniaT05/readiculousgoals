package com.readiculousgoals.model;
import java.util.*;

public class ReaderBook extends Book {

    private String status;
    private int pagesRead;
    private double rateOutOf5;

    public ReaderBook(int bookId, String title, String author, String genres, int totalPages, String ageRating, String status, int pagesRead, byte[] pdfContent, byte[] coverImage) {
        super(bookId, title, author, genres, totalPages, ageRating, pdfContent, coverImage);
        this.status = "To Read";
        this.pagesRead = 0;
        this.rateOutOf5 = 0.0;
    }

    public boolean isRead() {
        return "Completed".equalsIgnoreCase(status);
    }

    public void markAsRead() {
        this.status = "Completed";
    }

    public void setRateOutOf5(double rateOutOf5) {
        if (isRead()) {
            this.rateOutOf5 = rateOutOf5;
        } else {
            System.out.println("Cannot rate a book that hasn't been read.");
        }
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }
    public int getPagesRead() {
        return pagesRead;
    }
    public String getStatus() {
        return status;
    }

    public int getTotalReaderPages() {
        return getTotalPages();
    }

    public byte[] getPdfBookContent() {
        return getPdfContent();
    }

    // Getter for coverImage
    public byte[] getCoverImageContent() {
        return getCoverImage();
    }
}
