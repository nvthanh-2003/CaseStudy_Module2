import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        manager.loadData();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n\t╔════════════════════════════════════════════════════════╗");
            System.out.println("\t║            MANAGEMENT SYSTEM - VERSION 2.0             ║");
            System.out.println("\t╠════════════════════════════════════════════════════════╣");
            System.out.println("\t║  [1] THÊM SÁCH MỚI           [5] CHỈNH SỬA SÁCH        ║");
            System.out.println("\t║  [2] DANH SÁCH THƯ VIỆN      [6] SẮP XẾP THEO TÊN      ║");
            System.out.println("\t║  [3] TÌM KIẾM THEO ID        [7] SẮP XẾP TÁC GIẢ       ║");
            System.out.println("\t║  [4] XÓA SÁCH KHỎI KHO       [8] XUẤT FILE EXCEL       ║");
            System.out.println("\t╠════════════════════════════════════════════════════════╣");
            System.out.println("\t║              [0] THOÁT HỆ THỐNG                        ║");
            System.out.println("\t╚════════════════════════════════════════════════════════╝");
            System.out.print("\t   ➤ Nhập lệnh điều khiển (0-8): ");
            try {
                String input = sc.nextLine();
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    System.out.println("\t🔌 Đang đóng kết nối dữ liệu... Tạm biệt!");
                    break;
                }
                switch (choice) {
                    case 1:
                        System.out.println("\n--- THÊM SÁCH MỚI ---");
                        System.out.print("Nhập ID: "); String id = sc.nextLine();
                        System.out.print("Nhập tên sách: "); String title = sc.nextLine();
                        System.out.print("Nhập tên tác giả: "); String author = sc.nextLine();
                        System.out.print("Nhập nhà xuất bản: "); String pub = sc.nextLine();
                        int year;
                        while (true) {
                            try {
                                System.out.print("Nhập năm phát hành: ");
                                year = Integer.parseInt(sc.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Lỗi: Năm phát hành phải là số nguyên!");
                            }
                        }
                        System.out.print("Nhập xuất xứ: "); String origin = sc.nextLine();
                        manager.addDocument(new Book(id, title, author, pub, year, origin));
                        System.out.println("✅ Thêm sách thành công!");
                        manager.saveData();
                        break;
                    case 2:
                        printTableHeader();
                        manager.showAll();
                        break;
                    case 3:
                        System.out.print("🔍 Nhập ID cần tìm: ");
                        String searchId = sc.nextLine();
                        Document found = manager.findById(searchId);
                        if (found != null) {
                            printTableHeader();
                            found.displayInfo();
                        } else {
                            System.out.println("❌ Không tìm thấy sách có ID: " + searchId);
                        }
                        break;
                    case 4:
                        System.out.print("🗑️ Nhập ID sách cần xóa: ");
                        String deleteId = sc.nextLine();
                        manager.deleteById(deleteId);
                        manager.saveData();
                        break;
                    case 5:
                        System.out.print("\t📝 Nhập ID sách cần sửa: ");
                        String editId = sc.nextLine();
                        Document docToEdit = manager.findById(editId);
                        if (docToEdit != null) {
                            System.out.println("\t[ THÔNG TIN HIỆN TẠI ]");
                            printTableHeader();
                            docToEdit.displayInfo();
                            System.out.println("\n\t[ NHẬP THÔNG TIN CẬP NHẬT ]");
                            System.out.print("\t├── Tên mới: "); String nextTitle = sc.nextLine();
                            System.out.print("\t├── Tác giả mới: "); String nextAuthor = sc.nextLine();
                            System.out.print("\t├── Nhà xuất bản mới: "); String nextPub = sc.nextLine();
                            int nextYear;
                            while (true) {
                                try {
                                    System.out.print("\t├── Năm phát hành mới: ");
                                    nextYear = Integer.parseInt(sc.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("\t│   ⚠️ Lỗi: Năm phải là số nguyên!");
                                }
                            }
                            System.out.print("\t└── Xuất xứ mới: "); String nextOrigin = sc.nextLine();
                            if (manager.updateBook(editId, nextTitle, nextAuthor, nextPub, nextYear, nextOrigin)) {
                                System.out.println("\t✨ Cập nhật thông tin thành công!");
                                manager.saveData();
                            }
                        } else {
                            System.out.println("\t❌ Không tìm thấy sách có ID: " + editId);
                        }
                        break;
                    case 6:
                        manager.sortByName();
                        System.out.println("✨ Đã sắp xếp theo tên sách!");
                        printTableHeader();
                        manager.showAll();
                        break;
                    case 7:
                        manager.sortByAuthor();
                        System.out.println("✨ Đã sắp xếp theo tên tác giả!");
                        printTableHeader();
                        manager.showAll();
                        break;
                    case 8:
                        System.out.print("📁 Nhập tên file (VD: thu-vien.csv): ");
                        String fileName = sc.nextLine();
                        manager.exportToCSV(fileName);
                        break;
                    default:
                        System.out.println("⚠️ Lựa chọn không hợp lệ. Vui lòng chọn từ 0-8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Lỗi: Vui lòng nhập số tương ứng với menu!");
            }
        }
        sc.close();
    }

    private static void printTableHeader() {
        System.out.println("\n" + "=".repeat(100));
        System.out.printf("| %-5s | %-20s | %-15s | %-15s | %-6s | %-10s |\n",
                "ID", "TÊN SÁCH", "TÁC GIẢ", "NXB", "NĂM", "XUẤT XỨ");
        System.out.println("-".repeat(100));
    }
}