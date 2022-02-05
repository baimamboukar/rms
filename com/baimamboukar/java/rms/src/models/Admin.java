/*
 📌📌📌 THE ICT UNIVERSITY, FALL 20201 📌📌📌

 🚩 ♨♨♨♨♨ JAVA PROGRAMMING I ♨♨♨♨♨ 🚩

 🚩 INSTRUCTOR: ENGR. Daniel Moune

 📌﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋📌

 🎯 NAMES: BAIMAM BOUKAR JEAN JACQUES
 🎯 EMAIL: baimam.jeanjacque@ictuniversity.edu.cm
 🎯 WHATSAPP: (+237) 690535759
 🎯 TWITTER: @baimamjj
 🎯 LINKEDIN: linkedin.com/in/baimamboukar
 🎯 WEBSITE: www.baimamboukar.me
 */

package com.baimamboukar.java.rms.src.models;

public class Admin {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public Admin(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}