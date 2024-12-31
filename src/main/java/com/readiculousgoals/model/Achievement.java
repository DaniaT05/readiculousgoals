package com.readiculousgoals.model;
import java.util.Date;
public class Achievement {
    private String title;
    private String description;
    private Date dateEarned;
    public Achievement(String title, String description, Date dateEarned) {
        this.title = title;
        this.description = description;
        this.dateEarned = dateEarned;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Date getDateEarned() {
        return dateEarned;
    }
}
