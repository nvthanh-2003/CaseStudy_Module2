import java.io.*;
import java.util.ArrayList;
import java.util.List;

class LibraryManager {
    private List<Document> documents = new ArrayList<>();
    private final String FILE_PATH = "library_data.dat";

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public void sortByName() {
        if (documents.isEmpty()) {
            System.out.println("\t⚠️ Danh sách trống, không thể sắp xếp.");
            return;
        }
        documents.sort(new SortByName());
        System.out.println("\t✨ Đã sắp xếp danh sách theo Tên (A-Z)!");
    }

    public void sortByAuthor() {
        if (documents.isEmpty()) {
            System.out.println("\t⚠️ Danh sách trống, không thể sắp xếp.");
            return;
        }
        documents.sort(new SortByAuthor());
        System.out.println("\t✨ Đã sắp xếp danh sách theo Tác giả (A-Z)!");
    }

    public void showAll() {
        if (documents.isEmpty()) {
            System.out.println("Thư viện hiện chưa có tài liệu nào.");
        } else {
            for (Document d : documents) {
                d.displayInfo();
            }
        }
    }

    public Document findById(String id) {
        return documents.stream()
                .filter(d -> d.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public void exportToCSV(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "UTF-8"))) {

            writer.write('\ufeff');

            writer.write("ID,Tên Sách,Tác Giả,Nhà Xuất Bản,Năm Phát Hành,Xuất Xứ");
            writer.newLine();

            for (Document doc : documents) {
                if (doc instanceof Book) {
                    Book b = (Book) doc;
                    String line = String.format("%s,%s,%s,%s,%d,%s",
                            b.getId(), b.getTitle(), b.getAuthor(),
                            b.getPublisher(), b.getPublishYear(), b.getOrigin());
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("\t✅ Xuất file thành công: " + fileName);
        } catch (IOException e) {
            System.err.println("\t❌ Lỗi khi xuất file: " + e.getMessage());
        }
    }

    public boolean deleteById(String id) {
        boolean isRemoved = documents.removeIf(doc -> doc.getId().equalsIgnoreCase(id));

        if (isRemoved) {
            System.out.println("Đã xóa thành công sách có ID: " + id);
        } else {
            System.out.println("Không tìm thấy sách có ID: " + id + " để xóa.");
        }
        return isRemoved;
    }

    public boolean updateBook(String id, String newTitle, String newAuthor,
                              String newPublisher, int newYear, String newOrigin) {
        Document doc = findById(id);

        if (doc instanceof Book) {
            Book book = (Book) doc;
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPublisher(newPublisher);
            book.setPublishYear(newYear);
            book.setOrigin(newOrigin);
            return true;
        }
        return false;
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(documents);
            System.out.println("Hệ thống: Đã tự động sao lưu dữ liệu.");
        } catch (IOException e) {
            System.err.println("Lỗi lưu file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            documents = (ArrayList<Document>) ois.readObject();
            System.out.println("Hệ thống: Đã khôi phục " + documents.size() + " tài liệu từ bộ nhớ.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
        }
    }
}