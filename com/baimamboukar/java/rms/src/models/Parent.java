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

public class Parent {
    /*
     * 🎯🎯🎯 PDFBOX 🎯🎯🎯
     * This class is a model class for Parent. It has the daata attributes, getters
     * and setters
     * 
     * @author: BAIMAM BOUKAR JEAN JACQUES
     * 
     * @version: 1.0
     * 
     * @since: First Release
     * 
     * @apinote: PDFBox and iTEXTPDF libraries used
     * 
     */
    private String parentsID;
    private String name;
    private String phone;
    private String email;

    public Parent(String parentsID, String name, String phone, String email) {
        this.parentsID = parentsID;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    
    /** 
     * @return String
     */
    public String getParentsID() {
        return parentsID;
    }

    
    /** 
     * @param parentsID
     */
    public void setParentsID(String parentsID) {
        this.parentsID = parentsID;
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
    @Override
    public String toString() {
        return "Parent [email=" + email + ", name=" + name + ", parentsID=" + parentsID + ", phone=" + phone + "]";
    }

}
