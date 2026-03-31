package view;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(GamePanel panel) {
        this.setTitle("Khong Kich 365");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel); // Nạp giao diện vào khung
        this.pack(); // Tự động co dãn theo kích thước panel
        this.setSize(800, 600); // Hoặc đặt kích thước cố định
        this.setLocationRelativeTo(null); // Hiển thị ra giữa màn hình
        this.setVisible(true); // Có thể để luôn ở đây cho chắc chắn
    }
}