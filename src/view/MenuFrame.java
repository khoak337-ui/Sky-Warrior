package view;

import main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MenuFrame extends JFrame {
    private int selectedMap = 1;
    private ArrayList<Star> starField = new ArrayList<>();
    private Timer uiTimer;

    public MenuFrame() {
        setTitle("SKY WARRIORS: ETHEREAL WINGS");
        setSize(1000, 750); // Tăng không gian hiển thị
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Khởi tạo 100 hạt sao cho hiệu ứng Warp Drive
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            starField.add(new Star(r.nextInt(1000), r.nextInt(750), r.nextInt(3) + 1));
        }

        JPanel mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 1. Nền Gradient Đa Sắc (Deep Space)
                GradientPaint gp = new GradientPaint(0, 0, new Color(10, 15, 30), 0, 750, new Color(40, 20, 80));
                g2.setPaint(gp);
                g2.fillRect(0, 0, 1000, 750);

                // 2. Vẽ Sao bay động
                g2.setColor(new Color(255, 255, 255, 180));
                for (Star s : starField) {
                    g2.fillOval(s.x, s.y, s.size, s.size);
                    s.y += s.speed;
                    if (s.y > 750) { s.y = 0; s.x = r.nextInt(1000); }
                }

                // 3. Tiêu đề phong cách Cyber (Khung chữ sáng tạo)
                drawCyberTitle(g2);
            }
        };

        // 4. Thành phần giao diện (UI Components)
        setupUI(mainPanel);

        uiTimer = new Timer(16, e -> mainPanel.repaint());
        uiTimer.start();

        add(mainPanel);
    }

    private void drawCyberTitle(Graphics2D g2) {
        // Vẽ khung trang trí cho Tiêu đề
        g2.setColor(new Color(0, 255, 255, 100));
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(200, 50, 600, 100);
        g2.drawLine(180, 40, 220, 40); // Chi tiết góc Cyber
        g2.drawLine(780, 160, 820, 160);

        // Chữ Tiêu đề lấp lánh
        g2.setFont(new Font("Orbitron", Font.BOLD, 60));
        g2.setColor(new Color(0, 255, 255, 50));
        g2.drawString("SKY WARRIORS", 253, 123); // Shadow
        g2.setColor(Color.CYAN);
        g2.drawString("SKY WARRIORS", 250, 120);

        g2.setFont(new Font("Monospaced", Font.BOLD, 16));
        g2.setColor(Color.WHITE);
        g2.drawString("PROJECT ARTITION // SYSTEM READY", 360, 180);
    }

    private void setupUI(JPanel p) {
        // Label chọn Map
        JLabel lblMap = new JLabel("CHỌN VÙNG CHIẾN SỰ (SELECT SECTOR):");
        lblMap.setForeground(Color.CYAN);
        lblMap.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblMap.setBounds(350, 220, 400, 30);
        p.add(lblMap);

        // Combo Box Map cực ngầu
        String[] maps = {"Sector 01: Emerald Jungle", "Sector 02: Neon City", "Sector 03: Void Abyss"};
        JComboBox<String> mapCombo = new JComboBox<>(maps);
        mapCombo.setBounds(300, 250, 400, 40);
        mapCombo.setBackground(new Color(20, 20, 40));
        mapCombo.setForeground(Color.CYAN);
        mapCombo.addActionListener(e -> selectedMap = mapCombo.getSelectedIndex() + 1);
        p.add(mapCombo);

        // Nút chọn Phi cơ với thiết kế khung đa dạng
        createArtButton(p, "❖ TYPE-01: THE AVENGER (BLUE)", 340, Color.CYAN, 1);
        createArtButton(p, "❖ TYPE-02: THE GHOST (GREEN)", 430, Color.GREEN, 2);
        createArtButton(p, "❖ TYPE-03: THE TITAN (RED)", 520, Color.RED, 3);
    }

    private void createArtButton(JPanel p, String text, int y, Color c, int shipType) {
        JButton btn = new JButton(text);
        btn.setBounds(250, y, 500, 65);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Verdana", Font.BOLD, 18));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(c, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        // Hiệu ứng di chuột
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(c);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
            public void mouseExited(MouseEvent e) {
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createLineBorder(c, 2));
            }
        });

        // FIX LỖI: Truyền đủ 2 tham số (shipType, mapType) cho Main
        btn.addActionListener(e -> {
            uiTimer.stop();
            this.dispose();
            Main.startGame(shipType, selectedMap);
        });

        p.add(btn);
    }

    // Class phụ hỗ trợ hiệu ứng sao
    class Star {
        int x, y, size, speed;
        Star(int x, int y, int s) { this.x = x; this.y = y; this.size = s; this.speed = s * 2; }
    }
}
