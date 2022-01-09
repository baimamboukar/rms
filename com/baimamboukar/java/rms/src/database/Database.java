package com.baimamboukar.java.rms.src.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
            conn.close();

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
                avg = (rs.getInt("average"));
            }
            return avg;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return avg;
    }

    public static int getMode(String query, String countQuery) {
        int avg = 0;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                avg = (rs.getInt("mode"));
            }
            return avg;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return avg / getCount(countQuery);
    }

    public static int getTotalAVG() {
        int avg = ((getAVG("SELECT AVG(acount) FROM resultsdata as average") * 100)
                + (getAVG("SELECT AVG(bpluscount) FROM resultsdata as average") * 90)
                + (getAVG("SELECT AVG(bcount) FROM resultsdata as average") * 85)
                + (getAVG("SELECT AVG(cpluscount) FROM resultsdata as average") * 80)
                + (getAVG("SELECT AVG(ccount) FROM resultsdata as average") * 75)
                + (getAVG("SELECT AVG(dpluscount) FROM resultsdata as average") * 70)
                + (getAVG("SELECT AVG(dcount) FROM resultsdata as average") * 65)
                + (getAVG("SELECT AVG(fcount) FROM resultsdata as average") * 60)) / 100;
        return avg;
    }

}
