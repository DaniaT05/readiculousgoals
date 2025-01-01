package com.readiculousgoals.model;
import java.io.*;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;

public class FileUtilities {
            // Method to get the number of pages from a PDF file
    public static int getPdfTotalPages(String pdfFilePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            return document.getNumberOfPages();  // Returns the total number of pages
        }
    }
    // Writes a serializable object to a file
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> void writeObjectToFile(String filePath, T object) {
        ArrayList<T> existingObjects = readAllObjects(filePath, (Class<T>) object.getClass());
        existingObjects.add(object);
        
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(existingObjects);
        } catch (IOException e) {
            System.err.println("Error writing object to file: " + e.getMessage());
        }
    }

    public static <T extends Serializable> void writeAllObjects(String filePath, ArrayList<T> objects) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(objects);
            }
        } catch (IOException e) {
            System.err.println("Error writing objects: " + e.getMessage());
        }
    }

    // Reads a serializable object from a file
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T readObjectFromFile(String filePath, Class<T> clazz) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return clazz.cast(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading object from file: " + e.getMessage());
        }
        return null; // Return null if reading fails
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> ArrayList<T> readAllObjects(String filePath, Class<T> clazz) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                Object obj = ois.readObject();
                if (obj instanceof ArrayList<?>) {
                    return (ArrayList<T>) obj;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading objects: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> ArrayList<T> readObjectsFromFile(String filePath) {
        ArrayList<T> objects = new ArrayList<>();
        try {
            // Check if the file exists before attempting to read
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>(); // Return empty list if file doesn't exist
            }
    
            // Continue with the original logic to read multiple objects
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                while (true) {
                    try {
                        Object obj = ois.readObject();
                        if (obj instanceof ArrayList<?>) {
                            objects = (ArrayList<T>) obj; // Cast to ArrayList of T if the object is an ArrayList
                        }
                    } catch (EOFException e) {
                        break; // End of file reached
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Print detailed error message for debugging
            System.err.println("Error reading objects from file: " + e.getMessage());
        }
        return objects; // Return the list of objects (or empty if there was an error)
    }
    
    

    public static byte[] getFileContentAsBytes(String filePath) {
        try {
            File file = new File(filePath);
            byte[] content = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(content);
            }
            return content;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }

        // public static void testReadAllBooks() {
        //     ArrayList<ReaderBook> books = FileUtilities.readAllObjects("books.dat", ReaderBook.class);
        //     for (ReaderBook book : books) {
        //         System.out.println(book.getTitle()); // Ensure all titles are printed
        //     }
        // }
    }

}
