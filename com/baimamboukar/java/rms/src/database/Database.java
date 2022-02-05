/**
 📌📌📌 THE ICT UNIVERSITY, FALL 20201 📌📌📌
 
 🚩 ♨♨♨♨♨ JAVA PROGRAMMING I ♨♨♨♨♨ 🚩

 🚩 INSTRUCTOR: ENGR. Daniel Moune

 🎯 NAMES: BAIMAM BOUKAR JEAN JACQUES
 🎯 EMAIL: baimam.jeanjacque@ictuniversity.edu.cm
 🎯 WHATSAPP: (+237) 690535759
 🎯 TWITTER: @baimamjj
 🎯 LINKEDIN: linkedin.com/in/baimamboukar
 🎯 WEBSITE: www.baimamboukar.me
 */

package com.baimamboukar.java.rms.src.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.baimamboukar.java.rms.src.models.Course;
import com.baimamboukar.java.rms.src.models.Result;
import com.baimamboukar.java.rms.src.models.ResultData;
import com.baimamboukar.java.rms.src.models.Student;
import com.baimamboukar.java.rms.src.models.Teacher;

public class Database {

    protected static Connection connexion = null;

    public static Connection connect() {
        if (connexion == null) {
            try {
                // db parameters
                String url = "jdbc:sqlite:rms.sqlite";
                // create a connection to the database
                connexion = DriverManager.getConnection(url);

                return connexion;

            } catch (SQLException e) {
                System.out.println("ERROR" + e.getMessage());
            }
            return connexion;
        } else {
            return connexion;
        }
    }

    public static boolean insertResult(String query) {

        try {
            Connection sqlite = connect();
            Statement stmt = sqlite.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {

            System.out.println("ERROR: " + e.getMessage());
            return false;
        }

    }

    public static List<Student> getStudents(String query) {
        List<Student> list = new ArrayList<Student>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // loop through the result set
            while (rs.next()) {
                Student student = new Student(rs.getString("matricule"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getString("phone"), rs.getString("birthday"),
                        Arrays.asList("Java", "Pyhton"));
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("ERROR" + e.getMessage());

        }
        return list;
    }

    public static List<Course> getCourses(String query) {
        List<Course> list = new ArrayList<Course>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // loop through the result set
            while (rs.next()) {
                Course course = new Course(rs.getString("courseID"), rs.getString("teacherID"),
                        rs.getString("courseName"));
                list.add(course);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());

        }
        return list;
    }

    public static List<Teacher> getTeachers(String query) {
        List<Teacher> list = new ArrayList<Teacher>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // loop through the result set
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getInt("id"), rs.getString("names"),
                        rs.getInt("courseID"), rs.getString("email"));
                list.add(teacher);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());

        }
        return list;
    }

    public static int getCount(String query) {
        int count = 0;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = (rs.getInt("count"));
            }
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public static void resultInsert(String date, String course, String publisher, String desc, String file) {
        String sql = "INSERT INTO results(publicationDate, course, publisher, desc, file) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, course);
            pstmt.setString(3, publisher);
            pstmt.setString(4, desc);
            pstmt.setString(5, file);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Result> getResults(String query) {
        List<Result> list = new ArrayList<Result>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // loop through the result set
            while (rs.next()) {
                Result result = new Result(rs.getString("publicationDate"), rs.getString("course"),
                        rs.getString("publisher"), rs.getString("desc"), rs.getString("file"));
                list.add(result);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());

        }
        return list;
    }

    public static List<ResultData> getResultsData(String query) {
        List<ResultData> list = new ArrayList<ResultData>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // loop through the result set
            while (rs.next()) {
                ResultData resultData = new ResultData(rs.getString("acount"), rs.getString("bcount"),
                        rs.getString("ccount"), rs.getString("dcount"), rs.getString("fcount"),
                        rs.getString("bpluscount"), rs.getString("cpluscount"), rs.getString("dpluscount"));
                list.add(resultData);
            }

            int avg = ((getAVG("SELECT AVG(acount) FROM resultsdata") * 100)
                    + (getAVG("SELECT AVG(bpluscount) FROM resultsdata") * 90)
                    + (getAVG("SELECT AVG(bcount) FROM resultsdata") * 85)
                    + (getAVG("SELECT AVG(cpluscount) FROM resultsdata") * 80)
                    + (getAVG("SELECT AVG(ccount) FROM resultsdata") * 75)
                    + (getAVG("SELECT AVG(dpluscount) FROM resultsdata") * 70)
                    + (getAVG("SELECT AVG(dcount) FROM resultsdata") * 65)
                    + (getAVG("SELECT AVG(fcount) FROM resultsdata") * 60)) / 100;

            System.out.println(avg);
            return list;
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());

        }
        return list;
    }

    public static void deleteResult(String date) {
        String sql = "DELETE FROM results WHERE publicationDate = ?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, date);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** STATISTICAL ANALYSIS FROM THE DATABASE */
    public static int getAVG(String query) {
        int avg = 0;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                avg = rs.getInt("average");
            }
            return avg;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return avg;
    }

    public static int getSUM(String query) {
        int total = 0;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                total = rs.getInt("total");
            }
            return total;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    public static String getMode() {
        int amode = getSUM("SELECT SUM(acount) as total FROM resultsdata");
        int bmode = getSUM("SELECT SUM(bcount) as total FROM resultsdata");
        int cmode = getSUM("SELECT SUM(ccount) as total FROM resultsdata");
        int dmode = getSUM("SELECT SUM(dcount) as total FROM resultsdata");
        int fmode = getSUM("SELECT SUM(fcount) as total FROM resultsdata");
        int bplusmode = getSUM("SELECT SUM(bpluscount) as total FROM resultsdata");
        int cplusmode = getSUM("SELECT SUM(cpluscount) as total FROM resultsdata");
        int dplusmode = getSUM("SELECT SUM(dpluscount) as total FROM resultsdata");
        String modeClass = "x";
        List<Integer> modes = Arrays.asList(amode, bmode, cmode, dmode, fmode, bplusmode, cplusmode, dplusmode);
        int mode = Collections.max(modes);
        int modeIndex = modes.indexOf(mode);

        if (modeIndex == 0) {
            modeClass = "A";
        }
        if (modeIndex == 1) {
            modeClass = "B";
        }
        if (modeIndex == 2) {
            modeClass = "C";
        }
        if (modeIndex == 3) {
            modeClass = "D";
        }
        if (modeIndex == 4) {
            modeClass = "F";
        }
        if (modeIndex == 5) {
            modeClass = "B+";
        }
        if (modeIndex == 6) {
            modeClass = "C+";
        }
        if (modeIndex == 7) {
            modeClass = "D+";
        }
        return modeClass;
    }

    public static String getTotalAVG() {
        int avg = ((getAVG("SELECT avg(acount) as average FROM resultsdata") * 100)
                + (getAVG("SELECT avg(bpluscount) as average FROM resultsdata") * 90)
                + (getAVG("SELECT avg(bcount) as average FROM resultsdata") * 85)
                + (getAVG("SELECT avg(cpluscount) as average FROM resultsdata") * 80)
                + (getAVG("SELECT avg(ccount) as average FROM resultsdata") * 75)
                + (getAVG("SELECT avg(dpluscount) as average FROM resultsdata") * 70)
                + (getAVG("SELECT avg(dcount) as average FROM resultsdata") * 65)
                + (getAVG("SELECT avg(fcount) as average FROM resultsdata") * 60)) / 100;
        return getGradebasedOnScore(avg);
    }

    public static String getStandardDev() {
        // int total = getCount("SELECT COUNT(*) FROM resultsdata");
        double avg_avg = ((getAVG("SELECT avg(acount * acount) as average FROM resultsdata") * 100)
                + (getAVG("SELECT avg(bpluscount * bpluscount) as average FROM resultsdata") * 90)
                + (getAVG("SELECT avg(bcount * bcount) as average FROM resultsdata") * 85)
                + (getAVG("SELECT avg(cpluscount * cpluscount) as average FROM resultsdata") * 80)
                + (getAVG("SELECT avg(ccount * ccount) as average FROM resultsdata") * 75)
                + (getAVG("SELECT avg(dpluscount * dpluscount) as average FROM resultsdata") * 70)
                + (getAVG("SELECT avg(dcount * dcount) as average FROM resultsdata") * 65)
                + (getAVG("SELECT avg(fcount * fcount) as average FROM resultsdata") * 60)) / 100;

        double avg = (Math.pow((getAVG("SELECT avg(acount) as average FROM resultsdata") * 100), 2)
                + Math.pow((getAVG("SELECT avg(bpluscount) as average FROM resultsdata") * 90), 2)
                + Math.pow((getAVG("SELECT avg(bcount) as average FROM resultsdata") * 85), 2)
                + Math.pow((getAVG("SELECT avg(cpluscount) as average FROM resultsdata") * 80), 2)
                + Math.pow((getAVG("SELECT avg(ccount) as average FROM resultsdata") * 75), 2)
                + Math.pow((getAVG("SELECT avg(dpluscount) as average FROM resultsdata") * 70), 2)
                + Math.pow((getAVG("SELECT avg(dcount) as average FROM resultsdata") * 65), 2)
                + Math.pow((getAVG("SELECT avg(fcount) as average FROM resultsdata") * 60), 2)) / 100;
        double variance = (avg - avg_avg);
        DecimalFormat df = new DecimalFormat("#.##");
        return String.valueOf(df.format(Math.sqrt(variance)));
    }

    private static String getGradebasedOnScore(int marks) {
        // Handling the case when a user enter a negative value of marks
        if (marks < 0) {
            System.out.println("Grade less than 1");
            return "F";
        }

        // Handling the case when a user marks greater than 100
        if (marks > 100) {
            System.out.println("Grade less more 100");
            return "F";
        }

        // Identifying the range of marks and the correspondant grade
        if (marks >= 0 && marks < 60) {
            return "F";
        } else if (marks >= 60 && marks < 65) {
            return "D";
        } else if (marks >= 65 && marks < 70) {
            return "D+";
        } else if (marks >= 70 && marks < 75) {
            return "C";
        } else if (marks >= 75 && marks < 80) {
            return "C+";
        } else if (marks >= 80 && marks < 85) {
            return "B";
        } else if (marks >= 85 && marks < 90) {
            return "B+";
        } else {
            return "A";
        }
    }

}
