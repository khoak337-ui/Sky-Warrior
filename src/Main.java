package main;

import controller.GameController;
import model.Plane;
import view.GamePanel;
import view.MenuFrame;
import javax.swing.*;

public class Main {
    // Lưu lại cửa sổ Game để có thể đóng từ bất cứ đâu
    public static JFrame gameFrame;

    public static void main(String[] args) {
        // Chạy Menu khởi đầu
        SwingUtilities.invokeLater(() -> new MenuFrame().setVisible(true));
    }

    /**
     * Hàm bắt đầu game - Được gọi từ MenuFrame khi nhấn Start
     */
    public static void startGame(int shipType, int mapType) {
        gameFrame = new JFrame("SKY WARRIORS");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        // 1. Khởi tạo View
        GamePanel gv = new GamePanel();
        gv.init(mapType); // Nạp nhạc nền, âm thanh và map tại đây

        // 2. Khởi tạo Model
        Plane pl = new Plane(425.0f, 600.0f, shipType);

        // 3. Khởi tạo Controller
        GameController gc = new GameController(pl, gv);

        // Đăng ký bộ lắng nghe phím
        gameFrame.addKeyListener(gc);
        gv.addKeyListener(gc);

        gameFrame.add(gv);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        // Đảm bảo GamePanel nhận được các sự kiện bàn phím ngay lập tức
        gv.setFocusable(true);
        gv.requestFocusInWindow();
    }

    /**
     * Hàm quay lại Menu - Được gọi từ GameController khi nhấn 'M'
     */
    public static void backToMenu() {
        if (gameFrame != null) {
            gameFrame.dispose(); // Đóng và giải phóng bộ nhớ cửa sổ Game
        }
        // Mở lại cửa sổ Menu
        SwingUtilities.invokeLater(() -> new MenuFrame().setVisible(true));
    }
}