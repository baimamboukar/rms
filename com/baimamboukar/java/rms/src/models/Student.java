package com.baimamboukar.java.rms.src.models;

import java.util.List;

public class Student {
    private String matricule;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String birthDate;
    private List<String> courses;

    public Student(String matricule, String name, String email, String password, String phone, String birthDate,
            List<String> courses) {
        this.matricule = matricule;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        this.courses = courses;
    }

    public Student(String string, String text, String text2, String text3, String text4, String text5, Course course,
            Course course2, Course course3) {
    }

    public String getPassword() {
        return this.password;
    }

    void setPassword(String new_password) {
        this.password = new_password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student [birthDate=" + birthDate + ", courses=" + courses + ", email=" + email + ", matricule="
                + matricule + ", name=" + name + ", password=" + password + ", phone=" + phone + "]";
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public static void main(String[] args) {
        System.out.println("Hello Student");
    }
}