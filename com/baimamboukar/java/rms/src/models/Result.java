package com.baimamboukar.java.rms.src.models;

/* 
@uthor: BAIMAM BOUKAR JJ 
email: baimamboukar@gmail.com
📍 github.com/baimamboukar 
📍 linkedin.com/in/baimamboukar
📍 g.dev/baimamboukar

*/

public class Result {
    private String publicationDate;
    private String courseId;
    private String publisherId;
    private String description;
    private String resultsFile;

    public Result(String publicationDate, String courseId, String publisherId, String description,
            String resultsFile) {
        this.publicationDate = publicationDate;
        this.courseId = courseId;
        this.publisherId = publisherId;
        this.description = description;
        this.resultsFile = resultsFile;
    }

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
