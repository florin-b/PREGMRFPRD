package tiparire.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DocumentChainedComparator<Document> extends Object implements Comparator<Document> {
	private List<Comparator<Document>> listComparators;

	public DocumentChainedComparator(Comparator... comparators) {
		this.listComparators = Arrays.asList(comparators);
	}

	public int compare(Document d1, Document d2) {
		for (Comparator<Document> comparator : this.listComparators) {
			int result = comparator.compare(d1, d2);

			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
