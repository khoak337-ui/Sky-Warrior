package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HighScoreFrame extends JFrame {
    public HighScoreFrame() {
        setTitle("BẢNG VÀNG PHI CÔNG");
        setSize(500, 400);
        setLayout(new BorderLayout());

        // 1. Tiêu đề
        JLabel lbl = new JLabel("BẢNG XẾP HẠNG", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl, BorderLayout.NORTH);

        // 2. Tạo JTable (Yêu cầu bắt buộc của Tiêu chí 3)
        String[] columnNames = {"STT", "Tên Phi Công", "Điểm Số"};
        Object[][] data = {
                {"1", "Vy Cute", "1500"},
                {"2", "Khoa Pro", "1200"},
                {"3", "Nhi Xinh", "1100"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // 3. Đưa Table vào JScrollPane (Yêu cầu bắt buộc)
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 4. Nút quay lại (FlowLayout)
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnBack = new JButton("Quay lại Menu");
        pnlBottom.add(btnBack);
        add(pnlBottom, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}