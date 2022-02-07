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

public class Course {
    /*
     * 🎯🎯🎯 COURSE CLASS 🎯🎯🎯
     * This class is a model class for course. It has the daata attributes, getters
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
    private String courseID;
    private String teacherID;
    private String courseName;

    public Course(String courseID, String teacherID, String courseName) {
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course [courseID=" + courseID + ", courseName=" + courseName + ", teacherID=" + teacherID + "]";
    }

}
