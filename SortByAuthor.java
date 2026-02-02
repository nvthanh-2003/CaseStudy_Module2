import java.util.Comparator;

public class SortByAuthor implements Comparator<Document> {
    @Override
    public int compare(Document d1, Document d2) {
        if (d1 instanceof Book && d2 instanceof Book) {
            Book b1 = (Book) d1;
            Book b2 = (Book) d2;
            return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
        }
        return 0;
    }
}