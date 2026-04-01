package view;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(GamePanel panel) {
        this.setTitle("Khong Kich 365");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel); 
        this.pack(); 
        this.setSize(800, 600); 
        this.setLocationRelativeTo(null); 
        this.setVisible(true); 
    }
}
