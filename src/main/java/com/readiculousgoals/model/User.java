package com.readiculousgoals.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
public abstract class User implements Serializable {
    protected String fullName;
    protected int age;
    protected String username;
    protected String email;
    protected String password;
    protected Date joinDate;
    public User() {
        this.fullName = "";
        this.age = 0;
        this.username = "";
        this.email = "";
        this.password = "";
        this.joinDate = new Date(); // Default to the current date and time
    }
    // Parameterized constructor
    public User(String fullName, int age, String username, String email, String password, Date joinDate) {
        this.fullName = fullName;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
    }
    // Getters and Setters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getJoinDate() {
        return new Date(joinDate.getTime());
    }
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public abstract void performUserSpecificTask();
}
