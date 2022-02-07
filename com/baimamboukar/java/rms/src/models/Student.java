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

    
    /** 
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    
    /** 
     * @param new_password
     */
    void setPassword(String new_password) {
        this.password = new_password;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /** 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /** 
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    
    /** 
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    /** 
     * @return List<String>
     */
    public List<String> getCourses() {
        return courses;
    }

    
    /** 
     * @param courses
     */
    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Student [birthDate=" + birthDate + ", courses=" + courses + ", email=" + email + ", matricule="
                + matricule + ", name=" + name + ", password=" + password + ", phone=" + phone + "]";
    }

    
    /** 
     * @return String
     */
    public String getMatricule() {
        return matricule;
    }

    
    /** 
     * @param matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    
    /** 
     * @return String
     */
    public String getBirthDate() {
        return birthDate;
    }

    
    /** 
     * @param birthDate
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello Student");
    }
}