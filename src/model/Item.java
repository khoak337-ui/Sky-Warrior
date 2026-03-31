package model;
import java.awt.Color;
public class Item extends GameObject {
    private int type; // 1: Mạng, 2: Súng, 3: Khiên
    public Item(double x, double y, int type) {
        super(x, y, 25, 25);
        this.type = type;
    }
    public void update() { this.y += 4; }
    public int getType() { return type; }
    public Color getColor() {
        if(type == 1) return Color.RED;    // Đỏ là mạng
        if(type == 2) return Color.YELLOW; // Vàng là súng
        return Color.CYAN;                 // Xanh là khiên
    }
}