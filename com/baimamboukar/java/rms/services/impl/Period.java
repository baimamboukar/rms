// package com.baimamboukar.java.rms.services.impl;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.util.ArrayList;

// public class Period {
// private Integer id;
// private Integer day;
// private Integer month;
// private Integer year;
// public Connection periodConnection;
// public ArrayList<Period> periods;

// public Period(Integer id, Integer day, Integer month, Integer year,
// Connection periodConnection,
// ArrayList<Period> periods) {
// this.id = id;
// this.day = day;
// this.month = month;
// this.year = year;
// this.periodConnection = periodConnection;
// this.periods = periods;
// }

// public Integer getId() {
// return id;
// }

// public void setId(Integer id) {
// this.id = id;
// }

// public Integer getDay() {
// return day;
// }

// public void setDay(Integer day) {
// this.day = day;
// }

// public Integer getMonth() {
// return month;
// }

// public void setMonth(Integer month) {
// this.month = month;
// }

// public Integer getYear() {
// return year;
// }

// public void setYear(Integer year) {
// this.year = year;
// }

// public Connection getPeriodConnection() {
// return periodConnection;
// }

// public void setPeriodConnection(Connection periodConnection) {
// this.periodConnection = periodConnection;
// }

// public ArrayList<Period> getPeriods() {
// return periods;
// }

// public void setPeriods(ArrayList<Period> periods) {
// this.periods = periods;
// }

// public Boolean connect() throws SQLException {
// periodConnection = DriverManager.getConnection("ewallet.db");
// if (periodConnection != null) {
// return true;
// }
// return false;
// }

// // fetch all Period in database
// public ArrayList<Period> fetchAllPeriod() throws SQLException {
// periods = new ArrayList<Period>();
// String query = "SELECT id, day, month, year, FROM period";
// if (connect() == true) {
// java.sql.Statement statement = periodConnection.createStatement();
// java.sql.ResultSet resultSet = statement.executeQuery(query);
// while (resultSet.next()) {
// Period period = new Period(resultSet.getInt("id"), resultSet.getInt("day"),
// resultSet.getInt("month"),
// resultSet.getInt("year"), periodConnection, periods);
// periods.add(period);
// }
// }
// return periods;
// }

// public static void main(String[] args) {
// Period period = Period();
// ArrayList<Period> periods = fetchAllPeriod();
// }

// }
