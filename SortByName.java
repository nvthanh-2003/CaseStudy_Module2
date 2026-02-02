import java.util.Comparator;

public class SortByName implements Comparator<Document> {
    @Override
    public int compare(Document d1, Document d2) {
        return d1.getTitle().compareToIgnoreCase(d2.getTitle());
    }
}