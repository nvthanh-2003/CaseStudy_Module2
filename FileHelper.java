import java.io.*;
import java.util.List;

class FileHelper {
    private static final String FILE_PATH = "library.txt";

    public static void saveToFile(List<Document> docs) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Document d : docs) {
                writer.println(d.getId() + "," + d.getTitle());
            }
        } catch (IOException e) {
            System.err.println("Lỗi lưu file: " + e.getMessage());
        }
    }
}