package tiparire.sorting;

import java.util.Comparator;
import tiparire.model.Document;

public class ClientComparator extends Object implements Comparator<Document> {
	public int compare(Document d1, Document d2) {
		return d1.getClient().compareTo(d2.getClient());
	}
}
