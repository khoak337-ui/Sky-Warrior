package view;

import model.*;
import utils.DBContext; // Đảm bảo bạn đã tạo file DBContext mình đưa ở trên
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class GamePanel extends JPanel {
    private Plane p;
    private List<Bullet> bl;
    private List<Enemy> en;
    private List<Explosion> ex;
    private List<Item> items;
    private boolean isGameOver;
    private int score;
    private int bestScore = 0; // Biến lưu điểm cao nhất từ SQL
    private Image imgBg, imgPlayer, imgEnemy, imgItem;
    private float bgY = 0;

    // Quản lý Clip âm thanh
    private Clip clipShoot, clipExplode, clipItem, clipBgm;

    public GamePanel() {
        setPreferredSize(new Dimension(1000, 750));
        setFocusable(true);
        new Timer(16, e -> {
            if (!isGameOver) bgY = (bgY + 2) % 750;
            repaint();
        }).start();
    }

    private Clip loadSound(String name) {
        try {
            File f = new File("src/resources/" + name);
            if (!f.exists()) return null;
            AudioInputStream ais = AudioSystem.getAudioInputStream(f);
            Clip c = AudioSystem.getClip();
            c.open(ais);
            return c;
        } catch (Exception e) { return null; }
    }

    public void init(int mt) {
        String path = "src/resources/";
        imgPlayer = new ImageIcon(path + "player.png").getImage();
        imgEnemy  = new ImageIcon(path + "enemy.png").getImage();
        imgItem   = new ImageIcon(path + "item.png").getImage();
        imgBg     = new ImageIcon(path + (mt==2?"desert.jpg":mt==3?"ocean.jpg":"jungle.jpg")).getImage();

        // Nạp tất cả âm thanh
        clipShoot = loadSound("shoot.wav");
        clipExplode = loadSound("explosion.wav");
        clipItem = loadSound("item.wav");
        clipBgm = loadSound("bgm.wav");

        if (clipBgm != null) {
            clipBgm.loop(Clip.LOOP_CONTINUOUSLY);
            clipBgm.start();
        }
    }

    public void playShoot() { play(clipShoot); }
    public void playExplode() { play(clipExplode); }
    public void playItem() { play(clipItem); }
    public void stopBgm() { if(clipBgm != null) clipBgm.stop(); }

    private void play(Clip c) {
        if (c != null) { c.setFramePosition(0); c.start(); }
    }

    public void render(Plane p, List<Bullet> bl, List<Enemy> en, List<Explosion> ex, List<Item> it, boolean go, int s) {
        // Kiểm tra khoảnh khắc vừa mới Game Over để lấy Best Score từ MySQL
        if (!this.isGameOver && go) {
            this.bestScore = DBContext.getBestScore();
        }
        this.p = p; this.bl = bl; this.en = en; this.ex = ex; this.items = it;
        this.isGameOver = go; this.score = s;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // --- VẼ NỀN VÀ ĐỐI TƯỢNG ---
        if (imgBg != null) {
            g2.drawImage(imgBg, 0, (int)bgY, 1000, 750, null);
            g2.drawImage(imgBg, 0, (int)bgY - 750, 1000, 750, null);
        }
        if (en != null) for (Enemy e : en) g2.drawImage(imgEnemy, (int)e.x, (int)e.y, 80, 80, null);
        if (items != null) for (Item it : items) g2.drawImage(imgItem, (int)it.x, (int)it.y, 40, 40, null);

        // Vẽ đạn
        if (bl != null) {
            g2.setColor(Color.YELLOW);
            for (Bullet b : bl) g2.fillOval((int)b.x, (int)b.y, 10, 20);
        }

        // Vẽ hiệu ứng nổ
        if (ex != null) {
            for (Explosion e : ex) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0, e.life/20f)));
                for (Explosion.Particle part : e.particles) {
                    g2.setColor(Color.ORANGE);
                    g2.fillOval((int)part.x, (int)part.y, 8, 8);
                    part.update();
                }
                e.life--;
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

        // Vẽ máy bay người chơi
        if (p != null) g2.drawImage(imgPlayer, (int)p.x, (int)p.y, 100, 100, null);

        // Vẽ Score góc màn hình khi đang chơi
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString("SCORE: " + score, 20, 40);

        // --- BẢNG THÔNG BÁO GAME OVER (TÍCH HỢP SQL) ---
        if (isGameOver) {
            // Lớp phủ mờ
            g2.setColor(new Color(0, 0, 0, 210));
            g2.fillRect(0, 0, 1000, 750);

            // Khung bảng điểm bo góc
            g2.setColor(new Color(30, 30, 30, 250));
            g2.fillRoundRect(250, 150, 500, 450, 50, 50);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(250, 150, 500, 450, 50, 50);

            // Chữ GAME OVER
            g2.setColor(Color.RED);
            g2.setFont(new Font("Verdana", Font.BOLD, 70));
            String overTxt = "GAME OVER";
            g2.drawString(overTxt, 500 - g2.getFontMetrics().stringWidth(overTxt)/2, 260);

            // Điểm số vừa đạt được
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Verdana", Font.BOLD, 40));
            String scoreTxt = "YOUR SCORE: " + score;
            g2.drawString(scoreTxt, 500 - g2.getFontMetrics().stringWidth(scoreTxt)/2, 340);

            // ĐIỂM CAO NHẤT (LẤY TỪ SQL)
            g2.setColor(Color.ORANGE);
            g2.setFont(new Font("Verdana", Font.BOLD, 40));
            int displayBest = Math.max(score, bestScore);
            String bestTxt = "BEST SCORE: " + displayBest;
            g2.drawString(bestTxt, 500 - g2.getFontMetrics().stringWidth(bestTxt)/2, 410);

            // Hướng dẫn phím
            g2.setColor(new Color(200, 200, 200));
            g2.setFont(new Font("Arial", Font.PLAIN, 24));
            g2.drawString("Press 'R' to Play Again", 380, 490);
            g2.drawString("Press 'M' to Main Menu", 380, 535);
        }
    }
}