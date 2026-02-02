import java.io.Serializable;

public abstract class Document implements Serializable {
    private String id;
    private String title;
    private String publisher;
    private int publishYear;
    private String origin;
    private static final long serialVersionUID = 1L;

    public Document(String id, String title, String publisher, int publishYear, String origin) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.origin = origin;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getPublishYear() { return publishYear; }
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public abstract void displayInfo();
}

class Book extends Document {
    private String author;
    private static final long serialVersionUID = 1L;

    public Book(String id, String title, String author, String publisher, int publishYear, String origin) {
        super(id, title, publisher, publishYear, origin);
        this.author = author;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public void displayInfo() {
        System.out.printf("| %-5s | %-20s | %-15s | %-15s | %-6d | %-10s |\n",
                getId(), getTitle(), author, getPublisher(), getPublishYear(), getOrigin());
    }
}