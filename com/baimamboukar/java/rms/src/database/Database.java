package com.baimamboukar.java.rms.src.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baimamboukar.java.rms.src.models.Course;
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

    // public static boolean insert(String query) {

    // try {
    // Connection sqlite = connect();
    // Statement stmt = sqlite.createStatement();
    // boolean response = stmt.execute(query);
    // if (response == true) {
    // System.out.println("Success");
    // return true;
    // } else {
    // System.out.println("Error");
    // return false;
    // }
    // } catch (SQLException e) {

    // System.out.println(e.getMessage());
    // return false;
    // }

    // }

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

}
