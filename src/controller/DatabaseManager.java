package controller;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/chicken_game?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";

    // THỬ THAY ĐỔI Ở ĐÂY: "" HOẶC "123456" HOẶC "root"
    private static final String PASS = "123456";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Thieu Driver MySQL Connector!");
        }
    }
    // ... các hàm saveScore và getBestScore giữ nguyên ...
}