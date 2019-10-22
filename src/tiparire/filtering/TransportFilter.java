package tiparire.filtering;

import java.util.ArrayList;
import java.util.List;
import tiparire.enums.EnumTipTransport;
import tiparire.model.Document;

public class TransportFilter {
	public List<Document> getFilteredData(EnumTipTransport tipTransport, List<Document> listDocumente) {
		List<Document> tempList = new ArrayList<>();

		for (Document doc : listDocumente) {

			if (doc.getTipTransport() != null && doc.getTipTransport() == tipTransport) {
				tempList.add(doc);
			}
		}

		return tempList;
	}
}
