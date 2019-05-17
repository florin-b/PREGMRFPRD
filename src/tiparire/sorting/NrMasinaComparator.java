package tiparire.sorting;

import java.util.Comparator;
import tiparire.model.Document;

public class NrMasinaComparator extends Object implements Comparator<Document> {
	public int compare(Document d1, Document d2) {
		return d1.getNrMasina().compareTo(d2.getNrMasina());
	}
}
