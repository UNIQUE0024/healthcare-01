package com.healthcare.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
// 
⚠
 CHANGE THESE DATABASE SETTINGS
private static final String DB_URL = "jdbc:mysql://localhost:3306/healthcare_db";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "root";
private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
static {
try {
Class.forName(DB_DRIVER);
System.out.println("MySQL JDBC Driver loaded successfully");
} catch (ClassNotFoundException e) {
System.err.println("MySQL JDBC Driver not found!");
e.printStackTrace();
}
}
public static Connection getConnection() throws SQLException {
try {
Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
System.out.println("Database connection established successfully");
return connection;
} catch (SQLException e) {
System.err.println("Failed to establish database connection!");
e.printStackTrace();
throw e;
}
}
public static void closeConnection(Connection connection) {
if (connection != null) {
try {
connection.close();
System.out.println("Database connection closed successfully");
} catch (SQLException e) {
System.err.println("Error closing database connection!");
e.printStackTrace();
}
}
}
}
