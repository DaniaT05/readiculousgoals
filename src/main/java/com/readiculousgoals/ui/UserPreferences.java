package com.readiculousgoals.ui;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.readiculousgoals.model.Genre;
public class UserPreferences {
    private List<Genre> selectedGenres = new ArrayList<>();

    public void addGenre(Genre genre) {
        selectedGenres.add(genre);
    }

    public List<Genre> getSelectedGenres() {
        return selectedGenres;
    }

    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Genre genre : selectedGenres) {
                writer.write(genre.getName());
                writer.newLine();
            }
        }
    }
}
