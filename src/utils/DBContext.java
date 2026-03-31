package utils;

import java.sql.*;

public class DBContext {
    // Thay đổi user và pass theo cấu hình MySQL của bạn
    private static final String URL = "jdbc:mysql://localhost:3306/chicken_game";
    private static final String USER = "root";
    private static final String PASS = "123456"; // Mật khẩu MySQL của bạn

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy Driver MySQL!");
        }
    }

    // Lưu điểm khi chết
    public static void saveScore(int score) {
        String sql = "INSERT INTO highscore (player_name, score) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Player 1");
            ps.setInt(2, score);
            ps.executeUpdate();
            System.out.println("Đã lưu điểm: " + score);
        } catch (Exception e) {
            System.err.println("Lỗi lưu điểm: " + e.getMessage());
        }
    }

    // Lấy điểm cao nhất từ trước đến nay
    public static int getBestScore() {
        String sql = "SELECT MAX(score) AS high FROM highscore";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("high");
            }
        } catch (Exception e) {
            System.err.println("Lỗi lấy HighScore: " + e.getMessage());
        }
        return 0;
    }
}