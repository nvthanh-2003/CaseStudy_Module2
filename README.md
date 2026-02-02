# CaseStudy — Phần mềm Quản lý Thư viện (Console, Java)

Ứng dụng quản lý thư viện chạy trên **console** bằng **Java**, hỗ trợ lưu trữ danh sách sách, thao tác thêm/hiển thị/tìm kiếm/sắp xếp và xuất dữ liệu ra file **CSV (Excel)**.

---

## Tính năng

- **Thêm sách**
- **Hiển thị danh sách sách** (dạng bảng)
- **Tìm kiếm sách theo ID**
- **Sắp xếp theo tên sách**
- **Sắp xếp theo tên tác giả**
- **Xuất danh sách ra file CSV** (mở được bằng Excel)

---

## Yêu cầu môi trường

- **Java SDK 25** (hoặc tương thích dự án)
- IDE khuyến nghị: IntelliJ IDEA (hoặc chạy bằng terminal)

---

## Cách chạy chương trình

### Cách 1: Chạy bằng IDE (IntelliJ IDEA)
1. Mở project `CaseStudy` trong IntelliJ.
2. Tìm lớp có `main()` (thường là `LibraryApp`).
3. Chạy bằng nút **Run**.

### Cách 2: Chạy bằng terminal (gợi ý)
Tùy cấu trúc project (không dùng Maven/Gradle), bạn có thể biên dịch và chạy thủ công:
1. Compile:
    - Biên dịch các file `.java` trong thư mục dự án (hoặc nơi chứa source).
2. Run:
    - Chạy class chứa `main()` (ví dụ: `LibraryApp`)

> Nếu bạn muốn mình viết đúng lệnh `javac/java` theo cấu trúc thư mục source thực tế, hãy gửi vị trí các file `.java` (ví dụ: có nằm trong `src/` hay ngay thư mục gốc).

---

## Luồng sử dụng (gợi ý)

1. Mở chương trình
2. Chọn chức năng từ menu:
    - Thêm sách
    - Hiển thị danh sách
    - Tìm theo ID
    - Sắp xếp theo tên sách / tác giả
    - Xuất CSV
3. Thoát chương trình (nếu có) và dữ liệu sẽ được lưu (tùy cơ chế lưu của dự án)

---

## Dữ liệu & Lưu trữ

Dự án có các file dữ liệu phổ biến sau:

- `library_data.dat`  
  Thường dùng để **lưu dữ liệu nhị phân (serialize)** nhằm giữ danh sách sách sau khi tắt chương trình.

- `danhsach.csv`  
  File **CSV** để xuất danh sách (Excel mở được). Định dạng ví dụ:

    - Cột: `ID, Tên Sách, Tác Giả, Nhà Xuất Bản, Năm Phát Hành, Xuất Xứ`

> Gợi ý: Khi mở bằng Excel nếu lỗi font/dấu tiếng Việt, hãy import CSV với encoding UTF-8 (Data → From Text/CSV).

---

## Cấu trúc dự án (tham khảo)

- `LibraryApp` — điểm vào chương trình (chứa `main`)
- `LibraryManager` — xử lý nghiệp vụ: thêm/hiển thị/tìm kiếm/sắp xếp/xuất file
- `Document.java` — mô hình tài liệu (base) và sách (book)
- `FileHelper` — hỗ trợ đọc/ghi file (CSV hoặc `.dat`)
- `SortByName` — comparator sắp xếp theo tên sách
- `SortByAuthor` — comparator sắp xếp theo tác giả
- `danhsach.csv` — dữ liệu/đầu ra CSV mẫu
- `library_data.dat` — dữ liệu lưu trữ dạng nhị phân

---

## Quy ước dữ liệu sách

Mỗi cuốn sách bao gồm các thông tin cơ bản:

- **ID** (mã định danh, nên là duy nhất)
- **Tên sách**
- **Tác giả**
- **Nhà xuất bản**
- **Năm phát hành**
- **Xuất xứ**

---

## Xuất CSV (Excel)

- Chức năng xuất sẽ tạo/ghi ra file `.csv` theo danh sách hiện tại.
- File CSV có thể:
    - gửi cho người khác,
    - mở bằng Excel/Google Sheets,
    - dùng để lưu trữ nhanh dạng bảng.

---

## Tác giả / Thông tin

Case Study: **Phần mềm quản lý thư viện** (Java console)

---