package tiparire.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DocumentTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Document> docsList;
	private String[] colNames = { "ID", "Data emiterii", "Client",
			"Departament" };

	public DocumentTableModel() {

	}

	public void setData(List<Document> docsList) {
		this.docsList = docsList;
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return 0;
	}

	public Object getValueAt(int row, int col) {
		Document document = docsList.get(row);

		switch (col) {
		case 0:
			return document.getId();
		case 1:
			return document.getDataEmiterii();
		case 2:
			return document.getClient();
		case 3:
			return document.getDepartament();

		}

		return null;
	}

	public String getColumnName(int column) {
		return colNames[column];
	}

}
