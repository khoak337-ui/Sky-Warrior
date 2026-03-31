package model;

public class Enemy {
    public float x, y;
    public int hp, maxHp;
    public boolean isBoss;

    public Enemy(float x, float y, boolean isBoss) {
        this.x = x;
        this.y = y;
        this.isBoss = isBoss;
        if (isBoss) {
            this.maxHp = 50;
            this.hp = 50;
        } else {
            this.maxHp = 1;
            this.hp = 1;
        }
    }

    public void update() {
        if (isBoss) y += 0.5f;
        else y += 3.0f;
    }
}