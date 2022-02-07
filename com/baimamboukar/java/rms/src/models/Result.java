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

public class Result {
    /*
     * 🎯🎯🎯 RESULT CLASS 🎯🎯🎯
     * This class is a model class for Results. It contains all the attributes of a
     * Result.
     * And also the corresponding getters and setters
     * 
     * @author: BAIMAMBOUKAR
     * 
     * @version: 1.0
     * 
     * @since: First Release
     * 
     */

    private String publicationDate;
    private String courseId;
    private String publisherId;
    private String description;
    private String resultsFile;

    /**
     * 🚩🚩🚩 CONSTRUCTOR 🚩🚩🚩
     * This method is the constructor of the class result.
     * It is called when a new object of type result is created.
     * 
     * @author Baimam Boukar JJ
     * @since FIRST RELEASE
     * @param publicationDate
     * @param courseId
     * @param publisherId
     * @param description
     * @param resultsFile
     */
    public Result(String publicationDate, String courseId, String publisherId, String description,
            String resultsFile) {
        this.publicationDate = publicationDate;
        this.courseId = courseId;
        this.publisherId = publisherId;
        this.description = description;
        this.resultsFile = resultsFile;
    }

    /**
     * 🚩🚩🚩 GETTERS AND SETTERS 🚩🚩🚩
     * These are the getters and setters of the class result
     */

    public String getpublicationDate() {
        return publicationDate;
    }

    public void setpublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "Result [courseId=" + courseId + ", publicationDate=" + publicationDate + ", publisherId="
                + publisherId
                + "]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResultsFile() {
        return resultsFile;
    }

    public void setResultsFile(String resultsFile) {
        this.resultsFile = resultsFile;
    }

}
