# School Manager - Dual Database Synchronization

Hệ thống quản lý sinh viên chuyên nghiệp được xây dựng bằng Spring Boot, có khả năng đồng bộ dữ liệu song song giữa Database Local (SQL Server) và Database Online (Gear.host).

## 🚀 Tính năng nổi bật
- **Đồng bộ hóa thời gian thực:** Mọi thao tác Thêm/Sửa/Xóa đều được thực hiện đồng thời trên cả Local và Online.
- **Hợp nhất dữ liệu:** Tự động gộp dữ liệu từ hai nguồn khi hiển thị.
- **Giao diện hiện đại:** UI/UX tối giản, hỗ trợ tìm kiếm linh hoạt theo Tên và ID.
- **Cơ chế ID thông minh:** Sử dụng định danh chuỗi (String/UUID) để đảm bảo tính duy nhất trên toàn hệ thống.

## 🛠 Công nghệ sử dụng
- **Backend:** Java 17, Spring Boot 4.0.1, Spring Data JPA.
- **Database:** Microsoft SQL Server (Local & Online).
- **Frontend:** HTML5, CSS3, JavaScript (Vanilla), Bootstrap 5, Font Awesome 6.

## 📋 Hướng dẫn cài đặt

### 1. Chuẩn bị Database
- Cài đặt **SQL Server** và **SQL Server Management Studio (SSMS)**.
- Mở file [school_management_db.sql](./Database/school_management_db.sql) trong SSMS.
- Nhấn `F5` hoặc nút `Execute` để tạo database và dữ liệu mẫu.

### 2. Cấu hình ứng dụng
Ứng dụng sử dụng 2 file cấu hình riêng biệt trong thư mục `src/main/resources`:
- `application-local.properties`: Chứa thông tin kết nối SQL Server tại máy bạn.
- `application-online.properties`: Chứa thông tin kết nối SQL Server trên Cloud.

*Lưu ý: Đảm bảo thông tin `username` và `password` trong các file này là chính xác.*

### 3. Chạy ứng dụng
Mở terminal tại thư mục gốc của dự án và chạy lệnh:
```bash
# Windows
./mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw spring-boot:run
```

Sau khi chạy thành công, truy cập: [http://localhost:8080](http://localhost:8080)

## 📂 Cấu trúc mã nguồn quan trọng
- `com.example.schoolmanager.config`: Cấu hình kết nối đa nguồn dữ liệu (Multi-DataSource).
- `com.example.schoolmanager.service.StudentSyncService`: Logic xử lý đồng bộ hóa chính.
- `com.example.schoolmanager.model.Student`: Định nghĩa thực thể sinh viên với ID kiểu String.
- `src/main/resources/templates/index.html`: Giao diện người dùng tích hợp API.

## 📝 Ghi chú
- Nếu bạn thay đổi cấu hình Database, hãy đảm bảo cột `id` luôn là kiểu `NVARCHAR` (hoặc `VARCHAR`) để tương thích với cơ chế đồng bộ hiện tại.
- Hệ thống ưu tiên ID làm khóa chính để gộp dữ liệu.
