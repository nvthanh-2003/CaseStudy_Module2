import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class LibraryManager {
    private List<Document> documents = new ArrayList<>();
    private final String FILE_PATH = "library_data.dat";

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public void sortByName() {
        if (documents.isEmpty()) {
            System.out.println("Danh sách trống, không thể sắp xếp.");
            return;
        }

        Collections.sort(documents, new Comparator<Document>() {
            @Override
            public int compare(Document d1, Document d2) {
                return d1.getTitle().compareToIgnoreCase(d2.getTitle());
            }
        });

        System.out.println("Đã sắp xếp danh sách theo tên thành công!");
    }

    public void sortByAuthor() {
        documents.sort((d1, d2) -> {
            if (d1 instanceof Book && d2 instanceof Book) {
                return ((Book) d1).getAuthor().compareToIgnoreCase(((Book) d2).getAuthor());
            }
            return 0;
        });
        System.out.println("Đã sắp xếp theo tên tác giả!");
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("ID,Ten Sach,Tac Gia");
            writer.newLine();

            for (Document doc : documents) {
                if (doc instanceof Book) {
                    Book b = (Book) doc;
                    String line = String.format("%s,%s,%s",
                            b.getId(), b.getTitle(), b.getAuthor());
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Đã xuất file Excel (CSV) thành công tại: " + fileName);
        } catch (IOException e) {
            System.err.println("Lỗi khi xuất file: " + e.getMessage());
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

    public boolean updateBook(String id, String newTitle, String newAuthor) {
        Document doc = findById(id);

        if (doc instanceof Book) {
            Book book = (Book) doc;
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
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