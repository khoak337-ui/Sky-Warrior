package model;
import java.awt.Rectangle;

public abstract class GameObject {
    public double x, y;
    public int w, h;
    public GameObject(double x, double y, int w, int h) { this.x = x; this.y = y; this.w = w; this.h = h; }
    public Rectangle getBounds() { return new Rectangle((int)x, (int)y, w, h); }
}