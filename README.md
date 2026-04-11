# ✈️ Sky Warrior - Space Shooter Game

**Bài tập lớn cuối kỳ môn Lập trình Java - Nhóm 1**


## 👥 Thông tin nhóm (Team Members)

| STT | Họ và Tên | Mã Sinh Viên | Vai trò / Nhiệm vụ | Link GitHub |
|:---:|:---|:---:|:---|:---:|
| 1 | Nguyễn Lê Vy       |[3120225072] | Trưởng nhóm, , Code Logic, Code Controller,Code Model | [GitHub](https://github.com/nlv08022007-bot) |
| 2 | Nguyễn Phan Anh Khoa| [3120225178] | Setup Git,Database,Hiệu ứng hình ảnh, Âm thanh,Code ulits | [GitHub](https://github.com/khoak337-ui) |
| 3 | Phạm Ngọc Uyên Nhi| [3120225111] | Developer (View)**: Thiết kế GUI, Vẽ Graphics2D, Hiển thị HUD, Menu & Animation | [GitHub](https://github.com/nhi249007-code) |

---

## 📝 Giới thiệu dự án (Description)

**Sky Warrior** là một trò chơi thuộc thể loại 2D Space Shooter độc đáo được phát triển trên nền tảng Java. Trong game, người chơi điều khiển một phi thuyền chiến đấu để chống lại các thế lực ngoài hành tinh. 
Dự án tập trung vào việc xử lý đồ họa mượt mà bằng **Graphics2D**, quản lý thực thể game thông qua các mảng đối tượng và kết nối cơ sở dữ liệu bền vững để lưu trữ kỷ lục người chơi.

---

## ✨ Các chức năng chính (Features)

- [x] **Cơ chế chiến đấu:** Hệ thống bắn đạn, nâng cấp sức mạnh đạn khi ăn vật phẩm.
- [x] **Kẻ địch đa dạng:** Máy bay địch tự động sinh ra với nhiều kiểu di chuyển khác nhau.
  
- [x] **Lưu trữ kỷ lục (JDBC):** Tự động lưu điểm cao nhất vào MySQL, không bị mất dữ liệu khi tắt game.
- [x] **Kiến trúc chuẩn MVC:** Tách biệt hoàn toàn phần Logic vật lý (Model) khỏi Giao diện (View).
- [x] **Xử lý Đa luồng (Multithreading):** Game Loop chạy ổn định ở 60 FPS, không gây lag máy.

---

## 💻 Công nghệ & Thư viện sử dụng (Technologies)

* **Ngôn ngữ:** Java (JDK 17+)
* **Giao diện:** Java Swing, AWT (`Graphics2D` để vẽ hiệu ứng cháy nổ).
* **Cơ sở dữ liệu:** MySQL (Kết nối qua JDBC).
* **Thư viện:** `mysql-connector-j-8.0.33.jar`.
* **Quản lý mã nguồn:** Git & GitHub.

---

## 📂 Cấu trúc thư mục (Project Structure)

Mã nguồn được tổ chức chặt chẽ theo mô hình **MVC**:

```text
📦 Sky-Warrior
 ┣ 📂 src
 ┃ ┣ 📂 model       # Các thực thể: Máy bay, Đạn, Kẻ địch, Boss...
 ┃ ┣ 📂 view        # Giao diện: Menu, GamePanel, Hiển thị điểm số...
 ┃ ┣ 📂 controller  # Xử lý: Bàn phím, Va chạm, Kết nối Database...
 ┃ ┗ 📜 Main.java            # File khởi chạy ứng dụng
 ┣ 📂 mysql-connector-j-8.0.33.jar                   # Thư viện .jar kết nối MySQL
 ┗ 📜 database.sql           # Script tạo bảng dữ liệu 'chicken_game'
## Ảnh Demo:
 <img width="990" height="775" alt="Ảnh chụp màn hình 2026-03-31 051757" src="https://github.com/user-attachments/assets/00893d1e-2b41-491e-a432-8b63f847c6d7" />
<img width="979" height="734" alt="Ảnh chụp màn hình 2026-03-31 051714" src="https://github.com/user-attachments/assets/974cf5a1-9fac-4a2d-9519-c1d1dae813ee" />
<img width="972" height="753" alt="Ảnh chụp màn hình 2026-03-31 024152" src="https://github.com/user-attachments/assets/bdca9ba7-8128-4446-955c-256352a17d34" />


##🚀 Hướng dẫn cài đặt và chạy (Installation)
1. Yêu cầu hệ thống (Prerequisites)
.Java Development Kit (JDK): Phiên bản JDK 17 trở lên.

.Phần mềm lập trình (IDE): Khuyên dùng IntelliJ IDEA, Eclipse hoặc Visual Studio Code.

.Cơ sở dữ liệu: MySQL Server (đã được cài đặt để nạp dữ liệu).

2. Tải mã nguồn về máy (Clone Repository)
Mở Terminal hoặc Git Bash tại thư mục muốn lưu dự án và gõ lệnh sau:

Bash
git clone https://github.com/khoak337-ui/Sky-Warrior.git
3. Mở dự án và Khởi chạy (Setup & Run)
*Đối với IntelliJ IDEA:

1) Mở IntelliJ, chọn File > Open và chọn thư mục Sky-Warrior.

2) Truy cập File > Project Structure > Libraries, nhấn dấu + và chọn file lib/mysql-connector-j-8.0.33.jar.

3) Chạy các lệnh SQL trong file database.sql để tạo cấu trúc bảng.

4) Mở file src/Main.java, chuột phải chọn Run 'Main'.

Đối với Eclipse:

1) Mở Eclipse, chọn File > Import...

2) Chọn General > Projects from Folder or Archive rồi nhấn Next.

3) Bấm Directory... trỏ tới thư mục dự án và nhấn Finish.

4) Chuột phải vào dự án chọn Build Path > Configure Build Path để add file .jar trong thư mục lib.

5) Chạy file Main.java để bắt đầu game.
