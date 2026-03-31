package model;
import java.util.*;

public class Explosion {
    public float x, y;
    public int life = 20;
    public List<Particle> particles = new ArrayList<>();

    public Explosion(float x, float y) {
        this.x = x; this.y = y;
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            particles.add(new Particle(x + 40, y + 40, r.nextFloat() * 6 - 3, r.nextFloat() * 6 - 3));
        }
    }

    public class Particle {
        public float x, y, vx, vy;
        public Particle(float x, float y, float vx, float vy) {
            this.x = x; this.y = y; this.vx = vx; this.vy = vy;
        }
        public void update() { x += vx; y += vy; }
    }
}