import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        manager.loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- HỆ THỐNG QUẢN LÝ THƯ VIỆN ---");
            System.out.println("1. Thêm sách");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Tìm kiếm theo ID");
            System.out.println("4. Xóa sách theo ID");
            System.out.println("5. Sửa sách theo ID");
            System.out.println("6. Sắp xếp danh sách theo tên sách (A-Z)");
            System.out.println("7. Sắp xếp danh sách theo tên tác giả (A-Z)");
            System.out.println("8. Xuất dữ liệu ra file Excel (CSV)");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 0) break;

                switch (choice) {
                    case 1:
                        System.out.print("Nhập ID: ");
                        String id = sc.nextLine();
                        System.out.print("Nhập tên sách: ");
                        String title = sc.nextLine();
                        System.out.print("Nhập tên tác giả: ");
                        String author = sc.nextLine();
                        manager.addDocument(new Book(id, title, author));
                        System.out.println("Thêm sách thành công!");
                        manager.saveData();
                        break;
                    case 2:
                        manager.showAll();
                        break;
                    case 3:
                        System.out.print("Nhập ID cần tìm: ");
                        String searchId = sc.nextLine();
                        Document found = manager.findById(searchId);
                        if (found != null) found.displayInfo();
                        else System.out.println("Không tìm thấy!");
                        break;
                    case 4:
                        System.out.print("Nhập ID sách cần xóa: ");
                        String deleteId = sc.nextLine();
                        manager.deleteById(deleteId);
                        manager.saveData();
                        break;
                    case 5:
                        System.out.print("Nhập ID sách cần sửa: ");
                        String editId = sc.nextLine();
                        Document docToEdit = manager.findById(editId);

                        if (docToEdit != null) {
                            System.out.println("Thông tin hiện tại: ");
                            docToEdit.displayInfo();

                            System.out.print("Nhập tên mới: ");
                            String nextTitle = sc.nextLine();
                            System.out.print("Nhập tác giả mới: ");
                            String nextAuthor = sc.nextLine();

                            if (manager.updateBook(editId, nextTitle, nextAuthor)) {
                                System.out.println("Cập nhật thông tin thành công!");
                            }
                        } else {
                            System.out.println("Không tìm thấy ID này!");
                        }
                        manager.saveData();
                        break;
                    case 6:
                        manager.sortByName();
                        manager.showAll();
                        break;
                    case 7:
                        manager.sortByAuthor();
                        manager.showAll();
                        break;
                    case 8:
                        System.out.print("Nhập tên file (VD: danh_sach.csv): ");
                        String fileName = sc.nextLine();
                        manager.exportToCSV(fileName);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập số!");
            }
        }
        sc.close();
    }
}