package com.readiculousgoals.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String title;
    protected String author;
    protected String genre;
    protected String ageRating;
    protected int pageCount;
    protected byte[] pdfContent;
    protected byte[] coverImage;
    public static byte[] fileToByteArray(File file) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] byteArray = new byte[(int) file.length()];
            inputStream.read(byteArray);
            return byteArray;
        }
    }
    public Book(String title, String author, String genre, String ageRating, int totalPages, byte[] pdfContent, byte[] coverImage) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ageRating = ageRating;
        this.pdfContent = pdfContent;
        this.coverImage = coverImage;
        this.totalPages = totalPages;

    }
    
    @Override
    public String toString() {
        return "Book:\n" +
                "title='" + title + '\n' +
                ", author='" + author + '\n' +
                ", genre='" + genre + '\n' +
                ", ageRating='" + ageRating + '\n';
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return String.join(", ", genres); // Combines all genres into a single comma-separated string
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public byte[] getPdfContent() {
        return pdfContent;
    }

    // Getter for coverImage
    public byte[] getCoverImage() {
        return coverImage;
    }

    // Setters 
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public void setPdfContent(byte[] pdfContent) {
        this.pdfContent = pdfContent;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }
}