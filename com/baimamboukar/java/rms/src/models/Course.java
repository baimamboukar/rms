package com.baimamboukar.java.rms.src.models;

public class Course {
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
