import java.io.Serializable;

public abstract class Document implements Serializable {
    private String id;
    private String title;
    private static final long serialVersionUID = 1L;

    public Document(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void displayInfo();
}

class Book extends Document {
    private String author;
    private static final long serialVersionUID = 1L;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public void displayInfo() {
        System.out.printf("[Sách] ID: %-5s | Tên: %-20s | Tác giả: %-15s\n",
                getId(), getTitle(), author);
    }
}
