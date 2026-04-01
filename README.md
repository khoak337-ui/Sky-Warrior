### 🎓 SKYFALLS WARRIORS : Airplane Shooting Game
**Bài tập lớn cuối kỳ môn Lập trình Java - Nhóm [Điền số nhóm]**

---

### 👥 Thông tin nhóm (Team Members)

| STT | Họ và Tên | Mã Sinh Viên | Vai trò / Nhiệm vụ | Link GitHub Cá Nhân |
|:---:|---|:---:|---|:---:|
| 1 | [Nguyễn Lê Vy] | [3120225178] | Trưởng nhóm - Logic va chạm, UI | [GitHub](https://github.com/nlv08022007-bot) |
| 2 | [Họ tên của Khoa] | [3120225027] | Controller, Render Graphics | [GitHub](link) |
| 3 | [Phạm Ngọc Uyên Nhi] | [3120225111] | Tester, Nội dung game | [GitHub](https://github.com/nhi249007-code) |

---

### 📝 Giới thiệu dự án (Description)

*SKY WARRIOR* là game bắn máy bay 2D được xây dựng bằng *Java*, sử dụng thư viện *Swing & Graphics2D* để vẽ và xử lý đồ họa.

Người chơi điều khiển máy bay để né đạn, tiêu diệt kẻ địch và thu thập vật phẩm. Game tập trung vào xử lý va chạm thời gian thực, hiệu ứng chuyển động mượt và áp dụng mô hình *MVC* để tổ chức chương trình.

---

### ✨ Các chức năng chính (Features)

- [x] *Đồ họa 2D mượt:* Sử dụng Graphics2D vẽ hiệu ứng và chuyển động 60 FPS  
- [x] *Xử lý va chạm:* Phát hiện va chạm giữa máy bay, đạn và kẻ địch  
- [x] *Cơ chế Dash:* Húc tiêu diệt địch khi di chuyển nhanh  
- [x] *Map & Item:* Nhiều môi trường và vật phẩm hỗ trợ (Shield)  
- [x] *Kiến trúc MVC:* Tách Model – View – Controller rõ ràng  

---

## 💻 Công nghệ & Thư viện sử dụng (Technologies)
* **Ngôn ngữ:** Java (JDK 17+)
* **Giao diện:** Java Swing, AWT
* **Cơ sở dữ liệu / Lưu trữ:** MySQL (JDBC) / File Binary (.dat)
* **Công cụ khác:** Git, GitHub, IntelliJ IDEA / Eclipse


---

## 📂 Cấu trúc thư mục (Project Structure)
Mã nguồn được tổ chức chặt chẽ theo mô hình **MVC (Model - View - Controller)**:

  📦 src
   ┣ 📂 model       # Chứa các lớp đối tượng thực thể (Student, Product, Room...)
   ┣ 📂 view        # Chứa các lớp giao diện đồ họa (JFrame, JPanel, Dialog...)
   ┣ 📂 controller  # Chứa logic nghiệp vụ, thuật toán xử lý và lắng nghe sự kiện
   ┣ 📂 utils       # Chứa các lớp tiện ích dùng chung (DBConnection, DataValidator...)
   ┗ 📜 Main.java   # File Entry-point để khởi động ứng dụng

---

### 🚀 Hướng dẫn cài đặt và chạy (Installation)

**1. Yêu cầu hệ thống (Prerequisites)**

* **Java Development Kit (JDK):** Phiên bản JDK 11 trở lên  
* **Phần mềm lập trình (IDE):** IntelliJ IDEA hoặc Eclipse  
* **Phần cứng:** Không yêu cầu đặc biệt  

---

**2. Tải mã nguồn về máy (Clone Repository)**

Mở Terminal hoặc Git Bash và gõ lệnh sau:

```bash
git clone [https://github.com/khoak337-ui/Sky-Warrior.git](https://github.com/khoak337-ui/Sky-Warrior.git)
```
