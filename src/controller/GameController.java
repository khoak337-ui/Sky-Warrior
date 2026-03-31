package controller;

import model.*;
import view.GamePanel;
import main.Main;
import utils.DBContext;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class GameController extends KeyAdapter {
    private Plane player;
    private GamePanel gamePanel;
    private List<Bullet> bullets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private int score = 0, spawnTimer = 0, bulletLevel = 1;
    private boolean isGameOver = false;
    private Timer gameLoop;

    public GameController(Plane player, GamePanel panel) {
        this.player = player;
        this.gamePanel = panel;
        gameLoop = new Timer(16, e -> update());
        gameLoop.start();
    }

    private void update() {
        if (isGameOver) return;

        // --- PHẦN THÊM VÀO: ĐỊCH ĐÔNG & NHANH HƠN ---
        // Cứ mỗi 15 frame (thay vì 45) sẽ ra địch -> Địch đông gấp 3 lần!
        int spawnThreshold = 15;
        // Tăng tốc độ rơi của địch lên 6 (cũ là 4)
        float enemySpeed = 6.0f;

        // 1. Xử lý Đạn bay & Va chạm Địch
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).y -= 20; // Đạn bay nhanh hơn một chút để dễ bắn quái đông
            Rectangle rb = new Rectangle((int)bullets.get(i).x, (int)bullets.get(i).y, 10, 20);
            for (int j = 0; j < enemies.size(); j++) {
                Rectangle re = new Rectangle((int)enemies.get(j).x, (int)enemies.get(j).y, 70, 70);
                if (rb.intersects(re)) {
                    explosions.add(new Explosion(enemies.get(j).x, enemies.get(j).y));
                    gamePanel.playExplode();
                    if (Math.random() < 0.15) items.add(new Item(enemies.get(j).x, enemies.get(j).y, 1));
                    bullets.remove(i); enemies.remove(j);
                    score += 10;
                    break;
                }
            }
        }

        // 2. Xử lý Item & Va chạm chết
        Rectangle rp = new Rectangle((int)player.x + 20, (int)player.y + 20, 60, 60);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).y += 3;
            if (rp.intersects(new Rectangle((int)items.get(i).x, (int)items.get(i).y, 40, 40))) {
                bulletLevel = 2;
                gamePanel.playItem();
                items.remove(i);
            }
        }

        // PHẦN THÊM VÀO: Xóa quái khi bay quá màn hình để tránh lag máy
        for (int i = 0; i < enemies.size(); i++) {
            Enemy en = enemies.get(i);
            en.y += enemySpeed;
            if (rp.intersects(new Rectangle((int)en.x + 15, (int)en.y + 15, 50, 50))) {
                gameOverAction();
            }
            if (en.y > 850) {
                enemies.remove(i);
            }
        }

        // 3. SINH ĐỊCH (Đã chỉnh lại để ra nhiều hơn)
        if (++spawnTimer >= spawnThreshold) {
            // Mỗi lần sinh, có 50% cơ hội ra 2 con cùng lúc
            int count = (Math.random() > 0.5) ? 2 : 1;
            for(int k = 0; k < count; k++) {
                enemies.add(new Enemy((float)(Math.random() * 850), -100, false));
            }
            spawnTimer = 0;
        }

        // Cập nhật nổ (Xóa nổ cũ)
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).life--;
            if (explosions.get(i).life <= 0) explosions.remove(i);
        }

        gamePanel.render(player, bullets, enemies, explosions, items, isGameOver, score);
    }

    private void gameOverAction() {
        isGameOver = true;
        gamePanel.stopBgm();
        final int finalScore = score;
        new Thread(() -> {
            DBContext.saveScore(finalScore);
        }).start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (isGameOver) {
            if (k == KeyEvent.VK_R) restartGame();
            if (k == KeyEvent.VK_M) { gameLoop.stop(); Main.backToMenu(); }
            return;
        }

        float s = 40f; // Tăng tốc máy bay lên một chút để né quái đông
        if (k == KeyEvent.VK_LEFT && player.x > 0) player.x -= s;
        if (k == KeyEvent.VK_RIGHT && player.x < 900) player.x += s;
        if (k == KeyEvent.VK_UP && player.y > 0) player.y -= s;
        if (k == KeyEvent.VK_DOWN && player.y < 650) player.y += s;

        if (k == KeyEvent.VK_SPACE) {
            gamePanel.playShoot();
            if (bulletLevel == 1) {
                bullets.add(new Bullet(player.x + 45, player.y));
            } else {
                bullets.add(new Bullet(player.x + 20, player.y));
                bullets.add(new Bullet(player.x + 70, player.y));
            }
        }
    }

    private void restartGame() {
        score = 0; bulletLevel = 1; isGameOver = false;
        bullets.clear(); enemies.clear(); items.clear(); explosions.clear();
        player.x = 425; player.y = 600;
        gamePanel.init(1);
    }
}