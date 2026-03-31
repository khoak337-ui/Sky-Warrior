package model;

public class Bullet {
    public float x, y;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        this.y -= 10; // Đạn bay lên
    }
}