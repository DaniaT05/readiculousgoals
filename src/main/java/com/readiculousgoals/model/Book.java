package com.readiculousgoals.model;
import java.io.*;
class Book implements Serializable {
    private String title;
    private String author;
    private String genre;
    private String ageRating;
    private String status;
    private int pageCount;
    private int pagesRead;
    private byte[] pdfContent; // To store PDF file as bytes
    private byte[] coverImage; // To store image file as bytes
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
    }
    
    @Override
    public String toString() {
        return "Book:\n" +
                "title='" + title + '\n' +
                ", author='" + author + '\n' +
                ", genre='" + genre + '\n' +
                ", ageRating='" + ageRating + '\n';
    }
    public byte[] getPdfContent() {
        return pdfContent;
    }
    public byte[] getCoverImage() {
        return coverImage;
    }
    public String getStatus() {
        return status;
    }
    public String getAgeRating() {
        return ageRating;
    }
    public String getGenre() {
        return genre;
    }
}