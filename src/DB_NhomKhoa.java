import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.sql.*;
// IMPORT THIẾU NÀY GÂY LỖI Ở ẢNH 5
import java.awt.RenderingHints;

public class DB_NhomKhoa extends JFrame {
    private final String URL = "jdbc:mysql://localhost:3306/th7db";
    private final String USER = "root";
    private final String PASS = "";

    private JTextField txtName, txtAge, txtMajor, txtId;
    private JTable tblStudent;
    private DefaultTableModel model;

    public DB_NhomKhoa() {
        setTitle("QUẢN LÝ SINH VIÊN - NGUYÊN PHAN ANH KHOA");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // --- VÙNG NHẬP LIỆU (NORTH) ---
        JPanel pnlNorth = new JPanel(new GridLayout(3, 2, 10, 15));
        pnlNorth.setBorder(BorderFactory.createTitledBorder("NHẬP THÔNG TIN SINH VIÊN"));
        txtId = new JTextField();
        pnlNorth.add(new JLabel("  Họ và tên:")); txtName = new JTextField(); pnlNorth.add(txtName);
        pnlNorth.add(new JLabel("  Tuổi:")); txtAge = new JTextField(); pnlNorth.add(txtAge);
        pnlNorth.add(new JLabel("  Chuyên ngành:")); txtMajor = new JTextField(); pnlNorth.add(txtMajor);
        add(pnlNorth, BorderLayout.NORTH);

        // --- VÙNG CHỨC NĂNG (CENTER) - ICON TO ĐẸP ĐẦY ĐỦ ---
        JPanel pnlCenter = new JPanel(new BorderLayout(10, 10));
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));

        // Tạo 4 nút bấm với Icon vẽ bằng code (Material Design)
        JButton btnAdd = createBigBtn("Thêm (Add)", "add", new Color(76, 175, 80));
        JButton btnUpdate = createBigBtn("Sửa (Update)", "edit", new Color(33, 150, 243));
        JButton btnDelete = createBigBtn("Xóa (Delete)", "delete", new Color(244, 67, 54));
        JButton btnClear = createBigBtn("Mới (Refresh)", "refresh", new Color(255, 152, 0));

        pnlButtons.add(btnAdd); pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete); pnlButtons.add(btnClear);
        pnlCenter.add(pnlButtons, BorderLayout.NORTH);

        // --- BẢNG DỮ LIỆU ---
        model = new DefaultTableModel(new String[]{"ID", "Họ và tên", "Tuổi", "Chuyên ngành"}, 0);
        tblStudent = new JTable(model);
        tblStudent.setRowHeight(30);
        pnlCenter.add(new JScrollPane(tblStudent), BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);

        // --- LOGIC ---
        loadData();
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> { clear(); loadData(); });

        tblStudent.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = tblStudent.getSelectedRow();
                if(r != -1) {
                    txtId.setText(model.getValueAt(r, 0).toString());
                    txtName.setText(model.getValueAt(r, 1).toString());
                    txtAge.setText(model.getValueAt(r, 2).toString());
                    txtMajor.setText(model.getValueAt(r, 3).toString());
                }
            }
        });
    }

    private JButton createBigBtn(String text, String type, Color bg) {
        JButton btn = new JButton(text, new MaterialIcon(type, 28, Color.WHITE));
        btn.setPreferredSize(new Dimension(180, 60));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setIconTextGap(12);
        return btn;
    }

    private Connection getConn() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private void loadData() {
        model.setRowCount(0);
        try (Connection c = getConn(); Statement s = c.createStatement();
             ResultSet r = s.executeQuery("SELECT * FROM students")) {
            while (r.next()) { // Đã fix lỗi r/rs ở Ảnh 3
                model.addRow(new Object[]{r.getInt("id"), r.getString("full_name"), r.getInt("age"), r.getString("major")});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void addStudent() {
        if(txtName.getText().isEmpty()) return;
        try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement("INSERT INTO students (full_name, age, major) VALUES (?, ?, ?)")) {
            ps.setString(1, txtName.getText());
            ps.setInt(2, Integer.parseInt(txtAge.getText()));
            ps.setString(3, txtMajor.getText());
            ps.executeUpdate();
            loadData(); clear();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void updateStudent() {
        if(txtId.getText().isEmpty()) return;
        try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement("UPDATE students SET full_name=?, age=?, major=? WHERE id=?")) {
            ps.setString(1, txtName.getText());
            ps.setInt(2, Integer.parseInt(txtAge.getText()));
            ps.setString(3, txtMajor.getText());
            ps.setInt(4, Integer.parseInt(txtId.getText()));
            ps.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Đã sửa!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void deleteStudent() {
        if(txtId.getText().isEmpty()) return;
        if(JOptionPane.showConfirmDialog(this, "Xóa sinh viên này?", "Xác nhận", 0) == 0) {
            try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement("DELETE FROM students WHERE id=?")) {
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                loadData(); clear();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void clear() { txtId.setText(""); txtName.setText(""); txtAge.setText(""); txtMajor.setText(""); }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new DB_NhomKhoa().setVisible(true)); }

    // --- LỚP VẼ ICON (Đã fix lỗi RenderingHint ở Ảnh 5) ---
    class MaterialIcon implements Icon {
        private String type; private int size; private Color color;
        public MaterialIcon(String type, int size, Color color) { this.type = type; this.size = size; this.color = color; }
        @Override public int getIconWidth() { return size; }
        @Override public int getIconHeight() { return size; }
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            // ĐÃ FIX: Dùng RenderingHints (có s) thay vì RenderingHint
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color); g2d.translate(x, y);
            double s = size / 24.0; g2d.scale(s, s);
            Path2D.Double p = new Path2D.Double();
            if (type.equals("add")) { p.moveTo(19, 13); p.lineTo(13, 13); p.lineTo(13, 19); p.lineTo(11, 19); p.lineTo(11, 13); p.lineTo(5, 13); p.lineTo(5, 11); p.lineTo(11, 11); p.lineTo(11, 5); p.lineTo(13, 5); p.lineTo(13, 11); p.lineTo(19, 11); p.closePath(); }
            else if (type.equals("edit")) { p.moveTo(3, 17.25); p.lineTo(3, 21); p.lineTo(6.75, 21); p.lineTo(17.81, 9.94); p.lineTo(14.06, 6.19); p.lineTo(3, 17.25); p.closePath(); p.moveTo(20.71, 7.04); p.lineTo(18.37, 3.29); p.lineTo(15.13, 5.12); p.lineTo(18.88, 8.87); p.closePath(); }
            else if (type.equals("delete")) { p.moveTo(16, 9); p.lineTo(16, 19); p.lineTo(8, 19); p.lineTo(8, 9); p.closePath(); p.moveTo(14.5, 3); p.lineTo(9.5, 3); p.lineTo(5, 4); p.lineTo(5, 6); p.lineTo(19, 6); p.lineTo(19, 4); p.closePath(); }
            else if (type.equals("refresh")) { p.moveTo(17.65, 6.35); p.lineTo(12, 4); p.lineTo(4.01, 12); p.lineTo(12, 20); p.lineTo(19.18, 15.36); p.lineTo(17.07, 14.3); p.closePath(); }
            g2d.fill(p); g2d.dispose();
        }
    }
}