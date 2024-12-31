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
    public Book(String title, String author, String genre, String ageRating, int pageCount, byte[] pdfContent, byte[] coverImage) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ageRating = ageRating;
        this.pdfContent = pdfContent;
        this.coverImage = coverImage;
        this.pageCount = pageCount;

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
    public byte[] getPdfContent() {
        return pdfContent;
    }
    public byte[] getCoverImage() {
        return coverImage;
    }
    public String getAgeRating() {
        return ageRating;
    }
    public String getGenre() {
        return genre;
    }
    public int getPageCount() {
        return pageCount;
    }
}